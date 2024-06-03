package com.computer.tripsuitcase.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.computer.tripsuitcase.Dao.Trip;
import com.computer.tripsuitcase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;

public class TripNotificationService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference tripsRef = db.collection("Trips");
        tripsRef.whereEqualTo("userId", FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            for (QueryDocumentSnapshot document : querySnapshot) {
                                Trip trip = document.toObject(Trip.class);
                                String date = trip.getDate();
                                Date currentDate = new Date();
                                Date tripDate = new Date(date);
                                long diff = tripDate.getTime() - currentDate.getTime();
                                if (diff < 24 * 60 * 60 * 1000) { // 1 day

                                    sendNotification("Your trip is approaching!", "Don't forget your trip on " + date);
                                }
                            }
                        }
                    }
                });
    }

    public void sendNotification(String title, String message) {

        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("TripNotification", "Trip Notifications", NotificationManager.IMPORTANCE_HIGH);
        }
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "TripNotification");
        notificationBuilder.setSmallIcon(R.drawable.app_icon_two);
        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon_two));
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(message);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);

        notificationManager.notify(12345, notificationBuilder.build());
    }
    
}

package com.computer.tripsuitcase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class TripPlanActivity extends AppCompatActivity {

    CalendarView calendarView;
    Button btnTrip;
    TextView textTrip;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_plan);

        calendarView=findViewById(R.id.calendarView);
        btnTrip = findViewById(R.id.btnTripPlan);
        textTrip = findViewById(R.id.textTrip);
        db = FirebaseFirestore.getInstance();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                btnTrip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        String date = dayOfMonth + "/" + (month+1) +"/" + year;
                        textTrip.setText("Bir sonraki seyahatiniz : "+date);

                        Map<String, Object> trip = new HashMap<>();
                        trip.put("date", date);
                        trip.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());

                        db.collection("Trips").add(trip).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TripPlanActivity", "Trip date saved to Firestore");
                            }
                        });
                    }
                });
            }
        });
    }

}
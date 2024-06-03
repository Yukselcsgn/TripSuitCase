package com.computer.tripsuitcase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.computer.tripsuitcase.Dao.Trip;
import com.computer.tripsuitcase.Service.TripNotificationService;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Collections;
import java.util.Iterator;

public class TripNotificationServiceTest {

    @Mock
    private TripNotificationService tripNotificationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendNotificationWhenTripIsApproaching() {
        Trip mockTrip = new Trip("2024-06-04", "someUserId");

        FirebaseFirestore mockDb = mock(FirebaseFirestore.class);
        CollectionReference mockTripsRef = mock(CollectionReference.class);
        when(mockDb.collection("Trips")).thenReturn(mockTripsRef);

        QueryDocumentSnapshot mockDocumentSnapshot = mock(QueryDocumentSnapshot.class);
        when(mockDocumentSnapshot.toObject(Trip.class)).thenReturn(mockTrip);

        Iterator<QueryDocumentSnapshot> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true).thenReturn(false);
        when(iterator.next()).thenReturn(mockDocumentSnapshot);

        when(mockDocumentSnapshot.getData()).thenReturn(Collections.singletonMap("1",mockDocumentSnapshot));

        tripNotificationService.onMessageReceived(mock(RemoteMessage.class));

        verify(tripNotificationService).sendNotification(eq("Your trip is approaching!"), anyString());
    }
}

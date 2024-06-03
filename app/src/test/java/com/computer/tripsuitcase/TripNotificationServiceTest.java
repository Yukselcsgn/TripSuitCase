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
        // Create a mock Trip object with a date that is less than 24 hours from the current date
        Trip mockTrip = new Trip("2024-06-04", "someUserId");
        // Set a date that is within 24 hours

        // Mock the FirebaseFirestore and CollectionReference
        FirebaseFirestore mockDb = mock(FirebaseFirestore.class);
        CollectionReference mockTripsRef = mock(CollectionReference.class);
        when(mockDb.collection("Trips")).thenReturn(mockTripsRef);

        // Mock the query snapshot
        QueryDocumentSnapshot mockDocumentSnapshot = mock(QueryDocumentSnapshot.class);
        when(mockDocumentSnapshot.toObject(Trip.class)).thenReturn(mockTrip);

        Iterator<QueryDocumentSnapshot> iterator = mock(Iterator.class);
        when(iterator.hasNext()).thenReturn(true).thenReturn(false);
        when(iterator.next()).thenReturn(mockDocumentSnapshot);

        when(mockDocumentSnapshot.getData()).thenReturn(Collections.singletonMap("1",mockDocumentSnapshot));

        // Call the onMessageReceived method
        tripNotificationService.onMessageReceived(mock(RemoteMessage.class));

        // Verify that the sendNotification method was called with the correct parameters
        verify(tripNotificationService).sendNotification(eq("Your trip is approaching!"), anyString());
    }
}

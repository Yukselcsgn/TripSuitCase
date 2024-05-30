package com.computer.tripsuitcase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class TripPlanActivity extends AppCompatActivity {

    CalendarView calendarView;
    Button btnTrip;
    TextView textTrip;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_plan);

        calendarView=findViewById(R.id.calendarView);
        btnTrip = findViewById(R.id.btnTripPlan);
        textTrip = findViewById(R.id.textTrip);

        btnTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.getDateTextAppearance();

            }
        });


    }
}
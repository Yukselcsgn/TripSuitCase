package com.computer.tripsuitcase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.computer.tripsuitcase.Adapter.Adapter;
import com.computer.tripsuitcase.Constants.MyConstants;
import com.computer.tripsuitcase.Database.RoomDB;
import com.computer.tripsuitcase.data.AppData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> titles;
    List<Integer> images;
    Adapter adapter;
    Context context = MainActivity.this;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        if (recyclerView == null) {
            throw new NullPointerException("recyclerView is null");
        }

        database =  RoomDB.getInstance(context);

        addAddTitles();
        addAllImages();
        persistAppData();
        database = RoomDB.getInstance(context);
        System.out.println("---------------->"+ database.mainDao().getAllSelected(false).get(0).getItemname() );
        if (titles.isEmpty() || images.isEmpty()) {
            throw new IllegalStateException("titles or images list is empty");
        }


        adapter = new Adapter(context,titles,images,MainActivity.this);
        //altta context yerine this vardi
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private static final int TIME_INTERVAL =200;

    private long mBackPressed = 0;

    /** @noinspection deprecation*/
    @Override
    public void onBackPressed() {
        if (mBackPressed+TIME_INTERVAL>System.currentTimeMillis()){
            super.onBackPressed( );
            return;
        }else {
            Toast.makeText(this, "Tap back button in order to exit.", Toast.LENGTH_SHORT).show( );
        }
        mBackPressed = System.currentTimeMillis();    }
    /** @noinspection deprecation*/
    private void persistAppData(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        database = RoomDB.getInstance(context);
        AppData appData = new AppData(database);
        int last = prefs.getInt(AppData.LAST_VERSION,0);

        if (!prefs.getBoolean(MyConstants.FIRST_TIME_CAMEL_CASE,false)){
            appData.persistAllData();
            editor.putBoolean(MyConstants.FIRST_TIME_CAMEL_CASE,true);
            editor.apply();
        }else if (last<AppData.NEW_VERSION){
            database.mainDao().deleteAllSystemItems(MyConstants.SYSTEM_SMALL);
            appData.persistAllData();
            editor.putInt(AppData.LAST_VERSION,AppData.NEW_VERSION);
            editor.apply();
        }
    }
    private void addAddTitles(){
        titles = new ArrayList<>(  );
        titles.add(MyConstants.BABY_NEEDS_CAMEL_CASE);
        titles.add(MyConstants.CLOTHING_CAMEL_CASE);
        titles.add(MyConstants.PERSONAL_CARE_CAMEL_CASE);
        titles.add(MyConstants.BABY_NEEDS_CAMEL_CASE);
        titles.add(MyConstants.HEALTH_CAMEL_CASE);
        titles.add(MyConstants.TECHNOLOGY_CAMEL_CASE);
        titles.add(MyConstants.FOOD_CAMEL_CASE);
        titles.add(MyConstants.BEACH_SUPPLIES_CAMEL_CASE);
        titles.add(MyConstants.CAR_SUPPLIES_CAMEL_CASE);
        titles.add(MyConstants.NEEDS_CAMEL_CASE);
        titles.add(MyConstants.MY_LIST_CAMEL_CASE);
        titles.add(MyConstants.MY_SELECTIONS_CAMEL_CASE);
    }
    private void addAllImages(){
        images = new ArrayList<>(  );
        images.add(R.drawable.p1);
        images.add(R.drawable.p2);
        images.add(R.drawable.p3);
        images.add(R.drawable.p4);
        images.add(R.drawable.p5);
        images.add(R.drawable.p6);
        images.add(R.drawable.p7);
        images.add(R.drawable.p8);
        images.add(R.drawable.p9);
        images.add(R.drawable.p10);
        images.add(R.drawable.p11);
        images.add(R.drawable.p12);
    }
}
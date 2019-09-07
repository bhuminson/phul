package com.bhumin.android.phul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class RoutineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
    }

    public void launchDayActivity(View view) {
        int day_index  = ((ViewGroup)findViewById(R.id.days_layout)).indexOfChild(view);
        Intent intent = new Intent(this, DayActivity.class);
        intent.putExtra("DAY_INDEX", day_index);
        startActivity(intent);
     }
}

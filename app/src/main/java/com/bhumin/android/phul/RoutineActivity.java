package com.bhumin.android.phul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class RoutineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
    }

    public void launchDayActivity(View view) {
        String day_name =  (String)((TextView)view).getText();
        switch(view.getId()) {
            case R.id.upper_power_text:
            case R.id.lower_power_text:
            case R.id.upper_hypertrophy_text:
            case R.id.lower_hypertrophy_text: {
                Intent intent = new Intent(this, DayActivity.class);
                intent.putExtra(getString(R.string.day_name), day_name);
                startActivity(intent);
                break;
            }
        }
    }
}

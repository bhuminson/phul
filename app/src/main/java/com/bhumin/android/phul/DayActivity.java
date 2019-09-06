package com.bhumin.android.phul;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Day Name");
        setTitle(name);
        ViewStub stub = findViewById(R.id.layout_stub);
        assert name != null;
        switch (name) {
            case ("Upper Power"): {
                stub.setLayoutResource(R.layout.upper_power_exercises);
                stub.inflate();
                break;
            }
            case ("Lower Power"): {
                stub.setLayoutResource(R.layout.lower_power_exercises);
                stub.inflate();
                break;
            }
            case ("Upper Hypertrophy"): {
                stub.setLayoutResource(R.layout.upper_hypertrophy_exercises);
                stub.inflate();
                break;
            }
            case ("Lower Hypertrophy"): {
                stub.setLayoutResource(R.layout.lower_hypertrophy_exercises);
                stub.inflate();
                break;
            }
        }
        //todo add sets and reps info, and number each exercise
    }

    public void launchExerciseActivity(View view) {
        String exercise_name = (String)(((TextView)view).getText());
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("NAME", exercise_name);
        startActivity(intent);
    }
}

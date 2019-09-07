package com.bhumin.android.phul;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    private Day mDay;
    private int day_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        day_index = intent.getIntExtra("DAY_INDEX", 0);

        mDay = Routine.days.get(day_index);
        ViewStub stub = findViewById(R.id.layout_stub);
        setTitle(mDay.name);
        stub.setLayoutResource(mDay.layout_resource);
        stub.inflate();
    }

    public void launchExerciseActivity(View view) {
        ViewGroup layout = (ViewGroup)((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
        ViewGroup viewStub = (ViewGroup)layout.getChildAt(0);
        int view_index  = viewStub.indexOfChild(view);
        int exercise_index = view_index / 2;
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("DAY_INDEX", day_index);
        intent.putExtra("EXERCISE_INDEX", exercise_index);
        startActivity(intent);
    }
}

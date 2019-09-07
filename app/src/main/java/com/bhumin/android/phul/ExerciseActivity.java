package com.bhumin.android.phul;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class ExerciseActivity extends AppCompatActivity {

    private Day mDay;
    private Exercise mExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
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

        Intent intent = getIntent();
        int day_index = intent.getIntExtra("DAY_INDEX", 0);
        int exercise_index = intent.getIntExtra("EXERCISE_INDEX", 0);
        mDay = Routine.days.get(day_index);
        mExercise = mDay.exercises.get(exercise_index);
        setTitle(mExercise.name);

        TextView subtitle = findViewById(R.id.subtitle);
        subtitle.setText(mExercise.getDescription());
    }
}

package com.bhumin.android.phul;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Chronometer rest_timer = findViewById(R.id.rest_timer);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rest_timer.setBase(SystemClock.elapsedRealtime() - 500);
                rest_timer.start();
                Toast.makeText(getBaseContext(), "Rest Timer Started", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        int day_index = intent.getIntExtra("DAY_INDEX", 0);
        int exercise_index = intent.getIntExtra("EXERCISE_INDEX", 0);
        Day mDay = Routine.days.get(day_index);
        Exercise mExercise = mDay.exercises.get(exercise_index);
        setTitle(mExercise.name);

        TextView subtitle = findViewById(R.id.subtitle);
        subtitle.setText(mExercise.getDescription());
        rest_timer.setTextColor(subtitle.getCurrentTextColor());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.new_from_left, R.anim.old_to_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        finish();
        overridePendingTransition(R.anim.new_from_left, R.anim.old_to_right);
        return super.onOptionsItemSelected(item);
    }
}

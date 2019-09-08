package com.bhumin.android.phul;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Toast;

public class DayActivity extends AppCompatActivity {

    private int day_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//todo make textviews generate with adapter
        //todo implement timer
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        day_index = intent.getIntExtra("DAY_INDEX", 0);

        Day mDay = Routine.days.get(day_index);
        ViewStub stub = findViewById(R.id.layout_stub);
        setTitle(mDay.name);
        stub.setLayoutResource(mDay.layout_resource);
        stub.inflate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.new_from_left, R.anim.old_to_right);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        overridePendingTransition(R.anim.new_from_left, R.anim.old_to_right);
        return super.onOptionsItemSelected(item);
    }

    public void launchExerciseActivity(View view) {
        ViewGroup layout = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        ViewGroup viewStub = (ViewGroup) layout.getChildAt(0);
        int view_index = viewStub.indexOfChild(view);
        int exercise_index = view_index / 2;
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("DAY_INDEX", day_index);
        intent.putExtra("EXERCISE_INDEX", exercise_index);
        startActivity(intent);
        overridePendingTransition(R.anim.new_from_right, R.anim.old_to_left);
    }
}

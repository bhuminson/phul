package com.bhumin.android.phul;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.SystemClock;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {
    public LinearLayout current_session;
    public Exercise mExercise;
    public int sets = 0;
    public Button add_set_button;
    public Button delete_set_button;
    public ArrayList<EditText> weightInputs;
    public ArrayList<EditText> repsInputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView subtitle = findViewById(R.id.subtitle);
        current_session = findViewById(R.id.current_session);
        add_set_button = findViewById(R.id.add_set);
        delete_set_button = findViewById(R.id.delete_set);
        delete_set_button.setVisibility(View.GONE);

        final Chronometer rest_timer = findViewById(R.id.rest_timer);
        rest_timer.setTextColor(subtitle.getCurrentTextColor());
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
        mExercise = mDay.exercises.get(exercise_index);
        setTitle(mExercise.name);
        subtitle.setText(mExercise.getDescription());

        initCurrentSession();
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
    //todo implement shared preferences for session storage

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.save_button) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    public void initCurrentSession() {
        for(int i = 0; i< mExercise.minSets; i++) {
            addSetToSession(i);
        }
    }

    public void addSetToSession(View view) {
        addSetToSession(sets);
        if (sets == mExercise.maxSets) {
            add_set_button.setVisibility(View.GONE);
            delete_set_button.setVisibility(View.VISIBLE);
        }

    }

    public void addSetToSession(int i) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        TextView set_label = new TextView(this);
        sets++;
        set_label.setText(getString(R.string.dynamic_string_label, sets));
        params.setMargins(20,0,0,0);
        set_label.setLayoutParams(params);

        EditText weight_input = new EditText(this);
        weight_input.setInputType(InputType.TYPE_CLASS_NUMBER);
        weight_input.setHint(R.string.weight);
        weight_input.setGravity(Gravity.END);
        weight_input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
        weight_input.setImeOptions(EditorInfo.IME_ACTION_DONE);
        params.setMargins(50,0,0,0);
        weight_input.setLayoutParams(params);
        weightInputs.add(weight_input);

        TextView x = new TextView(this);
        x.setText("x");
        x.setLayoutParams(params);

        EditText reps_input = new EditText(this);
        reps_input.setInputType(InputType.TYPE_CLASS_NUMBER);
        reps_input.setHint(R.string.reps);
        reps_input.setGravity(Gravity.END);
        reps_input.setImeOptions(EditorInfo.IME_ACTION_DONE);
        reps_input.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
        reps_input.setLayoutParams(params);
        repsInputs.add(reps_input);

        row.addView(set_label);
        row.addView(weight_input);
        row.addView(x);
        row.addView(reps_input);
        current_session.addView(row, i + 1);
    }

    public void deleteSetInSession(View view) {
        current_session.removeViewAt(sets);
        sets--;
        if (sets == mExercise.minSets) {
            delete_set_button.setVisibility(View.GONE);
            add_set_button.setVisibility(View.VISIBLE);
        }
    }
}

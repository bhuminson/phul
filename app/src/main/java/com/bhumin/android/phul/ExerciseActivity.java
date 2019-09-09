package com.bhumin.android.phul;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public int sets;
    public Button add_set_button;
    public Button delete_set_button;
    public TextView prev_session_label;
    public TextView subtitle;

    public SharedPreferences inputs;
    public SharedPreferences.Editor editor;
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

        declareViews();
        initChronometer();
        initExercise();
        initSharedPrefs();

        if (inputs.getBoolean("show previous session", false)) {
            prev_session_label.setVisibility(View.VISIBLE); //todo make this true after first session storage
        }
        sets = 0;
        initCurrentSession();

//        if (inputs.getBoolean("saved", false)) {
//            restoreInputs();
//        }

        checkButtons();
    }

    @Override
    public void onBackPressed() {
        saveInputs();
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
        switch (item.getItemId()) {
            case (R.id.save_button):
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
//                saveSession();
                break;
            case (android.R.id.home):
                saveInputs();
                finish();
                overridePendingTransition(R.anim.new_from_left, R.anim.old_to_right);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void declareViews() {
        subtitle = findViewById(R.id.subtitle);
        current_session = findViewById(R.id.current_session);
        add_set_button = findViewById(R.id.add_set);
        delete_set_button = findViewById(R.id.delete_set);
        prev_session_label = findViewById(R.id.previous_session_label);
        prev_session_label.setVisibility(View.GONE);
    }

    private void initSharedPrefs() {
        weightInputs = new ArrayList<>();
        repsInputs = new ArrayList<>();
        inputs = getSharedPreferences(mExercise.name, Context.MODE_PRIVATE);
    }

    private void initExercise() {
        Intent intent = getIntent();
        int day_index = intent.getIntExtra("DAY_INDEX", 0);
        int exercise_index = intent.getIntExtra("EXERCISE_INDEX", 0);
        Day mDay = Routine.days.get(day_index);
        mExercise = mDay.exercises.get(exercise_index);
        setTitle(mExercise.name);
        subtitle.setText(mExercise.getDescription());
    }

    private void initChronometer() {
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
    }

    public void initCurrentSession() {
        while(sets < mExercise.minSets) {
            addSetToSession(current_session);
        }
    }

    private void showAddSetButton() {
        add_set_button.setVisibility(View.VISIBLE);
    }

    public void hideAddSetButton() {
        add_set_button.setVisibility(View.INVISIBLE);
    }

    public void showDeleteSetButton() {
        delete_set_button.setVisibility(View.VISIBLE);
    }

    private void hideDeleteSetButton() {
        delete_set_button.setVisibility(View.INVISIBLE);
    }

    public void checkButtons() {
        showDeleteSetButton();
        showAddSetButton();
        if (sets <= mExercise.minSets) {
            hideDeleteSetButton();
        }
        if (sets >= mExercise.maxSets) {
            hideAddSetButton();
        }
    }

    public void addSetToCurrentSession(View view) {
        addSetToSession(current_session);
    }

    public void addSetToSession(LinearLayout session) {
        sets++;
        generateSetViews(session);
        checkButtons();
    }

    private void generateSetViews(LinearLayout session) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

        TextView set_label = new TextView(this);
        set_label.setText(getString(R.string.dynamic_string_label, sets));
        params.setMargins(20, 0, 0, 0);
        set_label.setLayoutParams(params);

        EditText weight_input = new EditText(this);
        weight_input.setInputType(InputType.TYPE_CLASS_NUMBER);
        weight_input.setHint(R.string.weight);
        weight_input.setGravity(Gravity.END);
        weight_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        weight_input.setImeOptions(EditorInfo.IME_ACTION_DONE);
        params.setMargins(50, 0, 0, 0);
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
        reps_input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        reps_input.setLayoutParams(params);
        repsInputs.add(reps_input);

        row.addView(set_label);
        row.addView(weight_input);
        row.addView(x);
        row.addView(reps_input);
        session.addView(row, sets);
    }

    public void deleteSetInCurrentSession(View view) {
        current_session.removeViewAt(sets);
        sets--;
//        weightInputs.remove(sets);
        checkButtons();
    }

    public void saveInputs() {
        editor = inputs.edit();
        int i = 0;
        for (EditText weightInput : weightInputs) {
            editor.putString("weight" + i, weightInput.getText().toString());
            i++;
        }
        int j = 0;
        for (EditText repsInput : repsInputs) {
            editor.putString("reps" + j, repsInput.getText().toString());
            j++;
        }
        editor.putBoolean("saved", true);
        editor.putInt("total weight inputs", i);
        editor.putInt("total reps inputs", j);
        editor.apply();
    }

    public void restoreInputs() {
        int weightEntries = inputs.getInt("total weight inputs", 0);
        int repsEntries = inputs.getInt("total reps inputs", 0);
        while (sets - weightEntries != 0) {
            addSetToSession(current_session);
        }
        while (sets - repsEntries != 0) {
            addSetToSession(current_session);
        }
        int i = 0;
        for (EditText weightInput : weightInputs) {
            weightInput.setText(inputs.getString("weight" + i, ""));
            i++;
        }
        int j = 0;
        for (EditText repsInput : repsInputs) {
            repsInput.setText(inputs.getString("reps" + j, ""));
            j++;
        }
    }

//    private void saveSession() {
//        if(prev_session_label.getVisibility() == View.GONE) {
//            prev_session_label.setVisibility(View.VISIBLE);
//        }
//
//    }
}

package com.bhumin.android.phul;

import java.util.ArrayList;

public class Routine {

    public static final Day UpperPower;
    public static final Day LowerPower;
    public static final Day UpperHypertrophy;
    public static final Day LowerHypertrophy;
    public static final ArrayList<Day> days;

    static {
        UpperPower = new Day("Upper Power", R.layout.upper_power_exercises);
        Exercise bb_bench = new Exercise("Barbell Bench Press",3,4,3,5, 180);
        Exercise inc_db_bench = new Exercise("Dumbbell Incline Bench Press",3,4,6,10,90);
        Exercise bb_row = new Exercise("Barbell Row",3,4,3,5,180);
        Exercise lat_pulldown = new Exercise("Lat Pulldown",3,4,6,10,90);
        Exercise ohp = new Exercise("Overhead Press",2,3,5,8,90);
        Exercise bb_curl = new Exercise("Barbell Curl",2,3,6,10,90);
        Exercise skullcrusher = new Exercise("Skullcrushers",2,3,6,10,90);
        UpperPower.exercises.add(bb_bench);
        UpperPower.exercises.add(inc_db_bench);
        UpperPower.exercises.add(bb_row);
        UpperPower.exercises.add(lat_pulldown);
        UpperPower.exercises.add(ohp);
        UpperPower.exercises.add(bb_curl);
        UpperPower.exercises.add(skullcrusher);

        LowerPower = new Day("Lower Power", R.layout.lower_power_exercises);
        Exercise squat_pwr = new Exercise("Squat",3,4,3,5,180);
        Exercise deadlift = new Exercise("Deadlift",3,4,3,5,180);
        Exercise leg_press = new Exercise("Leg Press", 3,5,10,15,90);
        Exercise leg_curl_pwr = new Exercise("Leg Curl", 3,4,6,10,90);
        Exercise calf_exercise = new Exercise("Calf Exercise", 4,4,6,10,90);
        LowerPower.exercises.add(squat_pwr);
        LowerPower.exercises.add(deadlift);
        LowerPower.exercises.add(leg_press);
        LowerPower.exercises.add(leg_curl_pwr);
        LowerPower.exercises.add(calf_exercise);

        UpperHypertrophy = new Day("Upper Hypertrophy", R.layout.upper_hypertrophy_exercises);
        Exercise inc_bb_bench_press = new Exercise("Incline Barbell Bench Press", 3,4,8,12,90);
        Exercise chest_fly = new Exercise("Chest Fly", 3,4,8,12,90);
        Exercise cable_row = new Exercise("Seated Cable Row", 3,4,8,12,90);
        Exercise db_row = new Exercise("One Arm Dumbbell Row", 3,4,8,12,90);
        Exercise db_side_raise = new Exercise("Dumbbell Lateral Raise", 3,4,8,12,90);
        Exercise incline_db_curl = new Exercise("Seated Incline Dumbbell Curl", 3,4,8,12,90);
        Exercise tri_ext = new Exercise("Cable Triceps Extension", 3,4,8,12,90);
        UpperHypertrophy.exercises.add(inc_bb_bench_press);
        UpperHypertrophy.exercises.add(chest_fly);
        UpperHypertrophy.exercises.add(cable_row);
        UpperHypertrophy.exercises.add(db_row);
        UpperHypertrophy.exercises.add(db_side_raise);
        UpperHypertrophy.exercises.add(incline_db_curl);
        UpperHypertrophy.exercises.add(tri_ext);

        LowerHypertrophy = new Day("Lower Hypertrophy", R.layout.lower_hypertrophy_exercises);
        Exercise squat = new Exercise("Squat", 3,4,8,12,90);
        Exercise lunge = new Exercise("Lunge", 3,4,8,12,90);
        Exercise leg_ext = new Exercise("Leg Extension", 3,4,10,15,90);
        Exercise leg_curl = new Exercise("Leg Curl", 3,4,10,15,90);
        Exercise calf_raise = new Exercise("Seated Calf Raise", 3,4,8,12,90);
        Exercise calf_press = new Exercise("Calf Press", 3,4,8,12,90);
        LowerHypertrophy.exercises.add(squat);
        LowerHypertrophy.exercises.add(lunge);
        LowerHypertrophy.exercises.add(leg_ext);
        LowerHypertrophy.exercises.add(leg_curl);
        LowerHypertrophy.exercises.add(calf_raise);
        LowerHypertrophy.exercises.add(calf_press);

        days = new ArrayList<>();
        days.add(UpperPower);
        days.add(LowerPower);
        days.add(UpperHypertrophy);
        days.add(LowerHypertrophy);
    }
}

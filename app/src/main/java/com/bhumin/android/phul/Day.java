package com.bhumin.android.phul;

import java.util.ArrayList;

class Day {
    public String name;
    ArrayList<Exercise> exercises;
    int layout_resource;

    Day(String name, int layout_resource) {
        this.name = name;
        this.layout_resource = layout_resource;
        exercises = new ArrayList<>();
    }
}

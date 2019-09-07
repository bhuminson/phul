package com.bhumin.android.phul;

import java.util.ArrayList;

class Day {
    public String name;
    public ArrayList<Exercise> exercises;
    public int layout_resource;
    public int viewStub_id;

    public Day(String name, int layout_resource) {
        this.name = name;
        this.layout_resource = layout_resource;
        exercises = new ArrayList<>();
    }
}

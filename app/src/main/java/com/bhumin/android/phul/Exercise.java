package com.bhumin.android.phul;

class Exercise {
    public String name;
    private int minSets;
    private int maxSets;
    private int minReps;
    private int maxReps;
    private int rest;

    Exercise(String name, int minSets, int maxSets, int minReps, int maxReps, int rest) {
        this.name = name;
        this.minSets = minSets;
        this.maxSets = maxSets;
        this.minReps = minReps;
        this.maxReps = maxReps;
        this.rest = rest;
    }

    String getDescription() {
        String sets;
        if(minSets == maxSets) {
            sets = "" + minSets;
        } else {
            sets = "" + minSets + "-" + maxSets;
        }

        String reps;
        if(minReps == maxReps) {
            reps = "" + minReps;
        } else {
            reps = "" + minReps + "-" + maxReps;
        }

        String restString;
        int minutes = rest / 60;
        int seconds = rest % 60;
        if(minutes > 0) {
            restString = "" + minutes + " min ";
            if(seconds != 0) {
                restString += seconds + " sec ";
            }
            restString += "rest";
        } else {
            restString = "" + seconds + " sec rest";
        }
        return sets + " x " + reps + ", " + restString;
    }
}

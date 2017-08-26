package com.example.sameershekhar.hebecollins.activities.models;

import java.io.Serializable;

/**
 * Created by sameershekhar on 19-Aug-17.
 */

public class UserSingleWorkout implements Serializable {
    String exerciseName;
    String exerciseReps;
    String exreciseSets;
    String exerciseRest;

    public UserSingleWorkout(String exerciseName, String exerciseReps, String exreciseSets, String exerciseRest) {
        this.exerciseName = exerciseName;
        this.exerciseReps = exerciseReps;
        this.exreciseSets = exreciseSets;
        this.exerciseRest = exerciseRest;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseReps() {
        return exerciseReps;
    }

    public void setExerciseReps(String exerciseReps) {
        this.exerciseReps = exerciseReps;
    }

    public String getExreciseSets() {
        return exreciseSets;
    }

    public void setExreciseSets(String exreciseSets) {
        this.exreciseSets = exreciseSets;
    }

    public String getExerciseRest() {
        return exerciseRest;
    }

    public void setExerciseRest(String exerciseRest) {
        this.exerciseRest = exerciseRest;
    }
}

package com.fitapp.fitapp;

import java.util.Date;

/**
 * Created by Maxwhale on 7/29/16.
 */
public class Workout {
    public double distance;
    public boolean success;
    public String created;

    public Workout(double distance) {
        this.distance = distance;
        this.success = false;
        this.created = (new Date()).toString();

    }


}

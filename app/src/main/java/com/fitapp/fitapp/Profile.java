package com.fitapp.fitapp;

import java.io.Serializable;

/**
 * Created by William Zulueta on 7/29/16.
 */
public class Profile implements Serializable
{
    private String name;
    private Workout[] workouts;
    private double lastRunDistance;
    private boolean status;
    private String timeForRun;
    private int index;

    public Profile(String n, double lRD, boolean status)
    {
        name = n;
        lastRunDistance = lRD;
        this.status = status;
        workouts = new Workout[10];
    }

    public Profile(int size)
    {
        index = 0;
        workouts = new Workout[size];
    }

    public void addWorkout(Workout workout)
    {
        if (workouts != null)
            workouts[index++] = workout;
    }

    public double getBestDistance()
    {
        if (workouts == null)
            return -1;
        Workout workout = workouts[0];
        for (Workout w : workouts)
        {
            if (w == null)
                continue;
            if (w.getDistance() > workout.getDistance())
            {
                workout = w;
            }
        }
        return workout.getDistance();
    }

    public String getBestTime()
    {
        if (workouts == null)
            return "-1";
        Workout workout = workouts[0];
        for (Workout w : workouts)
        {
            if (w == null)
                continue;
            if (w.getTimeDouble() < workout.getTimeDouble())
            {
                workout = w;
            }
        }
        return workout.getTime();
    }

    public String getBestRate()
    {
        if (workouts == null)
            return "-1";
        Workout workout = workouts[0];
        for (Workout w : workouts)
        {
            if (w == null)
                continue;
            double rate = workout.getDistance() / workout.getTimeDouble() * 60;
            double rateW = w.getDistance() / w.getTimeDouble() * 60;
            if (rateW > rate)
            {
                workout = w;
            }
        }
        return workout.getDistance() / workout.getTimeDouble() * 60 + "";
    }

    public String getAverageDistance()
    {
        if (workouts == null)
            return "-1";
        double distance = 0.0;
        for (Workout w : workouts)
        {
            if (w == null)
                continue;
            distance += w.getDistance();
        }
        return distance / workouts.length + "";
    }

    public String getAverageTime()
    {
        if (workouts == null)
            return "-1";
        double time = 0.0;
        for (Workout w : workouts)
        {
            if (w == null)
                continue;
            time += w.getDistance();
        }
        return time / workouts.length + "";
    }

    public String getAverageRate()
    {
        if (workouts == null)
            return "-1";
        double rate = 0.0;
        for (Workout w : workouts)
        {
            if (w == null)
                continue;
            rate += w.getDistance() / w.getTimeDouble() * 60;
        }
        return (rate / workouts.length) + "";
    }

    public Workout[] getWorkouts()
    {
        return workouts;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getLastRunDistance()
    {
        return lastRunDistance;
    }

    public void setLastRunDistance(double lastRunDistance)
    {
        this.lastRunDistance = lastRunDistance;
    }

    public boolean getStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getTimeForRun()
    {
        return timeForRun;
    }

    public void setTimeForRun(String timeForRun)
    {
        this.timeForRun = timeForRun;
    }
}

package com.fitapp.fitapp;

/**
 * Created by William Zulueta on 7/29/16.
 */
public class Profile
{
    private String name;
    private double lastRunDistance;
    private boolean status;

    public Profile(String n, double lRD, boolean status)
    {
        name = n;
        lastRunDistance = lRD;
        this.status = status;
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
}

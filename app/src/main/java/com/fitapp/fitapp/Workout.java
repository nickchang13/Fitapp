package com.fitapp.fitapp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Maxwhale on 7/29/16.
 */
public class Workout implements Serializable
{
    private double distance;
    private boolean success;
    private String created;
    private String time;

    public Workout()
    {

    }

    public Workout(double distance)
    {
        this.distance = distance;
        this.success = false;
        this.created = (new Date()).toString();

    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public String getTime()
    {
        return time;
    }

    public Double getTimeDouble()
    {
        return Double.parseDouble(time.substring(3, 5));
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getCreated()
    {
        return created;
    }

    public void setCreated(String created)
    {
        this.created = created;
    }

    public String getFormattedDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(created.substring(0, created.indexOf('T')));
        return sdf.toLocalizedPattern();
    }
}

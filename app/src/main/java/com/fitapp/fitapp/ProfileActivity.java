package com.fitapp.fitapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by William Zulueta on 7/30/16.
 */
public class ProfileActivity extends Activity
{
    private TextView username;
    private Profile userProfile;
    private TextView averageDistance;
    private TextView averageTime;
    private TextView averageRate;
    private TextView bestDistance;
    private TextView bestTime;
    private TextView bestRate;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_redone);
        if (getActionBar() != null)
        {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        userProfile = FriendsActivity.getUserProfile();
        username = (TextView) findViewById(R.id.nameTV);
        username.setText("Michael");

        averageDistance = (TextView) findViewById(R.id.avg_distance);
        averageTime = (TextView) findViewById(R.id.avg_time);
        averageRate = (TextView) findViewById(R.id.avg_rate);
        bestDistance = (TextView) findViewById(R.id.best_distance);
        bestTime = (TextView) findViewById(R.id.best_time);
        bestRate = (TextView) findViewById(R.id.best_rate);

        bestDistance.setText("Distance : " + userProfile.getBestDistance() + " miles");
        bestTime.setText("Time : " + userProfile.getBestTime());
        bestRate.setText("Rate : " + userProfile.getBestRate() + " mph");
        averageDistance.setText("Distance : " + userProfile.getAverageDistance() + " miles");
        averageTime.setText("Time : " + userProfile.getAverageTime());
        averageRate.setText("Rate : " + userProfile.getAverageRate() + " mph");

        listView = (ListView) findViewById(R.id.profileListView);
        listView.setAdapter(new ProfileArrayAdapter(getApplicationContext(), userProfile.getWorkouts()));
    }
}

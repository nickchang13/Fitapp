package com.fitapp.fitapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by William Zulueta on 7/30/16.
 */
public class ProfileActivity extends Activity
{
    private TextView username;
    private Profile userProfile;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (getActionBar() != null)
        {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        userProfile = FriendsActivity.getUserProfile();
        username = (TextView) findViewById(R.id.nameTV);
        username.setText(userProfile.getName());
    }
}

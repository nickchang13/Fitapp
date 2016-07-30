package com.fitapp.fitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by William Zulueta on 7/29/16.
 */
public class FriendsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView mFriendsList;
    private Profile profiles[] = new Profile[2];
    private FloatingActionButton fabButton;
    private static Profile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        if (getActionBar() != null)
        {
            getActionBar().setTitle("");
        }

        profiles[0] = new Profile("Bob", 2.5, false);
        profiles[1] = new Profile("William", 3.0, true);

        mFriendsList = (ListView) findViewById(R.id.friendsListView);
        mFriendsList.setAdapter(new FriendsArrayAdapter(getApplicationContext(), profiles));

        fabButton = (FloatingActionButton) findViewById(R.id.newWorkoutButton);
        fabButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), NewWorkoutActivity.class);
                startActivity(intent);
            }
        });

        userProfile = new Profile("Max", 1.5, true);
    }

    public static Profile getUserProfile()
    {
        return userProfile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menuProfile)
        {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        // TODO SOMETHING
    }
}

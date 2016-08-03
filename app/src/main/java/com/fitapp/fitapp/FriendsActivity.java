package com.fitapp.fitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by William Zulueta on 7/29/16.
 */
public class FriendsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private double distance;
    private static String tag = "HttpFitapp";
    private ListView mFriendsList;
    private Profile profiles[] = new Profile[2];
    private FloatingActionButton fabButton;
    private static Profile userProfile;
    private Button startButton;

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

        for (Profile file : profiles)
        {
            Workout workout = new Workout();
            workout.setDistance(Math.round(Math.random() * 15));
            workout.setTime("00:" + (int)((Math.random() * 60) + workout.getDistance() * 10) + ":00");
            workout.setSuccess((workout.getDistance() > 5) ? true : false);
            file.addWorkout(workout);
        }

        mFriendsList = (ListView) findViewById(R.id.friendsListView);
        mFriendsList.setAdapter(new FriendsArrayAdapter(getApplicationContext(), profiles));
        mFriendsList.setOnItemClickListener(this);

        fabButton = (FloatingActionButton) findViewById(R.id.newWorkoutButton);
        fabButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), NewWorkoutActivity.class);
                startActivityForResult(intent, 1000);
            }
        });

        userProfile = new Profile("Max", 1.5, true);
        new Thread()
        {
            @Override
            public void run()
            {
                String response = queryProfile();
                decodeData(response);
            }
        }.start();

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), CurrentWorkoutActivity.class);
                intent.putExtra("distance", distance);
                startActivityForResult(intent, 10);
            }
        });
    }

    private void decodeData(String response)
    {
        JSONObject object = null;
        try
        {
            object = new JSONObject(response);
            JSONArray array = object.getJSONObject("data").getJSONObject("viewer").getJSONObject("allRunss").getJSONArray("edges");
            userProfile = new Profile(array.length());
//            Log.i(tag, "Array : " + array.toString());
            for (int i = 0; i < array.length(); ++i)
            {
                Workout workout = new Workout();
                JSONObject tempObject = array.getJSONObject(i).getJSONObject("node");
//                Log.i(tag, "Tempobject : " + tempObject.toString());
//                Log.i(tag, "Distance temp object : " + tempObject.get("distance"));
                String temp = (String)tempObject.get("distance");
                workout.setDistance(Double.parseDouble(temp.substring(0, temp.indexOf('m'))));
                workout.setSuccess((Boolean) tempObject.get("success"));
                workout.setTime((String) tempObject.get("timeRan"));
                workout.setCreated((String) tempObject.get("createdAt"));
                Log.i(tag, "Run distance : " + workout.getDistance());
                Log.i(tag, "Status : " + workout.isSuccess());
                Log.i(tag, "Time : " + workout.getTime());
                userProfile.addWorkout(workout);
            }
        } catch (JSONException e)
        {
            Log.e(tag, e.getLocalizedMessage());
        }
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

    private String queryProfile()
    {
        HttpClient hc = new DefaultHttpClient();
        String data;
        HttpPost p = new HttpPost("https://api.scaphold.io/graphql/decd9324-c558-4d92-a781-f8c052e4af8a");
        JSONObject object = new JSONObject();
        try
        {
            object.put("query", "query { viewer { allRunss{ edges{ node{ id createdAt modifiedAt distance success timeRan } } } } } ");
            object.put("variables", "");
        } catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }

        try
        {
            data = object.toString();
            p.setEntity(new StringEntity(data, "UTF8"));
            p.setHeader("Content-type", "application/json");
            HttpResponse result = hc.execute(p);
            if (result != null)
            {
                StringBuilder dataBuilder = new StringBuilder();
                //                Log.d("HtppFitapp", "That was easy!");
                //                Log.i("HttpFitapp", "Result : " + result.getEntity().);
                InputStream is = result.getEntity().getContent();
                if (is != null)
                {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        dataBuilder.append(line);
                        Log.i("HttpFitapp", line);
                    }
                } else
                {
                    Log.i("HttpFitapp", "Input stream is null");
                }
                return dataBuilder.toString();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    //    private HttpResponse getProfile()
    //    {
    //        HttpClient hc = new DefaultHttpClient();
    //        String data;
    //        HttpPost p = new HttpPost("https://api.scaphold.io/graphql/decd9324-c558-4d92-a781-f8c052e4af8a");
    //        JSONObject object = new JSONObject();
    //        try
    //        {
    //            object.put("query", "  mutation createNewRun ($runs:_CreateRunsInput!) { createRuns(input: $runs){ changedRuns { id createdAt modifiedAt distance success timeRan } } } ");
    //        } catch (JSONException e)
    //        {
    //            Log.e("Fitapp", e.getLocalizedMessage());
    //        }
    //        try
    //        {
    //            object.put("variables", "{\"runs\" : {\"distance\" : \"5mi\",\"success\" : true,\"timeRan\" : \"1:30:24\"}}");
    //        } catch (JSONException e)
    //        {
    //            Log.e("Fitapp", e.getLocalizedMessage());
    //        }
    //        data = object.toString();
    //        try
    //        {
    //            p.setEntity(new StringEntity(data, "UTF8"));
    //        } catch (UnsupportedEncodingException e)
    //        {
    //            Log.e("Fitapp", e.getLocalizedMessage());
    //        }
    //        p.setHeader("Content-type", "application/json");
    //        HttpResponse result = null;
    //        try
    //        {
    //            result = hc.execute(p);
    //        } catch (IOException e)
    //        {
    //            Log.e("Fitapp", e.getLocalizedMessage());
    //        }
    //        if (result != null)
    //        {
    //            Log.i("Fitapp", "Worked");
    //            return result;
    //        }
    //        return null;
    //    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(getApplicationContext(), FriendsProfileActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", profiles[position]);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != RESULT_OK)
            return;

        if (requestCode == 1000)
        {
            distance = data.getExtras().getDouble("key");
            Log.i("WorkoutFitapp", "Distance : " + distance);
            startButton.setVisibility(View.VISIBLE);
            fabButton.setVisibility(View.INVISIBLE);
        }

        if (requestCode == 10)
        {
            startButton.setVisibility(View.INVISIBLE);
            fabButton.setVisibility(View.VISIBLE);
        }
    }
}

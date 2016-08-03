package com.fitapp.fitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class NewWorkoutActivity extends AppCompatActivity implements View.OnClickListener
{
    private Workout workout;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout_redone);
        workout = new Workout(0);
        editText = (EditText) findViewById(R.id.distance_edit);
    }

    public void handleAdd(View v) {
        //handle add function
    }

    public void handleCancel(View v) {
        //handle cancel function
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.cancel_workout_button:
                finish();
                break;
            case R.id.add_workout_button:
                workout.setDistance(Double.parseDouble(editText.getText().toString()));
                Intent data = getIntent();
                data.putExtra("key", Double.parseDouble(editText.getText().toString()));
                setResult(RESULT_OK, data);
                finish();
                break;
            default:
                break;
        }
    }
}

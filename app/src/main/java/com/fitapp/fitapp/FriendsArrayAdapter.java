package com.fitapp.fitapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by William Zulueta on 7/29/16.
 */
public class FriendsArrayAdapter extends ArrayAdapter<Profile>
{
    private Context context;
    private final Profile values[];

    public FriendsArrayAdapter(Context context, Profile values[])
    {
        super(context, -1, values);
        this.values = values;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_redone, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView distance = (TextView) rowView.findViewById(R.id.run);
        name.setText(values[position].getName());
        distance.setText(values[position].getLastRunDistance() + " Miles");
        if(values[position].getStatus())
        {
            name.setTextColor(context.getResources().getColor(android.R.color.holo_green_light));
        } else
        {
            name.setTextColor(context.getResources().getColor(android.R.color.holo_red_light));
        }
        return rowView;
    }
}

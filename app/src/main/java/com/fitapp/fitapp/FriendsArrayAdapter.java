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
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView distance = (TextView) rowView.findViewById(R.id.run);
        name.setText(values[position].getName());
        distance.setText(values[position].getLastRunDistance() + " Miles");
        if(values[position].getStatus())
        {
            rowView.setBackgroundColor(context.getResources().getColor(R.color.good));
        } else
        {
            rowView.setBackgroundColor(context.getResources().getColor(R.color.fail));
        }
        return rowView;
    }
}

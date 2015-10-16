package training.simpletodo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter {

    public CustomArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CustomArrayAdapter(Context context, int resource, ArrayList<TodoItem> items) {
        super(context, R.layout.todo_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, null);
        }

        TodoItem p = (TodoItem) getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.itemName);
            TextView tt2 = (TextView) v.findViewById(R.id.itemPriority);
            ;

            if (tt1 != null) {
                tt1.setText(p.mName);
            }

            if (tt2 != null) {
                tt2.setText(p.mPriority);
            }
        }

        return v;
    }

}

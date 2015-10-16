package training.simpletodo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoItemAdapter extends ArrayAdapter {

    // View lookup cache
    private static class ViewHolder {
        TextView mItemName;
        TextView mItemPriority;
    }

    public TodoItemAdapter(Context context, ArrayList<TodoItem> items) {
        super(context, R.layout.todo_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem p = (TodoItem) getItem(position);

        if (p != null) {
            ViewHolder viewHolder; // view lookup cache stored in tag
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, null);
                viewHolder.mItemName = (TextView) convertView.findViewById(R.id.itemName);
                viewHolder.mItemPriority = (TextView) convertView.findViewById(R.id.itemPriority);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            if (viewHolder.mItemName != null) {
                viewHolder.mItemName.setText(p.mName);
            }

            if (viewHolder.mItemPriority != null) {
                viewHolder.mItemPriority.setText(p.mPriority);
            }
        }

        return convertView;
    }

}

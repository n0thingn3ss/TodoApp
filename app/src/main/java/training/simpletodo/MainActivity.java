package training.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<TodoItem> mItems;
    TodoItemAdapter mItemsAdapter;
    ListView lvItems;
    String[] mPriorities;
    private final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        readItems();

        lvItems = (ListView) findViewById(R.id.lvltems);
        mItemsAdapter = new TodoItemAdapter(this, mItems);
        lvItems.setAdapter(mItemsAdapter);
        lvItems.requestFocus();

        mPriorities = getResources().getStringArray(R.array.item_priority_array);
        setupListViewListener();
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        mItemsAdapter.add(new TodoItem(itemText, 0));
        etNewItem.setText("");
        writeItems();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            String item_name = data.getExtras().getString("item_name");
            Integer item_priority = data.getExtras().getInt("item_priority");
            int item_pos = data.getExtras().getInt("item_pos", -1);
            // Toast the name to display temporarily on screen

            if (item_pos >= 0 ) {
                TodoItem tmp = mItems.get(item_pos);
                tmp.mName = item_name;
                tmp.mPriority = item_priority;

                mItems.set(item_pos, tmp);
                mItemsAdapter.notifyDataSetChanged();
                writeItems();
                Toast.makeText(this, tmp.mName + '-' +  mPriorities[tmp.mPriority], Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupListViewListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item,
                                            int pos,
                                            long id) {
                        Intent editItem = new Intent(MainActivity.this, EditItemActivity.class);
                        editItem.putExtra("item_name", mItems.get(pos).mName);
                        editItem.putExtra("item_priority", mItems.get(pos).mPriority);
                        editItem.putExtra("item_pos", pos);
                        startActivityForResult(editItem, REQUEST_CODE);
                    }
                }
        );
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item,
                                                   int pos,
                                                   long id) {
                        mItems.remove(pos);
                        mItemsAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                }
        );
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");

        try {
            ArrayList<String> todoItems = new ArrayList<String>(FileUtils.readLines(todoFile));
            mItems = new ArrayList<TodoItem>();
            for(String todoItem : todoItems) {
                String[] tmp = todoItem.split(",");
                mItems.add(new TodoItem(
                        tmp[0], Integer.parseInt(tmp[1])
                    )
                );
            }
        } catch (IOException e) {
            mItems = new ArrayList<TodoItem>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");

        try {
            FileUtils.writeLines(todoFile, mItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package training.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String item_name = getIntent().getStringExtra("item_name");
        String item_priority = getIntent().getStringExtra("item_priority");
        int item_pos = getIntent().getIntExtra("item_pos", -1);

        EditText etItemName = (EditText) findViewById(R.id.etItemName);
        etItemName.setText(item_name);
        etItemName.setSelection(item_name.length());
        
        EditText etItemPriority = (EditText) findViewById(R.id.etItemPriority);
        etItemPriority.setText(item_priority);
        etItemPriority.setSelection(item_priority.length());
    }

    public void onSubmit(View v) {
        EditText etItemName = (EditText) findViewById(R.id.etItemName);
        EditText etItemPriority = (EditText) findViewById(R.id.etItemPriority);

        Intent res = new Intent();
        res.putExtra("item_name", etItemName.getText().toString());
        res.putExtra("item_priority", etItemPriority.getText().toString());
        res.putExtra("item_pos", getIntent().getIntExtra("item_pos", -1));
        res.putExtra("code", 200);
        setResult(RESULT_OK, res);

        finish();
    }
}

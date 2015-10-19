package training.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        String item_name = getIntent().getStringExtra("item_name");
        Integer item_priority = getIntent().getIntExtra("item_priority", 0);
        int item_pos = getIntent().getIntExtra("item_pos", -1);

        EditText etItemName = (EditText) findViewById(R.id.etItemName);
        etItemName.setText(item_name);
        etItemName.setSelection(item_name.length());

        Spinner spItemPriority = (Spinner) findViewById(R.id.spItemPriority);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.item_priority_array,
                        android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spItemPriority.setAdapter(staticAdapter);
        spItemPriority.setSelection(item_priority);
    }

    public void onSubmit(View v) {
        EditText etItemName = (EditText) findViewById(R.id.etItemName);
        Spinner spItemPriority = (Spinner) findViewById(R.id.spItemPriority);

        Intent res = new Intent();
        res.putExtra("item_name", etItemName.getText().toString());
        res.putExtra("item_priority", spItemPriority.getSelectedItemPosition());
        res.putExtra("item_pos", getIntent().getIntExtra("item_pos", -1));
        res.putExtra("code", 200);
        setResult(RESULT_OK, res);

        finish();
    }
}

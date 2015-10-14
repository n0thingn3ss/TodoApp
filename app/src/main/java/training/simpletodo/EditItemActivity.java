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

        String item = getIntent().getStringExtra("item");
        int pos = getIntent().getIntExtra("item_pos", 0);

        EditText etItem = (EditText) findViewById(R.id.etItem);
        etItem.setText(item);
        etItem.setSelection(item.length());
    }

    public void onSubmit(View v) {
        EditText etItem = (EditText) findViewById(R.id.etItem);

        Intent res = new Intent();
        res.putExtra("item", etItem.getText().toString());
        res.putExtra("item_pos", getIntent().getIntExtra("item_pos", -1));
        res.putExtra("code", 200);
        setResult(RESULT_OK, res);

        finish();
    }
}

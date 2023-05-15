package com.example.administrator.ld;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private EditText idEditText;
    private EditText salaryEditText;
    private Button updateButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_update);

        idEditText = findViewById(R.id.id_edit_text);
        salaryEditText = findViewById(R.id.salary_edit_text);
        updateButton = findViewById(R.id.update_button);
        dbHelper = new DBHelper(this);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEditText.getText().toString();
                float salary = Float.parseFloat(salaryEditText.getText().toString());
                ContentValues values = new ContentValues();
                values.put(DBHelper.SALARY, salary);
                int count = getContentResolver().update(Uri.parse("content://com.example.staffprovider/staff"), values, DBHelper.ID + "=?", new String[]{id});
                Toast.makeText(UpdateActivity.this, "更新成功，共更新" + count + "条数据", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}

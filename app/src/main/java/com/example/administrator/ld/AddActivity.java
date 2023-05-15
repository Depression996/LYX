package com.example.administrator.ld;
import android.os.Bundle;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText genderEditText;
    private EditText departmentEditText;
    private EditText salaryEditText;
    private Button addButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nameEditText = findViewById(R.id.name_edit_text);
        genderEditText = findViewById(R.id.gender_edit_text);
        departmentEditText = findViewById(R.id.department_edit_text);
        salaryEditText = findViewById(R.id.salary_edit_text);
        addButton = findViewById(R.id.add_button);
        dbHelper = new DBHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String gender = genderEditText.getText().toString();
                String department = departmentEditText.getText().toString();
                float salary = Float.parseFloat(salaryEditText.getText().toString());
                ContentValues values = new ContentValues();
                values.put(DBHelper.NAME, name);
                values.put(DBHelper.GENDER, gender);
                values.put(DBHelper.DEPARTMENT, department);
                values.put(DBHelper.SALARY, salary);
                Uri uri = getContentResolver().insert(Uri.parse("content://com.example.staffprovider/staff"), values);
                Toast.makeText(AddActivity.this, "添加成功，ID为" + uri.getLastPathSegment(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}

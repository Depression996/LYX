package com.example.administrator.ld;
import android.os.Bundle;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    private EditText idEditText;
    private Button deleteButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        idEditText = findViewById(R.id.id_edit_text);
        deleteButton = findViewById(R.id.delete_button);
        dbHelper = new DBHelper(this);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEditText.getText().toString();
                int count = getContentResolver().delete(Uri.parse("content://com.example.staffprovider/staff"), DBHelper.ID + "=?", new String[]{id});
                Toast.makeText(DeleteActivity.this, "删除成功，共删除" + count + "条数据", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}

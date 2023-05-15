package com.example.administrator.ld;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.os.Bundle;
public class ConfigActivity extends AppCompatActivity {
    private RadioGroup backgroundColorRadioGroup;
    private RadioGroup fontSizeRadioGroup;
    private RadioGroup fontColorRadioGroup;
    private Button saveButton;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        preferences = getSharedPreferences("config", MODE_PRIVATE);

        backgroundColorRadioGroup = findViewById(R.id.background_color_radio_group);
        fontSizeRadioGroup = findViewById(R.id.font_size_radio_group);
        fontColorRadioGroup = findViewById(R.id.font_color_radio_group);
        saveButton = findViewById(R.id.save_button);

        int backgroundColor = preferences.getInt("background_color", Color.WHITE);
        int fontSize = preferences.getInt("font_size", 16);
        int fontColor = preferences.getInt("font_color", Color.BLACK);

        switch (backgroundColor) {
            case Color.WHITE:
                backgroundColorRadioGroup.check(R.id.white_radio_button);
                break;
            case Color.YELLOW:
                backgroundColorRadioGroup.check(R.id.yellow_radio_button);
                break;
            case Color.GREEN:
                backgroundColorRadioGroup.check(R.id.green_radio_button);
                break;
            case Color.BLUE:
                backgroundColorRadioGroup.check(R.id.blue_radio_button);
                break;
        }

        switch (fontSize) {
            case 16:
                fontSizeRadioGroup.check(R.id.size_16_radio_button);
                break;
            case 18:
                fontSizeRadioGroup.check(R.id.size_18_radio_button);
                break;
            case 20:
                fontSizeRadioGroup.check(R.id.size_20_radio_button);
                break;
        }

        switch (fontColor) {
            case Color.BLACK:
                fontColorRadioGroup.check(R.id.black_radio_button);
                break;
            case Color.RED:
                fontColorRadioGroup.check(R.id.red_radio_button);
                break;
            case Color.BLUE:
                fontColorRadioGroup.check(R.id.blue_radio_button);
                break;
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = Color.WHITE;
                int fontSize = 16;
                int fontColor = Color.BLACK;
                switch (backgroundColorRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.white_radio_button:
                        backgroundColor = Color.WHITE;
                        break;
                    case R.id.yellow_radio_button:
                        backgroundColor = Color.YELLOW;
                        break;
                    case R.id.green_radio_button:
                        backgroundColor = Color.GREEN;
                        break;
                    case R.id.blue_radio_button:
                        backgroundColor = Color.BLUE;
                        break;
                }
                switch (fontSizeRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.size_16_radio_button:
                        fontSize = 16;
                        break;
                    case R.id.size_18_radio_button:
                        fontSize = 18;
                        break;
                    case R.id.size_20_radio_button:
                        fontSize = 20;
                        break;
                }
                switch (fontColorRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.black_radio_button:
                        fontColor = Color.BLACK;
                        break;
                    case R.id.red_radio_button:
                        fontColor = Color.RED;
                        break;
                    case R.id.blue_radio_button:
                        fontColor = Color.BLUE;
                        break;
                }
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("background_color", backgroundColor);
                editor.putInt("font_size", fontSize);
                editor.putInt("font_color", fontColor);
                editor.apply();
                finish();
            }
        });
    }
}

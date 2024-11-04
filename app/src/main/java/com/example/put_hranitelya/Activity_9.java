package com.example.put_hranitelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_9);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_9), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RadioGroup radioGroup_front = findViewById(R.id.radiogroup_front);
        Button button = findViewById(R.id.testing_button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioGroup_front.getCheckedRadioButtonId();
                Intent intent;

                if (checkedId == R.id.radio_yes) {
                    intent = new Intent(getApplicationContext(), Activity_10.class);
                } else if (checkedId == R.id.radio_no) {
                    intent = new Intent(getApplicationContext(), Activity_11.class);
                } else {

                    Toast.makeText(getApplicationContext(), "Пожалуйста, выберите значение.", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(intent);
            }
        });
    }}
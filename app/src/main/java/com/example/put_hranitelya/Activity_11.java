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

public class Activity_11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_11);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_11), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RadioGroup radiogroup_popularizatsiya = findViewById(R.id.radiogroup_popularizatsiya);
        Button button = findViewById(R.id.testing_button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radiogroup_popularizatsiya.getCheckedRadioButtonId();
                Intent intent;

                if (checkedId == R.id.radio_lektsiya) {
                    intent = new Intent(getApplicationContext(), Activity_12.class);
                } else if (checkedId == R.id.radio_informatsiya) {
                    intent = new Intent(getApplicationContext(), Activity_13.class);
                } else {

                    Toast.makeText(getApplicationContext(), "Пожалуйста, выберите значение.", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(intent);
            }
        });
    }}
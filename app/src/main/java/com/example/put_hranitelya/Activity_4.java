package com.example.put_hranitelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_4), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        Button button = findViewById(R.id.testing_button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                Intent intent;

                if (checkedId == R.id.radio_fed_znacheniye) {
                    intent = new Intent(getApplicationContext(), Activity_5.class);
                } else if (checkedId == R.id.radio_reg_znacheniye) {
                    intent = new Intent(getApplicationContext(), Activity_6.class);
                } else if (checkedId == R.id.radio_munitsip_znacheniye) {
                    intent = new Intent(getApplicationContext(), Activity_7.class);
                } else if (checkedId == R.id.radio_net) {
                    intent = new Intent(getApplicationContext(), Activity_8.class);
                } else {

                    Toast.makeText(getApplicationContext(), "Пожалуйста, выберите значение.", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(intent);
            }
        });
    }}
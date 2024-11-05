package com.example.put_hranitelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Database.DBConnect;
import Database.DataCollector;

public class Activity_14 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_14);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_14), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button testing_button2 = findViewById(R.id.testing_button2);
        DBConnect dbConnect = new DBConnect(this);
        testing_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){



                try {
                    DataCollector dataCollector = new DataCollector(Activity_14.this, findViewById(R.id.text1), findViewById(R.id.text2), findViewById(R.id.text3), findViewById(R.id.text5),
                            findViewById(R.id.text6), findViewById(R.id.text7), findViewById(R.id.text8), findViewById(R.id.text10), findViewById(R.id.text12),
                            findViewById(R.id.text13), findViewById(R.id.text14), findViewById(R.id.radiogroup), findViewById(R.id.radiogroup_front),
                            findViewById(R.id.radiogroup_popularizatsiya), findViewById(R.id.editTextText), findViewById(R.id.editTextText2));

                    long userId = dbConnect.getUserIdFromUsersTable();
                    DBConnect.ResultData resultData = dataCollector.collectDataFromUI(userId);

                    long newRowId = dbConnect.insertResult(resultData);

                    if (newRowId != -1) {
                        Intent intent = new Intent(Activity_14.this, viewProfile.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Activity_14.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(Activity_14.this, "Произошла ошибка: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });
    }

}
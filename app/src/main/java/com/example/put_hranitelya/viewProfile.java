package com.example.put_hranitelya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class viewProfile extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.view_profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
                textView = findViewById(R.id.email_text_profile);

                // Получаем текст из SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String savedText = sharedPreferences.getString("savedText", "Default Text");

                // Устанавливаем текст в TextView
                textView.setText(savedText);
            }



    public void goToNachalo(View v){
        Intent intent = new Intent(this, Activity_1.class);
        startActivity(intent);
    }
}
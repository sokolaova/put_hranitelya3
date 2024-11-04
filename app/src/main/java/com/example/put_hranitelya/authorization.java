package com.example.put_hranitelya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class authorization extends AppCompatActivity {
    EditText editTextTextEmailAddress, editTextTextPassword;
    Button authorization_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authorization);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.authorization), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress_author);
        editTextTextPassword = findViewById(R.id.editTextTextPassword_author);
        authorization_button = findViewById(R.id.authorization_button_author);

            DBConnect dbConnect = new DBConnect(this);

        authorization_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String email = editTextTextEmailAddress.getText().toString();
                        String password = editTextTextPassword.getText().toString();

                        if (dbConnect.checkUser(email, password)) {
                            Intent intent = new Intent(authorization.this, viewProfile.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(authorization.this, "У Вас нет аккаунта. Нажмите кнопку \"Назад\"", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(authorization.this, "Произошла ошибка: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
                });


    }

    public void goBackToRegistration(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
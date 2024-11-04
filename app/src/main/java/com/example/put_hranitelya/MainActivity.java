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

public class MainActivity extends AppCompatActivity {

    EditText editTextTextEmailAddress, editTextTextPassword;
    Button registration_button, authorization_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registration), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        EditText editTextTextPassword = findViewById(R.id.editTextTextPassword);
        Button registration_button = findViewById(R.id.registration_button);
        Button authorization_button = findViewById(R.id.authorization_button);


        DBConnect dbConnect = new DBConnect(this);
        authorization_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String email = editTextTextEmailAddress.getText().toString();
                    String password = editTextTextPassword.getText().toString();

                    if (dbConnect.checkUser(email, password)) {
                        Intent intent = new Intent(MainActivity.this, viewProfile.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "У Вас нет аккаунта. Нажмите кнопку \"ЗАРЕГИСТРИРОВАТЬСЯ\"", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Произошла ошибка: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });


        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Получаем значение из EditText
                String email = editTextTextEmailAddress.getText().toString();
                String password = editTextTextPassword.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Пожалуйста, введите логин", Toast.LENGTH_SHORT).show();
                    editTextTextEmailAddress.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Пожалуйста, введите пароль", Toast.LENGTH_SHORT).show();
                    editTextTextPassword.requestFocus();
                    return;
                }
                Users newUser = new Users(email, password);
                boolean isInserted =  dbConnect.addUser(newUser);
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, viewProfile.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Неуспешная регистрация", Toast.LENGTH_SHORT).show();
                }

            }
        });

}}
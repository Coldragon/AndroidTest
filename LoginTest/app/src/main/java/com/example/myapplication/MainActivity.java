package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    private final String passwordHash = BCrypt.withDefaults().hashToString(12, "Hello5000".toCharArray());
    private EditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEmail = findViewById(R.id.editTextTextEmailAddress);
        inputPassword = findViewById(R.id.editTextTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonReset = findViewById(R.id.buttonReset);

        buttonLogin.setOnClickListener((View v) -> {
                String emailString = inputEmail.getText().toString();
                String passwordString = inputPassword.getText().toString();

                if (emailString.isEmpty() || passwordString.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_field_empty), Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_email_not_valid), Toast.LENGTH_SHORT).show();
                } else if (!BCrypt.verifyer().verify(passwordString.toCharArray(), passwordHash).verified) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_wrong_password), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.success_login_successfully), Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(myIntent);
                    finish();
                }
        });

        buttonReset.setOnClickListener((View v) -> {
            inputEmail.setText("");
            inputPassword.setText("");
            Toast.makeText(getApplicationContext(), getString(R.string.reset_button_success), Toast.LENGTH_SHORT).show();
        });

    }
}
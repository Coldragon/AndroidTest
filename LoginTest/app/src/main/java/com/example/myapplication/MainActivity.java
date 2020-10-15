package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

	private String password_hash = BCrypt.withDefaults().hashToString(12, "Hello5000".toCharArray());
	private EditText input_email, input_password;
	private Button button_login, button_reset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		input_email  = (EditText) findViewById(R.id.editTextTextEmailAddress);
		input_password = (EditText) findViewById(R.id.editTextTextPassword);
		button_login = (Button) findViewById(R.id.buttonLogin);
		button_reset = (Button) findViewById(R.id.buttonReset);

		button_login.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				String email_str = input_email.getText().toString();
				String password_str = input_password.getText().toString();

				String result = new String("");

				// Check Email
				if(!TextUtils.isEmpty(email_str) && Patterns.EMAIL_ADDRESS.matcher(email_str).matches())
				{
						// Check Password
						if(!TextUtils.isEmpty(password_str) && BCrypt.verifyer().verify(password_str.toCharArray(), password_hash).verified)
						{
							result += "Login Successful";
						}
						else
						{
							result += TextUtils.isEmpty(password_str) ?
									"Password field can't be empty" :
									"Wrong password";
						}
				}
				else {
					result += TextUtils.isEmpty(email_str) ?
							"Email field can't be empty" :
							"\"" + email_str + "\"" + " isn't a valid email address";
				}
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
		});

		button_reset.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) {
				input_email.setText("");
				input_password.setText("");
				Toast.makeText(getApplicationContext(),	"Reset input field",Toast.LENGTH_SHORT).show();
			}
		});

	}
}
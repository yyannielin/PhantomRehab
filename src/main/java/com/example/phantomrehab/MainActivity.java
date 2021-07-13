package com.example.phantomrehab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;
    private TextView LoginError;
    private TextView SignUp;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.enter_username);
        Password = (EditText) findViewById(R.id.enter_pw);
        Login = (Button) findViewById(R.id.btn_login);
        LoginError = (TextView) findViewById(R.id.login_error);
        SignUp = (TextView) findViewById(R.id.sign_up);

        fAuth = FirebaseAuth.getInstance();

        LoginError.setText("");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = Username.getText().toString();
                String pw = Password.getText().toString();

                validate(user,pw);
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void validate(String user, String pw){

        //admin login (connection not needed)
        if ((user.equals("admin")) && (pw.equals("1234"))){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {

            //user login
            fAuth.signInWithEmailAndPassword(user, pw)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Logged in successfully.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Login failed. " + task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                                LoginError.setText(R.string.login_error);
                            }
                        }
                    });
        }
    }

    private void register(){
        startActivity(new Intent(getApplicationContext(),SignupActivity.class));
    }
}
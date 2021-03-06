package com.example.phantomrehab;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private EditText Username, Email, Password, RePassword;
    private Button SignUp;
    private TextView Cancel;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Username = findViewById(R.id.enter_username);
        Email = findViewById(R.id.enter_email);
        Password = findViewById(R.id.enter_pw);
        RePassword = findViewById(R.id.confirm_pw);
        SignUp = findViewById(R.id.btn_signup);
        Cancel = findViewById(R.id.cancel);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Username.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String pw = Password.getText().toString().trim();
                String r_pw = RePassword.getText().toString().trim();

                if (TextUtils.isEmpty(user)){
                    Username.setError("Username is required.");
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Email.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(pw)){
                    Password.setError("Password is required.");
                    return;
                }

                if (pw.length() < 6){
                    Password.setError("Password must be at least 6 characters.");
                }

                //re-entered pw must be the same as pw
                if (TextUtils.isEmpty(r_pw)){
                    RePassword.setError("Please reenter your password.");
                    return;
                }

                //register the user in Firebase

                fAuth.createUserWithEmailAndPassword(email, pw)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(SignupActivity.this, "User created.",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    // FirebaseUser user = fAuth.getCurrentUser();
                                    // updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignupActivity.this, "Authentication failed. " + task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                    //updateUI(null);
                                }
                            }
                });
            }
        });
    }

    public void ret(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}


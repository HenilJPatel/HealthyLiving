package com.example.healthyliving;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    final String TAG="LoginActivity";
    String txtEmail, txtPassword;
    EditText email;
    Button loginSignup;
    TextInputLayout emailL,passwordL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null) {
            Intent intent=new Intent(LoginActivity.this, Homescreen.class);
            startActivity(intent);
        } else {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_login);
            loginSignup = findViewById(R.id.login_signup);
            email = findViewById(R.id.login_email);
            emailL = findViewById(R.id.loginEmailLayout);
            final EditText password = findViewById(R.id.login_password);
            loginSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(intent);
                }
            });
            Button login = findViewById(R.id.login_login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!validateEmail()){
                        return;
                    }
                    txtEmail = email.getText().toString().trim();
                    txtPassword = password.getText().toString().trim();
                    Login(txtEmail, txtPassword);
                }
            });
        }
    }
    private void Login(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Authentication Success." + user.getEmail(), Toast.LENGTH_SHORT).show();
                            Intent Homescreen = new Intent(LoginActivity.this, Homescreen.class);
                            startActivity(Homescreen);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public boolean validateEmail(){
        String val=email.getText().toString().trim();
        if(val.isEmpty()){
            emailL.setError("Field Cannot be Empty");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(val).matches()){
            emailL.setError("Invalid Email!");
            return false;
        } else{
            emailL.setError(null);
            emailL.setErrorEnabled(false);
            return true;
        }
    }

}
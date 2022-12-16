package com.example.healthyliving;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextInputEditText full_name, username, email, password, confirm_password,contact;
    TextInputLayout full_name_Layout, usernameL, emailL, passwordL, confirm_password_Layout,contactL;
    String txt_name,txtContact,txtEmail,txtUsername;
    final String TAG="SignUpActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();

        full_name =findViewById(R.id.editName);
        full_name_Layout =findViewById(R.id.signupFullnameLayout);
        full_name.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                validateFullName();
            }
        });
        username=findViewById(R.id.editUsername);
        usernameL=findViewById(R.id.signupUsernameLayout);
        username.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                validateUsername();
            }
        });
        email=findViewById(R.id.editEmail);
        emailL=findViewById(R.id.signupEmailLayout);
        email.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                validateEmail();
            }
        });
        password=findViewById(R.id.editPassword);
        passwordL=findViewById(R.id.signupPasswordLayout);
        password.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                validatePassword();
            }
        });
        confirm_password =findViewById(R.id.editConfirmPassword);
        confirm_password_Layout =findViewById(R.id.signupConfirmPasswordLayout);
        confirm_password.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                validateCPassword();
            }
        });
        contact=findViewById(R.id.editContact);
        contactL=findViewById(R.id.signupContactLayout);
        contact.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                validateContact();
            }
        });

        MaterialButton signup=findViewById(R.id.signup_signup);
        signup.setOnClickListener(v -> Signup());
    }

    public void LoginPage(View view)
    {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void Signup(){
        Toast.makeText(this,"Function triggers",Toast.LENGTH_SHORT).show();
        if(!validateUsername()|!validateFullName()|!validateEmail()|!validatePassword()|!validateCPassword()|!validateContact()) {
            return;
        }
            String eml= Objects.requireNonNull(email.getText()).toString().trim();
            String pass= Objects.requireNonNull(password.getText()).toString();
            mAuth.createUserWithEmailAndPassword(eml, pass).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Log.d(TAG, "CreateUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    assert user != null;
                    Toast.makeText(SignUpActivity.this, "Authentication Success." + user.getEmail(), Toast.LENGTH_SHORT).show();
                    CreateProfile(user);
                } else {
                    Log.w(TAG, "CreateUserWithEmail:success", task.getException());
                    Toast.makeText(SignUpActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                }
            });
    }
    private boolean validateFullName(){
        String val= Objects.requireNonNull(full_name.getText()).toString().trim();
        if(val.isEmpty()){
            full_name_Layout.setError("Field Cannot be Empty");
            return false;
        } else {
            full_name_Layout.setError(null);
            full_name_Layout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername(){
        String val= Objects.requireNonNull(username.getText()).toString().trim();
        String run_check= "\\A\\w{1,20}\\z";
        if(val.isEmpty()){
            usernameL.setError("Field Cannot be Empty");
            return false;
        } else if(val.length()>20){
            usernameL.setError("Username is too large!");
            return false;
        } else if(!val.matches(run_check)){
            usernameL.setError("No whitespaces are allowed!");
            return false;
        } else{
            usernameL.setError(null);
            usernameL.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateContact(){
        String val= Objects.requireNonNull(contact.getText()).toString().trim();
        String run_check= "[0-9]{10}";
        if(val.isEmpty()){
            contactL.setError("Field Cannot be Empty");
            return false;
        } else if(val.length()!=10){
            contactL.setError("Phone number length invalid!");
            return false;
        } else if(!val.matches(run_check)){
            contactL.setError("Numbers only!");
            return false;
        } else{
            contactL.setError(null);
            contactL.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
        String val= Objects.requireNonNull(email.getText()).toString().trim();
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

    private boolean validatePassword(){
        String val= Objects.requireNonNull(password.getText()).toString().trim();
        String checkPassword= "^"+
                "(?=.*[A-Z])"+
                "(?=.*[a-z])"+
                "(?=.*[0-9])"+
                "(?=.*[@#$%^&+=])"+
                "(?=.\\S+$)"+
                ".{8,}"+
                "$";
        if(val.isEmpty()){
            passwordL.setError("Field Cannot be Empty");
            return false;
        } else if(!val.matches(checkPassword)){
            passwordL.setError("Requires Uppercase Letters,Lowercase Letters, Special Character @#$%^&+=, no spaces!\nLength should be between 8-32 characters");
            return false;
        } else{
            passwordL.setError(null);
            passwordL.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateCPassword(){
        String pass= Objects.requireNonNull(password.getText()).toString().trim();
        String val= Objects.requireNonNull(confirm_password.getText()).toString().trim();
        if(val.isEmpty()){
            confirm_password_Layout.setError("Field Cannot be Empty");
            return false;
        } else if(!val.matches(pass)){
            confirm_password_Layout.setError("Password does not match");
            return false;
        } else{
            confirm_password_Layout.setError(null);
            confirm_password_Layout.setErrorEnabled(false);
            return true;
        }
    }

    private void CreateProfile(FirebaseUser user){
        Data_Customer dataCustomer =new Data_Customer();
        txt_name = Objects.requireNonNull(((TextInputEditText) findViewById(R.id.editName)).getText()).toString().trim();
        txtContact= Objects.requireNonNull(((TextInputEditText) findViewById(R.id.editContact)).getText()).toString().trim();
        txtEmail= user.getEmail();
        txtUsername= Objects.requireNonNull(((TextInputEditText) findViewById(R.id.editUsername)).getText()).toString().trim();
        dataCustomer.setName(txt_name);
        dataCustomer.setContact(txtContact);
        dataCustomer.setEmail(txtEmail);
        dataCustomer.setUsername(txtUsername);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference dbref= database.getReference("Users/"+user.getUid()+"/LoginDetails");
        dbref.setValue(dataCustomer);
        Toast.makeText(SignUpActivity.this,"User Created",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(SignUpActivity.this,Homescreen.class);
        startActivity(intent);
    }
}
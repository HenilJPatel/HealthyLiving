package com.example.healthyliving;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextInputEditText fullname, username, email, password,cpassword,contact;
    TextInputLayout fullnameL, usernameL, emailL, passwordL,cpasswordL,contactL;
    String txtname,txtContact,txtEmail,txtUsername;
    final String TAG="SignUpActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();

        fullname=findViewById(R.id.editName);
        fullnameL=findViewById(R.id.signupFullnameLayout);
        fullname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validateFullName();
                }
            }
        });
        username=findViewById(R.id.editUsername);
        usernameL=findViewById(R.id.signupUsernameLayout);
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validateUsername();
                }
            }
        });
        email=findViewById(R.id.editEmail);
        emailL=findViewById(R.id.signupEmailLayout);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validateEmail();
                }
            }
        });
        password=findViewById(R.id.editPassword);
        passwordL=findViewById(R.id.signupPasswordLayout);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validatePassword();
                }
            }
        });
        cpassword=findViewById(R.id.editConfirmPassword);
        cpasswordL=findViewById(R.id.signupConfirmPasswordLayout);
        cpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validateCPassword();
                }
            }
        });
        contact=findViewById(R.id.editContact);
        contactL=findViewById(R.id.signupContactLayout);
        contact.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validateContact();
                }
            }
        });

        MaterialButton signup=findViewById(R.id.signup_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });
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
            String eml=email.getText().toString().trim();
            String pass=password.getText().toString();
            mAuth.createUserWithEmailAndPassword(eml, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "CreateUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignUpActivity.this, "Authentication Success." + user.getEmail(), Toast.LENGTH_SHORT).show();
                        CreateProfile(user);
                    } else {
                        Log.w(TAG, "CreateUserWithEmail:success", task.getException());
                        Toast.makeText(SignUpActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
    private boolean validateFullName(){
        String val=fullname.getText().toString().trim();
        if(val.isEmpty()){
            fullnameL.setError("Field Cannot be Empty");
            return false;
        } else {
            fullnameL.setError(null);
            fullnameL.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername(){
        String val=username.getText().toString().trim();
        String checkspaces= "\\A\\w{1,20}\\z";
        if(val.isEmpty()){
            usernameL.setError("Field Cannot be Empty");
            return false;
        } else if(val.length()>20){
            usernameL.setError("Username is too large!");
            return false;
        } else if(!val.matches(checkspaces)){
            usernameL.setError("No whitespaces are allowed!");
            return false;
        } else{
            usernameL.setError(null);
            usernameL.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateContact(){
        String val=contact.getText().toString().trim();
        String checkspaces= "[0-9]{10}";
        if(val.isEmpty()){
            contactL.setError("Field Cannot be Empty");
            return false;
        } else if(val.length()!=10){
            contactL.setError("Phone number length invalid!");
            return false;
        } else if(!val.matches(checkspaces)){
            contactL.setError("Numbers only!");
            return false;
        } else{
            contactL.setError(null);
            contactL.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail(){
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

    private boolean validatePassword(){
        String val=password.getText().toString().trim();
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
        String pass=password.getText().toString().trim();
        String val=cpassword.getText().toString().trim();
        if(val.isEmpty()){
            cpasswordL.setError("Field Cannot be Empty");
            return false;
        } else if(!val.matches(pass)){
            cpasswordL.setError("Password does not match");
            return false;
        } else{
            cpasswordL.setError(null);
            cpasswordL.setErrorEnabled(false);
            return true;
        }
    }

    private void CreateProfile(FirebaseUser user){
        Customer customer=new Customer();
        txtname= ((TextInputEditText)findViewById(R.id.editName)).getText().toString().trim();
        txtContact=((TextInputEditText)findViewById(R.id.editContact)).getText().toString().trim();
        txtEmail= user.getEmail();
        txtUsername=((TextInputEditText)findViewById(R.id.editUsername)).getText().toString().trim();
        customer.setName(txtname);
        customer.setContact(txtContact);
        customer.setEmail(txtEmail);
        customer.setUsername(txtUsername);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference dbref= database.getReference("Users/"+user.getUid()+"/LoginDetails");
        dbref.setValue(customer);
        Toast.makeText(SignUpActivity.this,"User Created",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(SignUpActivity.this,Homescreen.class);
        startActivity(intent);
    }
}
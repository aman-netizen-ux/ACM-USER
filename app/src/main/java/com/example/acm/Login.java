package com.example.acm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private EditText email;
    private EditText password;
    private Button Login;
    String Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        Login=findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = email.getText().toString();
                Password = password.getText().toString();

                if(TextUtils.isEmpty(Email)|| TextUtils.isEmpty(Password))
                {
                    Toast.makeText(Login.this,"Fill All Fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    LoginUser();
                }
            }

        });
    }

    private void LoginUser() {

        firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Login.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText((Login.this),"Error !"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
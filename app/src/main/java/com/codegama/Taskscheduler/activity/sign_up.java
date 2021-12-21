package com.codegama.Taskscheduler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.codegama.Taskscheduler.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sign_up extends AppCompatActivity {


    TextView alreay,reg;
    EditText mail,pas1,pas2;
    Button regi;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        alreay=findViewById(R.id.textView4);
        reg=findViewById(R.id.textView2);
        mail=findViewById(R.id.inputemail);
        pas1=findViewById(R.id.inputconfirmpassword);
        pas2=findViewById(R.id.inputconfirmpassword2);
        regi=findViewById(R.id.buttonregister);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        alreay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_up.this,sign_in.class));
            }
        });
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });
    }
    private void PerforAuth() {
        String email=mail.getText().toString();
        String password=pas1.getText().toString();
        String confirmpassword=pas2.getText().toString();
        if(!email.matches(emailPattern))
        {
            mail.setError("Enter correct email");
        }
        else if(password.isEmpty()||password.length()<6)
        {
            pas1.setError("Enter proper password");
        }
        else if(!password.equals(confirmpassword))
        {
            pas2.setError("Password not matched");
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {

                        startActivity(new Intent(sign_up.this,sign_in.class));
                        Toast.makeText(sign_up.this,"Registration successfull",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(sign_up.this,"Registration failed"+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

}
package com.codegama.Taskscheduler.activity;

import android.app.ProgressDialog;
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

public class sign_in extends AppCompatActivity {

    TextView textview1,textView2;
    EditText mail,pass;
    Button btn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        textview1=(TextView) findViewById(R.id.textView4);
        textView2=(TextView) findViewById(R.id.textView2);
        mail=(EditText) findViewById(R.id.inputemail);
        pass=(EditText) findViewById(R.id.inputpassword);
        btn=(Button) findViewById(R.id.button);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sign_in.this,sign_up.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforlogin();
            }
        });
    }

    private void perforlogin() {
        String email=mail.getText().toString();
        String password=pass.getText().toString();
        if(!email.matches(emailPattern))
        {
            mail.setError("Enter correct email");
        }
        else if(password.isEmpty()||password.length()<6)
        {
            pass.setError("Enter proper password");
        }
        else
        {
            progressDialog.setMessage("Please wait while login.....");
            progressDialog.setTitle("login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        startActivity(new Intent(sign_in.this,MainActivity.class));
                        Toast.makeText(sign_in.this,"Login successfull",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(sign_in.this,dashboard.class));

                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(sign_in.this,"Registration failed"+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
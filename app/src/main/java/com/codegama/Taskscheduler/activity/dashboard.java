package com.codegama.Taskscheduler.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.codegama.Taskscheduler.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dashboard extends AppCompatActivity {

    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    TextView txt;
    FirebaseAuth mAuth;
    ImageView tasklist,cf,history,aboutapp,todays_task,achievement;
    DatabaseReference databaseReference;
    public int value =0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigationView = findViewById(R.id.navigationview);
        navigationView.bringToFront();
        tasklist=findViewById(R.id.ictasklist);
        todays_task=findViewById(R.id.ictoday);
        aboutapp=findViewById(R.id.icabout);
        achievement =findViewById(R.id.icconnect);
        navigationView.setItemIconTintList(null);
        cf=findViewById(R.id.iccf);
        history=findViewById(R.id.ichistory);
        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        ///new added for firebase realtime database


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.logout:
                        mAuth.signOut();
                        startActivity(new Intent(dashboard.this,sign_in.class));
                        break;

                }
                return true;
            }
        });
        tasklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value=1;
                startActivity(new Intent(dashboard.this,MainActivity.class));
            }
        });
        cf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,cfreminder.class));
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,history.class));
            }
        });
        aboutapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,about.class));
            }
        });
        todays_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference= FirebaseDatabase.getInstance().getReference("task");
                SharedPreferences sharedPreferences=getSharedPreferences("our_data", MODE_PRIVATE);
                SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                Date todayDate = new Date();
                String thisDate = currentDate.format(todayDate);
                String s=thisDate.substring(0,2);
                Integer s2int=Integer.parseInt(s);
                String date_final_string=Integer.toString(s2int);
                String completed_task=sharedPreferences.getString(date_final_string,"0");
                Integer total_task=Integer.parseInt(completed_task);
                FirebaseUser user =mAuth.getCurrentUser();
                String name=user.getEmail();

                model_class model_class=new model_class(total_task,name);
                String currentUserId = mAuth.getCurrentUser().getUid().toString();
                databaseReference.child(currentUserId).setValue(model_class);
                startActivity(new Intent(dashboard.this,leaderboard.class));
            }
        });
        achievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dashboard.this,Achievements.class));
            }
        });

    }
    protected void onStart() {
        super.onStart();
        FirebaseUser user =mAuth.getCurrentUser();
        if(user==null){
            startActivity(new Intent(dashboard.this,sign_up.class));
        }
        else
        {
            String name=user.getEmail();
            View header = navigationView.getHeaderView(0);
            txt = header.findViewById(R.id.profilename);
            txt.setText(name);
        }
    }

}
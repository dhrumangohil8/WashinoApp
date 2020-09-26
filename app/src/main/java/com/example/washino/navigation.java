package com.example.washino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class navigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
BottomNavigationView bottomNavigationView;
    DatabaseReference databaseUsers;
    DatabaseReference profileDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_navigation);
        bottomNavigationView=findViewById(R.id.bottom_navigation_view);


        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }
    home homeFragment =new home();
    location locationFragment =new location();
    feedback feedbackFragment =new feedback();
   schedule  scheduleFragment =new schedule();
   ShowDetails showDetails = new ShowDetails();
    profile profileFragment =new profile();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,R.anim.fadeout).replace(R.id.container, homeFragment).commit();
                return true;
            case R.id.navigation_feedback:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,R.anim.fadeout).replace(R.id.container, feedbackFragment).commit();
                return true;
            case R.id.navigation_location:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,R.anim.fadeout).replace(R.id.container, locationFragment).commit();
                return true;
            case R.id.navigation_schedule:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,R.anim.fadeout).replace(R.id.container, scheduleFragment).commit();
                return true;
            case R.id.navigation_profile:
                editOrShowProfile();
                return true;

        }
        return false;
    }

    private void editOrShowProfile()
    {
        String id = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users").child(id).child("userPhone");
        profileDetail = FirebaseDatabase.getInstance().getReference("users").child(id);
        //databaseUsers.child(id);

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (snapshot.getValue() != null) {
                        try {
                            Log.e("TAG", "" + snapshot.getValue());
                            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,R.anim.fadeout).replace(R.id.container, showDetails).commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("TAG", " it's null.");
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,R.anim.fadeout).replace(R.id.container, profileFragment).commit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("onCancelled", " cancelled");
            }
        });

        //getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein,R.anim.fadeout).replace(R.id.container, showDetails).commit();
    }
}

package com.example.washino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Animation middleAnimation;
    TextView a;
    Button login_button;

    Spinner spinnerCountries;
    TextInputEditText etPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        a = findViewById(R.id.a);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        a.setAnimation(middleAnimation);
        login_button = findViewById(R.id.login_button);

        etPhone = findViewById(R.id.etPhone);
        spinnerCountries = findViewById(R.id.spinnerCountries);

        userIsLoggedIn();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = etPhone.getText().toString();
                String phoneNumber = spinnerCountries.getSelectedItem().toString() + p;
                if(p.isEmpty() || phoneNumber.length() < 10){
                    etPhone.setError("Valid phone number required!");
                    etPhone.requestFocus();
                    return;
                }
//                Toast.makeText(Login.this, phone, Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), verifyotp.class));
                Intent intent = new Intent(Login.this, verifyotp.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });
    }

    private void userIsLoggedIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(getApplicationContext(), navigation.class);
            startActivity(intent);
            finish();
            return;
        }
    }
}

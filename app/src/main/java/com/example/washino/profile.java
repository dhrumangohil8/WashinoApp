package com.example.washino;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {
    TextView a;
    Animation middleAnimation;
    TextInputEditText userName;
    Spinner userGender;
    TextInputEditText userEmail;
//    TextInputEditText userPhone;
    TextInputEditText userAddress;
    TextInputEditText userCarName;
    Spinner userCarType;
    TextInputEditText userCarNumber;
    Button userSave;

    List<String> users;

    DatabaseReference databaseUsers;
    DatabaseReference databaseUsers1;

    public profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_profile,container,false);
        a = view.findViewById(R.id.a);
        middleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.middle_animation);
        a.setAnimation(middleAnimation);
        userName = view.findViewById(R.id.user_name);
        userEmail = view.findViewById(R.id.user_email);
        userGender = view.findViewById(R.id.user_gender);
//        userPhone = view.findViewById(R.id.user_phone);
        userAddress = view.findViewById(R.id.user_address);
        userCarName = view.findViewById(R.id.user_car_name);
        userCarType = view.findViewById(R.id.user_car_type);
        userCarNumber = view.findViewById(R.id.user_car_number);
        userSave = view.findViewById(R.id.save);

        users = new ArrayList<>();

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        userSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        return view;
    }

    private void addUser()
    {
        users.add(userName.getText().toString());
        users.add(userGender.getSelectedItem().toString());
        users.add(userEmail.getText().toString());
//        users.add(userPhone.getText().toString());
        users.add(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber());
        users.add(userAddress.getText().toString());
        users.add(userCarName.getText().toString());
        users.add(userCarNumber.getText().toString());
        users.add(userCarType.getSelectedItem().toString());

        String id = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        User user = new User(users);
        databaseUsers.child(id).setValue(user);
        Toast.makeText(getActivity(),"Registration successful",Toast.LENGTH_LONG).show();
    }
}

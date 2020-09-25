package com.example.washino;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 234;
    TextView a;
    Animation middleAnimation;
    TextInputEditText userName;
    Spinner userGender;
    TextInputEditText userEmail;
    TextInputEditText userAddress;
    TextInputEditText userCarName;
    Spinner userCarType;
    TextInputEditText userCarNumber;
    Button userSave, logout;
    Spinner userDefaultTime;
    ImageView profileImg;
    private StorageReference mStorageRef;
    public Uri imguri;
    List<String> users;

    DatabaseReference databaseUsers;
    DatabaseReference profileDetail;


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
        // All UI Elements
        userName = view.findViewById(R.id.user_name);
        userEmail = view.findViewById(R.id.user_email);
        userGender = view.findViewById(R.id.user_gender);
        userAddress = view.findViewById(R.id.user_address);
        userCarName = view.findViewById(R.id.user_car_name);
        userCarType = view.findViewById(R.id.user_car_type);
        userCarNumber = view.findViewById(R.id.user_car_number);
        userSave = view.findViewById(R.id.save);
        logout = view.findViewById(R.id.logout);
        userDefaultTime = view.findViewById(R.id.time);
        profileImg = view.findViewById(R.id.profileImage);

        // Firebase Objects
        mStorageRef = FirebaseStorage.getInstance().getReference();
        users = new ArrayList<>();
        String usrId = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        profileDetail = FirebaseDatabase.getInstance().getReference("users").child(usrId);

        // Get Details Automatically from database


        // Save Button Listener
        userSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        // Logout Button Listener
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    // Add Users Profile Method
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
        users.add(userDefaultTime.getSelectedItem().toString());
        String id = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        User user = new User(users);
        databaseUsers.child(id).setValue(user);
        Toast.makeText(getActivity(),"Registration successful",Toast.LENGTH_LONG).show();
    }
}

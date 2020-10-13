package com.dhruman.washino;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShowDetails extends Fragment {
    private static final int PICK_IMAGE_REQUEST = 234;
    TextView a;
    Animation middleAnimation;
    TextView userName;
    TextView userGender;
    TextView userEmail;
    TextView userAddress;
    TextView userCarName;
    TextView userCarType;
    TextView userCarNumber;
    Button userEdit, logout;
    TextView userDefaultTime;
    ImageView profileImg;
    private StorageReference mStorageRef;
    public Uri imguri;
    List<String> users;

    DatabaseReference databaseUsers;
    DatabaseReference profileDetail;


    public ShowDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_show_details,container,false);
        a = view.findViewById(R.id.a);
        middleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.middle_animation);
        a.setAnimation(middleAnimation);
        // All UI Elements
        userName = view.findViewById(R.id.show_name);
        userEmail = view.findViewById(R.id.show_email);
        userGender = view.findViewById(R.id.show_gender);
        userAddress = view.findViewById(R.id.show_address);
        userCarName = view.findViewById(R.id.show_car_name);
        userCarType = view.findViewById(R.id.show_car_type);
        userCarNumber = view.findViewById(R.id.show_car_no);
        userEdit = view.findViewById(R.id.edit);
        logout = view.findViewById(R.id.logout);
        userDefaultTime = view.findViewById(R.id.show_wash_time);
        profileImg = view.findViewById(R.id.profileImage);

        loadWithGlide();
        // Firebase Objects
        mStorageRef = FirebaseStorage.getInstance().getReference();
        users = new ArrayList<>();
        String usrId = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        profileDetail = FirebaseDatabase.getInstance().getReference("users").child(usrId);

        // Get Details Automatically from database
        fillDetail();

        userEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapFragments();
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

    private void swapFragments()
    {
        profile profileFragment = new profile();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, profileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void fillDetail()
    {
        String id = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users").child(id);

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (snapshot.getValue() != null) {
                        try {
                            Log.e("TAG", "" + snapshot.getValue());

                            userName.setText(snapshot.child("userName").getValue().toString());
                            userAddress.setText(snapshot.child("userAddress").getValue().toString());
                            userCarName.setText(snapshot.child("userCarName").getValue().toString());
                            userCarNumber.setText(snapshot.child("userCarNumber").getValue().toString());
                            userCarType.setText(snapshot.child("userCarType").getValue().toString());
                            userEmail.setText(snapshot.child("userEmail").getValue().toString());
                            userGender.setText(snapshot.child("userGender").getValue().toString());
                            userDefaultTime.setText(snapshot.child("userDefaultTime").getValue().toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("TAG", " it's null.");
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
    }

    public void loadWithGlide() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        String id = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        String url = "gs://washinoapplication.appspot.com/"+id+"/images/photoOfCar";
        Log.i("url:",url);
        StorageReference gsReference = storage.getReferenceFromUrl(url);
        GlideApp.with(Objects.requireNonNull(getContext()))
                .load(gsReference)
                .into(profileImg);
    }
}
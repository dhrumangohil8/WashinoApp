package com.example.washino;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
    private Button btnSelect, btnUpload;
    private ImageView imageView;
    private Uri filePath;
    //private final int PICK_IMAGE_REQUEST = 22;

    FirebaseStorage storage;
    StorageReference storageReference;

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
        btnSelect = view.findViewById(R.id.choosePic);
        btnUpload = view.findViewById(R.id.uploadPic);
        imageView = view.findViewById(R.id.selectedImage);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SelectImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                uploadImage();
            }
        });

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

    private void swapFragments()
    {
        ShowDetails profileFragment = new ShowDetails();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, profileFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getActivity().getContentResolver(),
                                filePath);
                imageView.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String usrId = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(usrId)
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(getContext(),
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(getContext(),
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }
}

package com.dhruman.washino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PopUpClass extends AppCompatActivity {

    DatabaseReference databaseUsers;
    TextView schTime;
    TextView schDate;
    TextView schNotes;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pop_up_class);
//    }

    public void showPopupWindow(final View view) {


        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_pop_up_class, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //Initialize the elements of our window, install the handler

        schDate=popupView.findViewById(R.id.schDate);
        schNotes=popupView.findViewById(R.id.schNotes);
        schTime=popupView.findViewById(R.id.schTime);

        //Handler for clicking on the inactive zone of the window

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

        String id = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users").child(id).child("schedule");

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if (snapshot.getValue() != null) {
                        try {
                            Log.e("TAG", "" + snapshot.getValue());
                            if(snapshot.child("scheduleDate").getValue().toString()!=null )
                                schDate.setText("Scheduled Date: "+snapshot.child("scheduleDate").getValue().toString());
                            else
                                schDate.setText("Schedule Date: NA");

                            if(snapshot.child("scheduleNotes").getValue().toString()!=null || snapshot.child("scheduleNotes").getValue().toString()!="")
                                schNotes.setText("Scheduled Notes: "+snapshot.child("scheduleNotes").getValue().toString());
                            else
                                schNotes.setText("Schedule Notes: NA");

                            if(snapshot.child("scheduleTime").getValue().toString()!=null)
                                schTime.setText("Scheduled Time: "+snapshot.child("scheduleTime").getValue().toString());

                            else
                                schTime.setText("Scheduled Time: NA");

//                            userName.setText(snapshot.child("userName").getValue().toString());
//                            userAddress.setText(snapshot.child("userAddress").getValue().toString());
//                            userCarName.setText(snapshot.child("userCarName").getValue().toString());
//                            userCarNumber.setText(snapshot.child("userCarNumber").getValue().toString());
//                            //userCarType.setText(snapshot.child("userCarType").getValue().toString());
//                            userEmail.setText(snapshot.child("userEmail").getValue().toString());
//                            //userGender.setText(snapshot.child("userGender").getValue().toString());
//                            //userDefaultTime.setText(snapshot.child("userDefaultTime").getValue().toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("TAG", " it's null.");
                        schNotes.setText("Not Available");

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
}
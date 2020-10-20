package com.dhruman.washino;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class feedback extends Fragment {

    RatingBar ratingFeedback;
    String ratingStar, reviewText;
    EditText etReview;
    Button btnSubmitFeedback;

    public feedback() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_feedback,container,false);

        ratingFeedback = view.findViewById(R.id.ratingBar);
        etReview = view.findViewById(R.id.etReview);
        btnSubmitFeedback = view.findViewById(R.id.btnSubmitFeedback);

        ratingFeedback.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingStar = String.valueOf(rating);
            }
        });

        btnSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewText = etReview.getText().toString();
                DatabaseReference feedbackDb = FirebaseDatabase.getInstance().getReference("users");
                String feedbackId = feedbackDb.push().getKey();
                FeedbackClass feedback = new FeedbackClass(reviewText, ratingStar);
                feedbackDb.child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()).child("feedback").child(feedbackId).setValue(feedback);
                Toast.makeText(getContext(), "Thanks for your feedback!", Toast.LENGTH_SHORT).show();
                etReview.setText("");
                ratingFeedback.setRating(0.0f);
            }
        });
        return view;
    }
}

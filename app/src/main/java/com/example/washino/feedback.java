package com.example.washino;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;


/**
 * A simple {@link Fragment} subclass.
 */
public class feedback extends Fragment {
    private static final String TAG = "";

    public feedback() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_feedback,container,false);
        // Inflate the layout for this fragment
        try {

            SmileRating smileRating=view.findViewById(R.id.smile_rating);
            smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
                @Override
                public void onSmileySelected(int smiley, boolean reselected) {
                    String message =null;
                    switch (smiley) {
                        case SmileRating.BAD:
                            message="Bad";
                            break;
                        case SmileRating.GOOD:
                            Toast.makeText(getActivity(), "Click!", Toast.LENGTH_SHORT).show();

                            break;
                        case SmileRating.GREAT:
                            Log.i(TAG, "Great");
                            break;
                        case SmileRating.OKAY:
                            Log.i(TAG, "Okay");
                            break;
                        case SmileRating.TERRIBLE:
                            Log.i(TAG, "Terrible");
                            break;
                        case SmileRating.NONE:
                            Log.i(TAG, "None");
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + smiley);
                    }
                }
            });
            smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
                @Override
                public void onRatingSelected(int level, boolean reselected) {
                    Log.i(TAG, "Rated as: " + level + " - " + reselected);
                }
            });







        }catch (Exception e)
        {
            System.out.println(e);
        }
        return view;

    }
}

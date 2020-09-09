package com.example.washino;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.baoyachi.stepview.VerticalStepView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class location extends Fragment  {
    VerticalStepView verticalStepView;
    TextView a;
    Animation middleAnimation;

    public location()  {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_location,container,false);
        verticalStepView=view.findViewById(R.id.verticalstepview);
        a = view.findViewById(R.id.a);
        middleAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.middle_animation);
        a.setAnimation(middleAnimation);


        // add source

        List<String> sources = new ArrayList<>();
        sources.add("Start");
        sources.add("Arriving");
        sources.add("Arrived");
        sources.add("Washing");
        sources.add("Foam washing");
        sources.add("Cleaning");
        sources.add("Deseil Washing");
        sources.add("Completed");
        try {

            verticalStepView.setStepsViewIndicatorComplectingPosition(sources.size()-2).reverseDraw(false)
                    .setStepViewTexts(sources)
                    .setLinePaddingProportion(0.85f)
                    .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#2A2A72"))
                    .setStepViewComplectedTextColor(Color.parseColor("#0EBFE9"))
                    .setStepViewUnComplectedTextColor(Color.parseColor("#8B0000"))
                    .setStepViewComplectedTextColor(Color.parseColor("#0EBFE9"))
                    .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#8B0000"))
                    .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_check_circle_black_24dp))
                    .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_black_24dp))
                    .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_radio_button_checked_black_24dp));

        } catch (Exception e){
            System.out.println(e);
        }


        return view;

    }
}

package com.example.washino;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Locale;
import android.content.Context;
import android.graphics.Color;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;


import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class schedule extends Fragment {
    private static final String TAG = "";
    private CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    public schedule() {
        // Required empty public constructor
    }


    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_schedule,container,false);
        try{


            final CompactCalendarView compactCalendarView = view.findViewById(R.id.compactcalendar_view);
            final ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle(null);

            compactCalendar = view.findViewById(R.id.compactcalendar_view);
            compactCalendar.setUseThreeLetterAbbreviation(true);

            //Set an event for Teachers' Professional Day 2016 which is 21st of October

            Event ev1 = new Event(Color.RED, 1477040400000L, "Teachers' Professional Day");
            compactCalendar.addEvent(ev1);

            Event ev2 = new Event(Color.GREEN, 1594231873000L,"Mohammad' Professional Day");
            compactCalendar.addEvent(ev2);

            List<Event> events = compactCalendar.getEvents(1433701251000L);

            Log.d(TAG, "Events: " + events);

            compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {
                    Context context = Objects.requireNonNull(getActivity()).getApplicationContext();
                    List<Event> events = compactCalendarView.getEvents(dateClicked);
                    Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);

                    if (dateClicked.toString().compareTo("Mon Jul 06 00:00:00 GMT+05:30 2020") == 0) {
                        System.out.println("Hello");
                    } else {
                        Toast.makeText(context, "No Events Planned for that day", Toast.LENGTH_SHORT).show();
                        System.out.println(dateClicked);
                    }


                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
                    Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
                }

            });
        }catch (Exception e){System.out.println(e);
        }


        return view;

    }


}






















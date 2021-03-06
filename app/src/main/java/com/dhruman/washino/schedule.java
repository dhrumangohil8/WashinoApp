package com.dhruman.washino;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class schedule extends Fragment {
    CalendarView calendarViewScheduling;
    Spinner schedulingTime;
    Button btnSchedule;
    String date, time, schedule;
    EditText etScheduleNotes;
    Button showSchedule;

    public schedule() {
        // Required empty public constructor
    }


    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_schedule,container,false);

        calendarViewScheduling = view.findViewById(R.id.calendarViewScheduling);
        schedulingTime = view.findViewById(R.id.schedulingTime);
        btnSchedule = view.findViewById(R.id.btnSchedule);
        showSchedule = view.findViewById(R.id.showSchedule);
        etScheduleNotes = view.findViewById(R.id.etScheduleNotes);

        calendarViewScheduling.setDate(System.currentTimeMillis());
        calendarViewScheduling.setMinDate(System.currentTimeMillis() - 1000);

        calendarViewScheduling.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "-" + (month+1) + "-" + year;
            }
        });

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = schedulingTime.getSelectedItem().toString();
                schedule = "Scheduled on " + date + " at " + time;
                Toast.makeText(getContext(), schedule , Toast.LENGTH_SHORT).show();
                DatabaseReference dbSchedule = FirebaseDatabase.getInstance().getReference("users");
                ScheduleClass mySchedule = new ScheduleClass(date, time, etScheduleNotes.getText().toString());
                dbSchedule.child(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()).child("schedule").setValue(mySchedule);
            }
        });

        showSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpClass popUpClass = new PopUpClass();
                popUpClass.showPopupWindow(v);
            }
        });

        /*
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
                    Toast.makeText(getContext(), dateClicked.toString(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    actionBar.setTitle(dateFormatMonth.format(firstDayOfNewMonth));
                    Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
                }

            });
        }catch (Exception e){System.out.println(e);
        }

*/
        return view;

    }

}























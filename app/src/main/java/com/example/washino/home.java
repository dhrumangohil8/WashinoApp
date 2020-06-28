package com.example.washino;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {

    public home() {
        // Required empty public constructor
    }
///////Banner Slider

private ViewPager bannerSliderViewPager;
private List<SliderModel> sliderModelList;
private int currentPage = 2;
private Timer timer;
final private long DELAY_TIME = 3000;
final private long PERIOD_TIME = 3000;
////// BannerSlider
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        // Inflate the layout for this fragment
        /////BannerSlider


        bannerSliderViewPager= view.findViewById(R.id.banner_slider_view_pager);
        sliderModelList=new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.ic_home_black_24dp,"#A8A8A8"));
        sliderModelList.add(new SliderModel(R.drawable.ic_feedback_black_24dp,"#A8A8A8"));
        sliderModelList.add(new SliderModel(R.drawable.splashlogo,"#A8A8A8"));

        sliderModelList.add(new SliderModel(R.drawable.facebook,"#A8A8A8"));
        sliderModelList.add(new SliderModel(R.drawable.twitter,"#A8A8A8"));
        sliderModelList.add(new SliderModel(R.drawable.google,"#A8A8A8"));
        sliderModelList.add(new SliderModel(R.drawable.ic_person_black_24dp,"#A8A8A8"));
        sliderModelList.add(new SliderModel(R.drawable.ic_schedule_black_24dp,"#A8A8A8"));
        sliderModelList.add(new SliderModel(R.drawable.ic_home_black_24dp,"#A8A8A8"));

        sliderModelList.add(new SliderModel(R.drawable.ic_feedback_black_24dp,"#A8A8A8"));
        sliderModelList.add(new SliderModel(R.drawable.splashlogo,"#A8A8A8"));
        sliderModelList.add(new SliderModel(R.drawable.facebook,"#A8A8A8"));




        SliderAdapter sliderAdapter=new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);
        ViewPager.OnPageChangeListener onPageChangeListener =new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrollStateChanged(int position) {
                if(position==ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }

            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);
        startbannerslideshow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopbannerslideshow();
                if(event.getAction()==MotionEvent.ACTION_UP){
                    startbannerslideshow();
                }
                return false;
            }
        });

        /////BannerSlider





        return view;


    }
    /////BannerSlider
    private void pageLooper(){
        if(currentPage==sliderModelList.size() -2){

            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
        if(currentPage == 1){

            currentPage=sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }

    }

    private void startbannerslideshow(){
         final Handler handler=new Handler();
         final Runnable update=new Runnable() {
            @Override
            public void run() {
                if(currentPage>=sliderModelList.size()){
                    currentPage=1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++,true);

            }
        };
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }

    private void stopbannerslideshow(){
        timer.cancel();

    }
    /////BannerSlider




}

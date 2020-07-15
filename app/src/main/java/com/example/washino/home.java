package com.example.washino;

import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
final private long DELAY_TIME = 5000;
final private long PERIOD_TIME = 5000;
////// BannerSlider
///// strip_ad_layout
private ImageView stripimage;
private ConstraintLayout stripaddcontainer;

///// strip_ad_layout

///// Horizontal Product Layout
private TextView HorizontallayoutTitle;
private Button HorizontalviewAllbtn;
private RecyclerView horizantalRecyclerView;


////  Horizontal Product Layout
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


        ///// strip_ad_layout
        stripimage=view.findViewById(R.id.strip_ad_image);
        stripaddcontainer=view.findViewById(R.id.strip_ad_container);

        stripimage.setImageResource(R.drawable.car);
        stripaddcontainer.setBackgroundColor(Color.parseColor("#000000"));
        ///// strip_ad_layout


        ///// Horizontal Product Layout

        HorizontallayoutTitle=view.findViewById(R.id.horizontal_scroll_layout_title);
        HorizontalviewAllbtn=view.findViewById(R.id.horizontal_scroll_layout_button);
        horizantalRecyclerView=view.findViewById(R.id.horizontal_scroll_layout_recycleview);
        List<HorizontalProductScrollModel> horizontalProductScrollModelList=new ArrayList<>();
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.car,"Package1","50% Sale","Rs. 3000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.facebook,"Package1","50% Sale","Rs. 3000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ic_person_black_24dp,"Package1","50% Sale","Rs. 3000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.splashlogo,"Package1","50% Sale","Rs. 3000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.ic_home_black_24dp,"Package1","50% Sale","Rs. 3000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.car,"Package1","50% Sale","Rs. 3000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.google,"Package1","50% Sale","Rs. 3000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.car,"Package1","50% Sale","Rs. 3000"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter=new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizantalRecyclerView.setLayoutManager(linearLayoutManager);

        horizantalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        ///// Horizontal Product Layout
        //// Grid Layout

        TextView gridLayoutTitle=view.findViewById(R.id.grid_layout_title);
        Button gridLayoutButton=view.findViewById(R.id.grid_layout_view_button);
        GridView gridView=view.findViewById(R.id.grid_layout_gridview);

        gridView.setAdapter(new GridServicesAdapter(horizontalProductScrollModelList));
        //// Grid Layout





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

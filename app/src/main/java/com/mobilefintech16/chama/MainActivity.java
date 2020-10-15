package com.mobilefintech16.chama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnBoardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOnboardingIndicators = findViewById(R.id.layoutonBoardingIndicators);
        buttonOnBoardingAction = findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onBoardingAdapter);

        setupOnboardingIndicators();

        setCurrentOnBoardIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardIndicator(position);
            }
        });

        buttonOnBoardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onboardingViewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()){
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else{
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }
            }
        });
    }
    private void setupOnboardingItems(){
        List<OnBoardingItem> onBoardingItems = new ArrayList<>();
        OnBoardingItem itemPayOnline = new OnBoardingItem();
        itemPayOnline.setTitle("Open Your Chama Account 1");
        itemPayOnline.setDescription("Chamas will make you better persons ");
        itemPayOnline.setImage(R.drawable.web_hi_res_512);

        OnBoardingItem itemOnTheWay = new OnBoardingItem();
        itemOnTheWay.setTitle("Open Your Chama Account on he way 2");
        itemOnTheWay.setDescription("Chamas will make you better personsfasfja");
        itemOnTheWay.setImage(R.drawable.web_hi_res_512);

        OnBoardingItem itemEatTogether = new OnBoardingItem();
        itemEatTogether.setTitle("Open Your Chama Account on he way 3");
        itemEatTogether.setDescription("Chamas will make you better personsfasfja");
        itemEatTogether.setImage(R.drawable.web_hi_res_512);

        onBoardingItems.add(itemPayOnline);
        onBoardingItems.add(itemOnTheWay);
        onBoardingItems.add(itemEatTogether);

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItems);
    }

    private void setupOnboardingIndicators(){
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
            for(int i = 0; i <indicators.length; i++){
                indicators[i] = new ImageView(getApplicationContext());
                indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);
        }
    }
    @SuppressLint("SetTextI18n")
    private void setCurrentOnBoardIndicator(int index){
        int childCount = layoutOnboardingIndicators.getChildCount();
        for(int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if(index == onBoardingAdapter.getItemCount() - 1){
            buttonOnBoardingAction.setText("LOGIN");
        }else{
            buttonOnBoardingAction.setText("NEXT");
        }

    }
}
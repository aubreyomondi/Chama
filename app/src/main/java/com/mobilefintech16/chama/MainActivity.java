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
        OnBoardingItem pictureOne = new OnBoardingItem();
        pictureOne.setTitle("We are your partner. We share your vision. Go Ahead!");
        pictureOne.setDescription("Join a Chama, Save Money, Borrow and much more...");
        pictureOne.setImage(R.drawable.picture_one);

        OnBoardingItem pictureTwo = new OnBoardingItem();
        pictureTwo.setTitle("You'll Never Walk Alone!");
        pictureTwo.setDescription("We create foundational steps for you");
        pictureTwo.setImage(R.drawable.picture_two);

        OnBoardingItem pictureThree = new OnBoardingItem();
        pictureThree.setTitle("Excellent Customer Support");
        pictureThree.setDescription("We will be there whenever you need us");
        pictureThree.setImage(R.drawable.pictures_three);

        onBoardingItems.add(pictureOne);
        onBoardingItems.add(pictureTwo);
        onBoardingItems.add(pictureThree);

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
            buttonOnBoardingAction.setText("LOG IN");
        }else{
            buttonOnBoardingAction.setText("NEXT");
        }

    }
}
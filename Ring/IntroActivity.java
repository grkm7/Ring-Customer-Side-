package com.info.bitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroActivity extends AppCompatActivity {
    ImageView logo,appName,ring;
    LottieAnimationView lottieAnimationView;
    TextView tvRing;
    MediaPlayer bell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        logo=findViewById(R.id.logo);
        tvRing=findViewById(R.id.tvRing);
        bell=MediaPlayer.create(this,R.raw.bell);
        appName=findViewById(R.id.app_name);
        ring=findViewById(R.id.ring);
        lottieAnimationView=findViewById(R.id.lottie);

        //splashImg.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        //appName.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        ring.animate().scaleX(2).scaleY(2).setDuration(1700).setStartDelay(4500);
        ring.animate().rotationX(360).setStartDelay(4500);
        tvRing.animate().translationY(900).setDuration(1200).setStartDelay(4400);
        logo.animate().translationY(900).setDuration(1200).setStartDelay(4200);
        lottieAnimationView.animate().translationY(1400).setDuration(1200).setStartDelay(4000);





        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bell.start();
                startActivity(new Intent(IntroActivity.this,MainActivity.class));
                finish();
            }
        });

    }
}
package com.example.vazhikatti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logoImageView = findViewById(R.id.logoImageView);

        // Define the animation
        Animation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000); // 1 second
        // Set an animation listener to start the next activity when the animation ends
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation start, if needed
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Animation end, start the next activity
                startActivity(new Intent(MainActivity.this, Register.class));
                finish(); // Optional: Finish the current activity to prevent going back to it
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeat, if needed
            }
        });

        // Start the animation
        logoImageView.startAnimation(scaleAnimation);
    }
}
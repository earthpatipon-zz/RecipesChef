package com.example.earthpatipon.recipeschef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        imageView = findViewById(R.id.welcome_image);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fadein);

        imageView.startAnimation(animation);

        final Intent intent = new Intent(this, LoginActivity.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };

        timer.start();
    }

}

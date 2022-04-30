package com.example.bookshop.Events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.bookshop.R;

public class Animation_Vong1 extends AppCompatActivity {
    ImageView tieude,phg,nextvong1;
    Animation topanimation,bottomanimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amination_vong1);
        AnhXa();
        topanimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        tieude.setAnimation(topanimation);
        phg.setAnimation(bottomanimation);
        nextvong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Animation_Vong1.this, ChoiNgayActivity.class));
                finish();
            }
        });
    }

    private void AnhXa() {
        nextvong1 = findViewById(R.id.nextvong1);
        tieude = findViewById(R.id.tieude);
        phg = findViewById(R.id.phg);
    }
}
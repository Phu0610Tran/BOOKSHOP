package com.example.bookshop.Events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.bookshop.R;

public class Animation_Vong2 extends AppCompatActivity {
    ImageView animation_vong2,nextvong2_aim;
    Animation bottomanimation;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_vong2);
        bottomanimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        AnhXa();
        Intent intent = getIntent();
        id = intent.getIntExtra("id",123);
        animation_vong2.setAnimation(bottomanimation);
        nextvong2_aim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Animation_Vong2.this, ChoiNgayVong2.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
                finish();
            }
        });
    }

    private void AnhXa() {
        animation_vong2 = findViewById(R.id.animation_vong2);
        nextvong2_aim = findViewById(R.id.nextvong2_aim);

    }
}
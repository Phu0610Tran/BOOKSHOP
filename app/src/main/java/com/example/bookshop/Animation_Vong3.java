package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Animation_Vong3 extends AppCompatActivity {
    ImageView animation_vong3,nextvong3_aim;
    Animation bottomanimation;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_vong3);
        bottomanimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        AnhXa();
        Intent intent = getIntent();
        id = intent.getIntExtra("id",123);
        animation_vong3.setImageResource(R.drawable.titleba);
        animation_vong3.setAnimation(bottomanimation);
        nextvong3_aim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Animation_Vong3.this, ChoiNgayVong3.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
                finish();
            }
        });
    }

    private void AnhXa() {
        animation_vong3 = findViewById(R.id.animation_vong3);
        nextvong3_aim = findViewById(R.id.nextvong3_aim );

    }
}
package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshop.ActivityUser.HomeActivity;
import com.example.bookshop.ActivityUser.LoginActivity;
import com.example.bookshop.ActivityUser.MainActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class VuiCungTaiChinhActivity extends AppCompatActivity {
    ImageButton btn_choingay;
    CircleImageView img_tk_vctc;
    Button btn_huongdan, btnbanbe, btn_bangxephang;
    TextView tentk_vctc;
    ImageView quaylai_vctc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vui_cung_tai_chinh);
        AnhXa();
        Events();
        Getdata();
    }

    private void Getdata() {
        tentk_vctc.setText(LoginActivity.taiKhoan.getTENTK());
        if (LoginActivity.taiKhoan.getHINHANH() != null) {
            byte[] hinhAnh = LoginActivity.taiKhoan.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
            img_tk_vctc.setImageBitmap(bitmap);
        } else {
            img_tk_vctc.setImageResource(R.drawable.user);
        }
    }

    private void Events() {
        btn_choingay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VuiCungTaiChinhActivity.this, Animation_Vong1.class));
            }
        });
        btn_bangxephang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VuiCungTaiChinhActivity.this, XepHang_Activity.class));
            }
        });
        btnbanbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VuiCungTaiChinhActivity.this, BanBeActivity.class));
            }
        });
        quaylai_vctc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VuiCungTaiChinhActivity.this, HomeActivity.class));
            }
        });
        btn_huongdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VuiCungTaiChinhActivity.this, HuongDan_Activity.class));
            }
        });
    }


    private void AnhXa() {
        quaylai_vctc = findViewById(R.id.quaylai_vctc);
        tentk_vctc = findViewById(R.id.tentk_vctc);
        img_tk_vctc = findViewById(R.id.img_tk_vctc);
        btn_choingay = findViewById(R.id.btn_choingay);
        btn_huongdan = findViewById(R.id.btn_huongdan);
        btnbanbe = findViewById(R.id.btnbanbe);
        btn_bangxephang = findViewById(R.id.btn_bangxephang);
    }
}
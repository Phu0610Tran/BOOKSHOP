package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshop.ActivityUser.MainActivity;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.Models.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class XepHang_Activity extends AppCompatActivity {
    ImageView img_bxh_top1,img_bxh_top2,img_bxh_top3,img_bxh_top4,img_bxh_top5,img_bxh_top6,img_bxh_top7,quaylai_bxh;
    TextView ten_bxh_top1,ten_bxh_top2,ten_bxh_top3,ten_bxh_top4,ten_bxh_top5,ten_bxh_top6,ten_bxh_top7;
    TextView diem_bxh_top1,diem_bxh_top2,diem_bxh_top3,diem_bxh_top4,diem_bxh_top5,diem_bxh_top6,diem_bxh_top7;
    List<TaiKhoan> taiKhoanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xep_hang);
        AnhXa();
        taiKhoanList = GetData();
        SetData();
    }
    private void SetData() {
        byte[] hinhAnh = taiKhoanList.get(0).getHINHANH();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
        img_bxh_top1.setImageBitmap(bitmap);
        ten_bxh_top1.setText(taiKhoanList.get(0).getTENTK());
        diem_bxh_top1.setText(String.valueOf(taiKhoanList.get(0).getDIEM()) + " Điểm ");
        // top 2
        byte[] hinhAnh1 = taiKhoanList.get(1).getHINHANH();
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(hinhAnh1,0, hinhAnh1.length);
        img_bxh_top2.setImageBitmap(bitmap1);
        ten_bxh_top2.setText(taiKhoanList.get(1).getTENTK());
        diem_bxh_top2.setText(String.valueOf(taiKhoanList.get(1).getDIEM())+ " Điểm ");
        // top 3
        byte[] hinhAnh2 = taiKhoanList.get(2).getHINHANH();
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(hinhAnh2,0, hinhAnh2.length);
        img_bxh_top3.setImageBitmap(bitmap2);
        ten_bxh_top3.setText(taiKhoanList.get(2).getTENTK());
        diem_bxh_top3.setText(String.valueOf(taiKhoanList.get(2).getDIEM())+ " Điểm ");

        // top 4
        byte[] hinhAnh3 = taiKhoanList.get(3).getHINHANH();
        Bitmap bitmap3 = BitmapFactory.decodeByteArray(hinhAnh3,0, hinhAnh3.length);
        img_bxh_top4.setImageBitmap(bitmap3);
        ten_bxh_top4.setText(taiKhoanList.get(3).getTENTK());
        diem_bxh_top4.setText(String.valueOf(taiKhoanList.get(3).getDIEM())+ " Điểm ");
        // top 5
        byte[] hinhAnh4 = taiKhoanList.get(4).getHINHANH();
        Bitmap bitmap4 = BitmapFactory.decodeByteArray(hinhAnh4,0, hinhAnh4.length);
        img_bxh_top5.setImageBitmap(bitmap4);
        ten_bxh_top5.setText(taiKhoanList.get(4).getTENTK());
        diem_bxh_top5.setText(String.valueOf(taiKhoanList.get(4).getDIEM())+ " Điểm ");
        // top 6
        byte[] hinhAnh5 = taiKhoanList.get(5).getHINHANH();
        Bitmap bitmap5 = BitmapFactory.decodeByteArray(hinhAnh5,0, hinhAnh5.length);
        img_bxh_top6.setImageBitmap(bitmap5);
        ten_bxh_top6.setText(taiKhoanList.get(5).getTENTK());
        diem_bxh_top6.setText(String.valueOf(taiKhoanList.get(5).getDIEM())+ " Điểm ");
        // top 7
        byte[] hinhAnh6 = taiKhoanList.get(6).getHINHANH();
        Bitmap bitmap6 = BitmapFactory.decodeByteArray(hinhAnh6,0, hinhAnh6.length);
        img_bxh_top7.setImageBitmap(bitmap6);
        ten_bxh_top7.setText(taiKhoanList.get(6).getTENTK());
        diem_bxh_top7.setText(String.valueOf(taiKhoanList.get(6).getDIEM())+ " Điểm ");


    }
    private List<TaiKhoan> GetData() {
        List<TaiKhoan> list = new ArrayList<>();
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM TAIKHOAN ORDER BY DIEM DESC");
        while (cursor.moveToNext()){
            list.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getBlob(8),
                    cursor.getInt(9)));
        }
        return list;
    }

    private void AnhXa() {
        quaylai_bxh = findViewById(R.id.quaylai_bxh);
        quaylai_bxh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        img_bxh_top1 = findViewById(R.id.img_bxh_top1);
        img_bxh_top2 = findViewById(R.id.img_bxh_top2);
        img_bxh_top3 = findViewById(R.id.img_bxh_top3);
        img_bxh_top4 = findViewById(R.id.img_bxh_top4);
        img_bxh_top5 = findViewById(R.id.img_bxh_top5);
        img_bxh_top6 = findViewById(R.id.img_bxh_top6);
        img_bxh_top7 = findViewById(R.id.img_bxh_top7);

        ten_bxh_top1 = findViewById(R.id.ten_bxh_top1);
        ten_bxh_top2 = findViewById(R.id.ten_bxh_top2);
        ten_bxh_top3 = findViewById(R.id.ten_bxh_top3);
        ten_bxh_top4 = findViewById(R.id.ten_bxh_top4);
        ten_bxh_top5 = findViewById(R.id.ten_bxh_top5);
        ten_bxh_top6 = findViewById(R.id.ten_bxh_top6);
        ten_bxh_top7 = findViewById(R.id.ten_bxh_top7);

        diem_bxh_top1 = findViewById(R.id.diem_bxh_top1);
        diem_bxh_top2 = findViewById(R.id.diem_bxh_top2);
        diem_bxh_top3 = findViewById(R.id.diem_bxh_top3);
        diem_bxh_top4 = findViewById(R.id.diem_bxh_top4);
        diem_bxh_top5 = findViewById(R.id.diem_bxh_top5);
        diem_bxh_top6 = findViewById(R.id.diem_bxh_top6);
        diem_bxh_top7 = findViewById(R.id.diem_bxh_top7);
    }
}
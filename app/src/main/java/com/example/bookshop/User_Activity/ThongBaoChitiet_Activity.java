package com.example.bookshop.User_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshop.Adapter.ThongBaoChiTietAdapter;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.Models.ThongBao;
import com.example.bookshop.R;

import java.util.ArrayList;

public class ThongBaoChitiet_Activity extends AppCompatActivity {
    GridView gridview_bangtin;
    ArrayList<ThongBao> thongBaoArrayList;
    ThongBaoChiTietAdapter adapter;
    ImageView quaylaibantin;
    int idtb,notification;
    TextView title_qlhd;
    String tieude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_chitiet);
        Intent intent = getIntent();

        idtb = intent.getIntExtra("thongbaoct",123);
        notification = intent.getIntExtra("notification",2);
        tieude = intent.getStringExtra("tieude");
        AnhXa();

        thongBaoArrayList = new ArrayList<>();
        adapter = new ThongBaoChiTietAdapter(ThongBaoChitiet_Activity.this,R.layout.bangtin_layout,thongBaoArrayList);
        gridview_bangtin.setAdapter(adapter);
        if(idtb == 123)
        {
            GetData(notification);
            TrangChuFragment.database.XoaThongBao(
                    LoginActivity.taiKhoan.getMATK(),
                    tieude);
        }
        else
        {
            GetData(idtb);
        }

    }

    private void GetData(int IDTB) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT IDTB,TIEUDE,NOIDUNG,DATE,HINHANH,THICH,KHONGTHICH " +
                "FROM THONGBAO WHERE IDTB = " + IDTB);
        thongBaoArrayList.clear();
        while (cursor.moveToNext())
        {
            thongBaoArrayList.add(new ThongBao(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4),
                    cursor.getInt(5),
                    cursor.getInt(6)
            ));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        if(idtb == 123)
        {
            GetData(notification);
        }
        else
        {
            GetData(idtb);
        }
        super.onStart();
    }

    private void AnhXa() {
        quaylaibantin = findViewById(R.id.ibtnExit_thongbao);
        quaylaibantin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThongBaoChitiet_Activity.this, HomeActivity.class);
                intent.putExtra("thongbao", R.id.nav_thongbao);
                startActivity(intent);
            }
        });
        gridview_bangtin = findViewById(R.id.gridview_bangtin);
        title_qlhd = findViewById(R.id.title_qlhd);
        title_qlhd.setText("Thông Báo");

    }
}
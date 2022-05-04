package com.example.bookshop.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshop.Adapter.ThongBaoAdapter;
import com.example.bookshop.Adapter.ThongBaoChiTietAdapter;
import com.example.bookshop.Models.ThongBao;
import com.example.bookshop.R;
import com.example.bookshop.User_Activity.HomeActivity;
import com.example.bookshop.User_Activity.ThongBaoChitiet_Activity;
import com.example.bookshop.User_Fragment.TrangChuFragment;

import java.util.ArrayList;

public class QL_BaiViet_Activity extends AppCompatActivity {
    GridView gridview_bangtin;
    ArrayList<ThongBao> thongBaoArrayList;
    ThongBaoChiTietAdapter adapter;
    ImageView quaylaibantin;
    TextView title_qlhd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_chitiet);
        AnhXa();

        thongBaoArrayList = new ArrayList<>();
        adapter = new ThongBaoChiTietAdapter(QL_BaiViet_Activity.this,R.layout.bangtin_layout,thongBaoArrayList);
        gridview_bangtin.setAdapter(adapter);
        GetData();
        gridview_bangtin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QL_BaiViet_Activity.this,SuaBai_Activity.class);
                intent.putExtra("position",i);
                startActivity(intent);
            }
        });
    }
    private void GetData() {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT IDTB,TIEUDE,NOIDUNG,DATE,HINHANH,THICH,KHONGTHICH " +
                "FROM THONGBAO ORDER BY IDTB DESC  " );
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
        GetData();
        super.onStart();
    }

    private void AnhXa() {
        quaylaibantin = findViewById(R.id.ibtnExit_thongbao);
        quaylaibantin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        gridview_bangtin = findViewById(R.id.gridview_bangtin);
        title_qlhd = findViewById(R.id.title_qlhd);
        title_qlhd.setText("Bài Viết");

    }
}
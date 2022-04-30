package com.example.bookshop.Events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.Adapter.TaiKhoanAdapter;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.R;

import java.util.ArrayList;

public class BanBeActivity extends AppCompatActivity {
    ArrayList<TaiKhoan> taiKhoanArrayList;
    GridView gridviewBanBe;
    TaiKhoanAdapter adapter;
    ImageView quaylai_banbe,search_banbe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_be);
        gridviewBanBe  = findViewById(R.id.gridviewBanBe);
        taiKhoanArrayList = new ArrayList<>();
        adapter = new TaiKhoanAdapter(BanBeActivity.this,R.layout.layout_banbe,taiKhoanArrayList);
        gridviewBanBe.setAdapter(adapter);
        gridviewBanBe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BanBeActivity.this, ChoiNgayActivity.class);
                intent.putExtra("id",i);

                startActivity(intent);
            }
        });
        GetData();
        quaylai_banbe = findViewById(R.id.quaylai_banbe);
        quaylai_banbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BanBeActivity.this, VuiCungTaiChinhActivity.class));
            }
        });
        search_banbe = findViewById(R.id.search_banbe);
        search_banbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BanBeActivity.this, TimKiemBanBe_Activity.class));
            }
        });
    }

    private void GetData() {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT IDTAIKHOAN,TENTAIKHOAN,MATKHAU,SDT,EMAIL,NGAYSINH,LOAITK,DIACHI,HINHANH,DIEM FROM BANBE A, TAIKHOAN B WHERE A.IDTK_CHINH = "
                + LoginActivity.taiKhoan.getMATK()  + " AND A.IDTK_BAN = B.IDTAIKHOAN ");
        taiKhoanArrayList.clear();
        while (cursor.moveToNext())
        {
            taiKhoanArrayList.add(new TaiKhoan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getBlob(8),
                    cursor.getInt(9)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}
package com.example.bookshop.User_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookshop.Adapter.CTHoaDonAdapter;
import com.example.bookshop.Adapter.DanhGiaAdapter;
import com.example.bookshop.Models.CTHoaDon;
import com.example.bookshop.R;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;

public class DanhGiaActivity extends AppCompatActivity {
    ListView listview_danhsachdg;
    ImageView ibtnExit_danhgia;
    ArrayList<CTHoaDon> cthoaDonArrayList;
    DanhGiaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_gia);
        AnhXa();
        cthoaDonArrayList = new ArrayList<>();
        adapter = new DanhGiaAdapter(DanhGiaActivity.this, R.layout.danhgia, cthoaDonArrayList);
        listview_danhsachdg.setAdapter(adapter);
        registerForContextMenu(listview_danhsachdg);
        listview_danhsachdg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DanhGiaActivity.this,Products_information_activity.class);

                intent.putExtra("id", DanhGiaAdapter.ListCTHoaDon.get(i).getIDSANPHAM());
                startActivity(intent);
            }
        });
        GetData();
    }

    private void AnhXa() {
        listview_danhsachdg = findViewById(R.id.listview_danhsachdg);
        ibtnExit_danhgia = findViewById(R.id.ibtnExit_danhgia);
        ibtnExit_danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void GetData() {
        Cursor cursor = TrangChuFragment.database.Getdata(
                "SELECT A.IDCTHOADON,A.IDSANPHAM,A.IDTAIKHOAN,A.TENSANPHAM,A.SOLUONG,A.THANHTIEN FROM CHITIETHOADON A,HOADON B " +
                        "WHERE A.IDCTHOADON = B.IDCTHHOADON AND B.TINHTRANG = 6 AND A.IDTAIKHOAN = " + LoginActivity.taiKhoan.getMATK());
        cthoaDonArrayList.clear();
        while (cursor.moveToNext())
        {
            cthoaDonArrayList.add(new CTHoaDon(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
            ));
        }
        adapter.notifyDataSetChanged();
    }
}
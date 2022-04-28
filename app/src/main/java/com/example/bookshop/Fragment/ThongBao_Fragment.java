package com.example.bookshop.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.bookshop.ActivityUser.HomeActivity;
import com.example.bookshop.ActivityUser.LoginActivity;
import com.example.bookshop.ActivityUser.ThongBaoChitiet_Activity;
import com.example.bookshop.Adapter.ThongBaoAdapter;
import com.example.bookshop.Models.ThongBao;
import com.example.bookshop.R;

import java.util.ArrayList;


public class ThongBao_Fragment extends Fragment {


    View view;
    GridView gridviewThongBao;
    ArrayList<ThongBao> thongBaoArrayList;
    ThongBaoAdapter adapter;
    ImageView quaylaibantin;
    public ThongBao_Fragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thong_bao_, container, false);
        AnhXa();
        thongBaoArrayList = new ArrayList<>();
        adapter = new ThongBaoAdapter(ThongBao_Fragment.this,R.layout.thongbao_layout,thongBaoArrayList);
        gridviewThongBao.setAdapter(adapter);
        gridviewThongBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(TrangChuFragment.database.tieudeSOSANH(
                        LoginActivity.taiKhoan.getMATK(),
                        ThongBaoAdapter.thongBaoList.get(i).getTIEUDE()))
                {
                    TrangChuFragment.database.XoaThongBao(
                            LoginActivity.taiKhoan.getMATK(),
                            ThongBaoAdapter.thongBaoList.get(i).getTIEUDE());
                }


                Intent intent = new Intent(getActivity(), ThongBaoChitiet_Activity.class);
                intent.putExtra("thongbaoct", i);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT IDTB,TIEUDE,NOIDUNG,DATE,HINHANH,THICH,KHONGTHICH " +
                "FROM THONGBAO ORDER BY IDTB DESC ");
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

    private void AnhXa() {
        gridviewThongBao = view.findViewById(R.id.gridviewThongBao);
    }
}
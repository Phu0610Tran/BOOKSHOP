package com.example.bookshop.User_Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.bookshop.Adapter.SanPhamAdapter;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.User_Activity.Products_information_activity;
import com.example.bookshop.R;

import java.util.ArrayList;

public class PythonFragment extends Fragment {

    private View view;

    GridView gridView_SanPham;
    ArrayList<SanPham> sanPhamArrayList;
    SanPhamAdapter adapter;

    public PythonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_python, container, false);

        gridView_SanPham = (GridView) view.findViewById(R.id.gridviewSanPham);
        sanPhamArrayList = new ArrayList<>();
        adapter = new SanPhamAdapter(PythonFragment.this, R.layout.product_layout, sanPhamArrayList);
        gridView_SanPham.setAdapter(adapter);
        gridView_SanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Products_information_activity.class);


                intent.putExtra("iddanhmucsp",i);
                startActivity(intent);

            }
        });
        registerForContextMenu(gridView_SanPham);

        GetData();
        return view;
    }
    private void GetData() {
        //get data
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT * FROM SANPHAM WHERE IDDANHMUC = 1");
        sanPhamArrayList.clear();
        while (cursor.moveToNext())
        {
            sanPhamArrayList.add(new SanPham(
                    cursor.getInt(0),
                    cursor.getBlob(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getString(5)
            ));
        }
        adapter.notifyDataSetChanged();
    }

}
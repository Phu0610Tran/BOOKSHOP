package com.example.bookshop.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.bookshop.Adapter.SanPhamAdapter;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.Data.Database;
import com.example.bookshop.ActivityUser.Products_information_activity;
import com.example.bookshop.R;

import java.util.ArrayList;


public class WebFragment extends Fragment {

    private View view;

    private static Database database;
    GridView gridView_SanPham;
    ArrayList<SanPham> sanPhamArrayList;
    SanPhamAdapter adapter;


    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_web, container, false);

        database = new Database(getActivity(),"BookShop",null,2);

        gridView_SanPham = (GridView) view.findViewById(R.id.gridviewSanPham);
        sanPhamArrayList = new ArrayList<>();
        adapter = new SanPhamAdapter(WebFragment.this, R.layout.product_layout, sanPhamArrayList);
        gridView_SanPham.setAdapter(adapter);
        gridView_SanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Products_information_activity.class);


                intent.putExtra("id",i);
                startActivity(intent);

            }
        });
        registerForContextMenu(gridView_SanPham);

        GetData();

        return view;
    }

    private void GetData() {
        //get data
        Cursor cursor = database.Getdata("SELECT * FROM SANPHAM WHERE IDDANHMUC = 4");
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
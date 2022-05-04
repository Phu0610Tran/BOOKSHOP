package com.example.bookshop.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bookshop.Adapter.CategoryAdapter;
import com.example.bookshop.Adapter.HoaDonAdapter;
import com.example.bookshop.Admin_Fragment.HoaDonAdmin;
import com.example.bookshop.Models.Category;
import com.example.bookshop.Models.HoaDon;
import com.example.bookshop.R;
import com.example.bookshop.User_Fragment.TrangChuFragment;

import java.util.ArrayList;

public class SuaHoaDon_Activity extends AppCompatActivity {
    ArrayList<Category> listCategory;
    ArrayList<Category> list;
    CategoryAdapter categoryAdapter;
    int Danhmuc;
    String tendanhmuc=null;
    Button buttonAdd_hoadon,buttonHuy_hoadon;
    EditText edt_IDHOADON,edt_tongtien,edt_idct_hoadon,edt_ghichu,edt_diachi,edt_tinhtrang,edt_danhmuc;
    Spinner spinner_hoadon;
    ImageView quaylai_HoaDon;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_hoa_don);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",123);
        AnhXa();
        Getdata();
        listCategory = getListCategory();
        categoryAdapter = new CategoryAdapter(SuaHoaDon_Activity.this, R.layout.item_select, listCategory);
        spinner_hoadon.setAdapter(categoryAdapter);
        Danhmuc = 0;
        spinner_hoadon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Danhmuc = categoryAdapter.getItem(position).getIDcategory();
                tendanhmuc = categoryAdapter.getItem(position).getName();
                edt_danhmuc.setText(Danhmuc+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private ArrayList<Category> getListCategory() {
        list = new ArrayList<>();
        list.clear();
        list.add(new Category(
                        " Chờ duyệt ",
                        1
                )
        );
        list.add(new Category(
                        " Chờ lấy hàng ",
                        2
                )
        );
        list.add(new Category(
                        " Đang giao ",
                        3
                )
        );
        list.add(new Category(
                        " Đã giao ",
                        4
                )
        );
        list.add(new Category(
                        " Chờ nhận hàng ",
                        5
                )
        );
        list.add(new Category(
                        " Đã nhận hàng ",
                        6
                )
        );



        return list;
    }

    private void Getdata() {
        HoaDon hoaDon = HoaDonAdapter.ListHoaDon.get(id);
        edt_IDHOADON.setText(hoaDon.getIDHOADON() + "");
        edt_tongtien.setText(hoaDon.getTONGTIEN()+"");
        edt_idct_hoadon.setText(hoaDon.getIDCTHOADON()+"");
        edt_diachi.setText(hoaDon.getDIACHI());
        edt_ghichu.setText(hoaDon.getGHICHU());
        edt_tinhtrang.setText(hoaDon.getTINHTRANG()+"");
        edt_danhmuc.setEnabled(false);
        edt_IDHOADON.setEnabled(false);
        edt_tongtien.setEnabled(false);
        edt_idct_hoadon.setEnabled(false);
        edt_tinhtrang.setEnabled(false);

        buttonHuy_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buttonAdd_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChuFragment.database.UPDATE_HOADON(hoaDon.getIDHOADON(),
                        edt_ghichu.getText().toString(),
                        edt_diachi.getText().toString(),
                        Integer.valueOf(edt_danhmuc.getText().toString()));
                Toast.makeText(SuaHoaDon_Activity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        quaylai_HoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void AnhXa() {
        edt_danhmuc = findViewById(R.id.edt_danhmuc);
        quaylai_HoaDon = findViewById(R.id.quaylai_HoaDon);
        buttonHuy_hoadon = findViewById(R.id.buttonHuy_hoadon);
        buttonAdd_hoadon = findViewById(R.id.buttonAdd_hoadon);
        edt_IDHOADON = findViewById(R.id.edt_IDHOADON);
        edt_tongtien = findViewById(R.id.edt_tongtien);
        edt_idct_hoadon = findViewById(R.id.edt_idct_hoadon);
        edt_ghichu = findViewById(R.id.edt_ghichu);
        edt_diachi = findViewById(R.id.edt_diachi);
        edt_tinhtrang = findViewById(R.id.edt_tinhtrang);
        spinner_hoadon = findViewById(R.id.spinner_hoadon);

    }
}
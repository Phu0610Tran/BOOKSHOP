package com.example.bookshop.User_Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.Adapter.BinhLuanAdapter;
import com.example.bookshop.Adapter.BinhLuanTBAdapter;
import com.example.bookshop.Adapter.ThongBaoChiTietAdapter;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.Models.BinhLuan;
import com.example.bookshop.Models.ThongBao;
import com.example.bookshop.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BinhLuanActivity extends AppCompatActivity {
    int position;
    ArrayList<BinhLuan> listBL;
    ThongBao thongbao;
    BinhLuanTBAdapter binhLuanAdapter;

    TextView Solike,Sodislike,socmt;
    Button btn_GuiBl;
    ImageView quaylai_binhluan;
    NestedScrollView scrollV;
    RecyclerView rec_Binhluan;
    EditText edt_noidungbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);
        listBL = new ArrayList<>();
        Intent intent = getIntent();
        position = intent.getIntExtra("position",1);
        thongbao = ThongBaoChiTietAdapter.thongBaoList.get(position);
        AnhXa();
        GetdataBL();
        Events();
    }

    private void AnhXa() {
        Solike = findViewById(R.id.Solike);
        Solike.setText(String.valueOf(thongbao.getTHICH()));
        Sodislike = findViewById(R.id.Sodislike);
        Sodislike.setText(String.valueOf(thongbao.getKHONGTHICH()));

        socmt = findViewById(R.id.socmt);
        socmt.setText(String.valueOf(TrangChuFragment.database.demsoBL(thongbao.getIDTB())));
        quaylai_binhluan = findViewById(R.id.quaylai_binhluan);
        rec_Binhluan = findViewById(R.id.rec_Binhluan);
        btn_GuiBl = findViewById(R.id.btn_GuiBl);
        scrollV = findViewById(R.id.scrollV);
        edt_noidungbl=findViewById(R.id.edt_noidungbl);
    }

    @Override
    protected void onStart() {
        GetdataBL();
        socmt.setText(String.valueOf(TrangChuFragment.database.demsoBL(thongbao.getIDTB())));
        Solike.setText(String.valueOf(thongbao.getTHICH()));
        Sodislike.setText(String.valueOf(thongbao.getKHONGTHICH()));
        super.onStart();
    }

    private void GetdataBL() {
        thongbao = ThongBaoChiTietAdapter.thongBaoList.get(position);
        listBL = TrangChuFragment.database.LayBinhLuanTB(thongbao.getIDTB());

        binhLuanAdapter = new BinhLuanTBAdapter(listBL);
        rec_Binhluan.setAdapter(binhLuanAdapter);
        rec_Binhluan.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    private void Events() {
        quaylai_binhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        edt_noidungbl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                scrollV.scrollTo(0,1000);
            }
        });
        btn_GuiBl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_noidungbl.getText().length() > 0 || edt_noidungbl.getText().toString().equals("")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
                    String currentDateandTime = sdf.format(new Date());
                    thongbao = ThongBaoChiTietAdapter.thongBaoList.get(position);
                    TrangChuFragment.database.ThemBL(LoginActivity.taiKhoan.getMATK(), thongbao.getIDTB(),edt_noidungbl.getText().toString(),currentDateandTime);
                    listBL.add(0, new BinhLuan(
                            LoginActivity.taiKhoan.getMATK(),LoginActivity.taiKhoan.getHINHANH(),
                            edt_noidungbl.getText().toString(),currentDateandTime
                    ));
//                    Log.e("Tag",String.valueOf(listBL.size()));
                    binhLuanAdapter.notifyItemInserted(0);
                    edt_noidungbl.setText("");
                    Toast.makeText(BinhLuanActivity.this, " Bình luận thành công ", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(BinhLuanActivity.this, " Vui lòng nhập nội dung!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}
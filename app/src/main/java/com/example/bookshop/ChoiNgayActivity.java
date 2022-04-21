package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.ActivityUser.LoginActivity;
import com.example.bookshop.ActivityUser.MainActivity;
import com.example.bookshop.Adapter.TaiKhoanAdapter;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.Models.Vong;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChoiNgayActivity extends AppCompatActivity {
    TaiKhoan taiKhoan;
    ImageButton btn_vong1,btn_vong2,btn_vong3,btn_vong4,btn_vong5;
    TextView txt_vong1,txt_vong2,txt_vong3,txt_vong4,txt_vong5,tentk_choingay,diem_tk_choingay,themban_choingay;
    ImageView quaylai_choingay;
    CircleImageView img_tk_choingay;
    ImageView img_nextvong;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_ngay);
        AnhXa();
        Btn_setEnabled();


        Intent intent = getIntent();
        id = intent.getIntExtra("id",123);
        if (id==123){
            CheckThongTin(LoginActivity.taiKhoan.getMATK());
            quaylai_choingay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ChoiNgayActivity.this, VuiCungTaiChinhActivity.class));
                }
            });
        }else
        {
            taiKhoan = TaiKhoanAdapter.taiKhoanList.get(id);
            CheckThongTin(taiKhoan.getMATK());
            Btn_setEnabled();
            quaylai_choingay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            img_nextvong.setImageResource(R.drawable.next_vonghien);
            img_nextvong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(ChoiNgayActivity.this, Animation_Vong2.class);
                    intent1.putExtra("id",id);
                    startActivity(intent1);

                }
            });
            if(TrangChuFragment.database.TonTaiBanBe(LoginActivity.taiKhoan.getMATK(),taiKhoan.getMATK())){
                themban_choingay.setText("Thêm Bạn");
                themban_choingay.setBackgroundResource(R.drawable.custom_banbe);

                themban_choingay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TrangChuFragment.database.ThemBan(LoginActivity.taiKhoan.getMATK(),taiKhoan.getMATK());
                        Toast.makeText(ChoiNgayActivity.this, " Thêm thành công ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ChoiNgayActivity.this, BanBeActivity.class));
                    }
                });
            }else {
                themban_choingay.setText("Xóa Bạn");
                themban_choingay.setBackgroundResource(R.drawable.custom_banbe);
                themban_choingay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TrangChuFragment.database.XoaBan(LoginActivity.taiKhoan.getMATK(),taiKhoan.getMATK());
                        Toast.makeText(ChoiNgayActivity.this, " Xóa thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ChoiNgayActivity.this,BanBeActivity.class));
                    }
                });
            }

        }



        Event();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    private void CheckThongTin(int id) {
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT TENTAIKHOAN,HINHANH,DIEM FROM TAIKHOAN WHERE IDTAIKHOAN = " + id);
        cursor.moveToNext();
        tentk_choingay.setText(cursor.getString(0));
        if(String.valueOf(cursor.getInt(2)).length() == 0 )
        {
            diem_tk_choingay.setText("0");
        }else {
            diem_tk_choingay.setText(String.valueOf(cursor.getInt(2)));
        }

        if (cursor.getBlob(1)!=null){
            byte[] hinhAnh = cursor.getBlob(1);
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_tk_choingay.setImageBitmap(bitmap);
        }else {
            img_tk_choingay.setImageResource(R.drawable.user);
        }
        checkvong(id);
    }


    private void Btn_setEnabled() {
        btn_vong1.setEnabled(false);
        btn_vong2.setEnabled(false);
        btn_vong3.setEnabled(false);
        btn_vong4.setEnabled(false);
        btn_vong5.setEnabled(false);
        img_nextvong.setImageResource(R.drawable.next_vongan);
        img_nextvong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChoiNgayActivity.this, "Bạn hãy hoàn thành hết hành trình nhé!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Event() {

        btn_vong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiNgayActivity.this, TracnghiemActivity.class);
                intent.putExtra("vong",1.1F);
                startActivity(intent);
            }
        });
        btn_vong2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiNgayActivity.this,TracnghiemActivity.class);
                intent.putExtra("vong",1.2F);
                startActivity(intent);

            }
        });
        btn_vong3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiNgayActivity.this,TracnghiemActivity.class);
                intent.putExtra("vong",1.3F);
                startActivity(intent);
            }
        });
        btn_vong4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiNgayActivity.this,TracnghiemActivity.class);
                intent.putExtra("vong",1.4F);
                startActivity(intent);
            }
        });
        btn_vong5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiNgayActivity.this,TracnghiemActivity.class);
                intent.putExtra("vong",1.5F);
                startActivity(intent);
            }


        });

    }

    private void checkvong(int id) {
        List<Vong> a = new ArrayList();
        a.clear();
        Cursor cursor = TrangChuFragment.database.Getdata("SELECT VONG,SOSAO FROM VONGCHINH WHERE IDTK = " + id);
        while (cursor.moveToNext()){
            a.add(new Vong(
                    Float.valueOf(cursor.getString(0)),
                    cursor.getInt(1)
            ));

        }
        //---------------------------
        try {
            checksaovong1(a.get(0).getSOSAO());
            txt_vong1.setText("1");
        }catch (Exception e){
            btn_vong1.setImageResource(R.drawable.phihanhgia_choingay);
            btn_vong1.setEnabled(true);
            return;
        }
        //---------------------------
        try {
            checksaovong2(a.get(1).getSOSAO());
            txt_vong2.setText("2");
        }catch (Exception e){
            btn_vong2.setImageResource(R.drawable.phihanhgia_choingay);
            btn_vong2.setEnabled(true);
            return;
        }
        //---------------------------
        try {
            checksaovong3(a.get(2).getSOSAO());
            txt_vong3.setText("3");
        }catch (Exception e){
            btn_vong3.setImageResource(R.drawable.phihanhgia_choingay);
            btn_vong3.setEnabled(true);
            return;
        };
        //---------------------------
        try {
            checksaovong4(a.get(3).getSOSAO());
            txt_vong4.setText("4");
        }catch (Exception e){
            btn_vong4.setImageResource(R.drawable.phihanhgia_choingay);
            btn_vong4.setEnabled(true);
            return;
        };
        //---------------------------
        try {
            checksaovong5(a.get(4).getSOSAO());
            txt_vong5.setText("5");
            img_nextvong.setImageResource(R.drawable.next_vonghien);
            img_nextvong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ChoiNgayActivity.this, Animation_Vong2.class));
                }
            });
        }catch (Exception e){
            btn_vong5.setImageResource(R.drawable.phihanhgia_choingay);
            btn_vong5.setEnabled(true);
            return;
        };



    }

    private void checksaovong2(int sosao) {
        if(sosao == 0){
            btn_vong2.setImageResource(R.drawable.khongsao);
        }else if(sosao == 1){
            btn_vong2.setImageResource(R.drawable.motsao);
        }else if(sosao == 2){
            btn_vong2.setImageResource(R.drawable.haisao);
        }else{
            btn_vong2.setImageResource(R.drawable.basao);
        }
    }
    private void checksaovong3(int sosao) {
        if(sosao == 0){
            btn_vong3.setImageResource(R.drawable.khongsao);
        }else if(sosao == 1){
            btn_vong3.setImageResource(R.drawable.motsao);
        }else if(sosao == 2){
            btn_vong3.setImageResource(R.drawable.haisao);
        }else{
            btn_vong3.setImageResource(R.drawable.basao);
        }
    }
    private void checksaovong4(int sosao) {
        if(sosao == 0){
            btn_vong4.setImageResource(R.drawable.khongsao);
        }else if(sosao == 1){
            btn_vong4.setImageResource(R.drawable.motsao);
        }else if(sosao == 2){
            btn_vong4.setImageResource(R.drawable.haisao);
        }else{
            btn_vong4.setImageResource(R.drawable.basao);
        }
    }
    private void checksaovong5(int sosao) {
        if(sosao == 0){
            btn_vong5.setImageResource(R.drawable.khongsao);
        }else if(sosao == 1){
            btn_vong5.setImageResource(R.drawable.motsao);
        }else if(sosao == 2){
            btn_vong5.setImageResource(R.drawable.haisao);
        }else{
            btn_vong5.setImageResource(R.drawable.basao);
        }

    }
    private void checksaovong1(int sosao) {
        if(sosao == 0){
            btn_vong1.setImageResource(R.drawable.khongsao);
        }else if(sosao == 1){
            btn_vong1.setImageResource(R.drawable.motsao);
        }else if(sosao == 2){
            btn_vong1.setImageResource(R.drawable.haisao);
        }else{
            btn_vong1.setImageResource(R.drawable.basao);
        }
    }


    private void AnhXa() {
        img_nextvong = findViewById(R.id.img_nextvong);
        themban_choingay = findViewById(R.id.themban_choingay);
        quaylai_choingay = findViewById(R.id.quaylai_choingay);
        diem_tk_choingay = findViewById(R.id.diem_tk_choingay);
        tentk_choingay = findViewById(R.id.tentk_choingay);
        img_tk_choingay = findViewById(R.id.img_tk_choingay);
        txt_vong5 = findViewById(R.id.txt_vong5);
        txt_vong4 = findViewById(R.id.txt_vong4);
        txt_vong3 = findViewById(R.id.txt_vong3);
        txt_vong2 = findViewById(R.id.txt_vong2);
        txt_vong1 = findViewById(R.id.txt_vong1);
        btn_vong5 = findViewById(R.id.btn_vong5);
        btn_vong1 = findViewById(R.id.btn_vong1);
        btn_vong2 = findViewById(R.id.btn_vong2);
        btn_vong3 = findViewById(R.id.btn_vong3);
        btn_vong4 = findViewById(R.id.btn_vong4);
    }
}
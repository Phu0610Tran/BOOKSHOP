package com.example.bookshop.ActivityAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class DangBaiActivity extends AppCompatActivity {
    CircleImageView imgtk_dangbai;
    Button btn_dangbai;
    ImageView imghinh_dangbai,imageButtonCamera,imageButtonFolder;
    TextView tentk_dangbai;
    ImageView quaylai_dangbai;
    EditText edt_noidung_dangbai,edt_tieude_dangbai;
    private boolean isEnabled;
    final int REQUEST_CODE_CAMERA=123;
    final int REQUEST_CODE_FOLDER=456;
    boolean checkimage = true,checkimagecam=true;
    List<TaiKhoan> taiKhoanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dang_bai);
        taiKhoanList = new ArrayList<>();
        taiKhoanList = TrangChuFragment.database.LayALLTK();
        Toast.makeText(DangBaiActivity.this, "taikhoan : " + taiKhoanList.size(), Toast.LENGTH_SHORT).show();
        AnhXa();
        Events();
    }
    private void Events() {
        quaylai_dangbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_dangbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        Bitmap bitmap = BitmapFactory.decodeByteArray(LoginActivity.taiKhoan.getHINHANH(),0,LoginActivity.taiKhoan.getHINHANH().length);
//        imgtk_dangbai.setImageBitmap(bitmap);
//        tentk_dangbai.setText(LoginActivity.taiKhoan.getTENTK());
        btn_dangbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyen data image view -> mang byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imghinh_dangbai.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                //thoihian
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                TrangChuFragment.database.Dangbai(
                        edt_noidung_dangbai.getText().toString().trim(),
                        currentDateandTime,
                        hinhAnh,
                        edt_tieude_dangbai.getText().toString().trim()
                );
                for (int i=0;i<taiKhoanList.size();i++)
                {
                    TrangChuFragment.database.DangbaiALL(
                            edt_noidung_dangbai.getText().toString().trim(),
                            taiKhoanList.get(i).getMATK(),
                            edt_tieude_dangbai.getText().toString().trim());
                }
                startActivity(new Intent(DangBaiActivity.this,HomeAdmin.class));
                Toast.makeText(DangBaiActivity.this," Đăng thành công!",Toast.LENGTH_LONG).show();


            }
        });
        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        DangBaiActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );
            }
        });
        imageButtonFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        DangBaiActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER
                );
            }

        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,REQUEST_CODE_CAMERA);
                }else
                {
                    Toast.makeText(DangBaiActivity.this," Bạn không cho phép mở camera", Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_CODE_FOLDER:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_CODE_FOLDER);
                }else
                {
                    Toast.makeText(DangBaiActivity.this," Bạn không cho phép mở folder", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imghinh_dangbai.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imghinh_dangbai.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);


    }
    private void AnhXa() {
        edt_tieude_dangbai = findViewById(R.id.edt_tieude_dangbai);
        quaylai_dangbai = findViewById(R.id.quaylai_dangbai);
        btn_dangbai = findViewById(R.id.btn_dangbai);
        imgtk_dangbai= findViewById(R.id.imgtk_dangbai);
        imghinh_dangbai= findViewById(R.id.imghinh_dangbai);
        imageButtonCamera= findViewById(R.id.imageButtonCamera);
        imageButtonFolder= findViewById(R.id.imageButtonFolder);
        tentk_dangbai= findViewById(R.id.tentk_dangbai);
        edt_noidung_dangbai= findViewById(R.id.edt_noidung_dangbai);
    }
}
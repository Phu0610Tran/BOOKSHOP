package com.example.bookshop.AdminActivity;

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

import com.example.bookshop.Adapter.ThongBaoChiTietAdapter;
import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.Models.ThongBao;
import com.example.bookshop.R;
import com.example.bookshop.User_Fragment.TrangChuFragment;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class SuaBai_Activity extends AppCompatActivity {
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
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_bai);
        taiKhoanList = new ArrayList<>();
        taiKhoanList = TrangChuFragment.database.LayALLTK();
//        Toast.makeText(DangBaiActivity.this, "taikhoan : " + taiKhoanList.size(), Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        position = intent.getIntExtra("position",123);

        AnhXa();
        GetData();
        Events();
    }

    private void GetData() {
        ThongBao thongBao = ThongBaoChiTietAdapter.thongBaoList.get(position);
        // hình ảnh
        byte[] hinhAnh = thongBao.getHINHANHTHONGBAO();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
        imghinh_dangbai.setImageBitmap(bitmap);
        // tiêuđề nội dung
        edt_noidung_dangbai.setText(thongBao.getNOIDUNG());
        edt_tieude_dangbai.setText(thongBao.getTIEUDE());
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
                TrangChuFragment.database.Suabai(
                        edt_noidung_dangbai.getText().toString().trim(),
                        currentDateandTime,
                        hinhAnh,
                        edt_tieude_dangbai.getText().toString().trim()
                );

                if (TrangChuFragment.database.KiemtraTBNEW(edt_tieude_dangbai.getText().toString().trim()))
                {
                    TrangChuFragment.database.SuabaiALL(
                            edt_noidung_dangbai.getText().toString().trim(),
                            edt_tieude_dangbai.getText().toString().trim());
                }
                else
                {
//                    Toast.makeText(SuaBai_Activity.this, "Không có ", Toast.LENGTH_LONG).show();
                }

                startActivity(new Intent(SuaBai_Activity.this,QL_BaiViet_Activity.class));
                Toast.makeText(SuaBai_Activity.this," Sửa thành công!",Toast.LENGTH_LONG).show();


            }
        });
        imageButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        SuaBai_Activity.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                );
            }
        });
        imageButtonFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        SuaBai_Activity.this,
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
                    Toast.makeText(SuaBai_Activity.this," Bạn không cho phép mở camera", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(SuaBai_Activity.this," Bạn không cho phép mở folder", Toast.LENGTH_LONG).show();
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
        edt_tieude_dangbai.setEnabled(false);
        edt_tieude_dangbai.setBackgroundColor(SuaBai_Activity.this.getColor(R.color.chude));
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
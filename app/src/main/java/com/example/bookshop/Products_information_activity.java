package com.example.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.DAO.SanPhamDAO;
import com.example.bookshop.DTO.SanPhamDTO;
import com.example.bookshop.Fragment.TrangChuFragment;

import java.io.ByteArrayOutputStream;

public class Products_information_activity extends AppCompatActivity {

    SanPhamDTO sanPhamDTO;
    TextView name,price,content;
    ImageView imgHinh;
    EditText editTextSL;
    Button btnaddcart;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_information);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1123);
        Anhxa();

        GetDataSP();

        btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();


                int SL = Integer.parseInt(editTextSL.getText().toString());
                sanPhamDTO = SanPhamDAO.sanPhamDTOList.get(id);
                TrangChuFragment.database.SPGH(
                        LoginActivity.taiKhoanDTO.getMATK(),
                        hinhAnh,
                        sanPhamDTO.getMaSP(),
                        sanPhamDTO.getTenSP(),
                        SL,
                        SL * sanPhamDTO.getGiaSP()
                        );

//                Toast.makeText(getApplicationContext()," Đã thêm vào giỏ hàng" + hinhAnh,Toast.LENGTH_LONG).show();
            }
        });

    }

    private void Anhxa() {
        name = (TextView) findViewById(R.id.product_name_CT);
        price = (TextView) findViewById(R.id.product_content_CT);
        content = (TextView) findViewById(R.id.product_price_CT);
        imgHinh = (ImageView) findViewById(R.id.product_image_CT);
        btnaddcart= (Button) findViewById(R.id.btnadd_addtocart_CT);
        editTextSL = (EditText) findViewById(R.id.product_SL_CT);
    }

    private void GetDataSP() {
        //get data
        sanPhamDTO = SanPhamDAO.sanPhamDTOList.get(id);
        String ten = sanPhamDTO.getTenSP();
        String mota = sanPhamDTO.getMotaSP();
        String gia = sanPhamDTO.getGiaSP() + " VNĐ";
        name.setText(ten);
        content.setText(mota);
        price.setText(gia);
        byte[] hinhAnh = sanPhamDTO.getImageSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        imgHinh.setImageBitmap(bitmap);


    }
}
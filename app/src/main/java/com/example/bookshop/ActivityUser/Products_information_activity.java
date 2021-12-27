package com.example.bookshop.ActivityUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookshop.Adapter.BinhLuanAdapter;
import com.example.bookshop.Adapter.SanPhamAdapter;
import com.example.bookshop.Adapter.TimKiemAdapter;
import com.example.bookshop.Models.BinhLuan;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.R;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Products_information_activity extends AppCompatActivity {

    SanPham sanPham;
    TextView name,price,content,noidung_ctsp;
    ImageView imgHinh,img_hethang;
    EditText editTextSL;
    Button btnaddcart,btn_GuiBl;
    ImageButton btn_quaylai;
    int id,idtk;
    BinhLuanAdapter binhLuanAdapter;
    NestedScrollView scrollV;
    RecyclerView recV_chatbox;
    ArrayList<BinhLuan> listBL;
    EditText edt_noidungbl_sanpham;

    @Override
    protected void onStart() {
        if(idtk==2){
            sanPham = SanPhamAdapter.sanPhamList.get(id);
        }else
        {
            sanPham = TimKiemAdapter.sanPhamList.get(idtk);
        }

        listBL = TrangChuFragment.database.LayBinhLuan(sanPham.getMaSP());

        binhLuanAdapter = new BinhLuanAdapter(listBL);
        recV_chatbox.setAdapter(binhLuanAdapter);
        recV_chatbox.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_information);
        listBL = new ArrayList<>();
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1);
        idtk = intent.getIntExtra("idtk",2);
//        Toast.makeText(Products_information_activity.this, " sss : " + id + idtk, Toast.LENGTH_SHORT).show();
        Anhxa();
        Sukien();
        GetDataSP();
        btn_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Products_information_activity.this,HomeActivity.class));
            }
        });
        btnaddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();


                int SL = Integer.parseInt(editTextSL.getText().toString());

                if(idtk==2){
                    sanPham = SanPhamAdapter.sanPhamList.get(id);
                }else
                {
                    sanPham = TimKiemAdapter.sanPhamList.get(idtk);
                }


                if(LoginActivity.taiKhoan.getMATK() == -1)
                {
                    Toast.makeText(Products_information_activity.this, "Bạn phải đăng nhập để mua hàng !", Toast.LENGTH_SHORT).show();
                }else if(  sanPham.getSl_SP() == 1 ){
                    Toast.makeText(Products_information_activity.this, " Sản phẩm hiện đã hết hàng !  " , Toast.LENGTH_SHORT).show();

                }else if( SL > (sanPham.getSl_SP() - 1) ){
                    Toast.makeText(Products_information_activity.this, "Hàng trong kho chỉ còn : " + (sanPham.getSl_SP()- 1) + " sản phẩm ", Toast.LENGTH_SHORT).show();

                }else if(  SL == 0 ){
                    Toast.makeText(Products_information_activity.this, " Số lượng không hợp lệ !  " , Toast.LENGTH_SHORT).show();

                }
                else
                {
                    TrangChuFragment.database.SPGH(
                            LoginActivity.taiKhoan.getMATK(),
                            hinhAnh,
                            sanPham.getMaSP(),
                            sanPham.getTenSP(),
                            SL,
                            SL * sanPham.getGiaSP()
                    );
                    Toast.makeText(getApplicationContext()," Đã thêm vào giỏ hàng !",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Products_information_activity.this, HomeActivity.class);
                    intent.putExtra("giohang", R.id.nav_cart);
                    startActivity(intent);
                }

            }
        });

    }

    private void Sukien() {
        edt_noidungbl_sanpham.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                scrollV.scrollTo(0,edt_noidungbl_sanpham.getScrollY());
            }
        });
        btn_GuiBl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.taiKhoan.getMATK() == -1) {
                    Toast.makeText(Products_information_activity.this, "Bạn chưa đăng nhập !", Toast.LENGTH_LONG).show();
                } else {
                    if (edt_noidungbl_sanpham.getText().length() > 0); {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date());
                        if(idtk==2){
                            sanPham = SanPhamAdapter.sanPhamList.get(id);
                        }else
                        {
                            sanPham = TimKiemAdapter.sanPhamList.get(idtk);
                        }
                        TrangChuFragment.database.ThemBL(LoginActivity.taiKhoan.getMATK(), sanPham.getMaSP(),edt_noidungbl_sanpham.getText().toString(),currentDateandTime);
                        listBL.add(0, new BinhLuan(
                                LoginActivity.taiKhoan.getMATK(),LoginActivity.taiKhoan.getHINHANH(),
                                edt_noidungbl_sanpham.getText().toString(),currentDateandTime
                        ));
                        Log.e("Tag",String.valueOf(listBL.size()));
                        binhLuanAdapter.notifyItemInserted(0);
                        edt_noidungbl_sanpham.setText("");
                        Toast.makeText(Products_information_activity.this, " Bình luận thành công ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void Anhxa() {
        img_hethang = findViewById(R.id.img_hethang);
        noidung_ctsp = findViewById(R.id.noidung_ctsp);
        btn_GuiBl = (Button) findViewById(R.id.btn_GuiBl);
        scrollV =(NestedScrollView) findViewById(R.id.scrollV);
        edt_noidungbl_sanpham = findViewById(R.id.edt_noidungbl_sanpham);
        name = (TextView) findViewById(R.id.product_name_CT);
        price = (TextView) findViewById(R.id.product_price_CT);
        imgHinh = (ImageView) findViewById(R.id.product_image_CT);
        btnaddcart= (Button) findViewById(R.id.btnadd_addtocart_CT);
        editTextSL = (EditText) findViewById(R.id.product_SL_CT);
        btn_quaylai = (ImageButton) findViewById(R.id.btn_quaylai);
        recV_chatbox = findViewById(R.id.rec_Binhluan_sanpham);
    }

    private void GetDataSP() {
        //get data
        if(idtk==2){
            sanPham = SanPhamAdapter.sanPhamList.get(id);
        }else
        {
            sanPham = TimKiemAdapter.sanPhamList.get(idtk);
        }
        if(sanPham.getSl_SP() == 1){
            img_hethang.setImageResource(R.drawable.hethang);
        }
        String ten = sanPham.getTenSP();
//        String mota = sanPham.getMotaSP();
        name.setText(ten);
//        content.setText(mota);
        noidung_ctsp.setText(sanPham.getMotaSP());
        price.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(sanPham.getGiaSP()) + " VNĐ"));
        byte[] hinhAnh = sanPham.getImageSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        imgHinh.setImageBitmap(bitmap);


    }
}
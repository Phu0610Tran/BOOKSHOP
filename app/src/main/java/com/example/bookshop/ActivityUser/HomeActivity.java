package com.example.bookshop.ActivityUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookshop.Fragment.AndroidFragment;
import com.example.bookshop.Fragment.CFragment;
import com.example.bookshop.Fragment.GioHangFragment;
import com.example.bookshop.Fragment.GopYFragment;
import com.example.bookshop.Fragment.JavaFragment;
import com.example.bookshop.Fragment.PythonFragment;
import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.Fragment.UserFragment;
import com.example.bookshop.Fragment.WebFragment;
import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.R;
import com.example.bookshop.VuiCungTaiChinhActivity;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    // Drawer
    CircleImageView img_user_home;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    ImageView imagegiohang,timkiem_home;

    // Drawer

    TextView txt_TenTaiKhoan,count_giohang;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        AnhXa();
        HienThiTen();
        demSL();


        imagegiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                intent.putExtra("giohang", R.id.nav_cart);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int GioHangIntent = intent.getIntExtra("giohang", R.id.nav_home);
        if(GioHangIntent == R.id.nav_cart){
            navigationView.setCheckedItem(GioHangIntent);
            replaceFragment(new GioHangFragment());
        }
        timkiem_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,TimKiem.class));
            }
        });

    }

    private void demSL() {
        if(LoginActivity.taiKhoan.getMATK() != -1){
            Cursor cursor = TrangChuFragment.database.Getdata("SELECT SUM ( SOLUONG ) FROM GIOHANG WHERE IDTK = "
                    + LoginActivity.taiKhoan.getMATK());
            cursor.moveToNext();
            count_giohang.setText(String.valueOf(cursor.getInt(0)));
        }

    }

    @Override
    protected void onStart() {
        Menu menu = navigationView.getMenu();
        if(LoginActivity.taiKhoan.getMATK() == -1){
            menu.findItem(R.id.nav_logout).setVisible(false);
            menu.findItem(R.id.nav_voucher).setVisible(false);
        }else {
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_voucher).setVisible(true);
        }
        demSL();
        super.onStart();
    }

    private void HienThiTen() {
        View view = navigationView.inflateHeaderView(R.layout.header);
        txt_TenTaiKhoan = view.findViewById(R.id.txtTennguoidung);
        img_user_home = view.findViewById(R.id.img_user_home);

        txt_TenTaiKhoan.setText(LoginActivity.taiKhoan.getTENTK());

        if (LoginActivity.taiKhoan.getHINHANH() != null){
            byte[] hinhAnh = LoginActivity.taiKhoan.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_user_home.setImageBitmap(bitmap);
        }
    }

    private void AnhXa() {
        timkiem_home = findViewById(R.id.timkiem_home);
        imagegiohang = findViewById(R.id.imagegiohang);
        count_giohang = findViewById(R.id.count_giohang);
        img_user_home = findViewById(R.id.img_user_home);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.home_nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        // Drawer

        replaceFragment(new TrangChuFragment());
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Remember", "Failed");
            editor.putString("token", null);
            editor.apply();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else if (id == R.id.nav_logout) {
            Intent intent = new Intent(this, HomeActivity.class);
            LoginActivity.taiKhoan = new TaiKhoan();
            startActivity(intent);
        }else if (id == R.id.nav_home) {
                replaceFragment(new TrangChuFragment());
        }else if (id == R.id.nav_contact) {

                replaceFragment(new TrangChuFragment());
        }else if (id == R.id.nav_Feedback) {
                replaceFragment(new GopYFragment());

        }else if (id == R.id.nav_Android) {
                replaceFragment(new AndroidFragment());
        }else if (id == R.id.nav_NgonnguC) {
                replaceFragment(new CFragment());
        }else if (id == R.id.nav_Web) {
                replaceFragment(new WebFragment());
        }else if (id == R.id.nav_Java) {
                replaceFragment(new JavaFragment());
        }else if (id == R.id.nav_Python) {
                replaceFragment(new PythonFragment());
        }else if (id == R.id.nav_voucher) {
            startActivity(new Intent(HomeActivity.this, VuiCungTaiChinhActivity.class));
        }else if (id == R.id.nav_profile) {
            if(LoginActivity.taiKhoan.getMATK() != -1)
            {
                    replaceFragment(new UserFragment());
            }else
            {
                Toast.makeText(HomeActivity.this, " Bạn chưa đăng nhập ", Toast.LENGTH_LONG).show();
            }

        }else if (id == R.id.nav_cart) {
                replaceFragment(new GioHangFragment());
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void  replaceFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }
}
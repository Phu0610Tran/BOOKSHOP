package com.example.bookshop.ActivityAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.bookshop.Fragment_Admin.HoaDonAdmin;
import com.example.bookshop.Fragment_Admin.QLGopyFragment;
import com.example.bookshop.Fragment_Admin.QLSanphamFragment;
import com.example.bookshop.Fragment_Admin.QLTaikhoanFragment;
import com.example.bookshop.ActivityUser.LoginActivity;
import com.example.bookshop.Fragment_Admin.ThongKeSanPham;
import com.example.bookshop.R;
import com.google.android.material.navigation.NavigationView;

public class HomeAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    // Drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    // Drawer

    TextView txt_TenTaiKhoan,count_giohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        AnhXa();
        HienThiTen();


    }

    @Override
    protected void onStart() {
        Menu menu = navigationView.getMenu();
        if(LoginActivity.taiKhoan.getMATK() == -1){
            menu.findItem(R.id.nav_logout).setVisible(false);
        }else {
            menu.findItem(R.id.nav_login).setVisible(false);
        }
        super.onStart();
    }



    private void HienThiTen() {
        View view = navigationView.inflateHeaderView(R.layout.header);
        txt_TenTaiKhoan = view.findViewById(R.id.txtTennguoidung);

        Intent intent = getIntent();
        txt_TenTaiKhoan.setText(LoginActivity.taiKhoan.getTENTK());
    }

    private void AnhXa() {
        count_giohang = findViewById(R.id.count_giohang);
        drawerLayout = findViewById(R.id.drawer_layoutadmin);
        navigationView = findViewById(R.id.home_nav_viewadmin);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        // Drawer

        replaceFragment(new QLSanphamFragment());
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

        if (id == R.id.nav_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Remember", "Failed");
            editor.putString("token", null);
            editor.apply();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }else if (id == R.id.nav_ql_sanpham) {
                replaceFragment(new QLSanphamFragment());

        }else if (id == R.id.nav_ql_taikhoan) {
                replaceFragment(new QLTaikhoanFragment());

        }else if (id == R.id.nav_ql_hoadon) {
            startActivity(new Intent(HomeAdmin.this, HoaDonAdmin.class));
        }else if (id == R.id.nav_ql_gopy) {
            replaceFragment(new QLGopyFragment());

        }else if (id == R.id.nav_dangbai) {
            startActivity(new Intent(HomeAdmin.this, DangBaiActivity.class));

        }else if (id == R.id.nav_ql_soluongsanpham) {
                replaceFragment(new ThongKeSanPham());
        }else if (id == R.id.nav_ql_themsanpham) {
            startActivity(new Intent(HomeAdmin.this,QL_ThemSanPham.class));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }
}


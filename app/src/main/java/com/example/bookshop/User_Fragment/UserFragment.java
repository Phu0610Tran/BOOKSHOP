package com.example.bookshop.User_Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bookshop.ActivityAdmin.DangBaiActivity;
import com.example.bookshop.User_Activity.DanhGiaActivity;
import com.example.bookshop.User_Activity.HoTroKhachHangActivity;
import com.example.bookshop.User_Activity.InforUserActivity;
import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.Data.TaiKhoanDAO;
import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.R;
import com.example.bookshop.User_Activity.lichsuActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserFragment extends Fragment {

    private BottomNavigationView mbottomNavigationView;
    private View mView;
    Button btn_Login;
    TextView txt_Tentaikhoan, txt_Baomat, txt_Hotrokhachhang, txt_lichsu,txtDanhGia,count_danhgia;
    CircleImageView img_user_cn;
    TaiKhoanDAO taiKhoanDAO;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_user, container, false);

        AnhXa();
        taiKhoanDAO = new TaiKhoanDAO(getActivity());
        txt_Tentaikhoan.setText(LoginActivity.taiKhoan.getTENTK());
        if (LoginActivity.taiKhoan.getHINHANH() == null){
            img_user_cn.setImageResource(R.drawable.user);
        }else
        {
            byte[] hinhAnh = LoginActivity.taiKhoan.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_user_cn.setImageBitmap(bitmap);
//            Toast.makeText(InforUserActivity.this, "sssss : " + hinhAnh, Toast.LENGTH_SHORT).show();

        }
        return mView;
    }

    @Override
    public void onStart() {

        TaiKhoan kiemtra = taiKhoanDAO.KiemTraDangNhap(LoginActivity.taiKhoan.getTENTK(),
                LoginActivity.taiKhoan.getMATKHAU());
        LoginActivity.taiKhoan = kiemtra;
        if (LoginActivity.taiKhoan.getHINHANH() == null){
            img_user_cn.setImageResource(R.drawable.user);
        }else
        {
            byte[] hinhAnh = LoginActivity.taiKhoan.getHINHANH();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
            img_user_cn.setImageBitmap(bitmap);
//            Toast.makeText(InforUserActivity.this, "sssss : " + hinhAnh, Toast.LENGTH_SHORT).show();

        }
        super.onStart();
    }

    private void AnhXa() {
        count_danhgia = mView.findViewById(R.id.count_danhgia);
        count_danhgia.setText(String.valueOf(TrangChuFragment.database.countCTSP(LoginActivity.taiKhoan.getMATK())
         - TrangChuFragment.database.countDG(LoginActivity.taiKhoan.getMATK())));
        txtDanhGia = mView.findViewById(R.id.txtDanhGia);
        img_user_cn = mView.findViewById(R.id.img_user_cn);
        txt_Tentaikhoan = mView.findViewById(R.id.txtUsername);
        txt_Hotrokhachhang = mView.findViewById(R.id.txtHoTroKhachHang);
        txt_lichsu = mView.findViewById(R.id.txtLichsu);
        txt_Hotrokhachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HoTroKhachHangActivity.class));
            }
        });

        txt_Baomat = mView.findViewById(R.id.txtBaomat);
        txt_Baomat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginActivity.taiKhoan.getMATK() == -1)
                {
                    Toast.makeText(getActivity(), "Bạn chưa đăng nhập !", Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivity(new Intent(getActivity(), InforUserActivity.class));
                }

            }
        });
        txt_lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginActivity.taiKhoan.getMATK() == -1)
                {
                    Toast.makeText(getActivity(), "Bạn chưa đăng nhập !", Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivity(new Intent(getActivity(), lichsuActivity.class));
                }

            }
        });
        txtDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),DanhGiaActivity.class));
            }
        });

    }

}
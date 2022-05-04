package com.example.bookshop.User_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bookshop.AdminActivity.HomeAdmin;
import com.example.bookshop.Data.TaiKhoanDAO;
import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.User_Activity.HomeActivity;
import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.R;

public class LoginFragment extends Fragment implements View.OnClickListener{


    private View mView;
    TextView txt_DangKy, txt_QuenMK;
    Button btn_DangNhap;
    EditText edt_TK, edt_MK;
    ImageButton ibtn_exit;
    TaiKhoanDAO taiKhoanDAO;
    String mUsername = "";

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);

        AnhXa();

        return mView;
    }

    private void AnhXa() {
        txt_QuenMK = mView.findViewById(R.id.txtQuenmk);
        btn_DangNhap = mView.findViewById(R.id.btnDangnhap);
        edt_TK = mView.findViewById(R.id.edtTaikhoan);
        edt_MK = mView.findViewById(R.id.edtMatkhau);

        taiKhoanDAO = new TaiKhoanDAO(getActivity());
        btn_DangNhap.setOnClickListener(this);
        txt_QuenMK.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDangnhap:
                setBtn_Login();
                ;break;
        }
    }

    private void setBtn_Login(){
        String sTentaikhoan = edt_TK.getText().toString();
        String sMatkhau = edt_MK.getText().toString();
        TaiKhoan kiemtra = taiKhoanDAO.KiemTraDangNhap(sTentaikhoan, sMatkhau);

        mUsername = sTentaikhoan;

        if (kiemtra != null){
            LoginActivity.taiKhoan = kiemtra;
//            Toast.makeText(getActivity(), "Đăng nhập thành công ! " +LoginActivity.taiKhoan.getMAQUYEN() , Toast.LENGTH_LONG).show();
            if(LoginActivity.taiKhoan.getMAQUYEN()==1)

            {
                Intent iTrangchu = new Intent(getActivity(), HomeActivity.class);

                startActivity(iTrangchu);
            }
            else
            {
                startActivity(new Intent(getActivity(), HomeAdmin.class));
            }

        } else {
            Toast.makeText(getActivity(), "Đăng nhập thất bại !", Toast.LENGTH_LONG).show();
        }
    }

    public String getUsername() {
        return mUsername;
    }

}
package com.example.bookshop.ActivityUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bookshop.Fragment.TrangChuFragment;
import com.example.bookshop.R;

public class DoiMatKhauActivity extends AppCompatActivity {
    ImageButton ibtnExit;
    EditText edtMatkhaucu, edtMatkhaumoi, edtNhaplaimatkhaumoi;
    Button btnDoimatkhau, btnHuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        AnhXa();
    }

    private void AnhXa() {
        edtMatkhaucu = findViewById(R.id.edtMatkhaucu);
        edtMatkhaumoi = findViewById(R.id.edtMatkhaumoi);
        edtNhaplaimatkhaumoi = findViewById(R.id.edtNhaplaimatkhaumoi);
        btnHuy = findViewById(R.id.btnHuy);
        btnDoimatkhau = findViewById(R.id.btnDoimk);
        btnDoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtMatkhaucu.getText().length() !=0 && edtMatkhaumoi.getText().length() != 0 && edtNhaplaimatkhaumoi.getText().length() != 0){
                    if(TrangChuFragment.database.isMatKhau(LoginActivity.taiKhoan.getMATK(), edtMatkhaucu.getText().toString())){
                        if(edtMatkhaumoi.getText().toString().equals(edtNhaplaimatkhaumoi.getText().toString())){
                            TrangChuFragment.database.CapNhatMatKhau(LoginActivity.taiKhoan.getMATK(), edtMatkhaumoi.getText().toString());
                            Toast.makeText(DoiMatKhauActivity.this,"Đổi mật khẩu thành công !",Toast.LENGTH_LONG).show();
                            onBackPressed();
                        } else {
                            Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu mới không trùng khớp !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(DoiMatKhauActivity.this, "Nhập mật khẩu cũ không đúng !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DoiMatKhauActivity.this, "Nhập dữ liệu chưa đủ !", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ibtnExit = findViewById(R.id.ibtnExit);
        ibtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
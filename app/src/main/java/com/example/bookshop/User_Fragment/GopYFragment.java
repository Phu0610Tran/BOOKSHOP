package com.example.bookshop.User_Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bookshop.User_Activity.HomeActivity;
import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.R;

public class GopYFragment extends Fragment {
    private View view;
    ImageButton ibtn_Exit;
    EditText edt_Tentaikhoan, edt_Sdt, edt_NoiDunggopy;
    Button btn_Gopy, btn_Thoat;

    public GopYFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_gop_y, container, false);

        Anhxa();

        GetData();

        return view;
    }

    private void GetData() {

        edt_Tentaikhoan.setText(LoginActivity.taiKhoan.getTENTK());
        edt_Sdt.setText( "0" + String.valueOf(LoginActivity.taiKhoan.getSDT()));
    }

    private void Anhxa() {
        edt_Tentaikhoan = view.findViewById(R.id.edtTaikhoangopy);
        edt_Sdt = view.findViewById(R.id.edtSdtgopy);
        edt_NoiDunggopy = view.findViewById(R.id.edtNoidunggopy);
        btn_Gopy = view.findViewById(R.id.btnGopy);
        btn_Gopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrangChuFragment.database.INSERT_GOPY(
                        edt_Tentaikhoan.getText().toString().trim(),
                        Integer.parseInt(edt_Sdt.getText().toString().trim()),
                        edt_NoiDunggopy.getText().toString().trim()
                );
                Toast.makeText(getActivity(), "Gửi góp ý thành công !", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });

        btn_Thoat = view.findViewById(R.id.btnThoatgopy);
        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });


    }
}
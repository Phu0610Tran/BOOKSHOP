package com.example.bookshop.ActivityUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.Fragment.LoginFragment;
import com.example.bookshop.Fragment.RegisterFragment;
import com.example.bookshop.R;

public class LoginActivity extends AppCompatActivity{

    public static TaiKhoan taiKhoan = new TaiKhoan();
    TextView login_btn, signup_btn;
    ImageButton ibtn_Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AnhXa();

    }

    private void AnhXa() {
        login_btn = findViewById(R.id.login);
        signup_btn = findViewById(R.id.signup);
        ibtn_Exit = findViewById(R.id.ibtnExit);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadLoginFragment();
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSignUpFragment();
            }
        });

        ibtn_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getExit();
            }
        });

        loadLoginFragment();
    }

    private void loadLoginFragment()
    {
        LoginFragment loginFragment = new LoginFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.log_sign_layout, loginFragment);
        transaction.commit();
    }

    private void loadSignUpFragment()
    {
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.log_sign_layout, registerFragment);
        transaction.commit();
    }


    private void getExit()
    {
        Intent iExit = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(iExit);
    }

}
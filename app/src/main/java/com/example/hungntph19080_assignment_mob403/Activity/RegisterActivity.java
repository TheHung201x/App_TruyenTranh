package com.example.hungntph19080_assignment_mob403.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hungntph19080_assignment_mob403.AsyncTask.Register.RegisterHttpPost;
import com.example.hungntph19080_assignment_mob403.databinding.ActivityRegisterBinding;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    TextInputLayout txtUsername, txtPassword, txtEmail, txtFullName;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    new RegisterHttpPost(txtUsername, txtPassword, txtEmail, txtFullName).execute("https://f21oe9-8080.csb.app/users");
                }
            }
        });
    }

    private void init() {
        txtUsername = binding.txtUsernameRegister;
        txtPassword = binding.txtPasswordRegister;
        txtEmail = binding.txtEmailRegister;
        txtFullName = binding.txtFullNameRegister;
        btnRegister = binding.btnRegister;
    }

    private boolean validate() {
        String usernameValidate = txtUsername.getEditText().getText().toString();
        String passwordValidate = txtPassword.getEditText().getText().toString();
        String fullnameValidate = txtFullName.getEditText().getText().toString();
        String emailValidate = txtEmail.getEditText().getText().toString();
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

        if (usernameValidate.trim().length() <= 0) {
            txtUsername.setError("Vui lòng nhập tên tài khoản");
            return false;
        } else {
            txtUsername.setError("");
        }

        if (passwordValidate.trim().length() <= 0) {
            txtPassword.setError("Vui lòng nhập mật khẩu");
            return false;
        } else if (passwordValidate.trim().length() < 5) {
            txtPassword.setError("Vui lòng nhập mật khẩu trên 5 kí tự");
            return false;
        } else {
            txtPassword.setError("");
        }


        if (emailValidate.trim().length() <= 0) {
            txtEmail.setError("Vui lòng nhập email");
            return false;
        } else if (!emailValidate.trim().matches(emailPattern)) {
            txtEmail.setError("Định dạng email không hợp lệ");
            return false;
        } else {
            txtEmail.setError("");
        }

        if (fullnameValidate.trim().length() <= 0) {
            txtFullName.setError("Vui lòng nhập tên");
            return false;
        } else {
            txtFullName.setError("");
        }

        return true;
    }
}
package com.example.hungntph19080_assignment_mob403.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hungntph19080_assignment_mob403.Activity.Main.MainActivity;
import com.example.hungntph19080_assignment_mob403.AsyncTask.Login.LoginHttpPost;
import com.example.hungntph19080_assignment_mob403.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements LoginHttpPost.OnLoginResultListener {
    ActivityLoginBinding binding;
    TextInputLayout txtUsername, txtPassword;
    Button btnLogin;
    TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getEditText().getText().toString();
                String password = txtPassword.getEditText().getText().toString();
                if(validate(username, password)){
                    new LoginHttpPost(LoginActivity.this, LoginActivity.this).execute("https://f21oe9-8080.csb.app/users/" + "?username=" + username + "&password=" + password);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init() {
        txtUsername = binding.txtUsernameLogin;
        txtPassword = binding.txtPasswordLogin;
        btnLogin = binding.btnLogin;
        btnRegister = binding.btnRegisterInLogin;
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFail() {
        Toast.makeText(getApplicationContext(), "Thông tin đăng nhập không chính xác", Toast.LENGTH_SHORT).show();
    }


    private boolean validate(String user, String pass) {
        user = txtUsername.getEditText().getText().toString();
        pass = txtPassword.getEditText().getText().toString();

        if ((user.length() <= 0 || user.equals(null)) && (pass.length() <= 0 || pass.equals(null))) {
            txtUsername.setError("Vui lòng nhập tên tài khoản");
            txtPassword.setError("Vui lòng nhập mật khẩu");
            return false;
        } else if (user.length() <= 0 || user.equals(null)) {
            txtUsername.setError("Vui lòng nhập tên tài khoản");
            txtPassword.setError("");
            return false;
        } else if (pass.length() <= 0 || pass.equals(null)) {
            txtPassword.setError("Vui lòng nhập mật khẩu");
            txtUsername.setError("");
            return false;
        } else {
            txtUsername.setError("");
            txtPassword.setError("");
        }
        return true;

    }
}
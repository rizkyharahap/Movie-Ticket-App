package com.haphap.movieticketapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.model.User;
import com.haphap.movieticketapp.viewmodel.UserViewModel;

import java.util.Objects;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextInputEditText etFirstName
            , etLastName
            , etEmail
            , etPassword;
    private TextView tvLogin;
    private Button btnRegister;
    private LinearLayout llProgressBar;

    private UserViewModel userViewModel;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findView();
        initView();
        initListener();
    }

    @Override
    public void findView() {
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvLogin = findViewById(R.id.tv_login);
        btnRegister = findViewById(R.id.btn_sign_up);
        llProgressBar = findViewById(R.id.ll_progress_bar);
    }

    @Override
    public void initView() {
        handler = new Handler();
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public void initListener() {
        btnRegister.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up :
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
                register();
                break;
            case R.id.tv_login :
                gotoLoginActivity(this);
                break;
        }
    }

    private void register() {
        String firstName = Objects.requireNonNull(etFirstName.getText()).toString().trim();
        String lastName = Objects.requireNonNull(etLastName.getText()).toString().trim();
        String email = Objects.requireNonNull(etEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(etPassword.getText()).toString().trim();

        if (!validateInput(firstName, lastName, email, password)) {
            hideProgressbar();
        } else {
            showProgressbar();

            User newUser = new User(firstName, lastName, email, password);
            Long insertedUserId = userViewModel.inserUser(newUser);
            newUser.setId(insertedUserId);

            if (insertedUserId != null && insertedUserId != -1) {
                getSharedPrefence().setLoggedUserId(insertedUserId);
                openHandler(newUser);
            } else {
                showToast("Register Error !");
                hideProgressbar();
            }
        }

    }

    private void openHandler(final User newUser) {
        handler.postDelayed(() -> {
            hideProgressbar();
            showToast("Registration Successful");
            openMainActivity(RegisterActivity.this, newUser);
            finish();
        }, 2000);
    }

    private boolean validateInput(String firstName, String lastName, String email, String password) {
        if (TextUtils.isEmpty(firstName)) {
            etFirstName.setError("Required");
            return false;
        }

        if (TextUtils.isEmpty(lastName)) {
            etLastName.setError("Required");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Required");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required");
            return false;
        } else if (password.length()<6) {
            etPassword.setError("Password too short");
            return false;
        }

        return true;
    }

    private void showProgressbar(){
        llProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar(){
        llProgressBar.setVisibility(View.GONE);
    }

}

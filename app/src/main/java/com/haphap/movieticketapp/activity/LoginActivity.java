package com.haphap.movieticketapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.haphap.movieticketapp.R;
import com.haphap.movieticketapp.model.User;
import com.haphap.movieticketapp.viewmodel.UserViewModel;

import java.util.Objects;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private UserViewModel userViewModel;
    private Handler handler;
    private TextInputEditText etEmail,etPassword;
    private TextView tvRegister;
    private Button btnLogin;
    private LinearLayout llProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findView();
        initView();
        initListener();
    }

    @Override
    public void findView() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvRegister = findViewById(R.id.tv_register);
        btnLogin = findViewById(R.id.btn_sign_in);
        llProgressBar = findViewById(R.id.ll_progress_bar);
    }

    @Override
    public void initView() {
        handler = new Handler();
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        if (getSharedPrefence().getUserId() != -1) {
            for (User loggedUser: userViewModel.getallUsers()) {
                if (loggedUser.getId().equals(getSharedPrefence().getUserId())){
                    openMainActivity(LoginActivity.this, loggedUser);
                    finish();
                }
            }
        }

    }

    @Override
    public void initListener() {
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in :
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
                login();
                break;
            case R.id.tv_register :
                gotoRegisterActivity(LoginActivity.this);
                break;
        }
    }

    private void login() {
        String email = Objects.requireNonNull(etEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(etPassword.getText()).toString().trim();

        if (!validateEmailPassword(email, password)) {
            hideProgressbar();
        } else {
            showProgressbar();

            for (User user:userViewModel.getallUsers()) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    getSharedPrefence().setLoggedUserId(user.getId());
                    openMainActivityonDelay(user);
                    return;
                }
            }
            handler.postDelayed(() -> {
                hideProgressbar();
                showToast("Wrong email or password");
            }, 2000);
        }
    }

    private void openMainActivityonDelay(final User user) {
        handler.postDelayed(() -> {
            showProgressbar();
            openMainActivity(LoginActivity.this, user);
            finish();
        }, 1000);
    }


    private boolean validateEmailPassword(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Required");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Required");
            return false;
        } else if (password.length() < 6) {
            etPassword.setError("Password too short");
            return false;
        }
        return true;
    }

    private void showProgressbar() {
        llProgressBar.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);
    }

    private void hideProgressbar() {
        llProgressBar.setVisibility(View.GONE);
        btnLogin.setEnabled(true);
    }
}

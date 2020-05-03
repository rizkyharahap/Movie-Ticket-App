package com.haphap.movieticketapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.haphap.movieticketapp.MainActivity;
import com.haphap.movieticketapp.model.User;
import com.haphap.movieticketapp.utils.SharePrefenceHelper;

import static com.haphap.movieticketapp.utils.SharePrefenceHelper.*;

public abstract class BaseActivity extends AppCompatActivity {

    private SharePrefenceHelper sharePrefenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePrefenceHelper = getINSTANCE(this);
    }

    public SharePrefenceHelper getSharedPrefence() {
        return sharePrefenceHelper;
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void openMainActivity(Context context, User user) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, user);
        startActivity(intent);
    }

    protected void gotoLoginActivity(Context context) {
        startActivity(new Intent(context, LoginActivity.class));
    }

    protected void gotoRegisterActivity(Context context) {
        startActivity(new Intent(context, RegisterActivity.class));
    }

    public abstract void findView();
    public abstract void initView();
    public abstract void initListener();
}

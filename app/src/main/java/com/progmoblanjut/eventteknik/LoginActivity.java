package com.progmoblanjut.eventteknik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

public class LoginActivity extends AppCompatActivity {

    Button register, login;
    EditText email, password;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = (Button) findViewById(R.id.btnRegisterlogin);
        email = (EditText) findViewById(R.id.EmailLogin);
        password = (EditText) findViewById(R.id.PasswordLogin);
        login = (Button) findViewById(R.id.btnLoginlogin);

        db = new SQLiteHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_pengguna = email.getText().toString();
                String password_pengguna = password.getText().toString();
                Boolean login = db.login(email_pengguna, password_pengguna);
                if(login == true) {
                    Intent gotoHome = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(gotoHome);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong credentials. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
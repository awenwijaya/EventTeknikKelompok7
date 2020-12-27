package com.progmoblanjut.eventteknik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText nama, email, password, passwordConfirm;
    Button login, register;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nama = (EditText) findViewById(R.id.NamaRegister);
        email = (EditText) findViewById(R.id.EmailRegister);
        password = (EditText) findViewById(R.id.PasswordRegister);
        passwordConfirm = (EditText) findViewById(R.id.PasswordConfirmRegister);
        login = (Button) findViewById(R.id.btnLoginregister);
        register = (Button) findViewById(R.id.btnRegisterregister);

        db = new SQLiteHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_pengguna = nama.getText().toString();
                String email_pengguna = email.getText().toString();
                String password_pengguna = password.getText().toString();
                String password_confirm = passwordConfirm.getText().toString();

                if(TextUtils.isEmpty(nama_pengguna)) {
                    nama.setError("Field can't be empty");
                    nama.requestFocus();
                } else if(TextUtils.isEmpty(email_pengguna)) {
                    email.setError("Field can't be empty!");
                    email.requestFocus();
                } else if(TextUtils.isEmpty(password_pengguna)) {
                    password.setError("Field can't be empty");
                    password.requestFocus();
                } else if(TextUtils.isEmpty(password_confirm)) {
                    passwordConfirm.setError("Field can't be empty");
                    passwordConfirm.requestFocus();
                } else {
                    if(password_pengguna.equals(password_confirm)) {
                        Boolean chkemail = db.chkemail(email_pengguna);
                        if(chkemail == true) {
                            Boolean insert = db.insertPengguna(nama_pengguna, email_pengguna, password_pengguna);
                            if(insert == true) {
                                Toast.makeText(RegisterActivity.this, "Register successfull! Please login to continue", Toast.LENGTH_SHORT).show();
                                Intent gotoLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(gotoLogin);
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Email already taken!", Toast.LENGTH_SHORT).show();
                        }
                    }
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
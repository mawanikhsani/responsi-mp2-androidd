package com.example.responsi;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.responsi.room.AppDatabase;
import com.example.responsi.room.Mahasiswa;
import com.example.responsi.room.MahasiswaDao;
import com.example.responsi.util.PreferencesHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etPassword, etConfirm;
    Button btRegister;
    ImageButton kembali;
    PreferencesHelper preferencesHelper;
    SharedPreferences.Editor editor;

    private MahasiswaDao userDao, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.edemail2);
        etPassword = findViewById(R.id.edpassword2);
        etConfirm = findViewById(R.id.edconfirmpassword);
        btRegister = findViewById(R.id.btregister);
        kembali = findViewById(R.id.btnLogout);

        userDao = Room.databaseBuilder(this, AppDatabase.class, "mi-database.db").allowMainThreadQueries()
                .build().getUserDao();

        preferencesHelper = PreferencesHelper.getInstance(getApplicationContext());
        editor = preferencesHelper.preferences().edit();


        user = (MahasiswaDao) getIntent().getSerializableExtra("Mahasiswa");


        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = etEmail.getText().toString().trim();
                String Password = etPassword.getText().toString().trim();
                String Confirm = etConfirm.getText().toString().trim();

                if (Password.equals(Confirm)) {
                    Mahasiswa user = new Mahasiswa(Email, Password);
                    userDao.insertAll(user);
                    Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(moveToLogin);

                } else {
                    Toast.makeText(RegisterActivity.this, "Masukan Data Dengan Benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
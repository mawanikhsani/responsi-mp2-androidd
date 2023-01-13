package com.example.responsi;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.responsi.room.AppDatabase;
import com.example.responsi.room.Mahasiswa;
import com.example.responsi.room.MahasiswaDao;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    private EditText edemail, edpassword;
    private TextView txherelupa, txherereg;
    private MahasiswaDao db;
    AppDatabase database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = findViewById(R.id.btlogin);
        edemail = findViewById(R.id.edemail);
        edpassword = findViewById(R.id.edpassword);
        txherelupa = findViewById(R.id.herelupa);
        txherereg = findViewById(R.id.herereg);

        database = Room.databaseBuilder(this, AppDatabase.class, "mi-database.db")
                .allowMainThreadQueries()
                .build();
        db = database.getUserDao();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim = edemail.getText().toString();
                String password = edpassword.getText().toString();

                Mahasiswa user = db.getUser(nim, password);
                if (user != null) {
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    i.putExtra("User", user);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Masukan Data Dengan Benar", Toast.LENGTH_SHORT).show();
                }

            }
        });

        txherereg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


}
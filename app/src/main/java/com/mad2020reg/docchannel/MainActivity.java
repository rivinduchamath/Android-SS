package com.mad2020reg.docchannel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad2020reg.docchannel.Database.DBHelper;
import com.mad2020reg.docchannel.Database.Patient;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText phone;
    Button butttonLogin,btnRegister;
    private DBHelper db;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone = findViewById(R.id.edtPhone);
        butttonLogin = findViewById(R.id.button2);
        btnRegister = findViewById(R.id.button);
        db = new DBHelper(getApplicationContext());
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

          butttonLogin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

     String number = phone.getText().toString();

                  if(number.equals("") ){
                      Toast.makeText(MainActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();
                  }else {
                      Cursor cursor = db.loginPatient(number);
                      boolean bool = cursor.moveToFirst();
                      if(bool){

                          openMenu();
                      }else {
                          Toast.makeText(MainActivity.this, "Cannot Find Number", Toast.LENGTH_SHORT).show();
                      }
                  }

              }
          });

    }

    public void openRegisterActivity(){
        Intent intent = new Intent(this,RegisterPatient.class);
        startActivity(intent);

    }

    public void openMenu(){



        /****Question 04 ****/

        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);


  }
}
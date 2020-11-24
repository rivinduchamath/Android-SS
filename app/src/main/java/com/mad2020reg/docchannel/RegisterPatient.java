package com.mad2020reg.docchannel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mad2020reg.docchannel.Database.DBHelper;

import java.util.List;

public class RegisterPatient extends AppCompatActivity {

    EditText edtName, edtAge, edtContact;
    String PId;
    Button butttonRegister;
    private static final String TAG = "RegisterPatient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);

        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtContact = findViewById(R.id.edtPhone);
        butttonRegister = findViewById(R.id.butttonRegister);

        final DBHelper dbHelper = new DBHelper(this);
        final List patients= dbHelper.getPatient();
        final String[] patient = (String[])patients.get(0);
butttonRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // generate Patient Id
        try{


            Log.i(TAG, "onCreate: "+ patient[1]);
            PId = patient[1];


            if(Integer.parseInt(PId.substring(1,PId.length()))+1<10){
                PId = "P00" + (Integer.parseInt(PId.substring(1,PId.length()))+1);
            }else if(Integer.parseInt(PId.substring(1,PId.length()))+1<100){
                PId = "P0" + (Integer.parseInt(PId.substring(1,PId.length()))+1);
            }else {
                PId = "P" + (Integer.parseInt(PId.substring(1,PId.length()))+1);
            }

            Log.i(TAG, "onCreate: "+ PId);
        }catch (Exception e){
            Log.i(TAG, "onCreate: Error");
            PId = "P001";

        }
        saveData(PId);
    }
});

    }

    public void saveData(String stri){


        /****Question 05 ****/

        DBHelper db = new DBHelper(getApplicationContext());
        long i = db.addPatient(stri,edtName.getText().toString(),edtAge.getText().toString(),edtContact.getText().toString());
        if(i > 0) {
            Toast.makeText(RegisterPatient.this, "Data Inserted successfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(RegisterPatient.this, "Failed to Insert", Toast.LENGTH_SHORT).show();
        }

    }
}
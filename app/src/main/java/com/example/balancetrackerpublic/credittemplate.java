package com.example.balancetrackerpublic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class credittemplate extends AppCompatActivity {

    public EditText balance;
    public TextView bal;
    public EditText amount;
    public Button pay;
    public Button charge;
    public Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credittemplate);

        balance = findViewById(R.id.eBal);
        bal = findViewById(R.id.tBalance);
        amount = findViewById(R.id.eAmount);

        pay = findViewById(R.id.bWithdraw);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pay();
            }
        });

        charge = findViewById(R.id.bDeposit);
        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Charge();
            }
        });

        home = findViewById(R.id.bHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(credittemplate.this, MainActivity.class));
            }
        });
    }

    private void Charge() {
    }

    private void Pay() {
    }

    private String getStringFromFile(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fin = openFileInput(file);
            int c;
            while ((c = fin.read()) != -1) {
                stringBuilder.append(Character.toString((char) c));
            }
            fin.close();
        } catch (Exception e) {
            System.out.println("No previous debit file found, continuing...");
            return "0.00";
        }
        return stringBuilder.toString();
    }

    private void writeStringToFile(String file, String value) throws Exception {
        FileOutputStream fos = openFileOutput(file, MODE_PRIVATE);
        fos.write(value.getBytes());
        fos.close();
    }
}

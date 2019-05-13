package com.example.balancetrackerpublic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import static java.lang.Double.parseDouble;

public class DebitTemplate extends AppCompatActivity {

    public TextView account;
    public EditText balance;
    public EditText amount;
    public Button home;
    public Button submit;
    static String fdebit = "accName.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accName);

        account = findViewById(R.id.tAccName);
        account.setText("accName");

        balance = findViewById(R.id.eBalance);
        balance.setText(getStringFromFile(fdebit));

        amount = findViewById(R.id.eNew);

        home = findViewById(R.id.bHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(accName.this, MainActivity.class));
            }
        });

        submit = findViewById(R.id.bSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
        });

    }


    private void Submit() {
        String obal = getStringFromFile(fdebit);
        if(amount != null) {

            // get current balance adding original balance to amount if original balance exists
            String cbal;
            if (obal.length() > 0) {
                cbal = String.valueOf(parseDouble(obal) + parseDouble(amount.getText().toString()));
            } else if (parseDouble(amount.getText().toString()) > 0) {
                cbal = amount.getText().toString();
            } else {
                cbal = "0.00";
            }
            balance.setText(cbal);

            try {
                writeStringToFile(fdebit, cbal);
                Toast.makeText(getBaseContext(), "File Saved", Toast.LENGTH_SHORT).show();
                amount.setText(null);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringFromFile(String file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fin = openFileInput(file);
            int c;
            while ((c = fin.read()) != -1) {
                stringBuilder.append(Character.toString((char) c));
            }
            fin.close();
        } catch (Exception e) {
            System.out.println("No Previous Debit File Found, Continuing...");
        }
        return stringBuilder.toString();
    }

    public void writeStringToFile(String file, String value) throws Exception {
        FileOutputStream fos = openFileOutput(file, MODE_PRIVATE);
        fos.write(value.getBytes());
        fos.close();
    }
}

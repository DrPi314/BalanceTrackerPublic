package com.example.balancetrackerpublic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NewAccountSetup extends AppCompatActivity {

    public Button home;
    public Button debit;
    public Button credit;
    public EditText accName;
    static final String manifest = "C:/AndroidStudio/BalanceTrackerPublic/app/src/main/AndroidManifest.xml";
    public final String java = "C:/AndroidStudio/BalanceTrackerPublic/app/src/main/java/com/example/balancetrackerpublic/";
    public final String layout = "C:/AndroidStudio/BalanceTrackerPublic/app/src/main/res/layout/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account_setup);

        home = findViewById(R.id.bHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewAccountSetup.this, MainActivity.class));
            }
        });

        debit = findViewById(R.id.bCreate);
        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Debit();
            }
        });

        credit = findViewById(R.id.bCredit);
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Credit();
            }
        });

        accName = findViewById(R.id.eName);
    }

    private void Debit() {
        String account = accName.getText().toString();
        String javaFileName = "java" + account + ".java";
        String layoutFileName = "layout" + "activity_" + account + ".xml";
        String debitLayout = "";
        String debitJava = "";
        try {
            writeStringToFile(javaFileName, debitJava);
            writeStringToFile(layoutFileName, debitLayout);
            Manifest(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Credit() {
    }

    static void DebitText(String file, String account) {
        String classExtend = "public class " + account + " extends AppCompatActivity {";
        String balFileName = "    static String fdebit = \"" + account + ".txt\";";
        String setContView = "        setContentView(R.layout.activity_" + account + ");";
        String accountName = "        account.setText(\"" + account + "\");";
        String buttnToHome = "                startActivity(new Intent(" + account + ".this, MainActivity.class));";
        try{
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str = null;
            ArrayList<String> lines = new ArrayList<String>();
            while ((str = in.readLine()) != null) {
                lines.add(str);
            }
            lines.set(15, classExtend);
            lines.set(22, balFileName);
            lines.set(27, setContView);
            lines.set(30, accountName);
            lines.set(41, buttnToHome);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void Manifest(String account) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(manifest));
            String str = null;
            ArrayList<String> lines = new ArrayList<String>();
            while ((str = in.readLine()) != null) {
                lines.add(str);
            }
            lines.add(12, "        <activity android:name=\"." + account + "\" />");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStringToFile(String file, String value) throws Exception {
        FileOutputStream fos = openFileOutput(file, MODE_PRIVATE);
        fos.write(value.getBytes());
        fos.close();
    }
}

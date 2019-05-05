package com.example.balancetrackerpublic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewAccountSetup extends AppCompatActivity {

    public Button home;
    public Button create;
    public EditText accName;
    public EditText accType;

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

        create = findViewById(R.id.bCreate);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create();
            }
        });

        accName = findViewById(R.id.eName);
        accType = findViewById(R.id.eType);
    }

    private void Create() {
    }
}

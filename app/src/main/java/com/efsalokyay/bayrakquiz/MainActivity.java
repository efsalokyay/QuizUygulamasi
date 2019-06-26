package com.efsalokyay.bayrakquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button basla_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basla_btn = findViewById(R.id.basla_btn);

        veritabaniKopyala();

        basla_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QuizActivity.class));
            }
        });
    }

    public void veritabaniKopyala() {

        DatabaseCopyHelper helper = new DatabaseCopyHelper(this);

        try {
            helper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        helper.openDataBase();
    }
}

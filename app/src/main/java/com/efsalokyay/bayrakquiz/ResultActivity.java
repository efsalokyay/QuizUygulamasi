package com.efsalokyay.bayrakquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView sonuc_txt, yuzde_sonuc_txt;
    private Button tekrar_btn;

    private int dogruSayac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        sonuc_txt = findViewById(R.id.sonuc_text);
        yuzde_sonuc_txt = findViewById(R.id.yuzde_sonuc_txt);
        tekrar_btn = findViewById(R.id.tekrar_btn);

        dogruSayac = getIntent().getIntExtra("dogruSayac", 0);
        sonuc_txt.setText(dogruSayac + "DOĞRU" + (5 - dogruSayac) + "YANLIŞ");
        yuzde_sonuc_txt.setText("% " + (dogruSayac * 100) / 5 + "Başarı");

        tekrar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, QuizActivity.class));
                finish();
            }
        });
    }
}

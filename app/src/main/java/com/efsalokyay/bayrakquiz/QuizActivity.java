package com.efsalokyay.bayrakquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView dogru_txt, yanlis_txt, soru_sayisi_txt;
    private ImageView bayrak_image_view;
    private Button a_btn, b_btn, c_btn, d_btn;

    private ArrayList<Bayraklar> sorularListe;
    private ArrayList<Bayraklar> yanlislarListe;
    private Bayraklar dogruSoru;
    private Veritabani vt;

    //SAYAÇ
    private int soruSayac = 0;
    private int yanlisSayac = 0;
    private int dogruSayac = 0;

    //SEÇENEKLER
    private HashSet<Bayraklar> secenekleriKaristirmaListe = new HashSet<>();
    private ArrayList<Bayraklar> seceneklerListe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        dogru_txt = findViewById(R.id.dogru_txt);
        yanlis_txt = findViewById(R.id.yanlis_txt);
        soru_sayisi_txt = findViewById(R.id.soru_sayisi_txt);
        bayrak_image_view = findViewById(R.id.bayrak_image_view);
        a_btn = findViewById(R.id.a_btn);
        b_btn = findViewById(R.id.b_btn);
        c_btn = findViewById(R.id.c_btn);
        d_btn = findViewById(R.id.d_btn);

        vt = new Veritabani(this);

        sorularListe = new BayraklarDao().rastgele5Getir(vt);

        soruYukle();

        a_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(a_btn);
                sayacKontrol();
            }
        });

        b_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(b_btn);
                sayacKontrol();
            }
        });

        c_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(c_btn);
                sayacKontrol();
            }
        });

        d_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(d_btn);
                sayacKontrol();
            }
        });
    }

    private void soruYukle() {

        soru_sayisi_txt.setText((soruSayac + 1) + ". SORU");
        dogru_txt.setText("Doğru : " + dogruSayac);
        yanlis_txt.setText("Yanlış : " + yanlisSayac);

        dogruSoru = sorularListe.get(soruSayac);


        yanlislarListe = new BayraklarDao().rastgele3YanlisGetir(vt, dogruSoru.getBayrak_id());

        bayrak_image_view.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(), "drawable", getPackageName()));

        //Tüm seçenekleri hashset yardımıyla karıştırma
        secenekleriKaristirmaListe.clear();
        secenekleriKaristirmaListe.add(dogruSoru);//doğru seçenek
        secenekleriKaristirmaListe.add(yanlislarListe.get(0));
        secenekleriKaristirmaListe.add(yanlislarListe.get(1));
        secenekleriKaristirmaListe.add(yanlislarListe.get(2));

        //Hashset ile butonlara dinamik şekilde yazı yazdıramadığımızdan arraylist dönüşümü yaptık.
        seceneklerListe.clear();

        for (Bayraklar b : secenekleriKaristirmaListe) {
            seceneklerListe.add(b);
        }

        //Secenekleri buttonlara yerleştirdik.
        a_btn.setText(seceneklerListe.get(0).getBayrak_ad());
        b_btn.setText(seceneklerListe.get(1).getBayrak_ad());
        c_btn.setText(seceneklerListe.get(2).getBayrak_ad());
        d_btn.setText(seceneklerListe.get(3).getBayrak_ad());
    }

    public void dogruKontrol(Button button) {

        String button_yazi = button.getText().toString();
        String dogruCevap = dogruSoru.getBayrak_ad();

        Log.e("Doğru", dogruCevap);
        Log.e("ButtonYazi", button_yazi);

        if (button_yazi.equals(dogruCevap)) {
            dogruSayac++;
        } else {
            yanlisSayac++;
        }

        dogru_txt.setText("Doğru : " + (dogruSayac));
        yanlis_txt.setText("Yanlış : " + (yanlisSayac));
    }

    public void sayacKontrol() {

        soruSayac++;

        //soru sayısı 5 olduysa sonuca git
        if (soruSayac != 5) {
            soruYukle();
        } else {
            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
            intent.putExtra("dogruSayac", dogruSayac);
            startActivity(intent);
            finish();
        }
    }
}

package com.example.sorucevapoyunu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;


import androidx.appcompat.app.AppCompatActivity;

import easy.tuto.myquizapplication.SoruCevap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ToplamSorularTextView;
    TextView soruTextView;
    Button cevapA, cevapB, cevapC, cevapD;
    Button gonderBtn;

    int skor = 0;
    int Toplamsorular = SoruCevap.soru.length;
    int SuankiSoruIndeksi = 0;
    String secilencevap = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToplamSorularTextView = findViewById(R.id.toplamsorular);
        soruTextView = findViewById(R.id.soru);
        cevapA = findViewById(R.id.cevapA);
        cevapB = findViewById(R.id.cevapB);
        cevapC = findViewById(R.id.cevapC);
        cevapD = findViewById(R.id.cevapD);
        gonderBtn = findViewById(R.id.buton);

        cevapA.setOnClickListener(this);
        cevapB.setOnClickListener(this);
        cevapC.setOnClickListener(this);
        cevapD.setOnClickListener(this);
        gonderBtn.setOnClickListener(this);

        ToplamSorularTextView.setText("Toplam Sorular: " + Toplamsorular);
    }

    @Override
    public void onClick(View view) {

        cevapA.setBackgroundColor(Color.WHITE);
        cevapB.setBackgroundColor(Color.WHITE);
        cevapC.setBackgroundColor(Color.WHITE);
        cevapD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.buton){
            if(secilencevap.equals(SoruCevap.dogrucevaplar[SuankiSoruIndeksi])){
                skor++;
            }
            SuankiSoruIndeksi++;
            yeniSoruYukle();


        }else{
            secilencevap  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }

    void yeniSoruYukle(){

        if(SuankiSoruIndeksi == Toplamsorular ){
            SoruBitirme();
            return;
        }

        soruTextView.setText(SoruCevap.soru[SuankiSoruIndeksi]);
        cevapA.setText(SoruCevap.secim[SuankiSoruIndeksi][0]);
        cevapB.setText(SoruCevap.secim[SuankiSoruIndeksi][1]);
        cevapC.setText(SoruCevap.secim[SuankiSoruIndeksi][2]);
        cevapD.setText(SoruCevap.secim[SuankiSoruIndeksi][3]);

    }

    void SoruBitirme(){
        String GecmeDurumu = "";
        if(skor > Toplamsorular*0.60){
            GecmeDurumu = "Gecti";
        }else{
            GecmeDurumu = "Kaldı";
        }

        new AlertDialog.Builder(this)
                .setTitle(GecmeDurumu)
                .setMessage(" 4 soruda "+ skor+" doğru  ")
                .setPositiveButton("Restart",(dialogInterface, i) -> yenidenBaslat() )
                .setCancelable(false)
                .show();


    }

    void yenidenBaslat(){
        skor = 0;
        SuankiSoruIndeksi =0;
        yeniSoruYukle();
    }

}

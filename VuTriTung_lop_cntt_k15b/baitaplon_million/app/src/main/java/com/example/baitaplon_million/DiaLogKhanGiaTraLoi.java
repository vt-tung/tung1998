package com.example.baitaplon_million;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DiaLogKhanGiaTraLoi extends Dialog {
    TextView txvChonA, txvChonB, txvChonC, txvChonD;
    ProgressBar progress, progress1, progress2, progress3;
    int counter = 0;
    int counter1 = 0;
    int counter2 = 0;
    int counter3 = 0;
    public DiaLogKhanGiaTraLoi(@NonNull Context context, int vtD) {
        super(context);
        setContentView(R.layout.dialog_tro_giup_khan_gia);
        Button thoat;
        thoat = findViewById(R.id.thoat);
        txvChonA = findViewById(R.id.txvChonA);
        txvChonB = findViewById(R.id.txvChonB);
        txvChonC = findViewById(R.id.txvChonC);
        txvChonD = findViewById(R.id.txvChonD);
        progress = findViewById(R.id.progress);
        progress1 = findViewById(R.id.progress1);
        progress2 = findViewById(R.id.progress2);
        progress3 = findViewById(R.id.progress3);

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaLogKhanGiaTraLoi.super.onBackPressed();
            }
        });

        int arrdrom[] = new int[]{0, 0, 0, 0};
        int can = 25;
        for(int i=0;i<arrdrom.length; i++){
            arrdrom[i] = arrdrom[i]+can;
            if (i==vtD-1){
                arrdrom[i]=arrdrom[i]+25;
                can=can+25;
            }
            can=can+25;
        }
        int tong = 125;
        final int arrPhanTranTl[] = new int[]{0,0,0,0}; // a  b  c d
        final int soKhanGia = 200;
        Random r = new Random();
        for(int i=0;i<soKhanGia;i++){
            int chon = r.nextInt(tong);// 0 25 75 100 125
            if(chon>=0 && chon<arrdrom[0]){
                arrPhanTranTl[0]++;
            }else if(chon>= arrdrom[0] && chon<arrdrom[1]){
                arrPhanTranTl[1]++;
            }else if(chon>= arrdrom[1] && chon<arrdrom[2]){
                arrPhanTranTl[2]++;
            }else {
                arrPhanTranTl[3]++;
            }
        }

        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                progress.setProgress(counter);
                txvChonA.setText("A : "+counter+" %");
                if (counter==((int)(arrPhanTranTl[0]*100.0f/soKhanGia))){
                    t.cancel();
                }
            }
        };
        t.schedule(tt, 0, 120);

        final Timer t1 = new Timer();
        TimerTask tt1 = new TimerTask() {
            @Override
            public void run() {
                counter1++;
                progress1.setProgress(counter1);
                txvChonB.setText("B : "+counter1+" %");
                if (counter1==((int)(arrPhanTranTl[1]*100.0f/soKhanGia))){
                    t1.cancel();
                }
            }
        };
        t1.schedule(tt1, 0, 120);

        final Timer t2 = new Timer();
        TimerTask tt2 = new TimerTask() {
            @Override
            public void run() {
                counter2++;
                progress2.setProgress(counter2);
                txvChonC.setText("C : "+counter2+" %");
                if (counter2==((int)(arrPhanTranTl[2]*100.0f/soKhanGia))){
                    t2.cancel();
                }
            }
        };
        t2.schedule(tt2, 0, 120);

        final Timer t3 = new Timer();
        TimerTask tt3 = new TimerTask() {
            @Override
            public void run() {
                counter3++;
                progress3.setProgress(counter3);
                txvChonD.setText("D : "+counter3+" %");
                if (counter3==((int)(arrPhanTranTl[3]*100.0f/soKhanGia))){
                    t3.cancel();
                }
            }
        };
        t3.schedule(tt3, 0, 120);
    }

}

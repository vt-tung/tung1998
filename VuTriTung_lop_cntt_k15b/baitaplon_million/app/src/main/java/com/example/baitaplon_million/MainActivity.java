package com.example.baitaplon_million;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mysong;
    ViewPager viewpager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);
        addTab(viewpager);
        tabLayout.setupWithViewPager(viewpager);
        if (mysong==null){
            mysong = MediaPlayer.create(MainActivity.this, R.raw.ailatriuephu);
            mysong.setLooping(true);
            mysong.start();
        }
    }

    protected void onPause(){
        super.onPause();
        mysong.release();
    }
    public void addTab(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new start_game(), "Trang Chủ");
        adapter.add(new huong_dan(), "Hướng Dẫn");
        adapter.add(new diemcao(), "Điểm Cao");
        viewPager.setAdapter(adapter);
    }
}
package com.example.baitaplon_million;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    String urlGetdata = "http://192.168.1.3/ailatrieuphu/getdata.php";
    String urlInsert = "http://192.168.1.3/ailatrieuphu/luu_diem.php";
    int viTriCauHoi = 1;
    ArrayList<tienThuongModel> arrayTien;
    tienThuongAdapter tienThuongAdapter;
    FaceData faceData;
    MediaPlayer nhacnen, victory, lose;
    DrawerLayout drawerLayout;
    LinearLayout thuagame;
    RelativeLayout MainScreen;
    RecyclerView lsvTienThuong;
    String cauTraLoi;
    View.OnClickListener listener;
    CauHoi cauHoi;
    FloatingActionButton fab2, fab3, fab4;
    Animation fabOpen, fabClose, rotateFoward, rotateBackward;
    ImageView fab1;
    Button btnLuuDiem, btnTrangChu;
    EditText edtTenNguoiChoi;
    TextView txvCauHoi, txvCauTL1, txvCauTL2, txvCauTL3, txvCauTL4, txtThuaGame, txtLuuDiem, txvTienDangCo;
    ArrayList<TextView> arrTxvCauTraLoi;
    boolean isOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cauHoi = new CauHoi();
        faceData = new FaceData(this);
        arrTxvCauTraLoi = new ArrayList<>();
        arrayTien = new ArrayList<>();
        Tien(urlGetdata);
        anhXa();
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotateFoward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        LinearLayoutManager manager = new LinearLayoutManager(Main2Activity.this, LinearLayoutManager.VERTICAL, false);
        lsvTienThuong.setLayoutManager(manager);
        tienThuongAdapter = new tienThuongAdapter(Main2Activity.this, arrayTien);
        lsvTienThuong.setAdapter(tienThuongAdapter);
        setUp();
        setClick();
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });
        btnLuuDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten   = edtTenNguoiChoi.getText().toString().trim();

                if (hoten.isEmpty()){
                    Toast.makeText(Main2Activity.this, "Vui lòng nhập vào tên của bạn.", Toast.LENGTH_LONG).show();
                }else {
                    LuuDiem(urlInsert);
                }
            }
        });

        btnTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (nhacnen==null){
            nhacnen = MediaPlayer.create(Main2Activity.this, R.raw.nhannen);
            nhacnen.setLooping(true);
            nhacnen.start();
        }
    }

    protected void onPause(){
        super.onPause();
        nhacnen.release();
    }

    public void anhXa(){
        edtTenNguoiChoi = findViewById(R.id.edtTenNguoiChoi);
        btnLuuDiem = findViewById(R.id.btnLuuDiem);
        btnTrangChu = findViewById(R.id.btnTrangChu);
        txvTienDangCo = findViewById(R.id.txvTienDangCo);
        thuagame =findViewById(R.id.ThuaGame);
        MainScreen = findViewById(R.id.MainScreen);
        fab1 =findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        fab4 = findViewById(R.id.fab4);
        lsvTienThuong = findViewById(R.id.lsvTienThuong);
        txvCauHoi = findViewById(R.id.txvCauHoi);
        txvCauTL1 = findViewById(R.id.txvCauTL1);
        txvCauTL2 = findViewById(R.id.txvCauTL2);
        txvCauTL3 = findViewById(R.id.txvCauTL3);
        txvCauTL4 = findViewById(R.id.txvCauTL4);
        txtThuaGame = findViewById(R.id.txtThuaGame);
        txtLuuDiem = findViewById(R.id.txtLuuDiem);
        arrTxvCauTraLoi.add(txvCauTL1);
        arrTxvCauTraLoi.add(txvCauTL2);
        arrTxvCauTraLoi.add(txvCauTL3);
        arrTxvCauTraLoi.add(txvCauTL4);
    }

    private void LuuDiem(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(Main2Activity.this, "Lưu thành công", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Main2Activity.this, MainActivity.class));
                }else{
                    Toast.makeText(Main2Activity.this, "Lỗi không lưu được", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, "Xảy ra lỗi", Toast.LENGTH_LONG).show();
                Log.d("AAA", "Lỗi!\n" + error.toString());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tien", txtLuuDiem.getText().toString().trim());
                params.put("tenNguoiChoi", edtTenNguoiChoi.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void animateFab(){
        if(isOpen){
            fab1.startAnimation(rotateFoward);
            fab2.startAnimation(fabClose);
            fab3.startAnimation(fabClose);
            fab4.startAnimation(fabClose);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);
            isOpen=false;
        }else {
            fab1.startAnimation(rotateBackward);
            fab2.startAnimation(fabOpen);
            fab3.startAnimation(fabOpen);
            fab4.startAnimation(fabOpen);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            isOpen=true;
        }
    }

    public void Tien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i <= response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayTien.add(new tienThuongModel(
                                object.getInt("id"),
                                object.getString("tien")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                tienThuongAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main2Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void setUp(){
        thuagame.setVisibility(View.GONE);
        edtTenNguoiChoi.setVisibility(View.GONE);
        btnLuuDiem.setVisibility(View.GONE);
        hienCauHoi();
    }

    public void setClick(){
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCauTraLoi(((TextView)view));
            }
        };
        for (TextView t:arrTxvCauTraLoi){
            t.setOnClickListener(listener);
        }
    }

    public void checkCauTraLoi(TextView txv){
        cauTraLoi = txv.getText().toString();
        txv.setBackgroundResource(R.drawable.bg_btn_choose);
        new CountDownTimer(2000, 100){

            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
                for (TextView t : arrTxvCauTraLoi){
                    String s = t.getText().toString();
                    if (s.equals(cauHoi.getDapAnDung())){
                        t.setBackgroundResource(R.drawable.bg_btn_true);
                        break;
                    }
                }
                new CountDownTimer(2000, 100){

                    @Override
                    public void onTick(long l) {
                    }
                    @Override
                    public void onFinish() {
                        if (cauTraLoi.equals(cauHoi.getDapAnDung())){
                            txvTienDangCo.setText(arrayTien.get(15-viTriCauHoi).getTien()+" VND");
                            viTriCauHoi++;
                            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawerLayout.openDrawer(GravityCompat.START);
                            if (viTriCauHoi > 15){
                                viTriCauHoi=15;
                                MainScreen.setVisibility(View.GONE);
                                thuagame.setVisibility(View.VISIBLE);
                                edtTenNguoiChoi.setVisibility(View.VISIBLE);
                                btnLuuDiem.setVisibility(View.VISIBLE);
                                txtThuaGame.setText("Chúc mừng bạn đã xuất sắc vượt qua 15 câu hỏi, số tiền thưởng bạn nhận được là: "+arrayTien.get(0).getTien()+" VND");
                                txtLuuDiem.setText(arrayTien.get(0).getTien()+" VND");
                                victory = MediaPlayer.create(Main2Activity.this, R.raw.victory);
                                victory.start();
                                nhacnen.release();
                                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            }

                            hienCauHoi();
                        }else if(viTriCauHoi > 1 && viTriCauHoi <= 10){
                            thuagame.setVisibility(View.VISIBLE);
                            MainScreen.setVisibility(View.GONE);
                            txtThuaGame.setText("Bạn sẽ ra về với tiền thưởng là: "+arrayTien.get(16-viTriCauHoi).getTien()+" VND");
                            nhacthua();
                            Toast.makeText(Main2Activity.this, "Bạn đã trả lời sai.", Toast.LENGTH_SHORT).show();
                        }else if(viTriCauHoi < 2){
                            thuagame.setVisibility(View.VISIBLE);
                            MainScreen.setVisibility(View.GONE);
                            txtThuaGame.setText("Bạn sẽ ra về với tiền thưởng là 0 VND");
                            nhacthua();
                            Toast.makeText(Main2Activity.this, "Bạn đã trả lời sai.", Toast.LENGTH_SHORT).show();
                        }else if (viTriCauHoi > 10){
                            MainScreen.setVisibility(View.GONE);
                            thuagame.setVisibility(View.VISIBLE);
                            thuagame.setVisibility(View.VISIBLE);
                            edtTenNguoiChoi.setVisibility(View.VISIBLE);
                            btnLuuDiem.setVisibility(View.VISIBLE);
                            txtThuaGame.setText("Bạn sẽ ra về với tiền thưởng là: "+arrayTien.get(16-viTriCauHoi).getTien()+" VND");
                            txtLuuDiem.setText(arrayTien.get(16-viTriCauHoi).getTien()+" VND");
                            nhacthua();
                            Toast.makeText(Main2Activity.this, "Bạn đã trả lời sai.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.start();

            }
        }.start();
    }

    public void nhacthua(){
        lose = MediaPlayer.create(Main2Activity.this, R.raw.traloisai);
        lose.start();
        nhacnen.release();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void setCauHoi(){
        cauHoi = faceData.taoCauHoi(viTriCauHoi);
    }
    public void hienCauHoi(){
        setCauHoi();
        txvCauHoi.setText("Câu hỏi số "+viTriCauHoi+": "+cauHoi.getNoiDung()+" ?");
        ArrayList<String> arrCauTraLoi = new ArrayList<>();
        arrCauTraLoi.add(cauHoi.getDapAnSai1());
        arrCauTraLoi.add(cauHoi.getDapAnSai2());
        arrCauTraLoi.add(cauHoi.getDapAnSai3());
        arrCauTraLoi.add(cauHoi.getDapAnDung());
        //Xét ngẫu nhiên thứ tự của đáp án
        Random r = new Random();
        for (int i=0; i<5; i++){
            int vt1  = r.nextInt(arrCauTraLoi.size());
            int vt2  = r.nextInt(arrCauTraLoi.size());
            String a = arrCauTraLoi.get(vt1);
            arrCauTraLoi.set(vt1, arrCauTraLoi.get(vt2));
            arrCauTraLoi.set(vt2,a);
        }
        for(int i=0;i<arrTxvCauTraLoi.size();i++){
            arrTxvCauTraLoi.get(i).setOnClickListener(listener);
            arrTxvCauTraLoi.get(i).setVisibility(View.VISIBLE);
            arrTxvCauTraLoi.get(i).setBackgroundResource(R.drawable.bg_btn);
            arrTxvCauTraLoi.get(i).setText(arrCauTraLoi.get(i));
        }
        tienThuongAdapter.setViTriCauHoi(viTriCauHoi);
    }

    public void mora(View view) {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void onBackPressed(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private void dialog(){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Sự trợ giúp này đã được sử dụng, bạn không thể sử dụng lại nữa.");
        dialogXoa.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        dialogXoa.show();
    }

    boolean trogiup5050 = true;
    public void trogiup5050(View view) {
        if(trogiup5050 == false){
            dialog();
            return;
        }
        animateFab();
        Random r = new Random();
        int sodanAnAnDi = 2;
        do{
            int vitriDapAnAn = r.nextInt(4);
            TextView t = arrTxvCauTraLoi.get(vitriDapAnAn);
            if(t.getVisibility()==View.VISIBLE && t.getText().toString().equals(cauHoi.getDapAnDung())== false){
                t.setVisibility(View.INVISIBLE);
                t.setOnClickListener(null);
                sodanAnAnDi--;
            }
        }while (sodanAnAnDi>0);
        trogiup5050 = false;
    }


    boolean helpdocauhoi = true;
    public void helpdocauhoi(View view) {
        if (helpdocauhoi == false){
            dialog();
            return;
        }
        animateFab();
        hienCauHoi();
        helpdocauhoi = false;
    }

    boolean trogiupkhangia = true;
    public void trogiupkhangiatruongquay(View view) {
        if(trogiupkhangia == false){
            dialog();
            return;
        }
        animateFab();
        for (int i=0;i<arrTxvCauTraLoi.size(); i++){
            TextView t = arrTxvCauTraLoi.get(i);
            if(t.getText().toString().equals(cauHoi.getDapAnDung())){
                new DiaLogKhanGiaTraLoi(this, i+1).show();
                break;
            }
        }
        trogiupkhangia = false;
    }

}

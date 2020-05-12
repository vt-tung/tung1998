package com.example.baitaplon_million;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class diemcao extends Fragment {
    String urlGetdata = "http://192.168.1.3//ailatrieuphu/getdatadiemcao.php";
    String urlDelete = "http://192.168.1.3/ailatrieuphu/deletexoadiemcao.php";
    RecyclerView recycleDiemCao;
    ArrayList<DiemCaoModel> arrayDiem;
    DiemCaoAdapter diemCaoAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_diemcao, null);
        recycleDiemCao = view.findViewById(R.id.recycleDiemCao);
        arrayDiem = new ArrayList<>();
        GridLayoutManager manager1 = new GridLayoutManager(getContext(), 1);
        recycleDiemCao.setLayoutManager(manager1);
        diemCaoAdapter = new DiemCaoAdapter(diemcao.this, arrayDiem);
        recycleDiemCao.setAdapter(diemCaoAdapter);
        ReadJson(urlGetdata);
        return view;
    }

    private void ReadJson(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                arrayDiem.clear();
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayDiem.add(new DiemCaoModel(
                                object.getInt("id"),
                                object.getString("ten"),
                                object.getString("tien")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                diemCaoAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void DeleteDiemCao(final int idten){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    ReadJson(urlGetdata);
                }else{
                    Toast.makeText(getContext(), "Lỗi xóa", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Lỗi server", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idcuadiemcao", String.valueOf(idten));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}

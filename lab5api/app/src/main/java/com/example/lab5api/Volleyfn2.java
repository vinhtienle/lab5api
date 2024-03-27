package com.example.lab5api;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class Volleyfn2 {
    String strJSON="";

    public void getAllDataFromAPI(Context context, TextView textView){
        //tạo request
        RequestQueue queue = Volley.newRequestQueue(context);
        // url
        String url = "https://65d09620ab7beba3d5e362d1.mockapi.io/user";
        // gọi request
        //mảng của các đối tượng -> JsonArrayRequest(url,thanhcong,thatbai)
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Log thông báo khi nhận được phản hồi thành công từ API
                Log.d("API_RESPONSE", "Success! Data received from API.");
                // Xử lý dữ liệu như bình thường
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject person = response.getJSONObject(i);
                        String id = person.getString("id");
                        String name = person.getString("name");
                        String email = person.getString("email");
                        String password = person.getString("password");
                        strJSON += "Id: "+id+"\n";
                        strJSON += "name: "+name+"\n";
                        strJSON += "email: "+email+"\n";
                        strJSON += "password: "+password+"\n";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                textView.setText(strJSON);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                textView.setText("Error: "+volleyError.getMessage());
            }
        });
        queue.add(request); // Thực thi request
    }
}

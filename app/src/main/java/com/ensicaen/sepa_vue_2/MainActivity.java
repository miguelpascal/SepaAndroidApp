//package com.ensicaen.sepa_vue_2;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.ensicaen.sepa_vue_2.ui.login.LoginActivity;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//        startActivity(intent);
//    }

//    @Override
//    protected void onResume() {
////        RequestQueue requestQueue = Volley.newRequestQueue(this);
////        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://10.0.2.2/comptes/",
////            response -> {
////                Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO, "OK");
////                Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO, response);
////            },
////                error -> {
////                    Logger.getLogger(LoginActivity.class.getName()).log(Level.INFO, "KO");
////                    Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE,error.toString());
////                });
////        requestQueue.add(stringRequest);
//
//        super.onResume();
//    }
//}
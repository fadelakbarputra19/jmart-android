package com.fadelJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import com.fadelJmartPK.jmart_android.model.Account;
import com.fadelJmartPK.jmart_android.request.LoginRequest;
import com.fadelJmartPK.jmart_android.request.RegisterRequest;

/**
 * Activity menampilkan detail produk yang ada
 * @author Muhammad Fadel Akbar Putra
 */
public class RegisterActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private static Account loggedAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText textName = findViewById(R.id.InputNameRegister);
        EditText textEmail = findViewById(R.id.InputEmailRegister);
        EditText textPassword = findViewById(R.id.InputPasswordRegister);
        Button buttonRegister = findViewById(R.id.ButtonRegister);




        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object != null){
                                Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                loggedAccount = gson.fromJson(object.toString(), Account.class);
                                startActivity(intent);
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Error!", Toast.LENGTH_SHORT);
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(textName.getText().toString(), textEmail.getText().toString(), textPassword.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });

    }
}
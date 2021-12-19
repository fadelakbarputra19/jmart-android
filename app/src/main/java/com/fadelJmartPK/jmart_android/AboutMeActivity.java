package com.fadelJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fadelJmartPK.jmart_android.model.Store;
import com.fadelJmartPK.jmart_android.request.RegisterStoreRequest;
import com.fadelJmartPK.jmart_android.request.TopUpRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class AboutMeActivity extends AppCompatActivity {
    Store store = null;
    private static final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_about_me);



        TextView name = (TextView) findViewById(R.id.nameOutputAccount);
        name.setText("" + LoginActivity.getLoggedAccount().name);

        TextView email = (TextView) findViewById(R.id.emailOutputAccount);
        email.setText("" + LoginActivity.getLoggedAccount().email);

        TextView balance = (TextView) findViewById(R.id.balanceOutputAccount);
        balance.setText("" + LoginActivity.getLoggedAccount().balance);

        EditText inputBalance = (EditText) findViewById(R.id.topupInputAccount);

        Button buttonTopUP = (Button) findViewById(R.id.topupButtonAccount);


        inputBalance.setText("0");

        buttonTopUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> listenerTopUp = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(Boolean.parseBoolean(response)){
                            Toast.makeText(AboutMeActivity.this, "Topup berhasil", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AboutMeActivity.this, "Topup error!", Toast.LENGTH_SHORT).show();
                        }
                        LoginActivity.loggedAccount.balance += Double.parseDouble(inputBalance.getText().toString());
                        finish();
                        overridePendingTransition(0,0);
                        startActivity(getIntent());
                    }
                };
                TopUpRequest topUpRequest = new TopUpRequest(LoginActivity.getLoggedAccount().id, Double.parseDouble(inputBalance.getText().toString()), listenerTopUp, null);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(topUpRequest);
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerStoreButton);
        Button registerStore = (Button) findViewById(R.id.buttonRegisterStore);
        Button cancelRegister = (Button) findViewById(R.id.cancelRegisterStore);
        //Button history = findViewById(R.id.historyButton);

        CardView cardRegister = (CardView) findViewById(R.id.cardRegister);
        CardView cardStore = (CardView) findViewById(R.id.cardStoreInfo);

        EditText storeName = (EditText) findViewById(R.id.inputNameRegisterStore);
        EditText storeAddress = (EditText) findViewById(R.id.inputAddressRegisterStore);
        EditText storePhone = (EditText) findViewById(R.id.inputPhoneRegisterStore);

        registerButton.setVisibility(View.GONE);
        cardRegister.setVisibility(View.GONE);
        cardStore.setVisibility(View.GONE);

        if (LoginActivity.getLoggedAccount().store == null) {
            registerButton.setVisibility(View.VISIBLE);
        } else if (LoginActivity.getLoggedAccount().store != null) {
            TextView dataName = (TextView) findViewById(R.id.dataNameTextAbout);
            dataName.setText("" + LoginActivity.getLoggedAccount().store.name);
            TextView dataAddress = (TextView) findViewById(R.id.dataAddressTextAbout);
            dataAddress.setText("" + LoginActivity.getLoggedAccount().store.address);
            TextView dataPhone = (TextView) findViewById(R.id.dataPhoneTextAbout);
            dataPhone.setText("" + LoginActivity.getLoggedAccount().store.phoneNumber);
            cardStore.setVisibility(View.VISIBLE);
        } else {
            registerButton.setVisibility(View.VISIBLE);
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardRegister.setVisibility(View.VISIBLE);
                cancelRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardRegister.setVisibility(View.GONE);
                        registerButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        registerStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response.Listener<String> listenerStore = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            LoginActivity.loggedAccount.store = gson.fromJson(object.toString(),Store.class);
                            System.out.println(LoginActivity.loggedAccount.store);
                            finish();
                            overridePendingTransition(0,0);
                            startActivity(getIntent());
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                RegisterStoreRequest requestStore = new RegisterStoreRequest(LoginActivity.getLoggedAccount().id,storeName.getText().toString(),storeAddress.getText().toString(),storePhone.getText().toString(),listenerStore,null);
                RequestQueue requestQueueStore = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueueStore.add(requestStore);
            }
        });

//        history.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AboutMeActivity.this, StoreInvoiceActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
package com.fadelJmartPK.jmart_android;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;


public class ProductDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        TextView name = (TextView) findViewById(R.id.productName);
        TextView weight = (TextView) findViewById(R.id.weightProduct);
        TextView conditionProduct = (TextView) findViewById(R.id.conditionProduct);
        TextView price = (TextView) findViewById(R.id.priceProduct);
        TextView discount = (TextView) findViewById(R.id.discountProduct);
        TextView category = (TextView) findViewById(R.id.categoryProduct);
        TextView shipmentPlans = (TextView) findViewById(R.id.shipmentPlansProduct);
        Button buyButton = findViewById(R.id.checkout_button);
        Button backButton = findViewById(R.id.back_button);

        name.setText(fragment1.productClicked.name);
        weight.setText(String.valueOf(fragment1.productClicked.weight));
        conditionProduct.setText(convertConditionUsed(fragment1.productClicked.conditionUsed));
        price.setText("Rp." + String.valueOf(fragment1.productClicked.price));
        discount.setText(String.valueOf(fragment1.productClicked.discount));
        category.setText(String.valueOf(fragment1.productClicked.category));
        shipmentPlans.setText(convertShipmentPlans(fragment1.productClicked.shipmentPlans));

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String convertShipmentPlans(byte shipment){
        switch (shipment) {
            case 0:
                return "INSTANT";
            case 1:
                return "SAME DAY";
            case 2:
                return "NEXT DAY";
            case 3:
                return "REGULER";
            default:
                return "CARGO";
        }
    }

    private String convertConditionUsed(boolean conditionUsed){
        if (conditionUsed) {
            return "Used";
        }else{
            return "New";
        }
    }

}
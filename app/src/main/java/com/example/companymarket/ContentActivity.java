package com.example.companymarket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {
    TextView tv_getProductName, tv_getProductPrice, tv_getProductStatus, tv_getProductContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        tv_getProductName = findViewById(R.id.getProductName);
        tv_getProductPrice= findViewById(R.id.getProductPrice);
        tv_getProductStatus = findViewById(R.id.getProductStatus);
        tv_getProductContent = findViewById(R.id.getProductContent);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String getProductName = bundle.getString("product_name");
        String getProductPrice = bundle.getString("product_price");
        String getProductStatus = bundle.getString("product_status");

        tv_getProductName.setText(getProductName);
        tv_getProductPrice.setText(getProductPrice);
        tv_getProductStatus.setText(getProductStatus);
    }


}

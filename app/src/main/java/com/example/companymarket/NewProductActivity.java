package com.example.companymarket;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewProductActivity extends AppCompatActivity {
    EditText edt_productName, edt_productPrice, edt_productStatus, edt_productContent;
    Button btn_addProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newproduct);

        edt_productName = findViewById(R.id.edt_productName);
        edt_productPrice = findViewById(R.id.edt_productPrice);
        edt_productStatus = findViewById(R.id.edt_productStatus);
        edt_productContent = findViewById(R.id.edt_productContent);
        btn_addProduct = findViewById(R.id.btn_addProduct);

        btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

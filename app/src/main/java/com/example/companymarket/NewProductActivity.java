package com.example.companymarket;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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

                FirebaseDatabase database = FirebaseDatabase.getInstance(); // 파이어 데이터베이스 연동

                DatabaseReference databaseReference = database.getReference(); // 파이어베이스 테이블 연결

                databaseReference.child("Product").push().setValue(new Product("gs://companymarket-122c7.appspot.com/사진.png",edt_productName.getText().toString(),Integer.parseInt(edt_productPrice.getText().toString()),edt_productStatus.getText().toString(),edt_productContent.getText().toString()));
                //Map<String,Product> productMap = new HashMap<>();
                //productMap.put("test",new Product("",edt_productName.getText().toString(),Integer.parseInt(edt_productPrice.getText().toString()),edt_productStatus.getText().toString(),edt_productContent.getText().toString()));

                //product.setValue(productMap);
            }
        });
    }
}

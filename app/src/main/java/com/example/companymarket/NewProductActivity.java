package com.example.companymarket;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class NewProductActivity extends AppCompatActivity {
    EditText edt_productName, edt_productPrice, edt_productStatus, edt_productContent;
    Button btn_addProduct, btn_addImage;
    ImageView iv_preview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newproduct);

        edt_productName = findViewById(R.id.edt_productName);
        edt_productPrice = findViewById(R.id.edt_productPrice);
        edt_productStatus = findViewById(R.id.edt_productStatus);
        edt_productContent = findViewById(R.id.edt_productContent);
        btn_addProduct = findViewById(R.id.btn_addProduct);
        btn_addImage = findViewById(R.id.btn_addImage);
        iv_preview = findViewById(R.id.iv_preview);

        FirebaseStorage storage = FirebaseStorage.getInstance(); //스토리지 인스턴스 만들기
        StorageReference storageReference = storage.getReference(); //스토리지 참조

        btn_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent,10);
            }
        });


        btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance(); // 파이어 데이터베이스 연동
                DatabaseReference databaseReference = database.getReference(); // 파이어베이스 테이블 연결

                databaseReference.child("Product").push().setValue(new Product("https://firebasestorage.googleapis.com/v0/b/companymarket-122c7.appspot.com/o/%EC%82%AC%EC%A7%84.png?alt=media&token=1b3cff9d-33bd-4042-870d-e191c55515f7",edt_productName.getText().toString(),Integer.parseInt(edt_productPrice.getText().toString()),edt_productStatus.getText().toString(),edt_productContent.getText().toString()));
                //Map<String,Product> productMap = new HashMap<>();
                //productMap.put("test",new Product("",edt_productName.getText().toString(),Integer.parseInt(edt_productPrice.getText().toString()),edt_productStatus.getText().toString(),edt_productContent.getText().toString()));

                //product.setValue(productMap);
            }
        });
    }
}

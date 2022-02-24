package com.example.companymarket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class NewProductActivity extends AppCompatActivity {
    EditText edt_productName, edt_productPrice, edt_productStatus, edt_productContent;
    Button btn_addProduct;
    ImageButton btn_addImage;
    ImageView iv_preview;
    final int RALLERY_CODE = 10;
    FirebaseStorage storage;
    Uri file;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        file = data.getData();
        StorageReference storageRef = storage.getReference(); //스토리지 참조
        StorageReference riverRef = storageRef.child("photh/1.png");
        UploadTask uploadTask = riverRef.putFile(file);

        try {
            InputStream inputStream = getContentResolver().openInputStream(data.getData());
            Bitmap img = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            iv_preview.setImageBitmap(img);
        } catch (Exception e) {
            e.printStackTrace();
        }

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewProductActivity.this, "Fail",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(NewProductActivity.this, "Success",Toast.LENGTH_SHORT).show();
            }
        });
    }

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


        FirebaseDatabase database = FirebaseDatabase.getInstance(); // 파이어 데이터베이스 연동
        DatabaseReference databaseReference = database.getReference(); // 파이어베이스 테이블 연결

        storage = FirebaseStorage.getInstance(); //스토리지 인스턴스 만들기


        btn_addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent,RALLERY_CODE);
            }
        });


        btn_addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child("Product").push().setValue(new Product(file,edt_productName.getText().toString(),Integer.parseInt(edt_productPrice.getText().toString()),edt_productStatus.getText().toString(),edt_productContent.getText().toString()));
                Log.d("file_data", String.valueOf(file));
                //Map<String,Product> productMap = new HashMap<>();
                //productMap.put("test",new Product("",edt_productName.getText().toString(),Integer.parseInt(edt_productPrice.getText().toString()),edt_productStatus.getText().toString(),edt_productContent.getText().toString()));

                //product.setValue(productMap);
            }
        });
    }
}

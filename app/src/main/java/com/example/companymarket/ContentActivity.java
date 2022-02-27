package com.example.companymarket;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 10;

    TextView tv_getProductName, tv_getProductPrice, tv_getProductStatus, tv_getProductContent;
    ImageView iv_getProductImage;
    Button btn_chat;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ArrayList<User> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        // SecurityException:Permission Denial: reading com.android.providers.media.MediaProvider uri content: 에러 발생시
        // API 23이상부터 권한을 기기에서 직접 허락
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }

        iv_getProductImage = findViewById(R.id.getProductImage);
        tv_getProductName = findViewById(R.id.getProductName);
        tv_getProductPrice= findViewById(R.id.getProductPrice);
        tv_getProductStatus = findViewById(R.id.getProductStatus);
        tv_getProductContent = findViewById(R.id.getProductContent);
        btn_chat = findViewById(R.id.btn_chat);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String getProductImage = bundle.getString("product_Image");
        String getProductUid = bundle.getString("product_Uid");
        String getProductName = bundle.getString("product_name");
        String getProductPrice = bundle.getString("product_price");
        String getProductStatus = bundle.getString("product_status");
        String getProductContent = bundle.getString("product_content");


        Log.d("image_data",getProductImage);
        Glide.with(this).load(getProductImage).into(iv_getProductImage);
        tv_getProductName.setText(getProductName);
        tv_getProductPrice.setText(getProductPrice);
        tv_getProductStatus.setText(getProductStatus);
        tv_getProductContent.setText(getProductContent);

        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance(); // 파이어 데이터베이스 연동
        databaseReference = database.getReference("User"); // 파이어베이스 User테이블 연결


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // 파이어 베이스 데이터 받아옴
                arrayList.clear(); //배열리스트 초기화
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    User user = snapshot1.getValue(User.class);
                    arrayList.add(user);
                    Log.d("user", String.valueOf(arrayList));
                    System.out.println( arrayList.get(0).getId());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("user_error", String.valueOf(error.toException()));
            }
        });

        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ContentActivity.this, ChatActivity.class);
                intent1.putExtra("Chat_Uid",getProductUid);
                startActivity(intent1);
            }
        });
    }


}

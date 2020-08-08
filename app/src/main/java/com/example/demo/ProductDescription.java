package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

public class ProductDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        Button book = findViewById(R.id.Book);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   Intent intent1 = new Intent(getApplicationContext(),AddressInfo.class);
                    startActivity(intent1);

                }




    });
}
}
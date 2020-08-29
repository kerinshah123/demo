package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class ProductDescription extends AppCompatActivity {

    ImageView bookimage;
    TextView bookname,bookauthor,bookdescription,bookprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        final Button book = findViewById(R.id.Book);
        bookimage = findViewById(R.id.carDetailsImage);
        bookname  =findViewById(R.id.carDetailsName);
        bookauthor = findViewById(R.id.carDetailsPrice);
        bookdescription = findViewById(R.id.carDetailsDescription);
        bookprice = findViewById(R.id.bookprice);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id").trim();

        db.collection("products_master").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        bookname.setText((CharSequence) document.get("product_name"));
                        bookauthor.setText((CharSequence) document.get("product_name"));
                        bookdescription.setText((CharSequence) document.get("product_description"));
                        bookprice.setText((CharSequence) document.get("product_rates"));
                        Picasso.with(getApplicationContext())
                                .load(String.valueOf((CharSequence)document.get("product_image")))
                                .into(bookimage);

                    }
                });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), AddressInfo.class);
                startActivity(intent1);
            }


        });
    }
}
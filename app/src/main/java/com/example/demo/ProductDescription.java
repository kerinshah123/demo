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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ProductDescription extends AppCompatActivity {

    ImageView bookimage;
    TextView bookname,bookauthor,bookdescription,bookprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        final Button book = findViewById(R.id.Book);
        bookimage = findViewById(R.id.desc_image);
        bookname  =findViewById(R.id.carDetailsName);
        bookauthor = findViewById(R.id.carDetailsPrice);
        bookdescription = findViewById(R.id.carDetailsDescription);
        bookprice = findViewById(R.id.bookprice);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id").trim();

        db.collection("books").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        bookname.setText((CharSequence) document.get("book_name"));
                        bookauthor.setText((CharSequence) document.get("book_author"));
                        bookdescription.setText((CharSequence) document.get("book_description"));
                        bookprice.setText((CharSequence) document.get("book_price"));
                        String link = (String)document.get("book_image");
                        System.out.println(link);
                        Glide.with(getApplicationContext()).load(link).into(bookimage);
//                        Picasso.get()
//                                .load(link)
//                                .into(bookimage);

                    }
                });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Map<String, Object> user = new HashMap<>();
//                user.put("id", id);
//                user.put("price", );


                Intent intent1 = new Intent(getApplicationContext(), AddressInfo.class);
                startActivity(intent1);
            }


        });
    }
}
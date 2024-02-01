package com.example.letestmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity2 extends AppCompatActivity {

   // ImageView ;
    String tit,inf,over,relese,ratting,postr;
    ImageView img;
    TextView title,info,overview,rattings,rele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        title=findViewById(R.id.title1);
        overview=findViewById(R.id.overview);
        rattings=findViewById(R.id.language);
        rele=findViewById(R.id.release);
        img=findViewById(R.id.imageView2);


        Intent intent=getIntent();
        tit=intent.getStringExtra("title");
        inf=intent.getStringExtra("info");
        over=intent.getStringExtra("overview");
        postr=intent.getStringExtra("poster");
        relese=intent.getStringExtra("relese");
        ratting=intent.getStringExtra("ratting");

        Glide.with(this)
                .load("https://image.tmdb.org/t/p/original"+postr)
                .into(img);

        title.setText(tit);
       // info.setText(inf);
        overview.setText(over);
        rattings.setText(ratting);
        rele.setText(relese);




    }
}
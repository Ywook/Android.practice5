package com.examples.androidpractice5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView txtname, etmenu1, etmenu2, etmenu3;
    TextView tvTel, tvURL, tvRegDate;
    Button btnback;

    ImageView imageView2, imageView3, imgno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        init();
    }

    void init(){
        int n;

        txtname = (TextView) findViewById(R.id.textname);
        etmenu1 = (TextView) findViewById(R.id.etmenu1);
        etmenu2 = (TextView) findViewById(R.id.etmenu2);
        etmenu3 = (TextView) findViewById(R.id.etmenu3);

        imgno = (ImageView) findViewById(R.id.imgno);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);

        tvTel = (TextView) findViewById(R.id.tvTel);
        tvURL = (TextView) findViewById(R.id.tvURL);
        tvRegDate = (TextView) findViewById(R.id.tvRegdate);

        Intent intent = getIntent();

        restaurant r =intent.getParcelableExtra("restaurant");

        txtname.setText(r.getName());

        String[] menu = r.getMenu();

        etmenu1.setText(menu[0]);
        etmenu2.setText(menu[1]);
        etmenu3.setText(menu[2]);

        n = r.getType();

        imgno.setImageResource(setImage(n));

        final String tel = r.getTell();
        final String h = r.getHomepage();


        tvTel.setText(tel);
        tvURL.setText(h);
        tvRegDate.setText(r.getDate());

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/" + tel));
                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(h));
                startActivity(intent);
            }
        });
    }

    int setImage(int n){
        if(n==1){
            return R.drawable.chicken;
        }else if(n==2){
            return R.drawable.pizza;
        }else{
            return R.drawable.hamburger;
        }
    }

    public void onClick(View v){
        if(v.getId()==R.id.btnback){
            finish();
        }
    }
}

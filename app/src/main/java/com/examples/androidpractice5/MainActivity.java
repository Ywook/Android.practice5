package com.examples.androidpractice5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;

    ArrayAdapter<String> adapter;

    TextView tv;

    int Addition = 1;

    ArrayList<restaurant> storage = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    void init(){
        listview = (ListView)findViewById(R.id.listview);
        tv = (TextView)findViewById(R.id.tv);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, title);

        listview.setAdapter(adapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                final int position = i;
                dlg.setTitle("삭제")
                        .setMessage("삭제하시겠습니까 ?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                title.remove(position);
                                storage.remove(position);
                                adapter.notifyDataSetChanged();
                                tv.setText("맛집 리스트("+title.size()+"개)");
                            }
                        }).show();
                return true;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);

                intent.putExtra("restaurant", storage.get(i));

                startActivity(intent);
            }
        });
    }

    public void onClick(View v){
        Intent intent = new Intent(this, Main2Activity.class);

        startActivityForResult(intent, Addition);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Addition){
            if(resultCode == RESULT_OK){
                restaurant r = data.getParcelableExtra("restaurant");
                title.add(r.getName());
                storage.add(r);
                adapter.notifyDataSetChanged();
                tv.setText("맛집 리스트("+title.size()+"개)");
            }
        }
    }
}

package com.example.ndp_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {

    Button btnShow;
    ListView lvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShow = findViewById(R.id.btnShowList);
        lvShow = findViewById(R.id.lv5starSongs);
        ArrayList<Song> al;
        ArrayAdapter<Song> aa;

        DBHelper dbh = new DBHelper(this);
        al = dbh.getAllSongs();
        dbh.close();

        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1,al);
        lvShow.setAdapter(aa);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(secondActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongsbyStars(5));
                aa.notifyDataSetChanged();
            }
        });

        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(secondActivity.this, thirdActivity.class);
                i.putExtra("song", (Parcelable) al.get(position));
                startActivity(i);
            }
        });

    }
}
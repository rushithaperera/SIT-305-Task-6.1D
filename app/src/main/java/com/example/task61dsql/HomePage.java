package com.example.task61dsql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePage extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;

    String names[], description[];
    int images[] = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4, R.drawable.food5, R.drawable.food6, R.drawable.food7,R.drawable.food8,R.drawable.food9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        floatingActionButton = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.recyclerView);

        names = getResources().getStringArray(R.array.city_names);
        description = getResources().getStringArray(R.array.description);

        MyAdapter myAdapter = new MyAdapter(this, names, description, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, AddFood.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu3){
            Intent intent = new Intent(this, MyList.class);
            startActivity(intent);
        }

        if(id == R.id.menu1){
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
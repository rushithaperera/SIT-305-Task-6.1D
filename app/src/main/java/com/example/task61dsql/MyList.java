package com.example.task61dsql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyList extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description;

    String data1, data2;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        getData();
        setData();
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

    private void getData(){
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            myImage = getIntent().getIntExtra("myImage", 1);
        }else{
            Toast.makeText(this,"Data not Available", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        title.setText(data1);
        title.setTextColor(Color.parseColor("#000000"));
        description.setText(data2);
        description.setTextColor(Color.parseColor("#000000"));
        mainImageView.setImageResource(myImage);
    }
}
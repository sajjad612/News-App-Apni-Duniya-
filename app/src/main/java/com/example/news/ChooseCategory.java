package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ChooseCategory extends AppCompatActivity {

    private ImageView SelectChannel,SelectCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        SelectChannel = (ImageView)findViewById(R.id.idIVSelectChannel);
        SelectCategory = (ImageView) findViewById(R.id.idIVSelectCategory);

        SelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCategory.this,categoryMain.class);
                startActivity(intent);
            }
        });

        SelectChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCategory.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
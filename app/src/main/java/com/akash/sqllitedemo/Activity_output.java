package com.akash.sqllitedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_output extends AppCompatActivity {
TextView txt_roll,txt_name,txt_gender,txt_date;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        txt_roll = findViewById(R.id.txt_roll);
        txt_name= findViewById(R.id.txt_name);
     txt_gender = findViewById(R.id.txt_gender);
     txt_date=findViewById(R.id.txt_date);
        img  = findViewById(R.id.img_show);
     DatabaseHandler databaseHandler = new DatabaseHandler(this);
//     databaseHandler.getDetails(1);

     txt_roll.setText(databaseHandler.getDetails(1).getRoll());
     txt_name.setText(databaseHandler.getDetails(1).getName());
     txt_gender.setText(databaseHandler.getDetails(1).getGender());
     txt_date.setText(databaseHandler.getDetails(1).getDate());
byte[] image = databaseHandler.getDetails(1).getImage();
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        img.setImageBitmap(Bitmap.createScaledBitmap(bmp, 200,
                200, false));




    }
}

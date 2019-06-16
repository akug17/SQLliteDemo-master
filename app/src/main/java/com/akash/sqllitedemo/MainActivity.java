package com.akash.sqllitedemo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.sqllitedemo.Model.Details;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {
    TextView txt_setdate;
    EditText txt_roll, txt_name;
    RadioGroup radioGroup;
    Button btn_save;
    RadioButton radioButton;
    Button btn_show;
    String currentDateString;
byte[] data;
    ImageView img;
    DatabaseHandler db = new DatabaseHandler(this);
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img_gallery);

        txt_roll = findViewById(R.id.edt_roll);
        txt_name = findViewById(R.id.edt_name);
        radioGroup = findViewById(R.id.radio_grp);
        btn_save = findViewById(R.id.btn_save);
        btn_show = findViewById(R.id.btn_show);
        txt_setdate = findViewById(R.id.txt_setdate);
        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);

            }
        });
        txt_setdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new Datepickerfragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_details();

            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Activity_output.class);
                startActivity(intent);


            }
        });


    }

    private void add_details() {

        String roll = txt_roll.getText().toString();
        String name = txt_name.getText().toString();
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        String gender = radioButton.getText().toString();
        img.setDrawingCacheEnabled(true);
        img.buildDrawingCache();
        Bitmap bitmap = img.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        data = baos.toByteArray();


        Details details = new Details(roll, name, gender, currentDateString,data);

        db.add_details(details);
        Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());


        txt_setdate.setText(currentDateString);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                img.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(MainActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }


    }
}


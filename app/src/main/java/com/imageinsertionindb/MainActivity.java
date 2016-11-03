package com.imageinsertionindb;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DBHelper dbHelper;
    private Bitmap bitmap;
    private Button mBtnPickImage;
    private Button mBtnGetImageFromDB;
    private Button mBtnSaveImageInDB;
    private ImageView mIvImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          dbHelper = new DBHelper(this);
        mBtnPickImage = (Button) findViewById(R.id.btn_take_image);
        mBtnSaveImageInDB = (Button) findViewById(R.id.btn_save_image_in_db);
        mBtnGetImageFromDB = (Button) findViewById(R.id.btn_get_image_from_db);
        mIvImage = (ImageView) findViewById(R.id.iv_image);
        mBtnPickImage.setOnClickListener(this);
        mBtnSaveImageInDB.setOnClickListener(this);
        mBtnGetImageFromDB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_image:
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);


                break;
            case R.id.btn_save_image_in_db:
                byte[] image=  getBytes(bitmap);
                dbHelper.addImageToDB(image);
                break;
            case R.id.btn_get_image_from_db:
                bitmap=dbHelper.getImageFromDB(0);
                mIvImage.setImageBitmap(bitmap);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3 && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                mIvImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
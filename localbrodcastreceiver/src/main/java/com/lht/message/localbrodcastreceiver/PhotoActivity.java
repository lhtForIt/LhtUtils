package com.lht.message.localbrodcastreceiver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by lht on 2017/2/4.
 */

public class PhotoActivity extends AppCompatActivity {

    private ImageView iv;
    private Button bt;
    private Uri imageUri;
    private static int TAKE_PHOTO = 1;
    private static int CROP_PHOTO = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);

        iv = (ImageView) findViewById(R.id.iv);
        bt = (Button) findViewById(R.id.bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(Environment.getExternalStorageDirectory(),"test.jpg");
                //后期要修改的

                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(outputImage);
                Intent intent= new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Intent in = new Intent("com.android.camera.action.CROP");
                    in.setDataAndType(imageUri,"image/*");
                    in.putExtra("scale", true);
                    in.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(in, CROP_PHOTO);
                }
                break;
            case 2:

                if (resultCode == RESULT_OK) {
                        try {
                            Thread.sleep(1000);
                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                            iv.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                }

                break;
        }
    }
}

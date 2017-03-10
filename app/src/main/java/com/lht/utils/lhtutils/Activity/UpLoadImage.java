package com.lht.utils.lhtutils.Activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lht.utils.lhtutils.R;
import com.lht.utils.lhtutils.TuKu.PickPhotoActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lht on 2017/3/6.
 */

public class UpLoadImage extends BaseActivity {

    private Button bt;
    private String mImagePath;
    private PopupWindow pop;
    TextView tv;
    TextView tv1;
    TextView tv2;
    private String photoPath;
    private Map<String, Object> map = new HashMap<>();
    String url;
    Uri imageFileUri;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadimage);

        url = "http://app.680.com/api/upload.ashx";
        bt = (Button) findViewById(R.id.bt1);
        pop = new PopupWindow(UpLoadImage.this);
        View v = LayoutInflater.from(UpLoadImage.this).inflate(R.layout.selectview, null);
        tv = (TextView) v.findViewById(R.id.btn_take_photo);
        tv1 = (TextView) v.findViewById(R.id.btn_pick_photo);
        tv2 = (TextView) v.findViewById(R.id.btn_cancel);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                if (ContextCompat.checkSelfPermission(UpLoadImage.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(UpLoadImage.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UpLoadImage.this,
                            new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA"},
                            12546);
                } else {
//如果已经拥有权限则直接操作
                    takePhoto();
                }

            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pop.dismiss();
                if (ContextCompat.checkSelfPermission(UpLoadImage.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(UpLoadImage.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UpLoadImage.this,
                            new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA"},
                            12546);
                } else {
//如果已经拥有权限则直接操作
                    openPickPhoto();



                }

            }
        });

        pop.setContentView(v);
        pop.setHeight(400);
        pop.setWidth(800);
        pop.setAnimationStyle(R.style.popWindow_animation);
        //设置SelectPicPopupWindow弹出窗体的背景
        pop.setBackgroundDrawable(new ColorDrawable(0x00000000));
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("lhtt", "======================进来了");
                pop.showAtLocation(findViewById(R.id.ll), Gravity.BOTTOM, 0, 0);
                Log.d("lhtt", "======================进来了1");


            }
        });


    }


    private void takePhoto() {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {
            /**
             * 通过指定图片存储路径，解决部分机型onActivityResult回调 data返回为null的情况
             */
            //获取与应用相关联的路径
            String imageFilePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
//            //根据当前时间生成图片的名称
            String timestamp = "/" + formatter.format(new Date()) + ".jpg";
            File imageFile = new File(imageFilePath);// 通过路径创建保存文件
            mImagePath = imageFile.getAbsolutePath();
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);// 告诉相机拍摄完毕输出图片到指定的Uri
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DATA, imageFile.getAbsolutePath());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            imageFileUri = getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            startActivityForResult(intent, 0x123);
        } else {
            Toast.makeText(this, "内存卡不存在！", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1112) {
            File f = new File(PickPhotoActivity.stringList.get(0));
            OkhttpTool.getOkHttpTool().upLoadImage(url + "?type=sfz", f, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    Log.d("lhtt", "=================IOException:" + e.toString());


                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String s = response.body().string();
                    Log.d("lhttt", "=============================Response:" + s);

                }
            });
        }


        if (resultCode == RESULT_OK) {
            if (null != data) {
                //指定了路径，所以没有data
                if (data.hasExtra("data")) {

                    String name = new DateFormat().format("yyyymmdd",
                            Calendar.getInstance(Locale.CHINA))
                            + ".jpg";
                    Bitmap bmp = data.getParcelableExtra("data");// 解析返回的图片成bitmap


                    // 保存文件
                    FileOutputStream fos = null;
                    File file = new File(mImagePath);
                    if (!file.exists()) {
                        file.mkdirs();// 创建文件夹

                    }
                    String fileName = mImagePath + name;// 保存路径

                    try {// 写入SD card
                        fos = new FileOutputStream(fileName);
                        bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    photoPath = fileName;
                    Log.d("lhtt", "=======================photoPath:" + photoPath);
                    File f = new File(photoPath);
                    map.put("imgFile", f);
                    Log.d("lhtt", "==================1");
                    OkhttpTool.getOkHttpTool().upLoadImage(url + "?type=sfz", f, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                            Log.d("lhtt", "=================IOException:" + e.toString());


                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String s = response.body().string();
                            Log.d("lhttt", "=============================Response:" + s);
                        }
                    });


                } else {
                    Toast.makeText(UpLoadImage.this, "没有data", Toast.LENGTH_SHORT).show();
                }
            } else {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = managedQuery(imageFileUri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                String img_path = actualimagecursor.getString(actual_image_column_index);
                File file = new File(img_path);
                OkhttpTool.getOkHttpTool().upLoadImage(url + "?type=sfz", file, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        Log.d("lhtt", "=================IOException:" + e.toString());


                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s = response.body().string();
                        Log.d("lhttt", "=============================Response:" + s);
                    }
                });
            }
        }

    }

    private void openPickPhoto() {

        Intent in=new Intent(this, PickPhotoActivity.class);
        in.putExtra("number",String.valueOf(1));
        startActivityForResult(in,111);

    }


}

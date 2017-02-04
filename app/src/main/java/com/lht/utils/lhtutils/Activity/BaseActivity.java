package com.lht.utils.lhtutils.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lht on 2017/1/17.
 */

public class BaseActivity extends AppCompatActivity {

    protected static String TAG = "BaseActivity";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

        super.onCreate(savedInstanceState, persistentState);


        ActivityController.addActivity(this);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }


}

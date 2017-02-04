package com.lht.utils.lhtutils.Activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lht on 2017/1/17.
 */

public class ActivityController {

    public static List<Activity> act_list = new ArrayList<>();

    public static void addActivity(Activity activity){
        act_list.add(activity);
    }

    public static void removeActivity(Activity activity){
        act_list.remove(activity);
    }

    public static void finishAll(){

        for (Activity activity : act_list) {
            if(!activity.isFinishing()){
                activity.finish();
            }
        }



    }







}

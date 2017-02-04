package utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.system.StructStat;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by lht on 2017/2/4.
 */

public class PhotoUtils {

    private int number;
    private String type;
    private boolean favo;

    /*
    *
    *三类：
    * 模糊，一般，高清
    *
    * */

    private String Clarity;

    public static void file(String photoName) {
        File outputImage = new File(Environment.getExternalStorageDirectory(),photoName);
        //后期要修改的

        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


}

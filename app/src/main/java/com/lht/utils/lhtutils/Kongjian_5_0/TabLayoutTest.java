package com.lht.utils.lhtutils.Kongjian_5_0;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.widget.TextView;

import com.lht.utils.lhtutils.Activity.BaseActivity;
import com.lht.utils.lhtutils.R;

/**
 * Created by lht on 2017/3/9.
 */

public class TabLayoutTest extends BaseActivity{

    TabLayout tab;
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayouttest);


        tab = (TabLayout) findViewById(R.id.tab);
        tv = (TextView) findViewById(R.id.tv);
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getText().toString()) {
                    case "貂蝉":
                        tv.setText("貂蝉被选了");
                        break;
                    case "西施":
                        tv.setText("西施被选了");
                        break;
                    case "杨贵妃":
                        tv.setText("杨贵妃被选了");
                        break;
                    case "王昭君":
                        tv.setText("王昭君被选了");
                        break;
                    case "陈圆圆":
                        tv.setText("陈圆圆被选了");
                        break;
                    default:
                        break;
                }



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}

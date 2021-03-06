/*******************************************************************************
 * Copyright 2013 Tomasz Zawada
 * 
 * Based on the excellent PhotoView by Chris Banes:
 * https://github.com/chrisbanes/PhotoView
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.lht.utils.lhtutils.ZoomImageViewDemo;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lht.utils.lhtutils.R;


public class ViewPagerSampleActivity extends AppCompatActivity {

    private static class SamplePagerAdapter extends PagerAdapter {
        private Handler backgroundHandler;

        public SamplePagerAdapter() {
            // Create a background thread and a handler for it
            final HandlerThread backgroundThread = new HandlerThread("backgroundThread");
            backgroundThread.start();
            backgroundHandler = new Handler(backgroundThread.getLooper());
        }

        private static int[] drawables = {
                R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4,
                R.mipmap.image5, R.mipmap.image6, R.mipmap.image7, R.mipmap.image8,
                R.mipmap.image9
        };

        @Override
        public int getCount() {
            return drawables.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final ZoomImageView zoomImageView = new ZoomImageView(container.getContext());

            /*
             * Load the new bitmap in the background thread
             */
            final int bitmapResource = drawables[position];
            backgroundHandler.post(new Runnable() {
                @Override
                public void run() {
                    // Load the bitmap here. You should control the bitmap size
                    // using the BitmapFactory.Options.
                    final Bitmap bitmap = BitmapFactory.decodeResource(
                            zoomImageView.getResources(), bitmapResource);

                    // Show the bitmap
                    zoomImageView.post(new Runnable() {
                        @Override
                        public void run() {
                            zoomImageView.setImageBitmap(bitmap);
                        }
                    });
                }
            });

            container.addView(zoomImageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            return zoomImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

            /*
             * Recycle the old bitmap to free up memory straight away
             */
            try {
                final ImageView imageView = (ImageView) object;
                final Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                imageView.setImageBitmap(null);
                bitmap.recycle();
            } catch (Exception e) {}
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @TargetApi(VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
         /*
         * Use full screen window and translucent action bar
         */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawable(new ColorDrawable(0xFF000000));
        if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
            getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
//            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x88000000));
            getSupportActionBar().hide();
            // Note: if you use ActionBarSherlock use here getSupportActionBar()
        }
        super.onCreate(savedInstanceState);



        setContentView(R.layout.view_pager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.zoomViewPager);
        viewPager.setAdapter(new SamplePagerAdapter());

        // Add margin between pages (optional)
        viewPager.setPageMargin((int) getResources().getDisplayMetrics().density * 10);
    }
}

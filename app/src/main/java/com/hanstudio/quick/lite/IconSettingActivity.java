package com.hanstudio.quick.lite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by L on 2015-10-20.
 */
public class IconSettingActivity extends Activity {

    ImageView icon;

    Bitmap bit1, bit2;
    RoundedDrawable drawIcon1, drawIcon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.icon_setting_view);

        icon = (ImageView) findViewById(R.id.icon);
        Button button = (Button) findViewById(R.id.backButton);


        SharedPreferences prefButton = getSharedPreferences("buttonData", MODE_PRIVATE);
        String uriText = prefButton.getString("URI", "null");

        if (uriText.length() == 4) {

            icon.setImageResource(R.drawable.default_icon);
        } else {
            Uri uri = Uri.parse(prefButton.getString("URI", "null"));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);

                if (bit1.getWidth() > 1000 || bit1.getHeight() > 1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit1, bit1.getWidth() / 4, bit1.getHeight() / 4, true);

                    drawIcon1 = new RoundedDrawable(resized);
                } else {
                    drawIcon1 = new RoundedDrawable(bit1);
                }
                icon.setImageDrawable(drawIcon1);

            } catch (Exception e) {

                icon.setImageResource(R.drawable.default_icon);
            }


        }


        //icon 에 db 에 등록 되어 있는 이미지 불러오기.
        RelativeLayout list1 = (RelativeLayout) findViewById(R.id.list1);
        RelativeLayout list2 = (RelativeLayout) findViewById(R.id.list2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                icon.setImageResource(R.drawable.default_icon);

                SharedPreferences prefButton = getSharedPreferences("buttonData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefButton.edit();
                editor.putString("URI", "null");
                editor.commit();

                //기본 이미지로 등록

                SharedPreferences pref = getSharedPreferences("bool", MODE_PRIVATE);
                SharedPreferences.Editor editors = pref.edit();
                editors.putBoolean("isRunning", false);
                editors.commit();

                Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
                stopService(intent);

                Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
                startService(intent2);


            }
        });

        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);


            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

            Uri uri = data.getData();
            SharedPreferences prefButton = getSharedPreferences("buttonData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefButton.edit();
            editor.putString("URI", uri.toString());
            editor.commit();


            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);


                if (bit2.getWidth() > 1000 || bit2.getHeight() > 1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit2, bit2.getWidth() / 4, bit2.getHeight() / 4, true);

                    drawIcon2 = new RoundedDrawable(resized);
                } else {
                    drawIcon2 = new RoundedDrawable(bit2);
                }
                icon.setImageDrawable(drawIcon2);


            } catch (Exception e) {
                icon.setImageResource(R.drawable.default_icon);
            }



            Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
            stopService(intent);


            Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
            startService(intent2);


        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_to_rignt_l, R.anim.slide_to_rignt_r);
    }
}


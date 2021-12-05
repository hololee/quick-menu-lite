package com.hanstudio.quick.lite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;


public class ItemIconSettingActivity extends Activity {

    RelativeLayout list1,list2,list3,list4;

    ImageView img1,img2,img3,img4;

    RoundedDrawable drawIcon1,drawIcon2,drawIcon3,drawIcon4;

    Bitmap bit1,bit2,bit3,bit4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_icon_set_view);
        Button button = (Button) findViewById(R.id.backButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        list1 = (RelativeLayout) findViewById(R.id.list1);
        list2 = (RelativeLayout) findViewById(R.id.list2);
        list3 = (RelativeLayout) findViewById(R.id.list3);
        list4 = (RelativeLayout) findViewById(R.id.list4);


         img1 = (ImageView) findViewById(R.id.sub1);

        SharedPreferences prefItemIcon1 = getSharedPreferences("item1", MODE_PRIVATE);
        String item01 = prefItemIcon1.getString("item","null");

        if(item01.length() != 4) {
            Uri uri = Uri.parse(item01);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit1= BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);

                if(bit1.getWidth() > 1000 || bit1.getHeight() >1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit1, bit1.getWidth()/4, bit1.getHeight()/4, true);

                    drawIcon1 = new RoundedDrawable(resized);
                }else {
                    drawIcon1 = new RoundedDrawable(bit1);
                }
                img1.setImageDrawable(drawIcon1);

        } catch (Exception e) {

                img1.setImageResource(R.drawable.default_item);

        }


    }

         img2 = (ImageView) findViewById(R.id.sub2);

        SharedPreferences prefItemIcon2 = getSharedPreferences("item2", MODE_PRIVATE);
        String item02 = prefItemIcon2.getString("item","null");
        if(item02.length() != 4) {
            Uri uri2 = Uri.parse(item02);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri2), null, options);

                if(bit1.getWidth() > 1000 || bit1.getHeight() >1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit2, bit2.getWidth()/4, bit2.getHeight()/4, true);

                    drawIcon2 = new RoundedDrawable(resized);
                }else {
                    drawIcon2 = new RoundedDrawable(bit2);
                }
                img2.setImageDrawable(drawIcon2);

            } catch (Exception e) {

                img2.setImageResource(R.drawable.default_item);

            }


        }
         img3 = (ImageView) findViewById(R.id.sub3);

        SharedPreferences prefItemIcon3 = getSharedPreferences("item3", MODE_PRIVATE);
        String item03 = prefItemIcon3.getString("item","null");
        if(item03.length() != 4) {
            Uri uri3 = Uri.parse(item03);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit3 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri3), null, options);

                if(bit3.getWidth() > 1000 || bit3.getHeight() >1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit3, bit3.getWidth()/4, bit3.getHeight()/4, true);

                    drawIcon3 = new RoundedDrawable(resized);
                }else {
                    drawIcon3 = new RoundedDrawable(bit3);
                }
                img3.setImageDrawable(drawIcon3);

            } catch (Exception e) {
                img3.setImageResource(R.drawable.default_item);
            }


        }


         img4 = (ImageView) findViewById(R.id.sub4);

        SharedPreferences prefItemIcon4 = getSharedPreferences("item4", MODE_PRIVATE);
        String item04 = prefItemIcon4.getString("item","null");

        if(item04.length() != 4) {

            Uri uri4 = Uri.parse(item04);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit4 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri4), null, options);

                if(bit4.getWidth() > 1000 || bit4.getHeight() >1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit4, bit4.getWidth()/4, bit4.getHeight()/4, true);

                    drawIcon4 = new RoundedDrawable(resized);
                }else {
                    drawIcon4 = new RoundedDrawable(bit4);
                }
                img4.setImageDrawable(drawIcon4);

            } catch (Exception e) {

                img4.setImageResource(R.drawable.default_item);

            }



        }



        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);


            }
        });

        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 2);


            }
        });

        list3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 3);


            }
        });

        list4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 4);


            }
        });

        list1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                SharedPreferences prefItemIcon1 = getSharedPreferences("item1", MODE_PRIVATE);
                SharedPreferences.Editor edit1 = prefItemIcon1.edit();
                edit1.putString("item", "null");
                edit1.commit();

                img1.setImageResource(R.drawable.default_item);

                Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
                stopService(intent);


                Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
                startService(intent2);

                return true;


            }
        });

        list2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                SharedPreferences prefItemIcon2 = getSharedPreferences("item2", MODE_PRIVATE);
                SharedPreferences.Editor edit2 = prefItemIcon2.edit();
                edit2.putString("item","null");
                edit2.commit();

                img2.setImageResource(R.drawable.default_item);


                Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
                stopService(intent);


                Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
                startService(intent2);
                return true;


            }
        });

        list3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                SharedPreferences prefItemIcon1 = getSharedPreferences("item3", MODE_PRIVATE);
                SharedPreferences.Editor edit1 = prefItemIcon1.edit();
                edit1.putString("item","null");
                edit1.commit();

                img3.setImageResource(R.drawable.default_item);


                Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
                stopService(intent);


                Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
                startService(intent2);
                return true;


            }
        });

        list4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                SharedPreferences prefItemIcon1 = getSharedPreferences("item4", MODE_PRIVATE);
                SharedPreferences.Editor edit1 = prefItemIcon1.edit();
                edit1.putString("item","null");
                edit1.commit();

                img4.setImageResource(R.drawable.default_item);


                Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
                stopService(intent);


                Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
                startService(intent2);
                return true;


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            SharedPreferences prefItemIcon1 = getSharedPreferences("item1", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = prefItemIcon1.edit();
            edit1.putString("item",uri.toString());
            edit1.commit();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit1= BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);

                if(bit1.getWidth() > 1000 || bit1.getHeight() >1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit1, bit1.getWidth()/4, bit1.getHeight()/4, true);

                    drawIcon1 = new RoundedDrawable(resized);
                }else {
                    drawIcon1 = new RoundedDrawable(bit1);
                }
                img1.setImageDrawable(drawIcon1);

            } catch (Exception e) {
                img1.setImageResource(R.drawable.default_item);
            }



            Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
            stopService(intent);


            Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
            startService(intent2);



        }else  if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            SharedPreferences prefItemIcon2 = getSharedPreferences("item2", MODE_PRIVATE);
            SharedPreferences.Editor edit2 = prefItemIcon2.edit();
            edit2.putString("item",uri.toString());
            edit2.commit();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit2= BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);

                if(bit2.getWidth() > 1000 || bit2.getHeight() >1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit2, bit2.getWidth()/4, bit2.getHeight()/4, true);

                    drawIcon2 = new RoundedDrawable(resized);
                }else {
                    drawIcon2 = new RoundedDrawable(bit2);
                }

                img2.setImageDrawable(drawIcon2);

            } catch (Exception e) {
                img2.setImageResource(R.drawable.default_item);
            }



            Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
            stopService(intent);


            Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
            startService(intent2);


        }else  if (requestCode == 3 && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            SharedPreferences prefItemIcon3 = getSharedPreferences("item3", MODE_PRIVATE);
            SharedPreferences.Editor edit3 = prefItemIcon3.edit();
            edit3.putString("item",uri.toString());
            edit3.commit();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit3= BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);

                if(bit3.getWidth() > 1000 || bit3.getHeight() >1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit3, bit3.getWidth()/4, bit3.getHeight()/4, true);

                    drawIcon3 = new RoundedDrawable(resized);
                }else {
                    drawIcon3 = new RoundedDrawable(bit3);
                }

                img3.setImageDrawable(drawIcon3);

            } catch (Exception e) {
              img3.setImageResource(R.drawable.default_item);
            }



            Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
            stopService(intent);


            Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
            startService(intent2);

        }else  if (requestCode == 4 && resultCode == RESULT_OK && null != data) {
            Uri uri = data.getData();
            SharedPreferences prefItemIcon4 = getSharedPreferences("item4", MODE_PRIVATE);
            SharedPreferences.Editor edit4 = prefItemIcon4.edit();
            edit4.putString("item",uri.toString());
            edit4.commit();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit4= BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);


                if(bit4.getWidth() > 1000 || bit4.getHeight() >1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit4, bit4.getWidth()/4, bit4.getHeight()/4, true);

                    drawIcon4 = new RoundedDrawable(resized);
                }else {
                    drawIcon4 = new RoundedDrawable(bit4);
                }


                img4.setImageDrawable(drawIcon4);



            } catch (Exception e) {
                img4.setImageResource(R.drawable.default_item);
            }





            Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
            stopService(intent);


            Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
            startService(intent2);

        }


    }
}

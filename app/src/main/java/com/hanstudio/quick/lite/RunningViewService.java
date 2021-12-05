package com.hanstudio.quick.lite;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import static android.view.View.*;


public class RunningViewService extends Service {

    WindowManager.LayoutParams params;
    WindowManager manager;

    Bitmap bit1, bit2, bit3, bit4, bitx;
    RoundedDrawable drawIcon1, drawIcon2, drawIcon3, drawIcon4, drawIconx;
    ImageView button;
    int state = 2;

    ImageView item1, item2, item3, item4;

    private final int MENU_OPENED = 1;
    private final int MENU_CLOSED = 2;

    private float pressedX;
    private float pressedY;

    private float mTouchY;
    private int mViewY;

    boolean flag = true;

    boolean touchAble = true;
    boolean item1Added = false;
    boolean item2Added = false;
    boolean item3Added = false;
    boolean item4Added = false;

    String packageName1, packageName2, packageName3, packageName4;

    SharedPreferences preferences1, preferences2, preferences3, preferences4;

    int width;
    int height;

    OnClickListener item1ClickListener, item2ClickListener, item3ClickListener, item4ClickListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.noti_icon);
        builder.setContentTitle("QUICK MENU");
        builder.setContentText("running");
        builder.setWhen(0);
        Notification noti = builder.build();


        startForeground(729, noti);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (flag) {

            button = new ImageView(getApplicationContext());

            item1 = new ImageView(getApplicationContext());
            item2 = new ImageView(getApplicationContext());
            item3 = new ImageView(getApplicationContext());
            item4 = new ImageView(getApplicationContext());

            //button 이미지 설정

            SharedPreferences prefButton = getSharedPreferences("buttonData", MODE_PRIVATE);

            if (prefButton.getString("URI", "null").length() == 4) {
                //기본 이미지일 경우

                Bitmap bit = BitmapFactory.decodeResource(getResources(), R.drawable.default_icon);
                button.setImageBitmap(bit);
            } else {

                Uri uri = Uri.parse(prefButton.getString("URI", "null"));
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;

                try {
                    bitx = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);

                    if (bitx.getWidth() > 1000 || bitx.getHeight() > 1000) {

                        Bitmap resized = Bitmap.createScaledBitmap(bitx, bitx.getWidth() / 4, bitx.getHeight() / 4, true);

                        drawIconx = new RoundedDrawable(resized);
                    } else {
                        drawIconx = new RoundedDrawable(bitx);
                    }

                    button.setImageDrawable(drawIconx);

                } catch (Exception e) {
                    button.setImageResource(R.drawable.default_icon);
                }


            }


            //item 의  설정

            preferences1 = getSharedPreferences("APP1", MODE_PRIVATE);

            packageName1 = preferences1.getString("package", "null");
            if (packageName1.length() == 4) {
                item1.setBackgroundResource(R.drawable.default_item);
            } else if (packageName1.length() == 5) {
                //call

                item1.setBackgroundResource(R.drawable.call);

            } else {

                PackageManager pm = getApplicationContext().getPackageManager();
                Drawable icon1 = null;
                try {
                    icon1 = pm.getApplicationIcon(packageName1);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                item1.setImageDrawable(icon1);


            }

            preferences2 = getSharedPreferences("APP2", MODE_PRIVATE);

            packageName2 = preferences2.getString("package", "null");
            if (packageName2.length() == 4) {
                item2.setBackgroundResource(R.drawable.default_item);
            } else if (packageName2.length() == 5) {
                //call

                item2.setBackgroundResource(R.drawable.call);

            } else {

                PackageManager pm = getApplicationContext().getPackageManager();
                Drawable icon2 = null;
                try {
                    icon2 = pm.getApplicationIcon(packageName2);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                item2.setImageDrawable(icon2);


            }


            preferences3 = getSharedPreferences("APP3", MODE_PRIVATE);

            packageName3 = preferences3.getString("package", "null");
            if (packageName3.length() == 4) {
                item3.setBackgroundResource(R.drawable.default_item);
            } else if (packageName3.length() == 5) {
                //call

                item3.setBackgroundResource(R.drawable.call);

            } else {

                PackageManager pm = getApplicationContext().getPackageManager();
                Drawable icon3 = null;
                try {
                    icon3 = pm.getApplicationIcon(packageName3);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                item3.setImageDrawable(icon3);


            }

            preferences4 = getSharedPreferences("APP4", MODE_PRIVATE);

            packageName4 = preferences4.getString("package", "null");
            if (packageName4.length() == 4) {
                item4.setBackgroundResource(R.drawable.default_item);
            } else if (packageName4.length() == 5) {
                //call

                item4.setBackgroundResource(R.drawable.call);

            } else {

                PackageManager pm = getApplicationContext().getPackageManager();
                Drawable icon4 = null;
                try {
                    icon4 = pm.getApplicationIcon(packageName4);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                item4.setImageDrawable(icon4);


            }

            //각 아이템 이미지 설정
            SharedPreferences prefItemIcon1 = getSharedPreferences("item1", MODE_PRIVATE);
            String item01 = prefItemIcon1.getString("item", "null");

            if (item01.length() == 4) {
                //설정값이 없을경우 변화 없음
            } else {

                Uri uri = Uri.parse(item01);
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
                    item1.setImageDrawable(drawIcon1);

                } catch (Exception e) {
                    PackageManager pm = getApplicationContext().getPackageManager();
                    Drawable icon1 = null;

                    try {
                        icon1 = pm.getApplicationIcon(packageName1);
                    } catch (PackageManager.NameNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    item1.setImageDrawable(icon1);
                }


            }

            SharedPreferences prefItemIcon2 = getSharedPreferences("item2", MODE_PRIVATE);
            String item02 = prefItemIcon2.getString("item", "null");

            if (item02.length() == 4) {
                //설정값이 없을경우 변화 없음
            } else {

                Uri uri = Uri.parse(item02);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;

                try {
                    bit2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);

                    if (bit2.getWidth() > 1000 || bit1.getHeight() > 1000) {

                        Bitmap resized = Bitmap.createScaledBitmap(bit2, bit2.getWidth() / 4, bit2.getHeight() / 4, true);

                        drawIcon2 = new RoundedDrawable(resized);
                    } else {
                        drawIcon2 = new RoundedDrawable(bit2);
                    }

                    RoundedDrawable drawIcon = new RoundedDrawable(bit2);

                    item2.setImageDrawable(drawIcon);


                } catch (Exception e) {
                    PackageManager pm = getApplicationContext().getPackageManager();
                    Drawable icon2 = null;

                    try {
                        icon2 = pm.getApplicationIcon(packageName2);
                    } catch (PackageManager.NameNotFoundException e2) {
                        e2.printStackTrace();
                    }

                    item2.setImageDrawable(icon2);
                }
            }


        }
        SharedPreferences prefItemIcon3 = getSharedPreferences("item3", MODE_PRIVATE);
        String item03 = prefItemIcon3.getString("item", "null");

        if (item03.length() == 4) {
            //설정값이 없을경우 변화 없음
        } else {

            Uri uri = Uri.parse(item03);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit3 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);

                if (bit3.getWidth() > 1000 || bit3.getHeight() > 1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit3, bit3.getWidth() / 4, bit3.getHeight() / 4, true);

                    drawIcon3 = new RoundedDrawable(resized);
                } else {
                    drawIcon3 = new RoundedDrawable(bit3);
                }


                RoundedDrawable drawIcon = new RoundedDrawable(bit3);

                item3.setImageDrawable(drawIcon);

            } catch (Exception e) {
                PackageManager pm = getApplicationContext().getPackageManager();
                Drawable icon3 = null;

                try {
                    icon3 = pm.getApplicationIcon(packageName3);
                } catch (PackageManager.NameNotFoundException e3) {
                    e3.printStackTrace();
                }

                item3.setImageDrawable(icon3);
            }


        }

        SharedPreferences prefItemIcon4 = getSharedPreferences("item4", MODE_PRIVATE);
        String item04 = prefItemIcon4.getString("item", "null");

        if (item04.length() == 4) {
            //설정값이 없을경우 변화 없음
        } else {

            Uri uri = Uri.parse(item04);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            try {
                bit4 = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
                if (bit4.getWidth() > 1000 || bit4.getHeight() > 1000) {

                    Bitmap resized = Bitmap.createScaledBitmap(bit4, bit4.getWidth() / 4, bit4.getHeight() / 4, true);

                    drawIcon4 = new RoundedDrawable(resized);
                } else {
                    drawIcon4 = new RoundedDrawable(bit4);
                }


                RoundedDrawable drawIcon = new RoundedDrawable(bit4);

                item4.setImageDrawable(drawIcon);

            } catch (Exception e) {
                PackageManager pm = getApplicationContext().getPackageManager();
                Drawable icon4 = null;

                try {
                    icon4 = pm.getApplicationIcon(packageName4);
                } catch (PackageManager.NameNotFoundException e4) {
                    e4.printStackTrace();
                }

                item4.setImageDrawable(icon4);
            }


        }


        button.setPadding(15, 15, 15, 15);

        //button 초기화
        params = new WindowManager.LayoutParams(160, 160, WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT | Gravity.TOP;


        DisplayMetrics dm = new DisplayMetrics();
        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
        height = dm.heightPixels;

        params.x = (int) (width * 0.83);
        params.y = (int) (height * 0.4);
        params.windowAnimations = android.R.style.Animation_Translucent;

        //params가  뷰의 위치를 결정한다.
        manager.addView(button, params);


        item1ClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (packageName1.length() == 4) {

                } else if (packageName1.length() == 5) {
                    //call
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + preferences1.getString("number", "0000")));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(packageName1);

                    try{

                        startActivity(intent);

                    } catch (Exception ex){


                        Toast toast = Toast.makeText(getApplicationContext(),"실행이 불가한 앱입니다. \n 다른 앱을 등록해 주세요.",Toast.LENGTH_SHORT);
                        toast.show();
                    }


                }
                menuClose();
            }
        };

        item2ClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (packageName2.length() == 4) {

                } else if (packageName2.length() == 5) {
                    //call
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + preferences2.getString("number", "0000")));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(packageName2);
                    try{

                        startActivity(intent);

                    } catch (Exception ex){


                        Toast toast = Toast.makeText(getApplicationContext(),"실행이 불가한 앱입니다. \n 다른 앱을 등록해 주세요.",Toast.LENGTH_SHORT);
                        toast.show();
                    }


                }
                menuClose();
            }
        };

        item3ClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (packageName3.length() == 4) {

                } else if (packageName3.length() == 5) {
                    //call
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + preferences3.getString("number", "0000")));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(packageName3);
                    try{

                        startActivity(intent);

                    } catch (Exception ex){


                        Toast toast = Toast.makeText(getApplicationContext(),"실행이 불가한 앱입니다. \n 다른 앱을 등록해 주세요.",Toast.LENGTH_SHORT);
                        toast.show();
                    }


                }
                menuClose();
            }
        };

        item4ClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (packageName4.length() == 4) {

                } else if (packageName4.length() == 5) {
                    //call
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + preferences4.getString("number", "0000")));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                } else {
                    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(packageName4);
                    try{

                        startActivity(intent);

                    } catch (Exception ex){


                        Toast toast = Toast.makeText(getApplicationContext(),"실행이 불가한 앱입니다. \n 다른 앱을 등록해 주세요.",Toast.LENGTH_SHORT);
                        toast.show();
                    }


                }
                menuClose();
            }
        };


        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        //down 일때의 위치값 기억
                        pressedX = event.getX();
                        pressedY = event.getY();

                        // 움직이기 위한 정보
                        mTouchY = event.getRawY();
                        mViewY = params.y;

                        break;
                    case MotionEvent.ACTION_UP:

                        if (Math.abs(pressedX - event.getX()) < 8 && Math.abs(pressedY - event.getY()) < 8) {// 위치 변화가 없을 경우 (클릭으로 처리)
                            // OnClickListener
                            if (state == MENU_CLOSED) {

                                //menu 열기

                                //item1 추가
                                WindowManager.LayoutParams param = new WindowManager.LayoutParams(120, 120, WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
                                param.gravity = Gravity.LEFT | Gravity.TOP;

                                param.y = params.y + 20;
                                param.x = (int) (width * 0.05);
                                param.windowAnimations = android.R.style.Animation_Translucent;

                                manager.addView(item1, param);
                                item1Added = true;
                                item1.setOnClickListener(item1ClickListener);

                                param.x = (int) (width * 0.25);
                                manager.addView(item2, param);
                                item2Added = true;
                                item2.setOnClickListener(item2ClickListener);

                                param.x = (int) (width * 0.45);
                                manager.addView(item3, param);
                                item3Added = true;
                                item3.setOnClickListener(item3ClickListener);

                                param.x = (int) (width * 0.65);
                                manager.addView(item4, param);
                                item4Added = true;
                                item4.setOnClickListener(item4ClickListener);

                                touchAble = false;
                                state = MENU_OPENED;

                            } else if (state == MENU_OPENED) {

                                //menu 닫기

                                menuClose();

                            }
                        }

                        break;
                    case MotionEvent.ACTION_MOVE:

                        if (touchAble) {
                            int y = (int) (event.getRawY() - mTouchY);
                            params.y = mViewY + y;
                            manager.updateViewLayout(button, params);
                        }
                }
                return true;
            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
        manager.removeView(button);
        if (item1Added) {
            manager.removeView(item1);
        }
        if (item2Added) {
            manager.removeView(item2);
        }
        if (item3Added) {
            manager.removeView(item2);
        }
        if (item4Added) {
            manager.removeView(item2);
        }
    }

    private void menuClose() {


        manager.removeView(item1);
        item1Added = false;
        manager.removeView(item2);
        item2Added = false;
        manager.removeView(item3);
        item3Added = false;
        manager.removeView(item4);
        item4Added = false;

        touchAble = true;
        state = MENU_CLOSED;

    }


}

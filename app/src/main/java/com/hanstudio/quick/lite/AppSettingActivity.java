package com.hanstudio.quick.lite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.AvoidXfermode;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by L on 2015-10-20.
 */
public class AppSettingActivity extends Activity {

    ListView list;
    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_setting_view);

        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

       invalidate();



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        Intent intent0 = new Intent(getApplicationContext(), InstallAppListActivity.class);
                        intent0.putExtra("app", 1);
                        startActivity(intent0);
                        finish();
                        overridePendingTransition(R.anim.slide_to_left_r, R.anim.slide_to_left_l);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), InstallAppListActivity.class);
                        intent1.putExtra("app", 2);
                        startActivity(intent1);
                        finish();
                        overridePendingTransition(R.anim.slide_to_left_r, R.anim.slide_to_left_l);

                        break;
                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(), InstallAppListActivity.class);
                        intent2.putExtra("app", 3);
                        startActivity(intent2);
                        finish();
                        overridePendingTransition(R.anim.slide_to_left_r, R.anim.slide_to_left_l);

                        break;
                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(), InstallAppListActivity.class);
                        intent3.putExtra("app", 4);
                        startActivity(intent3);
                        finish();
                        overridePendingTransition(R.anim.slide_to_left_r, R.anim.slide_to_left_l);

                        break;
                }

            }
        });




      list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

              SharedPreferences preferences = getSharedPreferences("APP" + (position + 1), MODE_PRIVATE);
              SharedPreferences.Editor editor = preferences.edit();
              editor.putString("package", "null");
              editor.commit();

              invalidate();

              Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
              stopService(intent);


              Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
              startService(intent2);

              return true;
          }
      });
    }



    public void invalidate(){

        list = (ListView) findViewById(R.id.listView);
        ItemListAdpater adapter = new ItemListAdpater(getApplicationContext());

        //item1
        ItemItem item1 = new ItemItem();
        item1.setText("1");

        SharedPreferences preferences = getSharedPreferences("APP1", MODE_PRIVATE);
        String packageName1 = preferences.getString("package","null");

        if(packageName1.length() == 4){
            item1.setSub("");
        }else if(packageName1.length() ==5) {
            //call
            SharedPreferences pref  =getSharedPreferences("APP1", MODE_PRIVATE);
            item1.setSub(pref.getString("number","0000"));
            Drawable drawable = getResources().getDrawable(R.drawable.call);
            item1.setItem(drawable);

        }else{
            item1.setSub("" + packageName1);
        }
        PackageManager pm = getPackageManager();
        try {
            item1.setItem(pm.getApplicationIcon(packageName1));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        adapter.addItem(item1);

        //item2
        ItemItem item2 = new ItemItem();
        item2.setText("2");
        SharedPreferences preferences2 = getSharedPreferences("APP2", MODE_PRIVATE);
        String packageName2 = preferences2.getString("package","null");

        if(packageName2.length() == 4){
            item2.setSub("");
        }else if(packageName2.length() ==5) {
            //call
            SharedPreferences pref  =getSharedPreferences("APP2", MODE_PRIVATE);
            item2.setSub(pref.getString("number","0000"));
            Drawable drawable = getResources().getDrawable(R.drawable.call);
            item2.setItem(drawable);

        }else{
            item2.setSub("" + packageName2);
        }
        PackageManager pm2 = getPackageManager();
        try {
            item2.setItem(pm2.getApplicationIcon(packageName2));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        adapter.addItem(item2);


        //item3
        ItemItem item3 = new ItemItem();
        item3.setText("3");
        SharedPreferences preferences3 = getSharedPreferences("APP3", MODE_PRIVATE);
        String packageName3 = preferences3.getString("package","null");

        if(packageName3.length() == 4){
            item3.setSub("");
        }else if(packageName3.length() ==5) {
            //call
            SharedPreferences pref  =getSharedPreferences("APP3", MODE_PRIVATE);
            item3.setSub(pref.getString("number","0000"));
            Drawable drawable = getResources().getDrawable(R.drawable.call);
            item3.setItem(drawable);

        }else{
            item3.setSub("" + packageName3);
        }
        PackageManager pm3 = getPackageManager();
        try {
            item3.setItem(pm3.getApplicationIcon(packageName3));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        adapter.addItem(item3);


        //item4
        ItemItem item4 = new ItemItem();
        item4.setText("4");
        SharedPreferences preferences4 = getSharedPreferences("APP4", MODE_PRIVATE);
        String packageName4 = preferences4.getString("package","null");

        if(packageName4.length() == 4){
            item4.setSub("");
        }else if(packageName4.length() ==5) {
            //call
            SharedPreferences pref  =getSharedPreferences("APP4", MODE_PRIVATE);
            item4.setSub(pref.getString("number","0000"));
            Drawable drawable = getResources().getDrawable(R.drawable.call);
            item4.setItem(drawable);

        }else{
            item4.setSub("" + packageName4);
        }
        PackageManager pm4 = getPackageManager();
        try {
            item4.setItem(pm4.getApplicationIcon(packageName4));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        adapter.addItem(item4);



        list.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.slide_to_rignt_l, R.anim.slide_to_rignt_r);
    }
}

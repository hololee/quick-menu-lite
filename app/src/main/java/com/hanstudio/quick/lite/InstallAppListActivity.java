package com.hanstudio.quick.lite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by L on 2015-10-23.
 */
public class InstallAppListActivity extends Activity {
    SetItemListView view;

    List<PackageInfo> info;
    List<ApplicationInfo> appInfo;
    List<PackageInfo> inforeal;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install_app_view);

        //intent 정보 가져오기
        Bundle bundle = getIntent().getExtras();
        index = bundle.getInt("app");


        // applicaitonInfo를 이용하여 PackageInfo중 시스템 앱을 뺀 나머지를 info inforeal 에 저장
        info = getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);
        appInfo = getPackageManager().getInstalledApplications(PackageManager.GET_ACTIVITIES);
        inforeal = new ArrayList<>();

        for (int i = 0; i < info.size(); i++) {
            if ((appInfo.get(i).flags & ApplicationInfo.FLAG_SYSTEM) != 0) {

            } else {
                inforeal.add(info.get(i));
            }
        }

        ListView list = (ListView) findViewById(R.id.appList);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return inforeal.size() + 1;
            }

            @Override
            public Object getItem(int position) {
                return inforeal.get(position - 1);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (position == 0) {
                    view = new SetItemListView(getApplicationContext());
                    view.setTitle(getString(R.string.select_list1));
                    view.setSubTitle(getString(R.string.select_sub1));
                } else {


                    view = new SetItemListView(getApplicationContext());
                    PackageInfo app = inforeal.get(position - 1);
                    PackageManager pm = getApplicationContext().getPackageManager();

                    try {
                        view.setTitle((String) pm.getApplicationLabel(pm.getApplicationInfo(app.packageName, PackageManager.GET_ACTIVITIES)));
                        view.setSubTitle(app.packageName);

                        view.setIcon(pm.getApplicationIcon(app.packageName));
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                return view;


            }
        };


        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //db에 index 값에 맞게 저장한다.
                //position 0,1,2 는 별도로 설정

                if (position == 0) {

                    Intent intent = new Intent(getApplicationContext(), CallSettingActivity.class);
                    intent.putExtra("index", index);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_to_rignt_l, R.anim.slide_to_rignt_r);

                } else {

                    SharedPreferences preferences = getSharedPreferences("APP" + index, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("package", inforeal.get(position - 1).packageName);
                    editor.commit();

                    SharedPreferences prefItemIcon1 = getSharedPreferences("item1", MODE_PRIVATE);
                    SharedPreferences.Editor ed1 = prefItemIcon1.edit();
                    ed1.putString("item", "null");
                    ed1.commit();

                    SharedPreferences prefItemIcon2 = getSharedPreferences("item2", MODE_PRIVATE);
                    SharedPreferences.Editor ed2 = prefItemIcon2.edit();
                    ed2.putString("item", "null");
                    ed2.commit();

                    SharedPreferences prefItemIcon3 = getSharedPreferences("item3", MODE_PRIVATE);
                    SharedPreferences.Editor ed3 = prefItemIcon3.edit();
                    ed3.putString("item", "null");
                    ed3.commit();

                    SharedPreferences prefItemIcon4 = getSharedPreferences("item4", MODE_PRIVATE);
                    SharedPreferences.Editor ed4 = prefItemIcon4.edit();
                    ed4.putString("item", "null");
                    ed4.commit();


                    Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
                    stopService(intent);

                    Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
                    startService(intent2);

                    Intent intent0 = new Intent(getApplicationContext(), AppSettingActivity.class);
                    startActivity(intent0);
                    finish();
                    overridePendingTransition(R.anim.slide_to_rignt_l, R.anim.slide_to_rignt_r);

                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent0 = new Intent(getApplicationContext(), AppSettingActivity.class);
        startActivity(intent0);
        finish();
        overridePendingTransition(R.anim.slide_to_rignt_l, R.anim.slide_to_rignt_r);
    }
}

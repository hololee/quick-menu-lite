package com.hanstudio.quick.lite;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyCloseAd;
import com.fsn.cauly.CaulyCloseAdListener;

public class MainActivity extends Activity implements CaulyCloseAdListener {

    private static final String APP_CODE = "HT38xrqf"; // 광고 요청을 위한 App Code
    CaulyCloseAd mCloseAd ; // CloseAd광고 객체


    boolean isRunning;

    ImageView button;

    Intent intent;

    @Override
    protected void onResume() {
        super.onResume();
        if (mCloseAd != null)
            mCloseAd.resume(this); // 필수 호출
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CloseAd 초기화
        CaulyAdInfo closeAdInfo = new CaulyAdInfoBuilder(APP_CODE).build();
        mCloseAd = new CaulyCloseAd();
/* Optional
//원하는 버튼의 문구를 설정 할 수 있다.
mCloseAd.setButtonText("취소", "종료");
//원하는 텍스트의 문구를 설정 할 수 있다.
mCloseAd.setDescriptionText("종료하시겠습니까?");
*/
        mCloseAd.setAdInfo(closeAdInfo);
        mCloseAd.setCloseAdListener(this);
        mCloseAd.disableBackKey();// CaulyCloseAdListener 등록
// 종료광고 노출 후 back버튼 사용을 막기 원할 경우 disableBackKey();을 추가한다
// mCloseAd.disableBackKey();

        SharedPreferences pref = getSharedPreferences("bool", MODE_PRIVATE);
        isRunning =  pref.getBoolean("isRunning", false);

        button = (ImageView) findViewById(R.id.button);

        if(!isRunning){
            button.setBackgroundResource(R.drawable.off);
        }else{
            button.setBackgroundResource(R.drawable.on);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isRunning) {
                    button.setBackgroundResource(R.drawable.on);
                    isRunning = true;

                    SharedPreferences pref = getSharedPreferences("bool" ,MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("isRunning", true);
                    editor.commit();

                    intent = new Intent(getApplicationContext(), RunningViewService.class);
                    startService(intent);

                }else{
                    button.setBackgroundResource(R.drawable.off);
                    isRunning = false;

                    SharedPreferences pref = getSharedPreferences("bool" ,MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("isRunning", false);
                    editor.commit();

                    intent = new Intent(getApplicationContext(), RunningViewService.class);
                    stopService(intent);
                }
            }
        });


        ListView listview = (ListView) findViewById(R.id.list);
        ListAdpater adpater = new ListAdpater(getApplicationContext());

        Item item1 = new Item();
        item1.setText(getString(R.string.list1));
        item1.setSub(getString(R.string.sub1));
        Item item2 = new Item();
        item2.setText(getString(R.string.list2));
        item2.setSub(getString(R.string.sub2));
        Item item3 = new Item();
        item3.setText(getString(R.string.list3));
        item3.setSub(getString(R.string.sub3));

        Item item4 = new Item();
        item4.setText(getString(R.string.event));
        item4.setSub(getString(R.string.event_sub));

        adpater.addItem(item1);
        adpater.addItem(item2);
        adpater.addItem(item3);
        adpater.addItem(item4);

        listview.setAdapter(adpater);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        // 아이콘 설정
                        SharedPreferences prefs = getSharedPreferences("event", Context.MODE_PRIVATE);
                        if (prefs.getBoolean("unlock", false)) {

                            Intent intent1 = new Intent(getApplicationContext(), IconSettingActivity.class);
                            startActivity(intent1);
                            overridePendingTransition(R.anim.slide_to_left_r,R.anim.slide_to_left_l);


                        }else {
                            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.warring2), Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        break;
                    case 1:

                        Intent intent2 = new Intent(getApplicationContext(), AppSettingActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(R.anim.slide_to_left_r,R.anim.slide_to_left_l);

                        break;

                    case 2:

                        Toast toast2 = Toast.makeText(getApplicationContext(),getString(R.string.warring2),Toast.LENGTH_SHORT);
                        toast2.show();

                        break;

                    case 3:

                        SharedPreferences pref = getSharedPreferences("event", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editt = pref.edit();
                        editt.putBoolean("unlock",true);
                        editt.commit();

                        Intent msg = new Intent(Intent.ACTION_SEND);
                        msg.addCategory(Intent.CATEGORY_DEFAULT);
                        msg.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.hanstudio.quick.lite");
                        msg.setType("text/plain");
                        startActivity(Intent.createChooser(msg, "공유"));

                        break;



                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
// 앱을 처음 설치하여 실행할 때, 필요한 리소스를 다운받았는지 여부.


            if (mCloseAd.isModuleLoaded()) {
                mCloseAd.show(this);
            } else {
// 광고에 필요한 리소스를 한번만 다운받는데 실패했을 때 앱의 종료팝업 구현
                showDefaultClosePopup();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showDefaultClosePopup() {
        new AlertDialog.Builder(this).setTitle("").setMessage("종료 하시겠습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("아니요", null)
                .show();
    }

    // CaulyCloseAdListener
    @Override
    public void onFailedToReceiveCloseAd(CaulyCloseAd ad, int errCode, String errMsg) {
    }

    // CloseAd의 광고를 클릭하여 앱을 벗어났을 경우 호출되는 함수이다.
    @Override
    public void onLeaveCloseAd(CaulyCloseAd ad) {
    }

    // CloseAd의 request()를 호출했을 때, 광고의 여부를 알려주는 함수이다.
    @Override

    public void onReceiveCloseAd(CaulyCloseAd ad, boolean isChargable) {
    }

    //왼쪽 버튼을 클릭 하였을 때, 원하는 작업을 수행하면 된다.
    @Override
    public void onLeftClicked(CaulyCloseAd ad) {
    }

    //오른쪽 버튼을 클릭 하였을 때, 원하는 작업을 수행하면 된다.
//Default로는 오른쪽 버튼이 종료로 설정되어있다.
    @Override
    public void onRightClicked(CaulyCloseAd ad) {
        finish();
    }

    @Override
    public void onShowedCloseAd(CaulyCloseAd ad, boolean isChargable) {
    }

}

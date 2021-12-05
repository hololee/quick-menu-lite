package com.hanstudio.quick.lite;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by L on 2015-10-25.
 */
public class CallSettingActivity extends Activity {

    int index;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_set_view);

        Bundle bundle = getIntent().getExtras();

        index = bundle.getInt("index");


        edit = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("APP" + index, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("package", "calls");
                editor.putString("number", edit.getText().toString());

                editor.commit();

                SharedPreferences prefItemIcon1 = getSharedPreferences("item" + index, MODE_PRIVATE);
                SharedPreferences.Editor edit1 = prefItemIcon1.edit();
                edit1.putString("item","null");
                edit1.commit();

                finish();

                Intent intent = new Intent(getApplicationContext(), RunningViewService.class);
                stopService(intent);

                Intent intent2 = new Intent(getApplicationContext(), RunningViewService.class);
                startService(intent2);

            }
        });



    }
}

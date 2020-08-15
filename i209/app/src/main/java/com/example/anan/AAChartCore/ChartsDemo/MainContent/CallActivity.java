package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.Manifest;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallActivity extends AppCompatActivity {
    private MutableLiveData<String> mText;
   {
        mText = new MutableLiveData<>();
        mText.setValue("你好呀");
    }

    public LiveData<String> getText() {
        return mText;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);



        Button button21;
        button21 = (Button) findViewById(R.id.button21);


        button21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText et_amount = (EditText) findViewById(R.id.edt_call);


                et_amount.getText();

                String tel = String.valueOf(et_amount.getText().toString());
                callPhone(tel);


            }
        });
    }
    public void callPhone(String phoneNum){
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.CALL_PHONE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "已经拨打电话啦", Toast.LENGTH_LONG).show();
    }
}

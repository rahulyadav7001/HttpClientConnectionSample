package com.ryandro.httpclientconnectionsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    HttpHelperAsynctask httpHelperAsynctask;
    private TextView tv_data;

    private  HttpHelper httpHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_getDataFromServer = findViewById(R.id.btn_getDataFromServer);
        tv_data = findViewById(R.id.tv_data);

        btn_getDataFromServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* httpHelper = new HttpHelper(MainActivity.this);
                httpHelper.execute("Acer");*/

            httpHelperAsynctask = new HttpHelperAsynctask(MainActivity.this);
            httpHelperAsynctask.execute("Acer");
            }
        });


    }

  /*  public static void UpdateData(String s) {
    }*/
   /* public void UpdateData(String strData){
        tv_data.setText(strData);
    }*/
}

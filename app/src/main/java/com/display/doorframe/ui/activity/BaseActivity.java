package com.display.doorframe.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.display.doorframe.R;

/**
 * Created by Jun on 2015/4/24.
 */
public class BaseActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initToolbar(String title){
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        this.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回按钮
    }

    public void actionStart(Context fromContext,  Class<?> tClass, String category1, String category2){
        Intent intent = new Intent(fromContext, tClass);
        intent.putExtra("mainMenu",category1);
        intent.putExtra("secondMenu",category2);
        startActivity(intent);
    }

}

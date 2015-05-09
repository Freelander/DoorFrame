package com.display.doorframe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.display.doorframe.R;

/**
 * Created by Jun on 2015/4/24.
 */
public class CategoryActivity extends BaseActivity {

    private ListView categoryListView;

    private String[] categorys = new String[]{
            "厕所","浴室","卧室","大厅"
    };
    private String[] picSettings = new String[]{
            "门框图片管理","门图片管理"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_layout);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        categoryListView = (ListView) findViewById(R.id.category_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,categorys);
        //添加适配器
        categoryListView.setAdapter(adapter);


        if(category.equals("door")){
            initToolbar("门分类");
            categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 0:
                            actionStart(CategoryActivity.this,ImageActivity.class,"door","wc");
                            break;
                        case 1:
                            actionStart(CategoryActivity.this,ImageActivity.class,"door","bathroom");
                            break;
                        case 2:
                            actionStart(CategoryActivity.this,ImageActivity.class,"door","bedroom");
                            break;
                        case 3:
                            actionStart(CategoryActivity.this,ImageActivity.class,"door","hall");
                            break;

                    }
                }
            });

        }else if(category.equals("frame")){
            initToolbar("门框分类");

            categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 0:
                            actionStart(CategoryActivity.this,ImageActivity.class,"frame","wc");
                            break;
                        case 1:
                            actionStart(CategoryActivity.this,ImageActivity.class,"frame","bathroom");
                            break;
                        case 2:
                            actionStart(CategoryActivity.this,ImageActivity.class,"frame","bedroom");
                            break;
                        case 3:
                            actionStart(CategoryActivity.this,ImageActivity.class,"frame","hall");
                            break;

                    }
                }
            });
        }else if(category.equals("picSettings")){
            initToolbar("图片设置");

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,picSettings);
            //添加适配器
            categoryListView.setAdapter(adapter1);

            categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent;
                    switch (position){
                        case 0:
                            intent = new Intent(view.getContext(), ImageSelectorActivity.class);
                            intent.putExtra("settings_name","frame");
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(view.getContext(), ImageSelectorActivity.class);
                            intent.putExtra("settings_name","door");
                            startActivity(intent);
                            break;
                    }
                }
            });
        }


    }


}

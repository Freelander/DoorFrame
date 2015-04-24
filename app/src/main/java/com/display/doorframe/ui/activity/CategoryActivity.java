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

    String[] categorys = new String[]{
            "厕所","浴室","卧室","大厅"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_layout);

        categoryListView = (ListView) findViewById(R.id.category_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,categorys);
        //添加适配器
        categoryListView.setAdapter(adapter);


        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
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

        }else{
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
        }


    }


}

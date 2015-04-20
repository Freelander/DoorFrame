package com.display.doorframe.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.display.doorframe.R;
import com.display.doorframe.ui.fragment.CollectionFragment;
import com.display.doorframe.ui.fragment.HotFragment;
import com.display.doorframe.ui.fragment.SettingsFragment;
import com.display.doorframe.ui.fragment.SortFragment;

import java.util.List;

/**
 * Created by Jun on 2015/4/15.
 *
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private ImageButton hotImage,collectionImage,sortImage,settingsImage;

    private CollectionFragment collectionFragment;
    private HotFragment hotFragment;
    private SortFragment sortFragment;
    private SettingsFragment settingsFragment;

    private FragmentManager fragmentManager;

    private ViewPager viewPager;
    private List<View> viewList;
    private TextView hotText,diyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
        hotImage.setSelected(true);

    }

    //初始化控件
    public void initView(){
        hotImage = (ImageButton) findViewById(R.id.tab_hot);
        collectionImage = (ImageButton) findViewById(R.id.tab_collection);
        sortImage = (ImageButton) findViewById(R.id.tab_sort);
        settingsImage = (ImageButton) findViewById(R.id.tab_settings);

        hotImage.setOnClickListener(this);
        collectionImage.setOnClickListener(this);
        sortImage.setOnClickListener(this);
        settingsImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_hot:
                setTabSelection(0);
                hotImage.setSelected(true);
                collectionImage.setSelected(false);
                sortImage.setSelected(false);
                settingsImage.setSelected(false);
                break;
            case R.id.tab_collection:
                setTabSelection(1);
                hotImage.setSelected(false);
                collectionImage.setSelected(true);
                sortImage.setSelected(false);
                settingsImage.setSelected(false);
                break;
            case R.id.tab_sort:
                setTabSelection(2);
                hotImage.setSelected(false);
                collectionImage.setSelected(false);
                sortImage.setSelected(true);
                settingsImage.setSelected(false);
                break;
            case R.id.tab_settings:
                setTabSelection(3);
                hotImage.setSelected(false);
                collectionImage.setSelected(false);
                sortImage.setSelected(false);
                settingsImage.setSelected(true);
                break;
        }
    }

    public void setTabSelection(int index){

        //开始一个事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index){
            case 0:
                if(hotFragment == null){
                    // 如果HotFragment为空，则创建一个并添加到界面上
                    hotFragment = new HotFragment();
                    transaction.add(R.id.content, hotFragment);
                }else{
                    transaction
                            .show(hotFragment);
                }

                break;
            case 1:
                if(collectionFragment == null){
                    // 如果HotFragment为空，则创建一个并添加到界面上
                    collectionFragment = new CollectionFragment();
                    transaction.add(R.id.content,collectionFragment);
                }else{
                    transaction
                            .show(collectionFragment);
                }
                break;
            case 2:
                if(sortFragment == null){
                    // 如果HotFragment为空，则创建一个并添加到界面上
                    sortFragment = new SortFragment();
                    transaction.add(R.id.content,sortFragment);
                }else{
                    transaction
                            .show(sortFragment);
                }
                break;
            case 3:
                if(settingsFragment == null){
                    // 如果HotFragment为空，则创建一个并添加到界面上
                    settingsFragment = new SettingsFragment();
                    transaction.add(R.id.content,settingsFragment);
                }else{
                    transaction
                            .show(settingsFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param fragmentTransaction
     *            用于对Fragment执行操作的事务
     */

    public void hideFragments(FragmentTransaction fragmentTransaction){
        if(hotFragment != null){
            fragmentTransaction.hide(hotFragment);
        }
        if(collectionFragment != null){
            fragmentTransaction.hide(collectionFragment);
        }
        if(sortFragment != null){
            fragmentTransaction.hide(sortFragment);
        }
        if(settingsFragment != null){
            fragmentTransaction.hide(settingsFragment);
        }
    }

    /**
     * 屏幕旋转时会重新调用onCreate方法
     * 调用此方法即可避免重新初始化Activity
     * 在调用此方法前必须在AndroidManifest.xml中指定的Activity添加一下代码
     * android:configChanges="orientation|keyboard|keyboardHidden"
     * 这句代码表示，当设备旋转、显示键盘、隐藏键盘时调用Activity的onConfigurationChanged方法
     */

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(MainActivity.this, "现在是竖屏", Toast.LENGTH_SHORT).show();
        }
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(MainActivity.this, "现在是横屏", Toast.LENGTH_SHORT).show();
        }
    }
}

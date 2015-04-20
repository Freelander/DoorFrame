package com.display.doorframe.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.display.doorframe.R;

/**
 * Created by Jun on 2015/4/15.
 *
 */
public class SettingsFragment extends Fragment {

    private ListView lvSettings;
    private View view;

    private String[] value = new String[]{
            "热门区设置",
            "收藏区设置",
            "图片设置"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting,container,false);

        lvSettings = (ListView) view.findViewById(R.id.lv_settings);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1,value);
        lvSettings.setAdapter(adapter);

        lvSettings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        Toast.makeText(view.getContext(),"热门区设置",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(view.getContext(),"收藏区设置",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(view.getContext(),"图片设置",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return view;
    }


}

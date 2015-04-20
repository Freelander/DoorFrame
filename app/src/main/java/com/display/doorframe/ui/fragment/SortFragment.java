package com.display.doorframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
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
 */
public class SortFragment extends Fragment {

    private ListView sortLv;
    private View view;

    private String[] value = new String[]{
            "门框","门"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sort,container,false);

        sortLv = (ListView) view.findViewById(R.id.sort_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, value);
        sortLv.setAdapter(adapter);

        sortLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(view.getContext(),"点击门框",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(view.getContext(),"点击门",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return view;
    }
}

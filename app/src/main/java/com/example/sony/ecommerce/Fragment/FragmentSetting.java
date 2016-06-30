package com.example.sony.ecommerce.Fragment;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.sony.ecommerce.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSetting extends PreferenceFragment {
ImageView banner;
    ListView listView;
    public FragmentSetting() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragmen_setting, container, false);
       banner=(ImageView)view.findViewById(R.id.image_view_banner_setting);
        Glide.with(getActivity()).load(R.drawable.setting_banner).into(banner);
       listView=(ListView)view.findViewById(R.id.setting_list);
     // listView.addView( addPreferencesFromResource(R.xml.setting_pref));


        return view;
    }

}

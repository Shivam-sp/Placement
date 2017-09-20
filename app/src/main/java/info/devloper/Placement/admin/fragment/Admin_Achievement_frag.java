package info.devloper.Placement.admin.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.devloper.Placement.R;

/**
 * Created by Shivam Patel on 27-02-2017.
 */

public class Admin_Achievement_frag extends Fragment
{
    public Admin_Achievement_frag() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_achievement, container, false);
    }



}

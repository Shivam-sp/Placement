package info.devloper.Placement.admin.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.devloper.Placement.R;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 14-04-2017.
 */

public class Admin_Tech_main extends Fragment
{
    String IP,url;
    //Button btnadd,btnremove;
    public Admin_Tech_main() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/Technologies_Server";
            Log.d("IP --- ",url);
        }

        getView().findViewById(R.id.techripple1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Technologies_frag atf=new Admin_Technologies_frag();

                //FragmentManager fm=getActivity().getSupportFragmentManager();

                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("xyz");
               //int id=getIt.getId();
               // ft.hide(Admin_Tech_main.this);
                ft.replace(getView().getId(),atf);
                //ft.add(getView().getId(),atf);
                getActivity().findViewById(R.id.techripple1).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.techripple2).setVisibility(View.INVISIBLE);
                ft.commitAllowingStateLoss();
                // Log.d("fragmentmanager :",fm.toString());
                //fm.beginTransaction().replace(getView().getId(),atf).commit();
            }
        });

        getActivity().findViewById(R.id.techripple2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete_Technologies_frag dtf=new Delete_Technologies_frag();
                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("xyz");
                //int id=getIt.getId();
                // ft.hide(Admin_Tech_main.this);
                ft.replace(getView().getId(),dtf);
                //ft.add(getView().getId(),atf);
                getActivity().findViewById(R.id.techripple1).setVisibility(View.INVISIBLE);
                getActivity().findViewById(R.id.techripple2).setVisibility(View.INVISIBLE);
                ft.commitAllowingStateLoss();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_tech, container, false);
    }

}

package info.devloper.Placement.admin.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import info.devloper.Placement.R;
import info.devloper.Placement.admin.connectivity_classes.Technologies_Connection_Task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 27-02-2017.
 */

public class Admin_Technologies_frag extends Fragment
{
    private EditText Tid,Techname,Techtype;
    String tid,techname,techtype;

    private Button btntech;
    String url;
    String urlnext;
    String IP;
    public Admin_Technologies_frag() {
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
        Tid=(EditText)getView().findViewById(R.id.txtTid);
        Techname=(EditText)getView().findViewById(R.id.Tname);
        Techtype=(EditText)getView().findViewById(R.id.txtTtype);

        btntech=(Button)getActivity().findViewById(R.id.btntech);

        btntech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tid=Tid.getText().toString();
                techname=Techname.getText().toString();
                techtype=Techtype.getText().toString();

                urlnext=url+"?tid="+tid+"&techname="+techname+"&techtype="+techtype;

                Technologies_Connection_Task tct=new Technologies_Connection_Task();
                tct.execute(urlnext);
                urlnext="";
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_technologies, container, false);
    }



}

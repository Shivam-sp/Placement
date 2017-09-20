package info.devloper.Placement.admin.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import info.devloper.Placement.R;
import info.devloper.Placement.admin.connectivity_classes.Candidate_Verification_Task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 27-02-2017.
 */

public class Admin_Candidate_frag extends Fragment
{
    String IP;
    String url;
    ListView gridView;
    LinearLayout li;
    ScrollView scrollView;
    public Admin_Candidate_frag() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_candidate, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        li=(LinearLayout)getView().findViewById(R.id.linadmcan);
        scrollView=(ScrollView)getView().findViewById(R.id.admcansrc);
        gridView=(ListView) getView().findViewById(R.id.gridcandidatelist);
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/Admin_Candidate_Verification";
            Log.d("IP --- ",url);
        }

       Candidate_Verification_Task cvt=new Candidate_Verification_Task(getActivity(),gridView,li,scrollView);
        cvt.execute(url);
    }





    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }




}

package info.devloper.Placement.fragment;

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
import info.devloper.Placement.connectivity_classes.Qualification_Data_Task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 09-04-2017.
 */

public class Qualification_Frag extends Fragment
{
    String IP;
    String url,candidate_id;
    ListView gridView;
    LinearLayout li;
    ScrollView scrollView;
    public Qualification_Frag() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qualification_data, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = this.getArguments();
//        Log.d("fbundle--",bundle.toString());
        if (bundle != null)
        {
            candidate_id = bundle.getString("candidateid","");
            Log.d("-----",candidate_id);
        }
        li=(LinearLayout)getView().findViewById(R.id.lincanqa);
        scrollView=(ScrollView)getView().findViewById(R.id.qcansrc);
        gridView=(ListView) getView().findViewById(R.id.listqualification);
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/Qualification_Data_Server";
            Log.d("IP --- ",url);
        }



        String nurl=url+"?candidate_id="+candidate_id;

        Qualification_Data_Task qdt=new Qualification_Data_Task(getActivity(),gridView,li,scrollView);
        qdt.execute(nurl);
    }




    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
    }



}

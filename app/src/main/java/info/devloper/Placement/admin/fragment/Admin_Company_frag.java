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
import info.devloper.Placement.admin.connectivity_classes.Company_detail_task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 27-02-2017.
 */

public class Admin_Company_frag extends Fragment
{
    String IP;
    String url;
    ListView gridView;
    LinearLayout li;
    ScrollView scrollView;
    public Admin_Company_frag() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_company, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();

        li=(LinearLayout)getView().findViewById(R.id.linadmcom);
        scrollView=(ScrollView)getView().findViewById(R.id.admcomsrc);
        gridView=(ListView) getView().findViewById(R.id.gridcompanylist);
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url = "http://"+ IP+":8080/AppConnectivity/Admin_Get_CompanyList";
            Log.d("IP --- ", url);
        }

        Company_detail_task cdt=new Company_detail_task(getActivity(),gridView,li,scrollView);
        cdt.execute(url);
    }




}

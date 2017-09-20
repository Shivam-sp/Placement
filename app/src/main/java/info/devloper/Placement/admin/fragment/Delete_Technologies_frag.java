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
import info.devloper.Placement.admin.connectivity_classes.Technology_detail_task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 14-04-2017.
 */

class Delete_Technologies_frag extends Fragment
{

    String IP;
    String url;
    ListView gridView;
    LinearLayout li;
    ScrollView scrollView;
    public Delete_Technologies_frag() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.admin_delete_tech,container,false);

    }

    @Override
    public void onStart() {
        super.onStart();

        li=(LinearLayout)getView().findViewById(R.id.tclinadmcom);
        scrollView=(ScrollView)getView().findViewById(R.id.tcadmcomsrc);
        gridView=(ListView) getView().findViewById(R.id.tcgridcompanylist);
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url = "http://"+ IP+":8080/AppConnectivity/Admin_Get_Technology";
            Log.d("IP --- ", url);
        }

        Technology_detail_task tdt=new Technology_detail_task(getActivity(),gridView,li,scrollView);
        tdt.execute(url);

    }
}

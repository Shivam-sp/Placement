package info.devloper.Placement.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import info.devloper.Placement.R;
import info.devloper.Placement.ViewPagerAdepter;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 01-03-2017.
 */
public class Candidate_Educational_Details extends Fragment
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String candidateid;
    String url;
    String IP;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_candidate_educational, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/Candidate_Educational_Details";
            Log.d("IP --- ",url);
        }

        viewPager = (ViewPager)getView().findViewById(R.id.edviewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)getView().findViewById(R.id.edtabs);
        tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setupWithViewPager(viewPager);
    }



    private void setupViewPager(ViewPager viewPager) {
        Bundle bundle = this.getArguments();
//        Log.d("fbundle--",bundle.toString());
        if (bundle != null)
        {
            candidateid = bundle.getString("candidateid","");
            Log.d("-----",candidateid);
        }
        Bundle newbundle = new Bundle();
        Log.d("candidate id :--",candidateid+"");
        newbundle.putString("candidateid",candidateid);
        Qualification_Frag qlf=new Qualification_Frag();
        qlf.setArguments(newbundle);

        Projects_frag pf=new Projects_frag();
        pf.setArguments(newbundle);

        Achievement_add_frag adf=new Achievement_add_frag();
        adf.setArguments(newbundle);

        Toast.makeText(getContext(),"In Setup Pager",Toast.LENGTH_LONG).show();
        ViewPagerAdepter adapter=new ViewPagerAdepter(getActivity().getSupportFragmentManager());

        adapter.addFragment(pf,"Projects");
        adapter.addFragment(qlf,"Qualification");
        adapter.addFragment(adf,"Achievements");
        viewPager.setAdapter(adapter);
    }
}

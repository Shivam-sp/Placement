package info.devloper.Placement.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import info.devloper.Placement.R;
import info.devloper.Placement.connectivity_classes.Candidate_Profile_Task;
import info.devloper.Placement.connectivity_classes.Qualification_Data_Task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 23-03-2017.
 */

public class Candidate_Profile_Fragment extends Fragment
{
    Button addnew;
    LinearLayout mylayout;
    LinearLayout spc,li;
    String url;
    ImageView imageView;
    String IP,candidateid;
    ListView gridView;
    ScrollView scrollView;
    @Nullable

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.candidate_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/Candidate_Data_Server";
            Log.d("IP --- ",url);
        }

       // addnew=(Button)getView().findViewById(R.id.btnadd);
        imageView=(ImageView)getView().findViewById(R.id.profileimage);
        TextView textView= (TextView)getView().findViewById(R.id.username);
        TextView txtdegree = (TextView)getView().findViewById(R.id.degree);
        //TextView textlast=(TextView)getView().findViewById(R.id.ulastname);
        gridView= (ListView)getView().findViewById(R.id.listqualification);
        li=(LinearLayout)getView().findViewById(R.id.lincanqa);
        scrollView=(ScrollView)getView().findViewById(R.id.qcansrc);

        Bundle bundle = this.getArguments();
//        Log.d("fbundle--",bundle.toString());
        if (bundle != null)
        {
             candidateid = bundle.getString("candidateid","");
             Log.d("-----",candidateid);
        }

       String qurl="http://"+IP+":8080/AppConnectivity/Qualification_Data_Server";
        String nurl=qurl+"?candidate_id="+candidateid;

        Qualification_Data_Task qdt=new Qualification_Data_Task(getActivity(),gridView,li,scrollView);
        qdt.execute(nurl);
        String urln=url+"?candidate_id="+candidateid;
        Log.d("yoourl--",urln);

        Candidate_Profile_Task cpf=new Candidate_Profile_Task(getActivity(),imageView,textView,txtdegree);
        cpf.execute(urln);


       /* addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mylayout=new LinearLayout(getContext());
                mylayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                mylayout.setOrientation(LinearLayout.VERTICAL);

                EditText dg=new EditText(getContext());
                TextView tdg=new TextView(getContext());
                tdg.setText("Degree");
                EditText cb=new EditText(getContext());
                TextView tcb=new TextView(getContext());
                tcb.setText("Collage/Board");
                EditText yop=new EditText(getContext());
                TextView tyop=new TextView(getContext());
                tyop.setText("Year Of Passing");
                EditText percenr= new EditText(getContext());
                TextView tpercent=new TextView(getContext());
                tpercent.setText("Percent");
                EditText grd= new EditText(getContext());
                TextView tgrd=new TextView(getContext());
                tgrd.setText("Grade");

                mylayout.addView(tdg);
                mylayout.addView(dg);
                mylayout.addView(tcb);
                mylayout.addView(cb);
                mylayout.addView(tyop);
                mylayout.addView(yop);
                mylayout.addView(tpercent);
                mylayout.addView(percenr);
                mylayout.addView(tgrd);
                mylayout.addView(grd);


                spc =(LinearLayout)getView().findViewById(R.id.newqualify );
                spc.addView(mylayout);
            }
        });*/
    }
}

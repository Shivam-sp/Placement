package info.devloper.Placement.admin.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import fr.ganfra.materialspinner.MaterialSpinner;
import info.devloper.Placement.R;
import info.devloper.Placement.admin.connectivity_classes.Company_List_Task;
import info.devloper.Placement.admin.connectivity_classes.Placement_Candidate_Task;
import info.devloper.Placement.admin.connectivity_classes.Placement_Connection_Task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 27-02-2017.
 */

public class Admin_Placement_frag extends Fragment
{
    String spinnerurl;
    MaterialSpinner spinner;
    EditText year;
    String url,url1,actvurl,mainurl;
    String Byear,IP;
    Button btnplacement;
    AutoCompleteTextView actv;
    EditText Designation,Salarydetails,Workprofile,Doj;

    String batchyear,candidate_id,comanyname,designation,salarydetails,workprofile,doj;
    public Admin_Placement_frag()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            spinnerurl ="http://"+IP+":8080/AppConnectivity/Placement_Candidates_Server";
            actvurl="http://"+IP+":8080/AppConnectivity/Company_List_Server";
            mainurl="http://"+IP+":8080/AppConnectivity/Placement_Server";
            Log.d("IP --- ", url+"");
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        // the user is done typing.
        spinner=(MaterialSpinner)getActivity().findViewById(R.id.Candidates);
        year=(EditText)getActivity().findViewById(R.id.txtBid);
        actv = (AutoCompleteTextView)getActivity().findViewById(R.id.cmpid);
        btnplacement=(Button)getActivity().findViewById(R.id.btnplacement);
        Designation=(EditText)getActivity().findViewById(R.id.txtdesig);
        Salarydetails=(EditText)getActivity().findViewById(R.id.txtsal);
        Workprofile=(EditText)getActivity().findViewById(R.id.txtwp);
        Doj=(EditText)getActivity().findViewById(R.id.txtdoj);

        Company_List_Task clt=new Company_List_Task(getActivity(),actv);
        clt.execute(actvurl);

        btnplacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                batchyear=year.getText().toString();
                candidate_id=spinner.getSelectedItem().toString();
                comanyname=actv.getText().toString();
                designation=Designation.getText().toString();
                salarydetails=Salarydetails.getText().toString();
                workprofile=Workprofile.getText().toString();
                doj=Doj.getText().toString();
                Placement_Connection_Task pct=new Placement_Connection_Task();
                url=mainurl+"?batchyear="+batchyear+"&candidate_id="+candidate_id+"&comanyname="+comanyname+"&designation="+designation+"&salarydetails="+salarydetails+"&workprofile="+workprofile+"&doj="+doj;
                pct.execute(url);
                url="";
            }
        });

        year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    Byear=year.getText().toString();
                    Placement_Candidate_Task pct=new Placement_Candidate_Task(getActivity(),spinner);
                    url=spinnerurl+"?year="+Byear;
                    pct.execute(url);
                    url="";
                }
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_placement, container, false);
    }



}

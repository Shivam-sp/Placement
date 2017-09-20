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
import android.widget.EditText;

import info.devloper.Placement.R;
import info.devloper.Placement.connectivity_classes.Projects_Connection_Task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 01-03-2017.
 */

public class Projects_frag extends Fragment
{
    private EditText Project_ID,Candidate_ID,Title,Functionality,Grade,Year;
    String project_id,candidate_id,title,functionality,grade,year;

    private Button btnproject;
    String url;
    String urlnext,IP;

    public Projects_frag() {
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
            url="http://"+IP+":8080/AppConnectivity/Candidate_Educational_Details";
            Log.d("IP --- ",url);
        }

        Project_ID=(EditText)getView().findViewById(R.id.txtPid);
       // Candidate_ID=(EditText)getView().findViewById(R.id.txtCandid);
        Title=(EditText)getView().findViewById(R.id.txtTtl);
        Functionality=(EditText)getView().findViewById(R.id.txtfnc);
        Grade=(EditText)getView().findViewById(R.id.txtgrd);
        Year=(EditText)getView().findViewById(R.id.txtyr);

        getActivity().findViewById(R.id.submitprojectripple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                project_id=Project_ID.getText().toString();
                Bundle bundle = getArguments();
//        Log.d("fbundle--",bundle.toString());
                if (bundle != null)
                {
                    candidate_id = bundle.getString("candidateid","");
                    Log.d("-----",candidate_id);
                }
               // candidate_id=Candidate_ID.getText().toString();
                title=Title.getText().toString();
                functionality=Functionality.getText().toString();
                grade=Grade.getText().toString();
                year=Year.getText().toString();

                urlnext=url+"?project_id="+project_id+"&candidate_id="+candidate_id+"&title="+title+"&functionality="+functionality+"&grade="+grade+"&year="+year;

                Projects_Connection_Task pct=new Projects_Connection_Task(getContext());
                pct.execute(urlnext);
                urlnext="";
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects_add,container,false);
    }
}

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
import info.devloper.Placement.connectivity_classes.Achievement_Connection_Task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 01-03-2017.
 */

public class Achievement_add_frag extends Fragment
{
    private EditText Candidate_ID,AchievementDetails,MonthYear;
    String candidate_id,achievementdetails,monthyear;

    private Button btnachievement;
    String url;
    String urlnext,IP;

    public Achievement_add_frag() {
    }

    @Override
    public void onStart() {
        super.onStart();


        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/Achievement_Server";
            Log.d("IP --- ",url);
        }

       // Candidate_ID=(EditText)getView().findViewById(R.id.txtCaid);
        AchievementDetails=(EditText)getView().findViewById(R.id.txtAdid);
        MonthYear=(EditText)getView().findViewById(R.id.txtMthyr);

        getActivity().findViewById(R.id.btnachievement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
//        Log.d("fbundle--",bundle.toString());
                if (bundle != null)
                {
                    candidate_id = bundle.getString("candidateid","");
                    Log.d("-----",candidate_id);
                }
                //candidate_id=Candidate_ID.getText().toString();
                achievementdetails=AchievementDetails.getText().toString();
                monthyear=MonthYear.getText().toString();

                Log.d("candidate_id =",candidate_id);
                Log.d("achievement =",achievementdetails);
                Log.d("monthyear =",monthyear);
                urlnext=url+"?candidate_id="+candidate_id+"&achievementdetails="+achievementdetails+"&monthyear="+monthyear;

                Achievement_Connection_Task act=new Achievement_Connection_Task(getContext());
                act.execute(urlnext);
                urlnext="";
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_achievement_add,container,false);
    }
}

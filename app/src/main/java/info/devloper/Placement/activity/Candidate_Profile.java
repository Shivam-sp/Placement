package info.devloper.Placement.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import info.devloper.Placement.R;

import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 15-02-2017.
 */

public class Candidate_Profile extends AppCompatActivity
{
    Button addnew;
    LinearLayout mylayout;
    LinearLayout spc;
    String IP;
    String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_profile);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/AppConnectivity";
            Log.d("IP --- ",url);
        }
       /* addnew=(Button)findViewById(R.id.btnadd);
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mylayout=new LinearLayout(getApplicationContext());
                mylayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

                EditText dg=new EditText(getApplicationContext());
                EditText cb=new EditText(getApplicationContext());
                EditText yop=new EditText(getApplicationContext());
                EditText percenr= new EditText(getApplicationContext());
                EditText grd= new EditText(getApplicationContext());

                mylayout.addView(dg);
                mylayout.addView(cb);
                mylayout.addView(yop);
                mylayout.addView(percenr);
                mylayout.addView(grd);

                spc =(LinearLayout) findViewById(R.id.newqualify);

            }
        });*/
    }
}

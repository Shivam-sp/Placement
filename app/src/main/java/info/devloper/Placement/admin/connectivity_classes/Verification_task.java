package info.devloper.Placement.admin.connectivity_classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 04-03-2017.
 */

public class Verification_task extends AsyncTask<String,String,String>
{
    Context context;
    Button button;
    int pos;
    String IP;
    String[] candidateid;
    public Verification_task(Context context, Button btn,int position) {
        pos=position;
        this.context = context;
        button=btn;

    }

    @Override
    protected String doInBackground(String... url) {
        String json="";

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url[0]);
        try {
            candidateid=url[0].split("=");
          //  Log.d("candiid==",candidateid[1]+"");
            HttpResponse res=client.execute(httpGet);
            HttpEntity entity=res.getEntity();
            json= EntityUtils.toString(entity);

        } catch (IOException e) {
            Log.d("emsg :--- ",e.getMessage());
            e.printStackTrace();
        } //catch (//JSONException e) {
        //  e.printStackTrace();
        //}
        return json;
    }

    @Override
    protected void onPostExecute(String data) {
        // super.onPostExecute(jsondata);
        Log.d("----","Start.....");
        //JSONArray js= null;
        try {
                Log.d("json data;- ",data);

            SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String restoredText = prefs.getString("IP", null);
            if (restoredText != null) {
                IP = restoredText;
                Log.d("IP --- ",IP);
            }
            Button btn=(Button)button.findViewById(pos);
            btn.setText("Verified");
            Bundle b=new Bundle();
            b.putString("verify","Verified");
            String url="http://"+IP+":8080/AppConnectivity/Candidate_Notification";
            String urlwithid=url+"?candidateid="+candidateid[1];
            Notification_Task nt=new Notification_Task(context);
            nt.execute(url,candidateid[1]);

        } catch (Exception ex)
        {
            Log.e("exception :",ex.getMessage());
        }
        //  Log.d("json ;- ",js.toString());
        // Log.d("Json :- ",jsondata);
        Log.d("----","End.....");
    }
}

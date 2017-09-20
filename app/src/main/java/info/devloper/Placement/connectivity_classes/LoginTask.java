package info.devloper.Placement.connectivity_classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import info.devloper.Placement.activity.MainActivity;
import info.devloper.Placement.admin.activity.AdminMainActivity;

/**
 * Created by Shivam Patel on 21-02-2017.
 */

public class LoginTask extends AsyncTask<String,String,String>
{
    private Context mcontext;
    private View mrootView;
    public  LoginTask(Context context)
    {
        mcontext=context;

    }
    private ProgressDialog progressDialog;
   /* @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mcontext, "Logging in...", "Sending mail", true, false);
    }*/
String[] un;
    @Override
    protected String doInBackground(String... url) {
        String resp=null;

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url[0]);
        try {
            Log.d("url",url[0]);
            String[] s=url[0].split("=");
            un=s[1].split("&");
            Log.d("usn =",un[0]);
            HttpResponse res=client.execute(httpGet);
            HttpEntity entity=res.getEntity();
            resp= EntityUtils.toString(entity);
            Log.d("--- :",resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    String check="UPMatched";
    @Override
    protected void onPostExecute(String data) {
        // super.onPostExecute(jsondata);
        Log.d("----","Start.....");
        Log.d("msg==",data+"");
if(data.contains("UPMatched")) {
    Log.d("----","Start.....if");
    String[] n=data.split("-");
//    progressDialog.dismiss();
    Intent intent = new Intent(mcontext, MainActivity.class);
    intent.putExtra("candidate_id",un[0]);
    intent.putExtra("fname",n[1]);
    intent.putExtra("img",n[2]);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mcontext.startActivity(intent);
}
else if(data.contains("admatch"))
{
    Intent intent = new Intent(mcontext, AdminMainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    mcontext.startActivity(intent);
}
else
{
    Log.d("----","in else.....");
    Toast.makeText(mcontext,"User and Password could not  matched",Toast.LENGTH_LONG).show();

}
        Log.d("----","End.....");


    }
}

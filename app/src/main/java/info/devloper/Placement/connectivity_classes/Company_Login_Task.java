package info.devloper.Placement.connectivity_classes;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import info.devloper.Placement.activity.CompanyHome;

/**
 * Created by Shivam Patel on 21-02-2017.
 */

public class Company_Login_Task extends AsyncTask<String,String,String>
{
    private Context mcontext;
    private View mrootView;
    public Company_Login_Task(Context context)
    {
        mcontext=context;

    }
    String un;
    @Override
    protected String doInBackground(String... url) {
        String resp=null;

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url[0]);
        try {
           /* Log.d("url",url[0]);
            String[] s=url[0].split("/");
            un=s[4].substring(22,31);
            Log.d("usn =",un);*/
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
//        Log.d("msg==",data);
        if(data.contains("UPMatched")) {
            Log.d("----","Start.....if");
            String[] n=data.split("-");
            Intent intent = new Intent(mcontext,CompanyHome.class);

            intent.putExtra("cname",n[1]);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mcontext.startActivity(intent);
        }
        else
        {
            Log.d("----","in else.....");

        }
        Log.d("----","End.....");


    }
}

package info.devloper.Placement.connectivity_classes;

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

/**
 * Created by Shivam Patel on 21-02-2017.
 */

public class QualificationTask extends AsyncTask<String,String,String>
{
    private Context mcontext;
    private View mrootView;
    public QualificationTask(Context context)
    {
        mcontext=context;
    }

    @Override
    protected String doInBackground(String... url) {
        String resp=null;

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url[0]);
        try {
            HttpResponse res=client.execute(httpGet);
            HttpEntity entity=res.getEntity();
            resp= EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String data) {
        // super.onPostExecute(jsondata);
        Toast.makeText(mcontext,data,Toast.LENGTH_LONG).show();
        Log.d("msg==",data);
        Log.d("----","End.....");


    }
}

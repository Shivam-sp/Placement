package info.devloper.Placement.connectivity_classes;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Shivam Patel on 02-03-2017.
 */
public class Achievement_Connection_Task extends AsyncTask<String,String,String>
{

    Context mContext;

    public Achievement_Connection_Task(Context mContext) {
        this.mContext = mContext;
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
    protected void onPostExecute(String s) {
        Toast.makeText(mContext,s,Toast.LENGTH_LONG).show();
    }

}

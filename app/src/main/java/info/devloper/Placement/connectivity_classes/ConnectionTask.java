package info.devloper.Placement.connectivity_classes;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Shivam Patel on 21-02-2017.
 */

public class ConnectionTask extends AsyncTask<String,String,String>
{
    Context mcContext;

    public ConnectionTask(Context mcContext) {
        this.mcContext = mcContext;
    }

    @Override
    protected String doInBackground(String... url) {
        String resp=null;

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url[0]);
        try {
            Log.d("-----startback----",url[0]);
            HttpResponse res=client.execute(httpGet);
            Log.d("-----middleback----","==");
            HttpEntity entity=res.getEntity();
            resp= EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String s) {
       // super.onPostExecute(s);
        Log.d("datainserted",s);
        Toast.makeText(mcContext,s,Toast.LENGTH_LONG).show();
    }
}

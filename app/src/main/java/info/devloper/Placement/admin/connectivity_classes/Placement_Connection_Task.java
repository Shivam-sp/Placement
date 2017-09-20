package info.devloper.Placement.admin.connectivity_classes;

import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Shivam Patel on 21-02-2017.
 */

public class Placement_Connection_Task extends AsyncTask<String,String,String>
{

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
}

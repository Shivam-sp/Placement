package info.devloper.Placement.connectivity_classes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivam Patel on 01-04-2017.
 */

public class ImageByteArray_Task extends AsyncTask<String,String ,String >
{
    @Override
    protected String doInBackground(String... url) {
        String json="";

        DefaultHttpClient client = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(url[0]);
        try {
            String data=url[1];
            List nameValuePairs = new ArrayList();
            nameValuePairs.add(new BasicNameValuePair("bytestring",url[1]));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse res=client.execute(httpPost);
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
    protected void onPostExecute(String jsondata) {
        // super.onPostExecute(jsondata);
        Log.d("----","Start.."+jsondata);
        Bundle bd=new Bundle();
        bd.putString("imgpath",jsondata);
        JSONArray js= null;

        //  Log.d("json ;- ",js.toString());
        // Log.d("Json :- ",jsondata);
        Log.d("----","End.....");
    }
}

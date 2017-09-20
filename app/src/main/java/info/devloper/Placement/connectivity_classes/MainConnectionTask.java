package info.devloper.Placement.connectivity_classes;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import info.devloper.Placement.R;

/**
 * Created by Shivam Patel on 21-02-2017.
 */

public class MainConnectionTask extends AsyncTask<String,String,String>
{
    private Context mcontext;
    private View mrootView;
    public MainConnectionTask(Context context, View rootView)
    {
          mcontext=context;
          mrootView=rootView;
    }

    @Override
    protected String doInBackground(String... url) {
        String json="";

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url[0]);
        try {
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
    protected void onPostExecute(String jsondata) {
       // super.onPostExecute(jsondata);
        Log.d("----","Start.....");
        JSONArray js= null;
        try {
            Spinner spinner = null;
           // ArrayList<String> al=new ArrayList<String>();
            js = new JSONArray(jsondata);
            Log.d("json length :-", String.valueOf(js.length()));
            for(int i=0;i<js.length();i++)
            {
                JSONObject obj=(JSONObject)js.get(i);
                Log.d("json data;- ",obj.toString());
                String fn=obj.getString("firstname");
                Log.d("--------",".....");
                Log.d("name",fn);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  Log.d("json ;- ",js.toString());
       // Log.d("Json :- ",jsondata);
        Log.d("----","End.....");


    }

}

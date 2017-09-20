package info.devloper.Placement.connectivity_classes;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

import fr.ganfra.materialspinner.MaterialSpinner;
import info.devloper.Placement.R;

/**
 * Created by Shivam Patel on 02-03-2017.
 */

public class Degree_Connection_Task extends AsyncTask<String,String,String>
{
    private Context mcontext;
    private View mrootView;
   // ImageView imageView;
    public Degree_Connection_Task(Context context, View rootView)
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
            MaterialSpinner degree = null;
            ArrayList<String> al=new ArrayList<String>();
            js = new JSONArray(jsondata);
            for(int i=0;i<js.length();i++)
            {
            JSONObject obj=(JSONObject)js.get(i);
            Log.d("json data;- ",js.toString());
            String dg=obj.getString("degreename");
                dg=dg.replace("_"," ");
            Log.d("degree :- ",dg);
                al.add(i,dg);
                Log.d("name",al.toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mcontext, android.R.layout.simple_spinner_item,al);

            degree=(MaterialSpinner) mrootView.findViewById(R.id.degreespinner);
            degree.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  Log.d("json ;- ",js.toString());
        // Log.d("Json :- ",jsondata);
        Log.d("----","End.....");
    }
}

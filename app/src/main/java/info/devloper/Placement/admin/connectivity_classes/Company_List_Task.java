package info.devloper.Placement.admin.connectivity_classes;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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
 * Created by Shivam Patel on 04-03-2017.
 */

public class Company_List_Task extends AsyncTask<String,String,String>
{
    Context context;
    AutoCompleteTextView actv;

    public Company_List_Task(Context context,AutoCompleteTextView actv) {
        this.context = context;
        this.actv=actv;
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
            AutoCompleteTextView autoCompleteTextView = null;
            ArrayList<String> al=new ArrayList<String>();
            js = new JSONArray(jsondata);
            for(int i=0;i<js.length();i++)
            {
                JSONObject obj=(JSONObject)js.get(i);
                Log.d("json data;- ",js.toString());
                String dg=obj.getString("company_name");
                Log.d("Candidate_ID :- ",dg);
                al.add(i,dg);
                Log.d("name",al.toString());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,al);

            autoCompleteTextView=(AutoCompleteTextView)actv.findViewById(R.id.cmpid);
            autoCompleteTextView.setThreshold(1);
            autoCompleteTextView.setAdapter(adapter);
            autoCompleteTextView.setTextColor(Color.RED);
        } catch (JSONException e) {
            Log.e("-----",e.getMessage());
        }catch (Exception ex)
        {
            Log.e("exception :",ex.getMessage());
        }
        //  Log.d("json ;- ",js.toString());
        // Log.d("Json :- ",jsondata);
        Log.d("----","End.....");
    }
}

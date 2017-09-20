package info.devloper.Placement.connectivity_classes;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

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
import info.devloper.Placement.activity.QuaAdapter;
import info.devloper.Placement.listitem;

/**
 * Created by Shivam Patel on 09-04-2017.
 */

public class Qualification_Data_Task extends AsyncTask<String,String,String>
{
    Context context;
    View mrooView;
    ListView gridView;
    LinearLayout li;
    ScrollView scrollView;
    public Qualification_Data_Task(Context context, View rootview, LinearLayout li, ScrollView scrollView) {
        this.context = context;
        mrooView=rootview;
        this.li=li;
        this.scrollView=scrollView;
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
            li=(LinearLayout)li.findViewById(R.id.lincanqa);
            scrollView=(ScrollView)scrollView.findViewById(R.id.qcansrc);
            gridView=(ListView) mrooView.findViewById(R.id.listqualification);
            int[] btnid = new int[0];
            // AutoCompleteTextView autoCompleteTextView = null;
            ArrayList al=new ArrayList();
            js = new JSONArray(jsondata);
            Log.d("jjjj",js.toString());
            for(int i=0;i<js.length();i++)
            {
                JSONObject obj=(JSONObject)js.get(i);

                Log.d("json data;- ",js.toString());
                String degree=obj.getString("degree");
                String clgname=obj.getString("clgname");
                String year=obj.getString("year");
                String grade=obj.getString("grade");
                Log.d("Degree:- ",degree);
                listitem griditem=new listitem(degree,clgname,year,grade);
                al.add(griditem);
                Log.d("name",al.toString());
            }
            QuaAdapter adapter = new QuaAdapter(context,al);

            gridView=(ListView) mrooView.findViewById(R.id.listqualification);
            gridView.setAdapter(adapter);
            /*gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context,((Button) view).getText(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context,((Button) view).getText(), Toast.LENGTH_SHORT).show();
                    // Log.d("fragmentmanager :",fm.toString());
                    //fm.beginTransaction().replace(R.id.frame,cpf).commit();

                }
            });
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

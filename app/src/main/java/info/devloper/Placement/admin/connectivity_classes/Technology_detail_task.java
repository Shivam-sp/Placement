package info.devloper.Placement.admin.connectivity_classes;

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
import info.devloper.Placement.admin.TechCustomAdepter;
import info.devloper.Placement.admin.Techitem;

import static info.devloper.Placement.R.id.tcadmcomsrc;
import static info.devloper.Placement.R.id.tcgridcompanylist;

/**
 * Created by Shivam Patel on 14-04-2017.
 */

public class Technology_detail_task extends AsyncTask<String,String,String>
{

    String IP;
    String url;
    ListView gridView;
    LinearLayout li;
    ScrollView scrollView;
    Context context;
    View mrooView;

    public Technology_detail_task(Context context ,ListView gridView, LinearLayout li, ScrollView scrollView) {
        mrooView=gridView;
        this.context=context;
        this.gridView = gridView;
        this.li = li;
        this.scrollView = scrollView;
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
        Log.d("----","Start.="+jsondata);
        JSONArray js= null;
        try {
            li=(LinearLayout)li.findViewById(R.id.tclinadmcom);
            scrollView=(ScrollView)scrollView.findViewById(R.id.tcadmcomsrc);
            gridView=(ListView) mrooView.findViewById(R.id.tcgridcompanylist);
         //   int[] btnid = new int[0];
            // AutoCompleteTextView autoCompleteTextView = null;
            ArrayList al=new ArrayList();
            js = new JSONArray(jsondata);
            for(int i=0;i<js.length();i++)
            {
                JSONObject obj=(JSONObject)js.get(i);

                Log.d("json data;- ",js.toString());
                int tid=obj.getInt("Tid");
                String tname=obj.getString("Tname");
                String ttype=obj.getString("Ttype");
                Log.d("TName :- ",tname);
                Techitem techitem=new Techitem(tid,tname,ttype);
                al.add(techitem);
                Log.d("name",al.toString());
            }
            TechCustomAdepter adapter = new TechCustomAdepter(context,al);

            gridView=(ListView) mrooView.findViewById(tcgridcompanylist);
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

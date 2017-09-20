package info.devloper.Placement.connectivity_classes;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
import info.devloper.Placement.SearchAdepter;
import info.devloper.Placement.fragment.Candidate_Profile_Fragment;
import info.devloper.Placement.searchlist;

import static info.devloper.Placement.R.id.grid;

/**
 * Created by Shivam Patel on 28-03-2017.
 */

public class Candidate_Search_Task extends AsyncTask<String,String,String>
{
    ListView gridView;
    private Context mcontext;
    private View mrootView;
    FragmentManager fm;
    public Candidate_Search_Task(Context context, View mrootView, FragmentManager fm)
    {
        mcontext=context;
        this.mrootView=mrootView;
        this.fm=fm;
    }
    String un;
    String canid;
    @Override
    protected String doInBackground(String... url)
    {
        String resp=null;

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url[0]);
        try {
            Log.d("url",url[0]);
           /* String[] s=url[0].split("/");
            un=s[4].substring(22,31);
            Log.d("usn =",un);*/

            HttpResponse res=client.execute(httpGet);
            HttpEntity entity=res.getEntity();
            resp= EntityUtils.toString(entity);
            Log.d("--- :",resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    protected void onPostExecute(String jsondata) {
        // super.onPostExecute(jsondata);
        Log.d("----","Start.....");
        Log.d("msg==",jsondata);

        JSONArray js= null;
        ArrayList al=new ArrayList();
        try {
            js = new JSONArray(jsondata);
            for(int i=0;i<js.length();i++)
            {
                JSONObject obj=(JSONObject)js.get(i);
                Log.d("json data;- ",obj.toString());
                canid=obj.getString("candidateid");
                String fn=obj.getString("firstname");

                String ln=obj.getString("lastname");
                String gen=obj.getString("gender");
                String dob=obj.getString("date");

                String city=obj.getString("city");

                searchlist listitem=new searchlist(canid,fn,ln,gen,dob,city);

                al.add(listitem);

            }

            Log.d("arraylist--",al.toString());
            SearchAdepter adapter = new SearchAdepter(mcontext,al);

            gridView=(ListView)mrootView.findViewById(grid);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Candidate_Profile_Fragment cpf=new Candidate_Profile_Fragment();
                    Bundle bundle = new Bundle();
                    Log.d("candidate id :--",canid);
                    bundle.putString("candidateid",canid);
                    cpf.setArguments(bundle);
                    Log.d("bundle--",bundle.toString());
                   // Log.d("fragmentmanager :",fm.toString());
                    fm.beginTransaction().replace(R.id.frame,cpf).commit();

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  Log.d("json ;- ",js.toString());
        // Log.d("Json :- ",jsondata);
        Log.d("----","End.....");


    }
}

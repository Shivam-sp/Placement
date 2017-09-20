package info.devloper.Placement.connectivity_classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

import info.devloper.Placement.CicleTransform;

import static android.content.Context.MODE_PRIVATE;

import static info.devloper.Placement.R.id.degree;
import static info.devloper.Placement.R.id.profileimage;
import static info.devloper.Placement.R.id.username;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 23-03-2017.
 */

public class Candidate_Profile_Task extends AsyncTask<String,String,String> {
    private Context mcontext;
    private View mrootView;
    TextView mtextView,mtxtage,mtxtdegree;
    ImageView imageView;
    String IP;

    public Candidate_Profile_Task(Context context, View rootView, TextView textView, TextView txtdegree) {
        mcontext = context;
        mrootView = rootView;
        mtextView = textView;
        mtxtdegree = txtdegree;
    }

    @Override
    protected String doInBackground(String... url) {


        String json = "";

        DefaultHttpClient client = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url[0]);
        try {
            HttpResponse res = client.execute(httpGet);
            HttpEntity entity = res.getEntity();
            json = EntityUtils.toString(entity);

        } catch (IOException e) {
            Log.d("emsg :--- ", e.getMessage());
            e.printStackTrace();
        } //catch (//JSONException e) {
        //  e.printStackTrace();
        //}
        return json;
    }

    @Override
    protected void onPostExecute(String jsondata) {
        // super.onPostExecute(jsondata);

        SharedPreferences prefs = mcontext.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            Log.d("IP --- ",IP);
        }
        Log.d("----", "Start.....");
        JSONArray js = null;
        try {
            //Spinner spinner = null;
            TextView textView;
            TextView txtage,txtdegree;
            ArrayList<String> al = new ArrayList<String>();
            js = new JSONArray(jsondata);
            for (int i = 0; i < js.length(); i++) {
                //ImageView imageView;
                JSONObject obj = js.getJSONObject(i);
                Log.d("json data;- ", obj.toString());
                String fn = obj.getString("image");
                Log.d("image :- ", fn);
                fn = fn.replace("localhost", IP);
                Log.d("new path:-", fn);
                imageView = (ImageView) mrootView.findViewById(profileimage);
                textView = (TextView) mtextView.findViewById(username);
                txtdegree = (TextView) mtxtdegree.findViewById(degree);
                //Bitmap bt=
                //decodeUri(Uri.parse(fn));
                textView.setText(obj.getString("first"));

                txtdegree.setText(obj.getString("gender"));
                Picasso.with(mcontext) //Context
                        .load(fn).transform(new CicleTransform()).fit() //URL/FILE
                        .into(imageView);//an ImageView Object to show the loaded image;
                al.add(i, fn);
                Log.d("name", al.toString());
            }
           /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(mcontext, android.R.layout.simple_spinner_item,al);

            spinner=(Spinner)mrootView.findViewById(R.id.spinner);
            spinner.setAdapter(adapter);*/
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        //  Log.d("json ;- ",js.toString());
        // Log.d("Json :- ",jsondata);
    }


    }

package info.devloper.Placement.admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

import info.devloper.Placement.R;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 04-04-2017.
 */

public class CompCustomAdepter extends BaseAdapter
{
    Context context;
    ArrayList name;
    int[]btnid;
    Button btn;
    LayoutInflater inflter;
    String url,newurl,deleteurl;
    String IP;
    public CompCustomAdepter(Context applicationContext, ArrayList firstname) {
        this.context = applicationContext;
        inflter = (LayoutInflater.from(applicationContext));
        name = firstname;
        //this.btn=btn;
        //this.btnid=btnid;
    }


    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return name.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/Admin_Delete_Companies";
            deleteurl="http://"+IP+":8080/AppConnectivity/Admin_Delete_Companies";
            Log.d("IPp--- ",url);
        }
        convertView = inflter.inflate(R.layout.activity_admin_company_grid, null);
        TableLayout tb=(TableLayout)convertView.findViewById(R.id.lincmpgrid);
        TableRow linearLayout=(TableRow) convertView.findViewById(R.id.lincmpgrid0);
        final TextView cid=(TextView)convertView.findViewById(R.id.cmpid);
        TextView compname=(TextView)convertView.findViewById(R.id.compname);


        cid.setId(position);

        cid.setText(((Griditem)name.get(position)).getCandidateid());
        compname.setText(((Griditem)name.get(position)).getFirstname());
         Button button1 = (Button) convertView.findViewById(R.id.btn1);
        button1.setId(position);

        tb.setId(position+1);
        linearLayout.setId(position+2);
        final View finalConvertView1 = convertView;
        final FrameLayout fm= (FrameLayout) finalConvertView1.findViewById(R.id.framadmincom);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView tv= (TextView) fm.findViewById(position);
                Toast.makeText(context,tv.getText().toString(),Toast.LENGTH_LONG).show();
                Log.d("company id==",tv.getText().toString());
                String cmpid=tv.getText().toString();

                newurl=url+"?cid="+cmpid;
                Log.d("Canid -- ",newurl);
                //Toast.makeText(context,"xyz"+position,Toast.LENGTH_LONG).show();
                TableLayout tb=(TableLayout)fm.findViewById(position+1);
               TableRow linearLayout= (TableRow) tb.findViewById(position+2);
                Log.d("linne--",linearLayout.toString());
                CompDelete_task cdt= new CompDelete_task(context,position);
                cdt.execute(newurl);
                tb.removeView(linearLayout);

               /* TextView tv1=(TextView) fm.findViewById(0+position);
                TextView tv2=(TextView) fm.findViewById(1+position);
                TextView tv3=(TextView) fm.findViewById(2+position);*/
                // String x=button.getParent().toString();
                //Toast.makeText(context,"tv1="+ tv1.getText().toString()+" tv2="+tv2.getText().toString()+"tv3="+ tv3.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });
//        btn.setText("Verify");

        return convertView;
    }

    private class CompDelete_task extends AsyncTask<String,String,String>
    {
        Context context;
        int pos;
        String[] companyid;
        public CompDelete_task(Context context, int position) {
            pos=position;
            this.context = context;

        }

        @Override
        protected String doInBackground(String... url) {
            String json="";

            DefaultHttpClient client = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(url[0]);
            try {
                companyid=url[0].split("=");
                //  Log.d("candiid==",candidateid[1]+"");
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
        protected void onPostExecute(String data) {
            // super.onPostExecute(jsondata);
            Log.d("----","Start.....");
            //JSONArray js= null;
            try {
                Log.d("json data;- ",data);

                Toast.makeText(context,data,Toast.LENGTH_LONG).show();

            } catch (Exception ex)
            {
                Log.e("exception :",ex.getMessage());
            }
            //  Log.d("json ;- ",js.toString());
            // Log.d("Json :- ",jsondata);
            Log.d("----","End.....");
        }
    }
}

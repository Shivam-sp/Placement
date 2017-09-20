package info.devloper.Placement.admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import info.devloper.Placement.admin.connectivity_classes.Verification_task;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 30-03-2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList name;
    int[]btnid;
    Button btn;
    LayoutInflater inflter;
    String url,newurl,deleteurl;
    String IP;
    public CustomAdapter(Context applicationContext, ArrayList firstname) {
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
            url="http://"+IP+":8080/AppConnectivity/Verified_Candidates";
            deleteurl="http://"+IP+":8080/AppConnectivity/Admin_Delete_Candidates";
            Log.d("IP --- ",url);
        }
        convertView = inflter.inflate(R.layout.activity_gridview, null);
        TableLayout tb=(TableLayout)convertView.findViewById(R.id.lingrid);
        TableRow linearLayout=(TableRow)convertView.findViewById(R.id.lingrid0);
        final TextView cid=(TextView)convertView.findViewById(R.id.candidateid);
        TextView fn=(TextView)convertView.findViewById(R.id.firstname);
        TextView mn=(TextView)convertView.findViewById(R.id.middlename);


        cid.setId(position);

        cid.setText(((Griditem)name.get(position)).getCandidateid());
        fn.setText(((Griditem)name.get(position)).getFirstname());
        mn.setText(((Griditem)name.get(position)).getMiddlename());
        final Button button = (Button) convertView.findViewById(R.id.btn);
        Button button1 = (Button) convertView.findViewById(R.id.btn1);
        button.setId(position);
        button1.setId(position);

        tb.setId(position+1);
        linearLayout.setId(position+2);
        final View finalConvertView = convertView;
        final View finalConvertView1 = convertView;
        final FrameLayout fm= (FrameLayout) finalConvertView1.findViewById(R.id.framadmincan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context,"Verified",Toast.LENGTH_LONG).show();

                TextView tv= (TextView) fm.findViewById(position);
                Toast.makeText(context,tv.getText().toString(),Toast.LENGTH_LONG).show();
                Log.d("candidate id==",tv.getText().toString());
                String candidateid=tv.getText().toString();

                newurl=url+"?candidateid="+candidateid;
                Log.d("Canid -- ",newurl);
                Verification_task vt= new Verification_task(context,button,position);
                vt.execute(newurl);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(context,"xyz"+position,Toast.LENGTH_LONG).show();


                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do your work here
                        TextView tv= (TextView) fm.findViewById(position);
                        Toast.makeText(context,tv.getText().toString(),Toast.LENGTH_LONG).show();
                        Log.d("candidate id==",tv.getText().toString());
                        String candidateid=tv.getText().toString();
                        deleteurl=deleteurl+"?candidateid="+candidateid;
                        Log.d("Canid -- ",deleteurl);
                        TableLayout tb=(TableLayout)fm.findViewById(position+1);
                        TableRow linearLayout= (TableRow) tb.findViewById(position+2);
                        Log.d("linne--",linearLayout.toString());
                        DeleteCandidate_task dct= new DeleteCandidate_task(context,position);
                        dct.execute(deleteurl);
                        fm.removeView(linearLayout);
                        dialog.dismiss();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                alert.show();

            }


               /* TextView tv1=(TextView) fm.findViewById(0+position);
                TextView tv2=(TextView) fm.findViewById(1+position);
                TextView tv3=(TextView) fm.findViewById(2+position);*/
                // String x=button.getParent().toString();
                //Toast.makeText(context,"tv1="+ tv1.getText().toString()+" tv2="+tv2.getText().toString()+"tv3="+ tv3.getText().toString(),Toast.LENGTH_LONG).show();

        });
//        btn.setText("Verify");

        return convertView;

    }

    private class DeleteCandidate_task extends AsyncTask<String,String,String>
    {
        Context context;
        int pos;
        String[] candidateid;
        public DeleteCandidate_task(Context context, int position) {
            pos=position;
            this.context = context;

        }

        @Override
        protected String doInBackground(String... url) {
            String json="";

            DefaultHttpClient client = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(url[0]);
            try {
                candidateid=url[0].split("=");
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

package info.devloper.Placement.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import info.devloper.Placement.Mail.Mail;
import info.devloper.Placement.R;

import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 24-03-2017.
 */

public class Forgot_Password extends AppCompatActivity
{
    String urlwithmail;

    public Forgot_Password() {
    }

    EditText sendmail;
    String mailid;
    String passwordurl ;
    String IP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            passwordurl="http://"+IP+":8080/AppConnectivity/Forgot_Password_Server";
            Log.d("IP --- ",passwordurl);
        }

        setContentView(R.layout.activity_forgot_password);
       // Button addImage = (Button) findViewById(R.id.btnemail);
        sendmail = (EditText)findViewById(R.id.verifyemail);
        findViewById(R.id.btnemail).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mailid=sendmail.getText().toString();
                urlwithmail = passwordurl+"?mailid="+mailid;
                new SendMail().execute(urlwithmail);
            }
        });
    }

    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000)
        {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, "Press again to go back",
                    Toast.LENGTH_SHORT).show();


        }
        else
        {
            super.onBackPressed();
            startActivity(new Intent(Forgot_Password.this, LoginActivity.class));
            finish();
        }
    }

    private class SendMail extends AsyncTask<String,String, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Forgot_Password.this, "Please wait", "Sending mail", true, false);
        }

        protected String doInBackground(String... params) {
            String resp=null;
            DefaultHttpClient client = new DefaultHttpClient();

            HttpGet httpGet = new HttpGet(params[0]);
            try {
                HttpResponse res=client.execute(httpGet);
                HttpEntity entity=res.getEntity();
                resp= EntityUtils.toString(entity);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject obj= null;
            try {
                obj = new JSONObject(resp);
                Log.d("json data;- ",obj.toString());
                maildata=obj.getString("password");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Mail m = new Mail("shivam2296.ps@gmail.com", "8128shivam");

            String[] toArr = {mailid};
            m.setTo(toArr);
            m.setFrom("shivam2296.ps@gmail.com");
            m.setSubject("Password Recovery.");
            m.setBody("Your Password is : "+maildata);

            try {
                if(m.send()) {
                     Toast.makeText(getApplicationContext(), "Email sent successfully.", Toast.LENGTH_LONG).show();
                    System.out.println("Email Successfully Sent.");

                } else {
                    // Toast.makeText(MainActivity.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                }
            } catch(Exception e) {
                Log.e("MailApp", "Could not send email", e);
            }

            return resp;
        }

        String maildata;
        @Override
        protected void onPostExecute(String jsondata) {

            //super.onPostExecute(jsondata);


            LinearLayout ll=(LinearLayout) findViewById(R.id.mainlayout);
            TextView tv=new TextView(getApplicationContext());
            //tv.setText("Email sent Succsessfully");
           // ll.addView(tv);
            Toast.makeText(getApplicationContext(),"Email Successfully Sent",Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}

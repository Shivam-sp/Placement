package info.devloper.Placement.admin.connectivity_classes;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.devloper.Placement.Mail.Mail;

/**
 * Created by Shivam Patel on 01-04-2017.
 */

public class CandidateId_Notify extends AsyncTask<String,String,String>
{
    Context mContext;

    public CandidateId_Notify(Context mContext) {
        this.mContext = mContext;
    }

    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mContext, "Please wait", "Sending mail", true, false);
    }

    protected String doInBackground(String... params) {
        String resp=null;
        DefaultHttpClient client = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(params[0]);
        try {
            //String data=params[1];
            List nameValuePairs = new ArrayList();
            nameValuePairs.add(new BasicNameValuePair("candidateid",params[1]));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse res=client.execute(httpPost);
            HttpEntity entity=res.getEntity();
            resp= EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj= null;

        maildata=resp;
        Log.d("mid---",maildata+"");

        Mail m = new Mail("shivam2296.ps@gmail.com", "8128shivam");

        String[] toArr = {maildata};
        m.setTo(toArr);
        m.setFrom("shivam2296.ps@gmail.com");
        m.setSubject("Candidate ID Info..");
        m.setBody("Your Candidate Id is :"+params[1]);

        try {
            if(m.send()) {
                //  Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show();
                System.out.println("Email was sent successfully.");

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

        progressDialog.dismiss();
    }
}

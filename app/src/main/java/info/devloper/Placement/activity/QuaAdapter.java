package info.devloper.Placement.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import info.devloper.Placement.R;
import info.devloper.Placement.listitem;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 09-04-2017.
 */

public class QuaAdapter extends BaseAdapter {

    Context context;
    ArrayList name;
    int[]btnid;
    Button btn;
    LayoutInflater inflter;
    String url,newurl,deleteurl;
    String IP;
    public QuaAdapter(Context applicationContext, ArrayList firstname) {
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
        convertView = inflter.inflate(R.layout.qualification_listview, null);
        TableLayout tb=(TableLayout)convertView.findViewById(R.id.linlist);
        TableRow linearLayout=(TableRow)convertView.findViewById(R.id.linlist0);
        TextView dgr=(TextView)convertView.findViewById(R.id.quaadpt1);
        TextView clg=(TextView)convertView.findViewById(R.id.quaadpt2);
        TextView yr=(TextView)convertView.findViewById(R.id.quaadpt3);
        TextView gr=(TextView)convertView.findViewById(R.id.quaadpt4);


        dgr.setId(position);

        dgr.setText(((listitem)name.get(position)).getDegree());
        clg.setText(((listitem)name.get(position)).getCollage());
        yr.setText(((listitem)name.get(position)).getYear());
        gr.setText(((listitem)name.get(position)).getGrade());

        return convertView;

    }

}

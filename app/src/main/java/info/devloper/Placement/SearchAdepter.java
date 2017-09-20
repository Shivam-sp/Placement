package info.devloper.Placement;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 10-04-2017.
 */

public class SearchAdepter extends BaseAdapter
{
    Context context;
    ArrayList name;
    LayoutInflater inflter;
    String url,newurl,deleteurl;
    String IP;
    public SearchAdepter(Context applicationContext, ArrayList firstname) {
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
        convertView = inflter.inflate(R.layout.cansearch_listview, null);
        TableLayout tb=(TableLayout)convertView.findViewById(R.id.slinlist);
        TableRow linearLayout=(TableRow)convertView.findViewById(R.id.slinlist0);
        TextView canid=(TextView)convertView.findViewById(R.id.sadpt1);
        TextView canname=(TextView)convertView.findViewById(R.id.sadpt2);
        TextView canlast=(TextView)convertView.findViewById(R.id.sadpt3);
        TextView cangen=(TextView)convertView.findViewById(R.id.sadpt4);
        TextView candob=(TextView)convertView.findViewById(R.id.sadpt5);
        TextView cancity=(TextView)convertView.findViewById(R.id.sadpt6);


        canid.setId(position);

        canid.setText(((searchlist)name.get(position)).getCanid());
        canname.setText(((searchlist)name.get(position)).getCanname());
        canlast.setText(((searchlist)name.get(position)).getCanlast());
        cangen.setText(((searchlist)name.get(position)).getCangen());
        candob.setText(((searchlist)name.get(position)).getCandob());
        cancity.setText(((searchlist)name.get(position)).getCancity());

        return convertView;

    }
}

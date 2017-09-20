package info.devloper.Placement.admin.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import info.devloper.Placement.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Shivam Patel on 28-03-2017.
 */

public class GetIP extends Fragment
{
    public static final String MY_PREFS_NAME = "Ip Addresss";
    EditText et;
    Button btn;
    public GetIP() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        et=(EditText)getActivity().findViewById(R.id.iptext);
        btn=(Button)getActivity().findViewById(R.id.getip);
        final SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipaddress=et.getText().toString();
                editor.putString("IP", ipaddress);
                editor.commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_getip, container, false);
    }

}

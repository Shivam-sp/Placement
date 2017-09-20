package info.devloper.Placement.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import info.devloper.Placement.R;
import info.devloper.Placement.activity.Company_Form;

/**
 * Created by Shivam Patel on 29-01-2017.
 */
public class Company_frag extends Fragment implements  View.OnClickListener
{
    public Button cmpnybtn;
    Intent cmpnyform;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.company_frag,container,false);
        cmpnybtn=(Button)view.findViewById(R.id.cmpbutton);
        cmpnybtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v)
    {
        Toast.makeText(getActivity(),"Candidate Form",Toast.LENGTH_SHORT).show();
        cmpnyform = new Intent(getActivity(),Company_Form.class);
        startActivity(cmpnyform);
    }
}

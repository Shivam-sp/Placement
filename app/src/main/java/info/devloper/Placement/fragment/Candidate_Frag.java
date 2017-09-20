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

/**
 * Created by Shivam Patel on 28-01-2017.
 */
public class Candidate_Frag extends Fragment implements View.OnClickListener
{
    public Button candibtn;
    Intent candidform;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.candidate_frag,container,false);
        candibtn=(Button)view.findViewById(R.id.cndbutton);
        candibtn.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v)
    {
        Toast.makeText(getActivity(),"Candidate Form",Toast.LENGTH_SHORT).show();
         candidform = new Intent(getActivity(),Candidate_Frag.class);
        startActivity(candidform);
    }
}

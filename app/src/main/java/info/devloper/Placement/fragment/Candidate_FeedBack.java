package info.devloper.Placement.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.devloper.Placement.R;

/**
 * Created by Shivam Patel on 11-04-2017.
 */

public class Candidate_FeedBack extends Fragment
{

    public Candidate_FeedBack() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_candidate_feedback, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}

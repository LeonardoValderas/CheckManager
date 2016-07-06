package com.jofre.managercheck.receiverlist.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jofre.managercheck.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiverListFragment extends Fragment {


    public ReceiverListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_receiver_list, container, false);
    }

}

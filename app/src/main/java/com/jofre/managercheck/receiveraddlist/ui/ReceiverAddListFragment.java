package com.jofre.managercheck.receiveraddlist.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jofre.managercheck.ManagerCheckApp;
import com.jofre.managercheck.R;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.receiveraddlist.ReceiverAddListPresenter;
import com.jofre.managercheck.receiveraddlist.ui.alerts.ReceiverAddListImageAdapter;
import com.jofre.managercheck.receiveraddlist.ui.adapters.OnItemClickListener;
import com.jofre.managercheck.receiveraddlist.ui.adapters.ReceiverAddListAdapter;
import com.jofre.managercheck.receiveraddmain.ui.ReceiverMainActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiverAddListFragment extends Fragment implements ReceiverAddListView, OnItemClickListener {


    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.frameList)
    FrameLayout frameList;

    @Inject
    ReceiverAddListAdapter adapter;
    @Inject
    ReceiverAddListPresenter presenter;

    public ReceiverAddListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receiver_list, container, false);
        ButterKnife.bind(this, view);
        setupRecyclerView();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        presenter.onCreate();
        getChecks();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        ManagerCheckApp app = (ManagerCheckApp) getActivity().getApplication();
        app.getReceiverAddListComponent(this, this, this, getActivity()).inject(this);
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }

    @Override
    public void emptyList(String empty) {
        Snackbar.make(frameList, empty, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void errorShowList(String error) {
        Snackbar.make(frameList, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void errorDelete(String error) {
        Snackbar.make(frameList, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void successDelete(String success) {
        Snackbar.make(frameList, success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void removeCheck(Check check) {
        adapter.removeCheck(check);
    }

    @Override
    public void setChecks(List<Check> checks) {
        if (checks != null && checks.size() > 0) {
            adapter.setChecks(checks);
        }
    }
    @Override
    public void onDeleteClick(Check check) {
        presenter.removeCheck(check);
    }

    @Override
    public void onShowImageClick(ImageLoader imageLoader, Check check) {
        new ReceiverAddListImageAdapter(getContext(), imageLoader, check);
    }

    @Override
    public void onEditClick(Check check) {
        intentEditCheck(check);
    }


    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void getChecks() {
        presenter.getChecks(getActivity());
    }

    public void intentEditCheck(Check check){

        Intent intent = new Intent(getActivity(),ReceiverMainActivity.class);
        intent.putExtra("update",true);
        intent.putExtra("id",check.getId_check());
        intent.putExtra("number",check.getNumber());
        intent.putExtra("amount",check.getAmount());
        intent.putExtra("expiration",check.getExpiration());
        intent.putExtra("origin",check.getOrigin());
        intent.putExtra("photo",check.getPhoto());
        startActivity(intent);
    }
}

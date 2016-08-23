package com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui;


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
import android.widget.TextView;

import com.jofre.managercheck.ManagerCheckApp;
import com.jofre.managercheck.R;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.DeliveryOtherFragmentListPresenter;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.adapters.DeliveryOtherFragmentListAdapter;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.adapters.OnItemClickListener;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.alerts.DeliveryOtherFragmentListImageAdapter;
import com.jofre.managercheck.deliveryown.deliveryownmainactivity.Communicator;
import com.jofre.managercheck.deliveryown.deliveryownmainactivity.ui.DeliverOwnMainActivity;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DeliveryOtherFragmentListFragment extends Fragment implements DeliveryOtherFragmentListView, OnItemClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.frameList)
    FrameLayout frameList;
    @Bind(R.id.textTitleList)
    TextView textTitleList;
    @Inject
    DeliveryOtherFragmentListAdapter adapter;
    @Inject
    DeliveryOtherFragmentListPresenter presenter;

    public boolean is_action_mode = false;
    private Communicator communicator;
    private List<Check> checks_select = new ArrayList<>();
    private List<Check> checks = new ArrayList<>();
    private int counter = 0;

    public DeliveryOtherFragmentListFragment() {
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
        communicator = (Communicator) getActivity();
        getChecks();
    }

    public void deleteClick() {
        presenter.removeCheck(checks_select);
        DeliveryOtherFragmentListAdapter checkAdapter = (DeliveryOtherFragmentListAdapter) adapter;
        checkAdapter.updateAdapter(checks_select);
        communicator.clearActionMode();
    }

    private void setupRecyclerView() {
        textTitleList.setText(getString(R.string.delivery_own_list));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        ManagerCheckApp app = (ManagerCheckApp) getActivity().getApplication();
        app.getDeliveryOtherFragmentListComponent(this, this, this, getActivity()).inject(this);
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
            this.checks = checks;
            adapter.setChecks(checks);
        }
    }

    @Override
    public void onDeleteClick(List<Check> checks) {
        presenter.removeCheck(checks);
    }

    @Override
    public void onShowImageClick(ImageLoader imageLoader, Check check) {
        new DeliveryOtherFragmentListImageAdapter(getContext(), imageLoader, check);
    }

    @Override
    public void onEditClick(Check check) {
        intentEditCheck(check);
    }

    @Override
    public void onClickLinearLayout(View v, int position, boolean isSelected) {
        if (!isSelected) {
            checks_select.add(checks.get(position));
            counter = counter + 1;
            communicator.updateCounter(counter);
        } else {
            checks_select.remove(checks.get(position));
            counter = counter - 1;
            communicator.updateCounter(counter);
        }
    }

    @Override
    public void onLongClickLinearLayout(View v, int position, boolean isSelected) {
        is_action_mode = true;
        counter = 1;
        communicator.actionMode();
        communicator.updateCounter(counter);
        checks_select.add(checks.get(position));
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void getChecks() {
        presenter.getChecks();
    }

    public void intentEditCheck(Check check) {
        Intent intent = new Intent(getActivity(), DeliverOwnMainActivity.class);
        intent.putExtra("update", true);
        intent.putExtra("id", check.getId_check());
        intent.putExtra("number", check.getNumber());
        intent.putExtra("amount", check.getAmount());
        intent.putExtra("expiration", check.getExpiration());
        intent.putExtra("origin", check.getOrigin());
        intent.putExtra("photo", check.getPhoto());
        startActivity(intent);
    }

//    @Override
//    public boolean onLongClick(View v) {
//        is_action_mode = true;
//        communicator.actionMode();
//        return true;
//    }

    public void clearListSelected() {
        checks_select.clear();
        adapter.notifyDataSetChanged();
        counter = 0;
        is_action_mode = false;
    }
}

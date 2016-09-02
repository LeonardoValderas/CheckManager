package com.jofre.managercheck.deliveryother.deliveryotherfragment.ui;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jofre.managercheck.ManagerCheckApp;
import com.jofre.managercheck.R;
import com.jofre.managercheck.auxiliary.AuxiliaryGeneral;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.DeliveryOtherFragmentPresenter;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.adapters.DeliveryOtherFragmentAdapter;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.adapters.OnItemClickListener;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.alerts.DeliveryOtherFragmentImageAdapter;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.alerts.DeliveryOtherFragmentItemAdapter;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.alerts.DeliveryOtherFragmentItemBackAdapter;
import com.jofre.managercheck.deliveryother.deliveryothermainactivity.Communicator;
import com.jofre.managercheck.deliveryother.deliveryothermainactivity.ui.DeliveryOtherMainActivity;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DeliveryOtherFragment extends Fragment implements DeliveryOtherFragmentView, OnItemClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.frameList)
    FrameLayout frameList;
    @Bind(R.id.textTitleList)
    TextView textTitleList;
    @Inject
    DeliveryOtherFragmentAdapter adapter;
    @Inject
    DeliveryOtherFragmentPresenter presenter;

    private Communicator communicator;
    private List<Check> checks = new ArrayList<>();
    private DeliveryOtherFragmentItemAdapter delivery = null;
    private DeliveryOtherFragmentItemBackAdapter deliveryBack = null;
    private AuxiliaryGeneral auxiliaryGeneral = null;

    public DeliveryOtherFragment() {
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
        auxiliaryGeneral = new AuxiliaryGeneral(getActivity());
        communicator = (Communicator) getActivity();
        getChecks();
    }

    private void setupRecyclerView() {
        textTitleList.setText(getString(R.string.delivery_other));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        ManagerCheckApp app = (ManagerCheckApp) getActivity().getApplication();
        app.getDeliveryOtherFragmentComponent(this, this, this, getActivity()).inject(this);
    }

    @Override
    public void errorShowList(String error) {
        Snackbar.make(frameList, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void errorUpdate(String error) {
        Snackbar.make(frameList, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void closeDialogSuccess(String msg, List<Check> checks) {
        updateRecycler();
        delivery.alertDialog.dismiss();
        Snackbar.make(frameList, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void closeDialogError(String msg) {
        delivery.alertDialog.dismiss();
        Snackbar.make(frameList, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void dialogEmpty(String msg) {
        delivery.textEmpty.setVisibility(View.VISIBLE);
        delivery.textEmpty.setText(msg);
    }

    @Override
    public void closeDialogAccept(String msg, List<Check> checks) {

        deliveryBack.alertDialog.dismiss();
        updateRecycler();
        Snackbar.make(frameList, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void closeDialogBackError(String msg) {
        deliveryBack.alertDialog.dismiss();
        Snackbar.make(frameList, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void closeDialogCancel() {
        deliveryBack.alertDialog.dismiss();
    }

    @Override
    public void setChecks(List<Check> checks) {
        if (checks != null) {
            this.checks = checks;
            adapter.setChecks(checks);
        }
    }

    @Override
    public void onShowImageClick(ImageLoader imageLoader, Check check) {
        new DeliveryOtherFragmentImageAdapter(getContext(), imageLoader, check);
    }

    @Override
    public void onClickLinearLayout(View v, final Check check) {

        delivery = new DeliveryOtherFragmentItemAdapter(getContext(), check);
        delivery.buttonSaveDestiny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateCheck(check.getId_check(), delivery.textReceiverDestiny.getText().toString(), auxiliaryGeneral.dateNowShot(), true);
            }
        });
    }

    @Override
    public void onLongClickLinearLayout(View v, final Check check) {
        deliveryBack = new DeliveryOtherFragmentItemBackAdapter(getContext(), check);
        deliveryBack.buttonAcceptDestiny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.updateCheck(check.getId_check(), "", "", false);
            }
        });

        deliveryBack.buttonCancelDestiny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.closeDialogCancel();
            }
        });
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void getChecks() {
        presenter.getChecks();
    }

    public void updateRecycler() {
        presenter.getChecks();
        setupRecyclerView();
        communicator.refreshOtherList();
    }
}

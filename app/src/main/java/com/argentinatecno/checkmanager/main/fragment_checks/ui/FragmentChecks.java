package com.argentinatecno.checkmanager.main.fragment_checks.ui;

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

import com.argentinatecno.checkmanager.CheckManagerApp;
import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.auxiliary.AuxiliaryGeneral;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.main.activity.Communicator;
import com.argentinatecno.checkmanager.main.activity.ui.MainActivity;
import com.argentinatecno.checkmanager.main.fragment_checks.FragmentChecksPresenter;
import com.argentinatecno.checkmanager.main.fragment_checks.adapters.FragmentChecksAdapter;
import com.argentinatecno.checkmanager.main.fragment_checks.adapters.OnItemClickListener;
import com.argentinatecno.checkmanager.main.fragment_checks.dialogs.DialogClickLinear;
import com.argentinatecno.checkmanager.main.fragment_checks.dialogs.DialogClickImage;
import com.argentinatecno.checkmanager.main.fragment_checks.dialogs.DialogClickLinearBack;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentChecks extends Fragment implements FragmentChecksView, OnItemClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.frameList)
    FrameLayout frameList;

    @Inject
    FragmentChecksAdapter adapter;
    @Inject
    FragmentChecksPresenter presenter;


    private DialogClickLinear dialogClickLinear;
    private DialogClickLinearBack dialogClickLinearBack;
    public boolean is_action_mode = false;
    private Communicator communicator;
    private List<Check> checks_select = new ArrayList<>();
    private List<Check> checks = new ArrayList<>();
    private int counter = 0;
    private AuxiliaryGeneral auxiliaryGeneral;

    public FragmentChecks() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checks_list, container, false);
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
        auxiliaryGeneral = new AuxiliaryGeneral(getActivity());
        getChecks();
    }

    public void deleteClick() {
        presenter.removeCheck(checks_select);
        FragmentChecksAdapter checkAdapter = (FragmentChecksAdapter) adapter;
        checkAdapter.updateAdapter(checks_select);
        communicator.clearActionMode();
    }

    public void shareClick() {
        auxiliaryGeneral.share(checks_select, getActivity());
        communicator.clearActionMode();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        CheckManagerApp app = (CheckManagerApp) getActivity().getApplication();
        app.getFragmentChecksComponent(this, this, getActivity(), this).inject(this);
    }

    @Override
    public void emptyList(String empty) {
        showSnack(empty);
    }

    @Override
    public void errorShowList(String error) {
        showSnack(error);
    }

    @Override
    public void errorDelete(String error) {
        showSnack(error);
    }

    @Override
    public void successDelete(String success) {
        showSnack(success);
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
    public void closeDialogCancel() {
        dialogClickLinearBack.alertDialog.dismiss();
    }

    @Override
    public void closeDialogUpdateSuccess(String success) {
        getChecks();
        dialogClickLinear.alertDialog.dismiss();
        showSnack(success);
    }

    @Override
    public void closeDialogUpdateError(String error) {
        dialogClickLinear.alertDialog.dismiss();
        showSnack(error);
    }

    @Override
    public void closeDialogUpdateBackSuccess(String success) {
        getChecks();
        dialogClickLinearBack.alertDialog.dismiss();
        showSnack(success);
    }

    @Override
    public void closeDialogUpdateBackError(String error) {
        dialogClickLinearBack.alertDialog.dismiss();
        showSnack(error);
    }

    @Override
    public void onDeleteClick(List<Check> checks) {
        presenter.removeCheck(checks);
    }

    @Override
    public void onShowImageClick(ImageLoader imageLoader, Check check) {
        new DialogClickImage(getContext(), imageLoader, check);
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
    public void onClickDestinyLinearLayout(View v, final Check check, boolean isDestiny) {
        if(isDestiny) {
            dialogClickLinear = new DialogClickLinear(getContext(), check);
            dialogClickLinear.buttonSaveDestiny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.updateCheckDestiny(check.getId_check(), dialogClickLinear.textReceiverDestiny.getText().toString(), auxiliaryGeneral.dateNowShot(), true);
                }
            });
        }else{
            dialogClickLinearBack = new DialogClickLinearBack(getContext(),check);
            dialogClickLinearBack.buttonAcceptDestiny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.updateCheckDestiny(check.getId_check(), "", "", false);
                }
            });

            dialogClickLinearBack.buttonCancelDestiny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.closeDialogCancel();
                }
            });
        }
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
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("update", true);
        intent.putExtra("id", check.getId_check());
        intent.putExtra("type", check.getType());
        intent.putExtra("number", check.getNumber());
        intent.putExtra("amount", check.getAmount());
        intent.putExtra("expiration", check.getExpiration());
        intent.putExtra("photo", check.getPhoto());
        intent.putExtra("destiny", check.getDestiny());
        intent.putExtra("destiny_date", check.getDestinyDate());
        if(check.getType() == 0) //0=other //1=own
            intent.putExtra("origin", check.getOrigin());

        startActivity(intent);
    }

    public void clearListSelected() {
        checks_select.clear();
        adapter.notifyDataSetChanged();
        counter = 0;
        is_action_mode = false;
    }

    public void showSnack(String msg){
        Snackbar.make(frameList, msg, Snackbar.LENGTH_SHORT).show();
    }
}

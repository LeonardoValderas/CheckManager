package com.argentinatecno.checkmanager.main.fragment_reports.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.argentinatecno.checkmanager.CheckManagerApp;
import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.auxiliary.AuxiliaryGeneral;
import com.argentinatecno.checkmanager.entities.Maturities;
import com.argentinatecno.checkmanager.main.activity_maturities.ui.MaturitiesActivity;
import com.argentinatecno.checkmanager.main.fragment_reports.FragmentReportPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentReport extends Fragment implements FragmentReportView {


    @Bind(R.id.text_quantity_other_total)
    TextView text_quantity_other_total;
    @Bind(R.id.text_amount_total_other)
    TextView text_amount_total_other;
    @Bind(R.id.text_amount_other)
    TextView text_amount_other;
    @Bind(R.id.text_quantity_own)
    TextView text_quantity_own;
    @Bind(R.id.text_amount_total_own)
    TextView text_amount_total_own;
    @Bind(R.id.text_amount_own)
    TextView text_amount_own;
    @Bind(R.id.maturities)
    Button maturities;
    @Bind(R.id.linearConteiner)
    LinearLayout linearConteiner;


    //  private Communicator communicator;
    private AuxiliaryGeneral auxiliaryGeneral = AuxiliaryGeneral.getInstance(getActivity());
    @Inject
    FragmentReportPresenter presenter;

    public FragmentReport() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        communicator = (Communicator) getActivity();
        setupInjection();
        presenter.onCreate();
        hideKeyBoard();
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        String dateWeek = auxiliaryGeneral.getDateWeek();
        if (dateWeek != null)
            presenter.getMaturitiesWeek(dateWeek);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void hideKeyBoard() {
        View view = getActivity().getCurrentFocus();
        if(view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setupInjection() {
        CheckManagerApp app = (CheckManagerApp) getActivity().getApplication();
        app.getFragmentReportComponent(this, getActivity(), this).inject(this);
    }


    public void showSnackbar(String msg) {
        Snackbar.make(linearConteiner, msg, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.maturities)
    public void goToMaturities() {
        startActivity(new Intent(getActivity(), MaturitiesActivity.class));
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getError(String error) {
        showSnackbar(error);
    }

    @Override
    public void setMaturitiesWeek(Maturities maturities) {
        if (maturities != null) {
            text_quantity_other_total.setText(maturities.getQuantityOther());
            if (maturities.getAmountTotalOther() == null)
                text_amount_total_other.setText("$ 0");
            else
                text_amount_total_other.setText("$ " + maturities.getAmountTotalOther());
            if (maturities.getAmountOther() == null)
                text_amount_other.setText("$ 0");
            else
                text_amount_other.setText("$ " + maturities.getAmountOther());

            text_quantity_own.setText(maturities.getQuantityOwn());

            if (maturities.getAmountTotalOwn() == null)
                text_amount_total_own.setText("$ 0");
            else
                text_amount_total_own.setText(maturities.getAmountTotalOwn());
            if (maturities.getAmountOwn() == null)
                text_amount_own.setText("$ 0");
            else
                text_amount_own.setText("$ " + maturities.getAmountOwn());
        }
    }
}

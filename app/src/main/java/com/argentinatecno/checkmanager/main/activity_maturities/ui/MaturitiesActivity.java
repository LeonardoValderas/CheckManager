package com.argentinatecno.checkmanager.main.activity_maturities.ui;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.argentinatecno.checkmanager.CheckManagerApp;
import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.auxiliary.AuxiliaryGeneral;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Maturities;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.main.activity_maturities.MaturitiesPresenter;
import com.argentinatecno.checkmanager.main.activity_maturities.adapters.ActivityMaturitiesAdapter;
import com.argentinatecno.checkmanager.main.activity_maturities.adapters.OnItemClickListener;
import com.argentinatecno.checkmanager.main.fragment_checks.dialogs.DialogClickImage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MaturitiesActivity extends AppCompatActivity implements MaturitiesView, OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.counterText)
    TextView counterText;
    @Bind(R.id.daysExpirationSince)
    EditText daysExpirationSince;
    @Bind(R.id.monthsExpirationSince)
    Spinner monthsExpirationSince;
    @Bind(R.id.yearsExpirationSince)
    Spinner yearsExpirationSince;
    @Bind(R.id.linearDateSince)
    LinearLayout linearDateSince;
    @Bind(R.id.daysExpirationUntil)
    EditText daysExpirationUntil;
    @Bind(R.id.monthsExpirationUntil)
    Spinner monthsExpirationUntil;
    @Bind(R.id.yearsExpirationUntil)
    Spinner yearsExpirationUntil;
    @Bind(R.id.linearDateUntil)
    LinearLayout linearDateUntil;
    @Bind(R.id.relativeContent)
    RelativeLayout relativeContent;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;


    @Inject
    ActivityMaturitiesAdapter adapter;
    @Inject
    MaturitiesPresenter presenter;
    @Bind(R.id.text_quantity_own)
    TextView textQuantityOwn;
    @Bind(R.id.text_amount_own)
    TextView textAmountOwn;
    @Bind(R.id.text_quantity_other)
    TextView textQuantityOther;
    @Bind(R.id.text_amount_other)
    TextView textAmountOther;
    @Bind(R.id.text_quantity_total)
    TextView textQuantityTotal;
    @Bind(R.id.text_amount_total)
    TextView textAmountTotal;


    private ArrayAdapter<String> monthsAdapter;
    private ArrayAdapter<String> yearsAdapter;
    private int iMonthSince;
    private int iMonthUntil;
    private int iYearSince;
    private int iYearUntil;
    private AuxiliaryGeneral auxiliaryGeneral;
    private List<Check> checks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_maturities);
        ButterKnife.bind(this);
        setupInjection();
        setupNavigation();
        presenter.onCreate();
        auxiliaryGeneral = new AuxiliaryGeneral(MaturitiesActivity.this);
        setupRecyclerView();
        adapter.setChecks(checks);
        fillSpinnerDate();
        initEditText(daysExpirationSince, daysExpirationUntil);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(MaturitiesActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setupNavigation() {
        counterText.setText("Vencimientos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupInjection() {
        CheckManagerApp app = (CheckManagerApp) this.getApplication();
        app.getMaturitiesComponent(this, this, MaturitiesActivity.this, this).inject(this);
    }

    private void fillSpinnerDate() {
        monthsAdapter = new ArrayAdapter<String>(MaturitiesActivity.this, R.layout.simple_spinner_item, getResources().getStringArray(R.array.months));
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthsExpirationSince.setAdapter(monthsAdapter);
        monthsExpirationUntil.setAdapter(monthsAdapter);

        yearsAdapter = new ArrayAdapter<String>(MaturitiesActivity.this, R.layout.simple_spinner_item, auxiliaryGeneral.getYearsSpinner());
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearsExpirationSince.setAdapter(yearsAdapter);
        yearsExpirationSince.setSelection(1);
        yearsExpirationUntil.setAdapter(yearsAdapter);
        yearsExpirationUntil.setSelection(1);


        monthsExpirationSince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iMonthSince = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iMonthSince = 0;
            }
        });
        monthsExpirationUntil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iMonthUntil = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iMonthUntil = 0;
            }
        });

        yearsExpirationSince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iYearSince = Integer.parseInt(yearsExpirationSince.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iYearSince = Integer.parseInt(yearsExpirationSince.getSelectedItem().toString());
            }
        });
        yearsExpirationUntil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iYearUntil = Integer.parseInt(yearsExpirationUntil.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                iYearUntil = Integer.parseInt(yearsExpirationUntil.getSelectedItem().toString());
            }
        });
    }

    @Override
    public void emptyMaturities(String empty) {
        Snackbar.make(mainContent, empty, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void errorMaturities(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setMaturitiesCheck(Maturities checkMaturities) {

        textAmountOther.setText("$ " + checkMaturities.getAmountOther());
        //textAmountOtherInbag.setText("$ " + checkMaturities.getAmountOtherInBag());
        textAmountOwn.setText("$ " + checkMaturities.getAmountOwn());

        textQuantityOther.setText(checkMaturities.getQuantityOther());
       // textQuantityOtherInbag.setText(checkMaturities.getQuantityOtherInBag());
        textQuantityOwn.setText(checkMaturities.getQuantityOwn());

        textQuantityTotal.setText(checkMaturities.getQuantityTotal());
        textAmountTotal.setText("$ " + checkMaturities.getAmountTotal());

//        textNumbersOtherDelivery.setText(checkMaturities.getNumbersOtherDelivery());
//        textNumbersOtherInbag.setText(checkMaturities.getNumbersOtherInBag());
//        textNumbersOwn.setText(checkMaturities.getNumbersOwn());
    }

    @Override
    public void setMaturitiesAdapter(List<Check> checks) {
        if (checks != null && checks.size() > 0) {
            this.checks = checks;
            adapter.setChecks(checks);
        }
    }

    @OnClick(R.id.fab)
    public void getMaturitiesCheck() {
        String since = null;
        String until = null;
        if (auxiliaryGeneral.getExpitationDate(iYearSince, iMonthSince, daysExpirationSince.getText().toString())) {
            since = String.valueOf(iYearSince) + auxiliaryGeneral.validateLengthMountOrDay(String.valueOf(iMonthSince + 1)) + auxiliaryGeneral.validateLengthMountOrDay(daysExpirationSince.getText().toString());
        }
        if (auxiliaryGeneral.getExpitationDate(iYearUntil, iMonthUntil, daysExpirationUntil.getText().toString())) {
            until = String.valueOf(iYearUntil) + auxiliaryGeneral.validateLengthMountOrDay(String.valueOf(iMonthUntil + 1)) + auxiliaryGeneral.validateLengthMountOrDay(daysExpirationUntil.getText().toString());
        }
        presenter.getMaturitiesChecks(since, until);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//        if (id == R.id.item_delete) {
//            getFragmentList().deleteClick();
//            return true;
//        }
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return true;
    }

    public void initEditText(View daysExpirationSince, View daysExpirationUntil) {
        auxiliaryGeneral.initOnClickEditText(daysExpirationSince);
        auxiliaryGeneral.initOnClickEditText(daysExpirationUntil);
    }

    @Override
    public void onShowImageClick(ImageLoader imageLoader, Check check) {
        new DialogClickImage(MaturitiesActivity.this, imageLoader, check);
    }
}

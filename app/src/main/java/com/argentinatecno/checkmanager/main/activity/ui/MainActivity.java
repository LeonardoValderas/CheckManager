package com.argentinatecno.checkmanager.main.activity.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.main.activity.Communicator;
import com.argentinatecno.checkmanager.main.activity.ui.adapters.SectionsPagerAdapter;
import com.argentinatecno.checkmanager.main.fragment_add.ui.FragmentAdd;
import com.argentinatecno.checkmanager.main.fragment_checks.ui.FragmentChecks;
import com.argentinatecno.checkmanager.main.fragment_reports.ui.FragmentReport;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Communicator {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.counterText)
    TextView counterText;
    @Inject
    SectionsPagerAdapter adapter;
    int counter = 0;
    boolean is_action_mode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupInjection();
        setupNavigation();
    }

    private void setupNavigation() {
        counterText.setText(getString(R.string.string_item_nav));
        setSupportActionBar(toolbar);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
    }

    private void setupInjection() {
        String[] titles = new String[]{getString(R.string.string_tab_cargar),
                getString(R.string.string_tab_cheques), getString(R.string.string_tab_reportes)};
        Fragment[] fragments = new Fragment[]{new FragmentAdd(),
                new FragmentChecks(), new FragmentReport()};
        adapter = new SectionsPagerAdapter(getSupportFragmentManager(), titles, fragments);
    }

    @Override
    public void refresh() {
        getFragmentChecks().getChecks();
    }

    public FragmentChecks getFragmentChecks() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentChecks fragment = (FragmentChecks) manager
                .findFragmentByTag("android:switcher:" + viewPager.getId()
                        + ":" + 1);
        return fragment;
    }

    @Override
    public void actionMode() {
        toolbar.getMenu().clear();
        updateCounter(1);
        is_action_mode = true;
        toolbar.inflateMenu(R.menu.menu_action_mode);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void updateCounter(int counter) {
        if (counter == 0) {
            clearActionMode();
        } else if (counter == 1) {
            counterText.setText("1 cheque selecccionado");
        } else {
            counterText.setText(counter + " cheques selecccionados");
        }
    }

    @Override
    public void clearActionMode() {
        is_action_mode = false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_check);
        counterText.setText(getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getFragmentChecks().clearListSelected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_delete) {
            getFragmentChecks().deleteClick();
            return true;
        }
        if (id == R.id.action_share) {
            getFragmentChecks().shareClick();
            return true;
        }
        if (id == android.R.id.home) {
            if (is_action_mode) {
                clearActionMode();
                //   is_action_mode = false;

            }

//            else {
//                NavUtils.navigateUpFromSameTask(this);
//            }
            return true;
        }
        return true;
    }
}

package com.jofre.managercheck.deliveryother.deliveryothermainactivity.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jofre.managercheck.R;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.DeliveryOtherFragment;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.DeliveryOtherFragmentListFragment;
import com.jofre.managercheck.deliveryother.deliveryothermainactivity.Communicator;
import com.jofre.managercheck.deliveryother.deliveryothermainactivity.ui.adapters.DeliveryOtherSectionsPagerAdapter;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DeliveryOtherMainActivity extends AppCompatActivity implements Communicator {

    @Bind(R.id.counterText)
    TextView counterText;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    DeliveryOtherSectionsPagerAdapter adapter;
    int counter = 0;
    boolean is_action_mode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_own_main);
        ButterKnife.bind(this);
        setupInjection();
        setupNavigation();
    }

    private void setupNavigation() {
        counterText.setText(getString(R.string.delivery_other_nav));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    private void setupInjection() {
        String[] titles = new String[]{getString(R.string.delivery_title),
                getString(R.string.receiver_title_list)};
        Fragment[] fragments = new Fragment[]{new DeliveryOtherFragment(),
                new DeliveryOtherFragmentListFragment()};
        adapter = new DeliveryOtherSectionsPagerAdapter(getSupportFragmentManager(), titles, fragments);
    }

    public DeliveryOtherFragmentListFragment getFragmentList() {
        FragmentManager manager = getSupportFragmentManager();
        DeliveryOtherFragmentListFragment fragment = (DeliveryOtherFragmentListFragment) manager
                .findFragmentByTag("android:switcher:" + viewPager.getId()
                        + ":" + 1);
        return fragment;
    }


//    @Override
//    public void actionMode() {
//        toolbar.getMenu().clear();
//        updateCounter(1);
//        is_action_mode = true;
//        toolbar.inflateMenu(R.menu.menu_action_mode);
//    }

//    @Override
//    public void updateCounter(int counter) {
//        if (counter == 0) {
//            clearActionMode();
//        } else if (counter == 1) {
//            counterText.setText("1 cheque selecccionado");
//        } else {
//            counterText.setText(counter + " cheques selecccionados");
//        }
//    }

//    @Override
//    public void clearActionMode() {
//        is_action_mode = false;
//        toolbar.getMenu().clear();
//        toolbar.inflateMenu(R.menu.menu_check);
//        counterText.setText(getString(R.string.receiver_item_nav));
//        getFragmentList().clearListSelected();
//    }

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
            if (is_action_mode) {
          //      clearActionMode();
                is_action_mode = false;
            } else {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return true;
    }

    @Override
    public void refreshOther() {

    }

    @Override
    public void refreshOtherList() {
        getFragmentList().updateRecycler();
    }
}

package com.jofre.managercheck.navigationmain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.jofre.managercheck.ManagerCheckApp;
import com.jofre.managercheck.R;
import com.jofre.managercheck.deliveryother.deliveryothermainactivity.ui.DeliverOtherMainActivity;
import com.jofre.managercheck.deliveryown.deliveryownmainactivity.ui.DeliverOwnMainActivity;
import com.jofre.managercheck.entities.CheckInformationAdd;
import com.jofre.managercheck.navigationmain.NavigationMainPresenter;
import com.jofre.managercheck.receiveradd.receiveraddmain.ui.ReceiverMainActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NavigationMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationMainActivityView {

    @Bind(R.id.counterText)
    TextView counterText;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.buttonReceiverCheck)
    Button buttonReceiverCheck;
    @Bind(R.id.buttonDeliveryOwnCheck)
    Button buttonDeliveryOwnCheck;
    @Bind(R.id.buttonDeliveryOtherCheck)
    Button buttonDeliveryOtherCheck;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.quantity_addCheck_text_)
    TextView quantityAddCheckText;
    @Bind(R.id.total_addCheck_text_)
    TextView totalAddCheckText;
    @Bind(R.id.quantity_addCheck_expiration_text)
    TextView quantityAddCheckExpirationText;
    @Bind(R.id.total_addCheck_expiration_text)
    TextView totalAddCheckExpirationText;
    @Bind(R.id.quantity_ownCheck_text)
    TextView quantityOwnCheckText;
    @Bind(R.id.total_ownCheck_text)
    TextView totalOwnCheckText;
    @Bind(R.id.quantity_ownCheck_expiration_text)
    TextView quantityOwnCheckExpirationText;
    @Bind(R.id.total_ownCheck_expiration_text)
    TextView totalOwnCheckExpirationText;
    @Inject
    NavigationMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setupInjection();
        presenter.onCreate();
        counterText.setText(R.string.title_main);
        setSupportActionBar(toolbar);
        initDrawer();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        ManagerCheckApp app = (ManagerCheckApp) this.getApplication();
        app.getNavigationMainComponent(this, this, this).inject(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        presenter.getInformation();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_check, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_receiver) {
            goToReceiverMain();
        } else if (id == R.id.nav_sender_own) {
            goToDeliveryMain();
        } else if (id == R.id.nav_sender_other) {
            goToDeliveryMainOther();
        } else if (id == R.id.nav_maturities) {
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.buttonReceiverCheck)
    public void onClickButtonReceiver() {
        goToReceiverMain();
    }

    public void goToReceiverMain() {
        startActivity(new Intent(this, ReceiverMainActivity.class));
    }

    @OnClick(R.id.buttonDeliveryOwnCheck)
    public void onClickButtonDeliveryOwn() {
        goToDeliveryMain();
    }

    public void goToDeliveryMain() {
        startActivity(new Intent(this, DeliverOwnMainActivity.class));
    }

    @OnClick(R.id.buttonDeliveryOtherCheck)
    public void onClickButtonDeliveryOther() {
        goToDeliveryMainOther();
    }

    public void goToDeliveryMainOther() {
        startActivity(new Intent(this, DeliverOtherMainActivity.class));
    }

    @Override
    public void getError(String error) {
        totalAddCheckText.setText(error);
        quantityAddCheckText.setText(error);
    }

    @Override
    public void setAddCheck(CheckInformationAdd checkInformationAdd) {
        quantityAddCheckText.setText(checkInformationAdd.getAmountTotal());
        totalAddCheckText.setText(checkInformationAdd.getAmountQuantityTotal());
    }

//    @Override
//    public void getInformation() {
//
//    }

}

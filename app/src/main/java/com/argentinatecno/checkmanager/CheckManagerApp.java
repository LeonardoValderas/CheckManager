package com.argentinatecno.checkmanager;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.argentinatecno.checkmanager.lib.di.LibsModule;
import com.argentinatecno.checkmanager.main.activity_maturities.di.DaggerMaturitiesComponent;
import com.argentinatecno.checkmanager.main.activity_maturities.di.MaturitiesComponent;
import com.argentinatecno.checkmanager.main.activity_maturities.di.MaturitiesModule;
import com.argentinatecno.checkmanager.main.activity_maturities.ui.MaturitiesView;
import com.argentinatecno.checkmanager.main.fragment_add.di.DaggerFragmentAddComponent;
import com.argentinatecno.checkmanager.main.fragment_add.di.FragmentAddComponent;
import com.argentinatecno.checkmanager.main.fragment_add.di.FragmentAddModule;
import com.argentinatecno.checkmanager.main.fragment_add.ui.FragmentAddView;
import com.argentinatecno.checkmanager.main.fragment_checks.adapters.OnItemClickListener;
import com.argentinatecno.checkmanager.main.fragment_checks.di.DaggerFragmentChecksComponent;
import com.argentinatecno.checkmanager.main.fragment_checks.di.FragmentChecksComponent;
import com.argentinatecno.checkmanager.main.fragment_checks.di.FragmentChecksModule;
import com.argentinatecno.checkmanager.main.fragment_checks.ui.FragmentChecksView;
import com.argentinatecno.checkmanager.main.fragment_reports.di.DaggerFragmentReportComponent;
import com.argentinatecno.checkmanager.main.fragment_reports.di.FragmentReportComponent;
import com.argentinatecno.checkmanager.main.fragment_reports.di.FragmentReportModule;
import com.argentinatecno.checkmanager.main.fragment_reports.ui.FragmentReportView;


public class CheckManagerApp extends Application {
    private LibsModule libsModule;
    private CheckManagerAppModule checkManagerAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
//        initDB();
        initModules();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //      DBTearDown();
    }

    private void initModules() {
        libsModule = new LibsModule();
        checkManagerAppModule = new CheckManagerAppModule(this);
    }

    public FragmentAddComponent getFragmentAddComponent(FragmentAddView view, Fragment fragment) {
        return DaggerFragmentAddComponent
                .builder()
                .checkManagerAppModule(checkManagerAppModule)
                .libsModule(new LibsModule(fragment))
                .fragmentAddModule(new FragmentAddModule(view, fragment))
                .build();
    }

    public FragmentChecksComponent getFragmentChecksComponent(FragmentChecksView view, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        return DaggerFragmentChecksComponent
                .builder()
                .checkManagerAppModule(checkManagerAppModule)
                .libsModule(new LibsModule(fragment))
                .fragmentChecksModule(new FragmentChecksModule(view, onItemClickListener, context, fragment))
                .build();
    }

    public FragmentReportComponent getFragmentReportComponent(FragmentReportView view, Context context, Fragment fragment) {
        return DaggerFragmentReportComponent
                .builder()
                .checkManagerAppModule(checkManagerAppModule)
                .libsModule(new LibsModule(fragment))
                .fragmentReportModule(new FragmentReportModule(view, context, fragment))
                .build();
    }

    public MaturitiesComponent getMaturitiesComponent(MaturitiesView view, com.argentinatecno.checkmanager.main.activity_maturities.adapters.OnItemClickListener onItemClickListener, Context context, Activity activity) {
        return DaggerMaturitiesComponent
                .builder()
                .checkManagerAppModule(checkManagerAppModule)
                .libsModule(new LibsModule(activity))
                .maturitiesModule(new MaturitiesModule(view, onItemClickListener, context, activity))
                .build();
    }
}

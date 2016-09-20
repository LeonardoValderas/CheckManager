package com.argentinatecno.checkmanager.main.fragment_reports.di;

import com.argentinatecno.checkmanager.CheckManagerAppModule;
import com.argentinatecno.checkmanager.lib.di.LibsModule;
import com.argentinatecno.checkmanager.main.fragment_reports.ui.FragmentReport;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {FragmentReportModule.class, LibsModule.class, CheckManagerAppModule.class})
public interface FragmentReportComponent {
    void inject(FragmentReport fragment);
}

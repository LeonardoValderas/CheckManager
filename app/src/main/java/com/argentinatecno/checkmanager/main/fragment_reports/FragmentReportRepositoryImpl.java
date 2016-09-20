package com.argentinatecno.checkmanager.main.fragment_reports;

import android.content.Context;

import com.argentinatecno.checkmanager.db.DataBaseController;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Maturities;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.main.fragment_reports.events.FragmentReportEvent;

import java.util.ArrayList;
import java.util.List;

public class FragmentReportRepositoryImpl implements FragmentReportRepository {
    EventBus eventBus;
    DataBaseController dataBaseController;
    Maturities maturities = new Maturities();
    List<Check> checkArrayList = new ArrayList<>();
    Context context;

    public FragmentReportRepositoryImpl(Context context, EventBus eventBus) {
        this.context = context;
        this.eventBus = eventBus;
    }


    @Override
    public void getMaturitiesWeek(String date) {
        if (date != null) {
            String since = date.substring(0, 8);
            String until = date.substring(8, 16);
            instanceController(context);
            maturities = dataBaseController.selectMaturitiesWeek(since, until);
            if (maturities != null)
                post(maturities, null);
            else
                post(FragmentReportEvent.ERROR);
        }
    }

    private void post(Maturities maturities, String error) {
        FragmentReportEvent event = new FragmentReportEvent();
        event.setError(error);
        event.setMaturities(maturities);
        eventBus.post(event);
    }

    private void post(String error) {
        post(null, error);
    }

    public void instanceController(Context context) {
        dataBaseController = new DataBaseController(context);
    }
}

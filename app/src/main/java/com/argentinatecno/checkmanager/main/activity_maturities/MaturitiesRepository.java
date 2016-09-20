package com.argentinatecno.checkmanager.main.activity_maturities;

import java.util.List;


public interface MaturitiesRepository {
    void selectMaturities(String since, String until);
}

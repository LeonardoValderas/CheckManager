package com.jofre.managercheck.maturities;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface MaturitiesRepository {
    void selectMaturities(String since, String until);
}

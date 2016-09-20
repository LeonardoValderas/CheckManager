package com.argentinatecno.checkmanager.main.fragment_checks;


import com.argentinatecno.checkmanager.entities.Check;

import java.util.List;

public interface FragmentChecksInteractor {
    void removeCheck(List<Check> checks);
    void updateCheckDestiny(int id, String destiny, String destinyDate, boolean isUpdate);
    void getChecks();
}

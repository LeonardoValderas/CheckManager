package com.argentinatecno.checkmanager.main.fragment_checks.ui;

import com.argentinatecno.checkmanager.entities.Check;

import java.util.List;

public interface FragmentChecksView {
    void emptyList(String empty);

    void errorShowList(String error);

    void errorDelete(String error);

    void successDelete(String success);

    void removeCheck(Check check);

    void setChecks(List<Check> checks);

    void closeDialogCancel();

    void closeDialogUpdateSuccess(String success);

    void closeDialogUpdateError(String error);

    void closeDialogUpdateBackSuccess(String error);

    void closeDialogUpdateBackError(String error);

    void setChecksSearch(List<Check> checks);

}

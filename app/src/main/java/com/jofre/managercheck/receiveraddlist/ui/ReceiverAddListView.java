package com.jofre.managercheck.receiveraddlist.ui;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.util.List;

/**
 * Created by LEO on 3/7/2016.
 */
public interface ReceiverAddListView {
    void emptyList(String empty);
    void errorShowList(String error);
    void errorDelete(String error);
    void successDelete(String success);
    void removeCheck (Check check);
    void setChecks(List<Check> checks);

}

package com.argentinatecno.checkmanager.main.fragment_checks.adapters;

import android.view.View;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;

import java.util.List;

public interface OnItemClickListener {
    void onDeleteClick(List<Check> checks);
    void onShowImageClick(ImageLoader imageLoader, Check check);
    void onEditClick(Check check);
    void onClickLinearLayout(View v, int position, boolean isSelected);
    void onLongClickLinearLayout(View v, int position, boolean isSelected);
    void onClickDestinyLinearLayout(View v, Check check, boolean isDestiny);
}

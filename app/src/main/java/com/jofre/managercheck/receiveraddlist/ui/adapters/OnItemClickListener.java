package com.jofre.managercheck.receiveraddlist.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface OnItemClickListener {
    void onDeleteClick(List<Check> checks);
    void onShowImageClick(ImageLoader imageLoader,Check check);
    void onEditClick(Check check);
    void onClickLinearLayout(View v , int position, boolean isSelected);
}

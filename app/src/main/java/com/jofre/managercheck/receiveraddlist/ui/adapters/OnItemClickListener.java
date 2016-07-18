package com.jofre.managercheck.receiveraddlist.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

/**
 * Created by LEO on 8/7/2016.
 */
public interface OnItemClickListener {
    void onDeleteClick(Check check);
    void onShowImageClick(ImageLoader imageLoader,Check check);
    void onEditClick(Check check);
}

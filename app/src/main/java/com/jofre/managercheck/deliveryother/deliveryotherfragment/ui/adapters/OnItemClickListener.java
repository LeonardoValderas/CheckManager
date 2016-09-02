package com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.adapters;

import android.view.View;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public interface OnItemClickListener {
    void onShowImageClick(ImageLoader imageLoader, Check check);
    void onClickLinearLayout(View v, Check check);
    void onLongClickLinearLayout(View v, Check check);
}

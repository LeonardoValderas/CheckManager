package com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jofre.managercheck.R;
import com.jofre.managercheck.auxiliary.AuxiliaryGeneral;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.DeliveryOtherFragment;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LEO on 8/7/2016.
 */
public class DeliveryOtherFragmentAdapter extends RecyclerView.Adapter<DeliveryOtherFragmentAdapter.ViewHolder> {

    private List<Check> checkList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private DeliveryOtherFragment fragment;
    private AuxiliaryGeneral auxiliaryGeneral;

    public DeliveryOtherFragmentAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        this.checkList = checkList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.fragment = (DeliveryOtherFragment) fragment;
        auxiliaryGeneral = new AuxiliaryGeneral(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_receiveradd_list, parent, false);
        return new ViewHolder(view, fragment);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Check currentCheck = checkList.get(position);

        holder.setOnItemClickListener(currentCheck, onItemClickListener, context, imageLoader);
        holder.textReceiverNumber.setText(currentCheck.getNumber());
        holder.textReceiverAmount.setText("$ " + currentCheck.getAmount());
        holder.linearDataReceiver.setTag(0);
        holder.textReceiverExpirate.setText(currentCheck.getExpiration());
        holder.textTitleOrigin.setText(context.getText(R.string.delivery_other_add));
        holder.textReceiverOrigin.setText(currentCheck.getOrigin());
        if (currentCheck.getDestiny() != null) {
            if (!currentCheck.getDestiny().isEmpty()) {

                holder.linearDestiny.setVisibility(View.VISIBLE);
                holder.textReceiverDestiny.setText(currentCheck.getDestiny());
                holder.linearDataReceiver.setTag(1);
                holder.linearDataReceiver.setBackgroundResource(R.drawable.border_rect_linear_delivery_solid);
                holder.textReceiverDestinyDate.setText(currentCheck.getDestinyDate());
            }
        }
        holder.imgEdit.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return checkList.size();
    }

    public void removeCheck(Check check) {
        checkList.remove(check);
        notifyDataSetChanged();
    }

    public void setChecks(List<Check> checks) {
        this.checkList = checks;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgReceiver)
        ImageView imgReceiver;
        @Bind(R.id.textReceiverNumber)
        TextView textReceiverNumber;
        @Bind(R.id.textReceiverAmount)
        TextView textReceiverAmount;
        @Bind(R.id.textReceiverExpirate)
        TextView textReceiverExpirate;
        @Bind(R.id.textReceiverOrigin)
        TextView textReceiverOrigin;
        @Bind(R.id.imgEdit)
        ImageButton imgEdit;
        @Bind(R.id.linearDataReceiver)
        LinearLayout linearDataReceiver;
        @Bind(R.id.textTitleOrigin)
        TextView textTitleOrigin;
        @Bind(R.id.linearDestiny)
        LinearLayout linearDestiny;
        @Bind(R.id.textReceiverDestiny)
        TextView textReceiverDestiny;
        @Bind(R.id.textReceiverDestinyDate)
        TextView textReceiverDestinyDate;
        DeliveryOtherFragment fragment;

        public ViewHolder(View view, DeliveryOtherFragment fragment) {
            super(view);
            this.fragment = fragment;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final Check check,
                                           final OnItemClickListener listener, final Context context, final ImageLoader imageLoader) {

            imgReceiver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShowImageClick(imageLoader, check);
                }
            });
            linearDataReceiver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getTag() == 0) {
                        listener.onClickLinearLayout(v, check);
                    }
                }
            });
            linearDataReceiver.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (v.getTag() == 1) {
                        listener.onLongClickLinearLayout(v, check);
                    }
                    return true;
                }
            });
        }
    }
}

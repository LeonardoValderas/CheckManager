package com.jofre.managercheck.receiveraddlist.ui.adapters;

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
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.receiveraddlist.ui.ReceiverAddListFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LEO on 8/7/2016.
 */
public class ReceiverAddListAdapter extends RecyclerView.Adapter<ReceiverAddListAdapter.ViewHolder> {


    private List<Check> checkList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;
    private SimpleDateFormat formate = new SimpleDateFormat(
            "dd-MM-yyyy");
    private Context context;
    private ReceiverAddListFragment fragment;

    public ReceiverAddListAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        this.checkList = checkList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.fragment = (ReceiverAddListFragment) fragment;
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
        holder.linearDataReceiver.setBackgroundResource(R.drawable.border_rect_linear);
        holder.textReceiverExpirate.setText(currentCheck.getExpiration());
        holder.textReceiverOrigin.setText(currentCheck.getOrigin());
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
        ReceiverAddListFragment fragment;
//        @Bind(R.id.imgDelete)
//        ImageButton imgDelete;

        public ViewHolder(View view, ReceiverAddListFragment fragment) {
            super(view);
            this.fragment = fragment;
            ButterKnife.bind(this, view);
            linearDataReceiver.setOnLongClickListener(fragment);
        }

        public void setOnItemClickListener(final Check check,
                                           final OnItemClickListener listener, final Context context, final ImageLoader imageLoader) {

            imgReceiver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShowImageClick(imageLoader, check);
                }
            });
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEditClick(check);
                }
            });
            linearDataReceiver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getTag() == 0) {
                        listener.onClickLinearLayout(v, getAdapterPosition(), false);
                        v.setBackgroundResource(R.drawable.border_rect_linear_solid);
                        v.setTag(1);
                    } else {
                        listener.onClickLinearLayout(v, getAdapterPosition(), true);
                        v.setBackgroundResource(R.drawable.border_rect_linear);
                        v.setTag(0);
                    }
                }
            });
        }
    }
    public void updateAdapter (List<Check> list){
        for(Check check: list){
            checkList.remove(check);
        }
        notifyDataSetChanged();
    }
}

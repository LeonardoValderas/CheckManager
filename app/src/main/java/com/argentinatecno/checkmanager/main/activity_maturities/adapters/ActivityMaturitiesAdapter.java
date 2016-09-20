package com.argentinatecno.checkmanager.main.activity_maturities.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.argentinatecno.checkmanager.R;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.main.activity_maturities.ui.MaturitiesActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityMaturitiesAdapter extends RecyclerView.Adapter<ActivityMaturitiesAdapter.ViewHolder> {


    private List<Check> checkList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private MaturitiesActivity activity;

    public ActivityMaturitiesAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Activity activity) {
        this.checkList = checkList;
        this.imageLoader = imageLoader;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.activity = (MaturitiesActivity) activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cargar_list, parent, false);
        return new ViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Check currentCheck = checkList.get(position);

        holder.setOnItemClickListener(currentCheck, onItemClickListener, context, imageLoader);

        holder.linearConteinerData.setTag(0);
        holder.linearConteinerData.setBackgroundResource(android.R.color.transparent);
        holder.imgButtonEdit.setVisibility(View.INVISIBLE);
        //o=other 1 = own
        if (currentCheck.getType() == 0) {
            holder.linearDestiny.setVisibility(View.VISIBLE);
            holder.textTypeCheck.setText("Cheque Tercero");
            holder.textTypeCheck.setPaintFlags(holder.textTypeCheck.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        } else {
            holder.linearDestiny.setVisibility(View.GONE);
            holder.textTypeCheck.setText("Cheque Propio");
            holder.textTypeCheck.setPaintFlags(holder.textTypeCheck.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        if (currentCheck.getDestiny() != null) {
            if (!currentCheck.getDestiny().isEmpty()) {

                holder.textDestiny.setText(currentCheck.getDestiny());
                holder.textDestinyDate.setText(currentCheck.getDestinyDate());
            } else {
                holder.textDestiny.setText("--");
                holder.textDestinyDate.setText("--");
            }
        }

        if (currentCheck.getPhoto() != null) {
            byte[] bytePhoto = currentCheck.getPhoto();
            imageLoader.load(holder.imageCheckPhoto, bytePhoto);

        }
        holder.textNumber.setText(currentCheck.getNumber());
        holder.textAmount.setText("$ " + currentCheck.getAmount());
        holder.textExpirate.setText(currentCheck.getExpiration());
        holder.textOrigin.setText(currentCheck.getOrigin());
    }

    @Override
    public int getItemCount() {
        return checkList.size();
    }

//    public void removeCheck(Check check) {
//        checkList.remove(check);
//        notifyDataSetChanged();
//    }

    public void setChecks(List<Check> checks) {
        this.checkList = checks;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imageCheckPhoto)
        CircleImageView imageCheckPhoto;
        @Bind(R.id.textTypeCheck)
        TextView textTypeCheck;
        @Bind(R.id.textNumber)
        TextView textNumber;
        @Bind(R.id.textAmount)
        TextView textAmount;
        @Bind(R.id.textExpirate)
        TextView textExpirate;
        @Bind(R.id.textOrigin)
        TextView textOrigin;
        @Bind(R.id.imgButtonEdit)
        ImageButton imgButtonEdit;
        @Bind(R.id.textDestiny)
        TextView textDestiny;
        @Bind(R.id.textDestinyDate)
        TextView textDestinyDate;
        @Bind(R.id.linearConteinerData)
        LinearLayout linearConteinerData;
        @Bind(R.id.linearDestiny)
        LinearLayout linearDestiny;

        MaturitiesActivity activity;

        public ViewHolder(View view, MaturitiesActivity activity) {
            super(view);
            this.activity = activity;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final Check check,
                                           final OnItemClickListener listener, final Context context, final ImageLoader imageLoader) {

            imageCheckPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onShowImageClick(imageLoader, check);
                }
            });
        }
    }

    public void updateAdapter(List<Check> list) {
        for (Check check : list) {
            checkList.remove(check);
        }
        notifyDataSetChanged();
    }
}

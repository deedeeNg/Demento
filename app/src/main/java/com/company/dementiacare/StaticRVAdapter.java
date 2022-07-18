package com.company.dementiacare;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StaticRVAdapter extends RecyclerView.Adapter<StaticRVAdapter.StaticRVViewHolder> {

    private ArrayList<StaticRVModel> items;
    int row_index = -1;  //Check the item selected or not

    public StaticRVAdapter(ArrayList<StaticRVModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item_layout,parent,false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        return staticRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRVViewHolder holder,final int position) {
        StaticRVModel currentItem = items.get(getItemViewType(position));
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = getItemViewType(position);
                notifyDataSetChanged();
            }
        });
        if (row_index == getItemViewType(position)){
            currentItem.setVisibility(!currentItem.visibility);
            holder.expandedLayout.setVisibility(currentItem.isVisible() ?  View.VISIBLE : View.GONE);
            if (currentItem.isVisible()) {
                holder.finishedBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentItem.setVisibility(!currentItem.visibility);
                        holder.expandedLayout.setVisibility(currentItem.isVisible() ? View.VISIBLE : View.GONE);
                        holder.constraintLayout.setBackgroundResource(R.drawable.dynamic_rv_selected_bg);
                        holder.checkImage.setVisibility(View.VISIBLE);
                    }
                });
                holder.unfinishedBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentItem.setVisibility(!currentItem.visibility);
                        holder.expandedLayout.setVisibility(currentItem.isVisible() ? View.VISIBLE : View.GONE);
                        holder.constraintLayout.setBackgroundResource(R.drawable.dynamic_rv_bg);
                        holder.checkImage.setVisibility(View.GONE);
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView, checkImage, finishedBtn, unfinishedBtn;
        ConstraintLayout constraintLayout, expandedLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);
            checkImage = itemView.findViewById(R.id.checkImage);
            constraintLayout = itemView.findViewById(R.id.constraintLayout2);
            expandedLayout = itemView.findViewById(R.id.expandedLayout);
            finishedBtn = itemView.findViewById(R.id.finishedBtn);
            unfinishedBtn = itemView.findViewById(R.id.unfinishedBtn);
        }
    }

}

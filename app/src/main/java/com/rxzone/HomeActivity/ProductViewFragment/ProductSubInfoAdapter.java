package com.rxzone.HomeActivity.ProductViewFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rxzone.HomeActivity.AllPostFragment.AllPostData;
import com.rxzone.Util.DateUtil;
import com.rxzone.rxzone.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.Adapter;
import static android.support.v7.widget.RecyclerView.OnClickListener;

/**
 * Created by PROXIM on 2/6/2018.
 */

public class ProductSubInfoAdapter extends Adapter<ProductSubInfoAdapter.ViewHolder> {
    Context mContext;
    Context context;
    FragmentManager fragmentManager;
    String[] allPostData;
    ProductViewFragment productViewFragment;
    Bundle bundle;

    private String[] subInfoTitles;

    int[] producuSubInfoimages = new int[] {
            R.drawable.barcode,
            R.drawable.cardboardbox,
            R.drawable.pills,
            R.drawable.dateto,
            R.drawable.supplier,
            R.drawable.bedsize,
            R.drawable.raingaugefilled

    };

    @Override
    public ProductSubInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_sub_info, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    public ProductSubInfoAdapter(Context context,
                                 FragmentManager fragmentManager,
                                 String[] allPostData) {
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.allPostData = allPostData;
        subInfoTitles = context.getResources().getStringArray(R.array.productsubinfoarray);
    }

    @Override
    public void onBindViewHolder(ProductSubInfoAdapter.ViewHolder holder, int position) {
        holder.postTitle.setText(subInfoTitles[position]);
        if (position == 4) {
            holder.subinfocontent.setText(DateUtil.serverSentDateChange(allPostData[position]));
        } else {
            holder.subinfocontent.setText(allPostData[position]);
        }

        holder.postimg.setImageResource(producuSubInfoimages[position]);

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        ImageView postimg, likeIv, decrementIv, addIv;
        TextView postTitle, subinfocontent, postdate, counttxt;
        Button addbtn;

        public ViewHolder(View itemView) {
            super(itemView);
            postimg = (ImageView) itemView.findViewById(R.id.subinfoImg);
            postTitle = (TextView) itemView.findViewById(R.id.subinfoTitle);
            subinfocontent = (TextView) itemView.findViewById(R.id.subinfocontent);
        }

        @Override
        public void onClick(View v) {
        }
    }
}

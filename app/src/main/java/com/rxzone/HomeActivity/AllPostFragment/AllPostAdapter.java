package com.rxzone.HomeActivity.AllPostFragment;

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

import com.rxzone.HomeActivity.ProductViewFragment.ProductViewFragment;
import com.rxzone.Util.DateUtil;
import com.rxzone.rxzone.R;

import java.io.Serializable;
import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by PROXIM on 2/6/2018.
 */

public class AllPostAdapter extends RecyclerView.Adapter<AllPostAdapter.ViewHolder> {
    Context mContext;
    Context context;
    FragmentManager fragmentManager;
    List<AllPostData> allPostData;
    ProductViewFragment productViewFragment;
    Bundle bundle;

    @Override
    public AllPostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postorsaleitem, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(view);
    }

    public AllPostAdapter(Context context,
                          FragmentManager fragmentManager,
                          List<AllPostData> allPostData) {
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.allPostData = allPostData;
    }

    @Override
    public void onBindViewHolder(AllPostAdapter.ViewHolder holder, int position) {
//            holder.notification_item_img.setImageResource(R.drawable.shades);
//         Glide.with(context).load(allPostData.get(position).getPackageName()).into(holder.postimg);
        if (allPostData.get(position).getPackageName() != null && !allPostData.get(position).getPackageName().isEmpty()) {
            holder.postTitle.setText(Html.fromHtml(allPostData.get(position).getPackageName() + ""));
        }

        if (allPostData.get(position).getPackagee() != null && !allPostData.get(position).getPackagee().isEmpty()) {
            holder.postinfo.setText("Dosage : " + Html.fromHtml(allPostData.get(position).getPackagee() + ""));
        }
        if (allPostData.get(position).getExpDate() != null && !allPostData.get(position).getExpDate().isEmpty()) {
            holder.postdate.setText("Exp Date(Days) : " + DateUtil.serverSentDateChange(Html.fromHtml(allPostData.get(position).getExpDate()) + ""));
        }

    }

    @Override
    public int getItemCount() {
        return allPostData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        ImageView postimg, likeIv, decrementIv, addIv;
        TextView postTitle, postinfo, postdate, counttxt;
        Button addbtn;

        public ViewHolder(View itemView) {
            super(itemView);
            postimg = (ImageView) itemView.findViewById(R.id.postimg);
            postTitle = (TextView) itemView.findViewById(R.id.postTitle);
            postinfo = (TextView) itemView.findViewById(R.id.postinfo);
            postdate = (TextView) itemView.findViewById(R.id.postdate);
            likeIv = (ImageView) itemView.findViewById(R.id.likeIv);
            decrementIv = (ImageView) itemView.findViewById(R.id.decrementIv);
            likeIv = (ImageView) itemView.findViewById(R.id.addIv);
            counttxt = (TextView) itemView.findViewById(R.id.counttxt);
            addbtn = (Button) itemView.findViewById(R.id.addbtn);
            addbtn.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.addbtn) {
                addbtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                addbtn.setText("Delete");
                Toast.makeText(mContext, "Added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Product Complete Details", Toast.LENGTH_LONG).show();
                productViewFragment = new ProductViewFragment();
                bundle = new Bundle();
                bundle.putInt("POSITION_VALUE", getAdapterPosition());
                bundle.putSerializable("PRODUCT_DETAILS", (Serializable) allPostData);
                productViewFragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.frame, productViewFragment)
                        .addToBackStack("")
                        .commit();
            }

        }
    }
}

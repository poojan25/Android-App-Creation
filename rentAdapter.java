package com.example.poojanaik.buildtech;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */

public class rentAdapter extends RecyclerView.Adapter<rentAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<rentprop> productList;

    public rentAdapter(Context mCtx, List<rentprop> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.rentlist, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        rentprop product = productList.get(position);

        //loading the image
       /* Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);*/

        holder.textViewTitle.setText(product.getname());
        holder.textViewShortDesc.setText(product.getloc());
        holder.textViewShort.setText(product.getbhk());
        holder.textViewPrice.setText(product.price());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice,textViewShort;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewShort = itemView.findViewById(R.id.textViewShort);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

        }
    }
}



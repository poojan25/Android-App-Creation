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

public class propAdapter extends RecyclerView.Adapter<propAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<property> productList;

    public propAdapter(Context mCtx, List<property> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.proplist, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        property product = productList.get(position);

        //loading the image
        /*Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);*/

        holder.textViewTitle.setText(product.getname());
        holder.textView.setText(product.getContact());
        holder.textViewShortDesc.setText(product.getloc());
        holder.textViewShort.setText(product.getBhk());
        holder.textViewPrice.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle,textView, textViewShortDesc,textViewShort, textViewRating, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textView = itemView.findViewById(R.id.textView);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewShort = itemView.findViewById(R.id.textViewShort);
            //textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            //imageView = itemView.findViewById(R.id.imageView);
        }
    }
}



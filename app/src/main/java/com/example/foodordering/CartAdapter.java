package com.example.foodordering.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodordering.Domain.Foods;
import com.example.foodordering.Helper.ChangeNumberItemsListener;
import com.example.foodordering.Helper.ManagmentCart;
import com.example.foodordering.R;

import java.util.ArrayList;
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {
    ArrayList<Foods> list;
    private ManagmentCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter (ArrayList<Foods> list, Context context, ChangeNumberItemsListener changeNumberItemsListener){
        this.list = list;
        managementCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }



    @NonNull
    @Override
    public CartAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.viewholder holder, int position) {
        Foods currentFood = list.get(position);

        holder.title.setText(currentFood.getTitle());
        holder.feeEachItem.setText("$" + currentFood.getPrice());

        // Calculate total cost for each item
        double totalCost = currentFood.getNumberInCart() * currentFood.getPrice();
        holder.totalEachItem.setText("$" + totalCost);

        // Convert num to string before setting it to TextView
        holder.num.setText(String.valueOf(currentFood.getNumberInCart()));

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberItem(list, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberItem(list, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, plusItem, minusItem;
        ImageView pic;
        TextView totalEachItem, num;


        public viewholder(@NonNull View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachitem);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            totalEachItem = itemView.findViewById(R.id.totalEachitem);
            num = itemView.findViewById(R.id.numberItemTxt);

        }
    }
}

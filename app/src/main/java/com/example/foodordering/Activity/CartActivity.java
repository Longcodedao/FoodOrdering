package com.example.foodordering.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.foodordering.Adapter.CartAdapter;
import com.example.foodordering.Domain.Foods;
import com.example.foodordering.Helper.ManagmentCart;
import com.example.foodordering.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;

    private double tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);

        setVariable();
        calculateCart();
        initList();
        

    }

    private void initList() {
        ArrayList<Foods> managementListCart =  managmentCart.getListCart();

        if(managementListCart.isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollviewCart.setVisibility(View.GONE);
        }else{
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollviewCart.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);

        binding.cardView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managementListCart, this, this::calculateCart);
        binding.cardView.setAdapter(adapter);
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10;

        tax = Math.round(managmentCart.getTotalFee()* percentTax * 100.0)/100;

        double total = Math.round((managmentCart.getTotalFee()*tax+ delivery)* 100)/100;
        double itemTotal = Math.round(managmentCart.getTotalFee()*100)/100;

        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$"+ delivery);
        binding.totalTxt.setText("$"+ total);


    }

    private void setVariable() {
    }
}
package com.example.foodordering.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.foodordering.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
        getWindow().setStatusBarColor(Color.parseColor( "#FFE485"));

    }
    private void setVariable(){
//        binding.loginBtn.setOnClickListener(v -> {
//            if(mAuth.getCurrentUser()!= null){
//                startActivity(new Intent(IntroActivity.this, MainActivity.class));
//            }else{
//                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
//            }
//
//        });
//        binding.signupBtn.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this, SignupActivity.class)));
        TextView loginBtn = binding.loginBtn;
        TextView signUpBtn = binding.signupBtn;

        moveAuthentication(loginBtn, this, LoginActivity.class);
        moveAuthentication(signUpBtn, this, SignupActivity.class);
    }

    private void moveAuthentication(TextView view, Context context, Class<?> destinationActivity){
        view.setOnClickListener(v -> {

            if (mAuth.getCurrentUser() == null) {
                Intent intent = new Intent(context, destinationActivity);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }

        });
    }
}
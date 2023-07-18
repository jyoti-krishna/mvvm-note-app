package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvvm.databinding.ActivityDataBinding;

public class dataActivity extends AppCompatActivity {
    ActivityDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String type=getIntent().getStringExtra("type");
        if(type.equals("add")){
            setTitle("Add Note");
            binding.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent();
                    intent.putExtra("title",binding.editText1.getText().toString());
                    intent.putExtra("des",binding.editText2.getText().toString());
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }
        else{
            setTitle("Edit Note");
            binding.editText1.setText(getIntent().getStringExtra("title"));
            binding.editText2.setText(getIntent().getStringExtra("des"));
            int id=getIntent().getIntExtra("id",0);
            binding.button2.setText("UPDATE");
            binding.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent();
                    intent.putExtra("title",binding.editText1.getText().toString());
                    intent.putExtra("des",binding.editText2.getText().toString());
                    intent.putExtra("id",id);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });

        }

    }
}
package com.example.mvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.databinding.ItemrvBinding;


public class RVAdapter  extends ListAdapter<Note,RVAdapter.ViewHolder> {
    protected RVAdapter() {
        super(CALLBACK);
    }
    private static DiffUtil.ItemCallback<Note> CALLBACK =new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDes().equals(newItem.getDes());
        }
    };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note=getItem(position);
        holder.binding.textView2.setText(note.getTitle());
        holder.binding.textView3.setText(note.getDes());
    }

    public Note getNote(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemrvBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemrvBinding.bind(itemView);
        }
    }
}

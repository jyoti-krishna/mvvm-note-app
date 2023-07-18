package com.example.mvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.mvvm.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private NoteVM noteVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Note it");
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteVM=new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteVM.class);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,dataActivity.class);
                intent.putExtra("type","add");
                startActivityForResult(intent,1);
            }
        });
        binding.rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        binding.rv.setHasFixedSize(true);
        RVAdapter adapter=new RVAdapter();
        binding.rv.setAdapter(adapter);


        noteVM.getdata().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction==ItemTouchHelper.LEFT){
                    Intent intent=new Intent(MainActivity.this,dataActivity.class);
                    intent.putExtra("type","edit" );
                    intent.putExtra("title",adapter.getNote(viewHolder.getAdapterPosition()).getTitle() );
                    intent.putExtra("des",adapter.getNote(viewHolder.getAdapterPosition()).getDes() );
                    intent.putExtra("id",adapter.getNote(viewHolder.getAdapterPosition()).getId() );
                    startActivityForResult(intent,2);

                }
                else{
                    noteVM.delete(adapter.getNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "note deleted", Toast.LENGTH_SHORT).show();
                }
            }
        }).attachToRecyclerView(binding.rv);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            String title=data.getStringExtra("title");
            String des=data.getStringExtra("des");
            Note note=new Note(title,des);
            noteVM.insert(note);
            Toast.makeText(this, "added new note", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==2){
            String title=data.getStringExtra("title");
            String des=data.getStringExtra("des");
            Note note=new Note(title,des);
            note.setId(data.getIntExtra("id",0));
            noteVM.update(note);
            Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
        }
    }
}
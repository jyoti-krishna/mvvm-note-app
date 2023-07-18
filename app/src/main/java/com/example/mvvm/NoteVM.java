package com.example.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteVM extends AndroidViewModel {
    private NoteRepo noteRepo;
    private LiveData<List<Note>> noteList;
    public NoteVM(@NonNull Application application) {
        super(application);
        noteRepo=new NoteRepo(application);
        noteList=noteRepo.getData();
    }
    public void insert(Note note){
        noteRepo.insertData(note);
    }
    public void delete(Note note){
        noteRepo.deleteData(note);
    }
    public void update(Note note){ noteRepo.updateData(note); }
    public LiveData<List<Note>> getdata(){
        return noteList;
    }
}

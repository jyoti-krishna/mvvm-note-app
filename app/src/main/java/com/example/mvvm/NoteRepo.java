package com.example.mvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepo {
    private Notedao notedao;
    private LiveData<List<Note>> notelist;
    public NoteRepo(Application application){
        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
        notedao=noteDatabase.notedao();
        notelist=notedao.getData();
    }
    public void insertData(Note note){new InsertTask(notedao).execute(note);}
    public void updateData(Note note){new UpdateTask(notedao).execute(note);}
    public void deleteData(Note note){new DeleteTask(notedao).execute(note);}
    public  LiveData<List<Note>> getData(){
        return notelist;
    }
    private static class InsertTask extends AsyncTask<Note,Void,Void>{
        private Notedao notedao;

        public InsertTask(Notedao notedao) {
            this.notedao = notedao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notedao.insert(notes[0]);
            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<Note,Void,Void>{
        private Notedao notedao;

        public DeleteTask(Notedao notedao) {
            this.notedao = notedao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notedao.delete(notes[0]);
            return null;
        }
    }
    private static class UpdateTask extends AsyncTask<Note,Void,Void>{
        private Notedao notedao;

        public UpdateTask(Notedao notedao) {
            this.notedao = notedao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            notedao.update(notes[0]);
            return null;
        }
    }
}

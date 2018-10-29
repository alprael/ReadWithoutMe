package com.alprael.readwithoutme.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.alprael.readwithoutme.R;

@Database(entities = {Book.class}, version = 1, exportSchema = true)
public abstract class BookDatabase extends RoomDatabase {

  private static final String DATABASE_NAME = "book_db";

  private static BookDatabase instance = null;

  public static synchronized BookDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context,
          BookDatabase.class, DATABASE_NAME)
          .addCallback(new Callback(context.getApplicationContext()))
          .build();
    }
    return instance;
  }

  public static synchronized void forgetInstance(Context context) {instance = null;}

  public abstract BookDao getBookDao();

  private static class Callback extends RoomDatabase.Callback {
    private Context context;

    public Callback(Context context) {
      this.context = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      new PrepopulateTask(context).execute();
    }

    @Override
    public void onOpen(@NonNull SupportSQLiteDatabase db) {
      super.onOpen(db);
    }
  }

  private static class PrepopulateTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    public PrepopulateTask(Context context) {
      this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      BookDatabase db = getInstance(context);
      BookDao dao = db.getBookDao();
      Book book = new Book();
      book.setBookName("This Book is Green");
      book.setId(1);
      dao.insert(book);
      forgetInstance(context);
      return null;
    }
  }

}

package com.alprael.readwithoutme.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.alprael.readwithoutme.model.dao.BookDao;
import com.alprael.readwithoutme.model.dao.UserDao;
import com.alprael.readwithoutme.model.entity.Book;
import com.alprael.readwithoutme.model.entity.User;

@Database(entities = {Book.class, User.class}, version = 1, exportSchema = true)
public abstract class RWMDatabase extends RoomDatabase {

  private static final String DATABASE_NAME = "rwm_db";

  private static RWMDatabase instance = null;

  public static synchronized RWMDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context,
          RWMDatabase.class, DATABASE_NAME)
          .addCallback(new Callback(context.getApplicationContext()))
          .build();
    }
    return instance;
  }

  public static synchronized void forgetInstance(Context context) {instance = null;}

  public abstract BookDao getBookDao();

  public abstract UserDao getUserDao();

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

    PrepopulateTask(Context context) {
      this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      RWMDatabase db = getInstance(context);
      BookDao bookDao = db.getBookDao();
      Book book = new Book();
      book.setBookName("This Book is Green");
      book.setFileName("green_book.html");
      book.setAuthorName("Alex Rael");
      bookDao.insert(book);
      forgetInstance(context);
      return null;
    }
  }

}

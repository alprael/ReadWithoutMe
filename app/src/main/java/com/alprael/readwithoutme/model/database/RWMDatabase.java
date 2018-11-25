package com.alprael.readwithoutme.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.dao.BookDao;
import com.alprael.readwithoutme.model.dao.BooksReadDao;
import com.alprael.readwithoutme.model.dao.QuizDao;
import com.alprael.readwithoutme.model.dao.UserDao;
import com.alprael.readwithoutme.model.entity.Book;
import com.alprael.readwithoutme.model.entity.BooksRead;
import com.alprael.readwithoutme.model.entity.Quiz;
import com.alprael.readwithoutme.model.entity.User;

/**
 * The main Read Without Me database extending Room.
 */
@Database(entities = {Book.class, User.class, BooksRead.class, Quiz.class},
    version = 1, exportSchema = true)
public abstract class RWMDatabase extends RoomDatabase {

  private static final String DATABASE_NAME = "rwm_db";

  private static RWMDatabase instance = null;

  /**
   * Gets the current instance of the database, and if null,
   * builds the database to allow access to it elsewhere in the project.
   * @param context
   * @return
   */
  public static synchronized RWMDatabase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context,
          RWMDatabase.class, DATABASE_NAME)
          .addCallback(new Callback(context.getApplicationContext()))
          .build();
    }
    return instance;
  }

  /**
   * Sets the current instance of the database back to null.
   * @param context
   */
  public static synchronized void forgetInstance(Context context) {instance = null;}

  /**
   * Allows the database to grab methods from the Book Dao interface to allow for
   * updating, inserting, and deleting data within the Book entity in the database.
   * @return
   */
  public abstract BookDao getBookDao();

  /**
   * Allwws the database to grab methods from the User Dao interface to allow for
   * updaing, inserting, and deleting data within the User entity in the database.
   * @return
   */
  public abstract UserDao getUserDao();

  /**
   * Allows the database to grab methods from the BooksRead Dao interface to allow for
   * updating, inserting, and deleting data within the BooksRead entity in the database.
   * @return
   */
  public abstract BooksReadDao getBooksReadDao();

  /**
   * Allows the database to grab methods from the Quiz Dao interface to allow for
   * updating, inserting, and deletingn data within the Quiz entity in the database.
   * @return
   */
  public abstract QuizDao getQuizDao();

  private static class Callback extends RoomDatabase.Callback {
    private Context context;

    Callback(Context context) {
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

    /**
     * Grabs from the various Dao's and inserts data into the appropriate entities.
     * @param voids
     * @return
     */
    @Override
    protected Void doInBackground(Void... voids) {
      RWMDatabase db = getInstance(context);

      QuizDao quizDao = db.getQuizDao();
      Quiz quiz = new Quiz();
      quiz.setFileName("theGreenBookQuiz.html");
      long quizId = quizDao.insert(quiz);

      BookDao bookDao = db.getBookDao();
      Book book = new Book();
      book.setBookName("This Book is Green");
      book.setFileName("greenBook.html");
      book.setAuthorName("Alex Rael");
      book.setQuizId(quizId);
      book.setResImage(R.drawable.the_green_book_cover);
      bookDao.insert(book);

      BookDao bookDao1 = db.getBookDao();
      Book book1 = new Book();
      book1.setFileName("haHa.html");
      book1.setAuthorName("Alex Rael");
      book1.setBookName("HA HA");
      book1.setResImage(R.drawable.book_img_192);
      bookDao.insert(book1);

      forgetInstance(context);
      return null;
    }
  }

}

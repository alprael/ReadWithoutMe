package com.alprael.readwithoutme.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.alprael.readwithoutme.model.entity.Book;
import java.util.List;

/**
 * Book Dao interface for the Book entity in the Read Without Me database.
 */
@Dao
public interface BookDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Book book);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(Book... books);


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Book> books);

  @Query("SELECT * FROM books ORDER BY book_name ASC")
  List<Book> select();

  @Query("SELECT * FROM books WHERE book_id=:bookId")
  Book selectBook(long bookId);

  @Query("SELECT file_name FROM books WHERE book_id=:bookId")
  String selectFileName(long bookId);

  @Query("SELECT * FROM books WHERE file_name=:fileName")
  Book selectAllFileName(String fileName);

  @Query("SELECT resource_image FROM books WHERE book_id=:bookId")
  int selectAllResImage(long bookId);

  @Query("SELECT resource_image FROM books WHERE resource_image=:resImage")
  int selectResImage(String resImage);

  @Query("SELECT * FROM books WHERE quiz_id=:quizId")
  Book selectAllQuiz(long quizId);


    @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Book books);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Book... books);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(List<Book> books);

  @Delete
  int delete(Book book);

  @Delete
  int delete(Book... books);

  @Delete
  int delete(List<Book> books);

}

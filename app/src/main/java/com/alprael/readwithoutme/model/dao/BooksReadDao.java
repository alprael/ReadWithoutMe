package com.alprael.readwithoutme.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.alprael.readwithoutme.model.entity.BooksRead;
import java.util.List;

/**
 * BooksRead Dao for the BooksRead entity in the Read Without Me Database.
 */
@Dao
public interface BooksReadDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(BooksRead booksRead);


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(BooksRead... booksReads);


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<BooksRead> booksReads);

  @Query("SELECT * FROM booksRead ORDER BY user_id ASC")
  List<BooksRead> selectAll();

  @Query("SELECT * FROM booksRead WHERE book_id=:bookId")
  BooksRead selectAllBookId(long bookId);

  @Query("SELECT * FROM booksRead WHERE user_id=:userId")
  BooksRead selectAllUserId(long userId);

  @Query("SELECT user_id FROM booksRead WHERE user_id=:userId")
  Long selectUserId(long userId);

  @Query("SELECT book_id FROM booksRead WHERE book_id=:bookId")
  Long selectBookId(long bookId);

  @Query("SELECT * FROM booksRead WHERE book_read_time=:bookReadTime")
  BooksRead selectAllBookReadTime(long bookReadTime);


  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(BooksRead booksRead);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(BooksRead... booksReads);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(List<BooksRead> booksReads);

  @Delete
  int delete(BooksRead booksRead);

  @Delete
  int delete(BooksRead... booksReads);

  @Delete
  int delete(List<BooksRead> booksReads);

}

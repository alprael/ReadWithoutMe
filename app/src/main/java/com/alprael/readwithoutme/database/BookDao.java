package com.alprael.readwithoutme.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface BookDao {

  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(Book book);

  @Insert(onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(Book... books);


  @Insert(onConflict = OnConflictStrategy.FAIL)
  List<Long> insert(List<Book> books);

  @Query("SELECT * FROM books ORDER BY book_name ASC")
  List<Book> select();

  @Update(onConflict = OnConflictStrategy.FAIL)
  int update(Book books);

  @Update(onConflict = OnConflictStrategy.FAIL)
  int update(Book... books);

  @Update(onConflict = OnConflictStrategy.FAIL)
  int update(List<Book> books);

  @Delete
  int delete(Book book);

  @Delete
  int delete(Book... books);

  @Delete
  int delete(List<Book> books);

}

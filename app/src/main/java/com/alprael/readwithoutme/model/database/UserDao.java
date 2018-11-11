package com.alprael.readwithoutme.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

@Dao
public interface UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(User user);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(User... users);


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<User> users);

  @Query("SELECT * FROM users ORDER BY user_id ASC")
  List<Book> select();

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(User users);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(User... users);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(List<User> users);

  @Delete
  int delete(User user);

  @Delete
  int delete(User... users);

  @Delete
  int delete(List<User> users);

}

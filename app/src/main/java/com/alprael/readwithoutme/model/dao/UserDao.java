package com.alprael.readwithoutme.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.alprael.readwithoutme.model.entity.User;
import java.util.List;

/**
 * User Dao for the User Entity in the Read Without Me database.
 */
@Dao
public interface UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(User user);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(User... users);


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<User> users);

  @Query("SELECT * FROM users ORDER BY user_name ASC")
  List<User> select();

  @Query("SELECT * FROM users WHERE user_id=:userId")
  User selectAllUser(long userId);


  @Query("SELECT * FROM users WHERE email=:email")
  User selectEmail(String email);

  @Query("SELECT * FROM users WHERE user_name=:userName")
  User selectUserName(String userName);

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

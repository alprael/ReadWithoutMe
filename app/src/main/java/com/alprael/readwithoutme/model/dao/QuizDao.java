package com.alprael.readwithoutme.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.alprael.readwithoutme.model.entity.Quiz;
import java.util.List;

/**
 * Quiz Dao for the Quiz Entity in the Read Without Me Database
 */
@Dao
public interface QuizDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(Quiz quiz);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(Quiz... quizzes);


  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Quiz> quizzes);

  @Query("SELECT * FROM quiz ORDER BY quiz_id ASC")
  List<Quiz> selectAll();

  @Query("SELECT quiz_id FROM quiz WHERE quiz_id=:quizId")
  Long selectQuizId(long quizId);

  @Query("SELECT * FROM quiz WHERE quiz_id=:quizId")
  Quiz selectAllQiuizId(long quizId);


  @Query("SELECT * FROM quiz WHERE file_name=:fileName")
  Quiz selectAllFileName(String fileName);

  @Query("SELECT file_name FROM quiz WHERE quiz_id=:quiz_id")
  String selectFileName(long quiz_id);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Quiz quiz);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(Quiz... quizzes);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(List<Quiz> quizzes);

  @Delete
  int delete(Quiz quiz);

  @Delete
  int delete(Quiz... quizzes);

  @Delete
  int delete(List<Quiz> quizzes);

}

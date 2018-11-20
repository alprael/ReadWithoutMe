package com.alprael.readwithoutme.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Quiz entity for the Read Without Me database.
 * This entity holds the contents of the quiz and assigns each an ID.
 * It also defines a file path for the current location of the quiz for later access.
 */
@Entity(
    tableName = "quiz",
    indices = {@Index(value = {"quiz_id"}, unique = true)}
)
public class Quiz {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "quiz_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "file_name")
  private String fileName;

  /**
   * Allows rest of project to get the current quiz ID.
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Allows rest of project to set an ID for a quiz (is usually auto generated.)
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Allows rest of project to get the file path for a certain quiz.
   * @return
   */
  @NonNull
  public String getFileName() {
    return fileName;
  }

  /**
   * Allows rest of project to define a file path for a certain quiz.
   * @param fileName
   */
  public void setFileName(@NonNull String fileName) {
    this.fileName = fileName;
  }


}
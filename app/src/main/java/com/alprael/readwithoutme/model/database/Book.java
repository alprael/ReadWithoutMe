package com.alprael.readwithoutme.model.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    tableName = "books",
    indices = {@Index(value = {"book_name"}, unique = true)}
)
public class Book {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "book_id")
  private long id;

  @NonNull
  @ColumnInfo
  private String fileName;



  @NonNull
  @ColumnInfo(name = "book_name", collate = ColumnInfo.NOCASE)
  private String BookName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getBookName() {
    return BookName;
  }

  public void setBookName(@NonNull String bookName) {
    this.BookName = bookName;
  }

  @NonNull
  public String getFileName() {
    return fileName;
  }

  public void setFileName(@NonNull String fileName) {
    this.fileName = fileName;
  }
}
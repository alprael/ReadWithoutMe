package com.alprael.readwithoutme.model.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Book Entity for the Read Without Me database.
 * It defines the primary and foreign keys of the table.
 */
@Entity(
    tableName = "books",
    indices = {@Index(value = {"book_name"}, unique = true)}
)
public class Book {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "book_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "file_name", collate = ColumnInfo.NOCASE)
  private String fileName;

  @NonNull
  @ColumnInfo(name = "author_name", collate = ColumnInfo.NOCASE)
  private String authorName;


  @NonNull
  @ColumnInfo(name = "book_name", collate = ColumnInfo.NOCASE)
  private String bookName;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getBookName() {
    return bookName;
  }

  public void setBookName(@NonNull String bookName) {
    this.bookName = bookName;
  }

  @NonNull
  public String getFileName() {
    return fileName;
  }

  public void setFileName(@NonNull String fileName) {
    this.fileName = fileName;
  }

  @NonNull
  public String getAuthorName() { return authorName; }

  public void setAuthorName(@NonNull String authorName) {this.authorName = authorName;}

}

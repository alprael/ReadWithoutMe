package com.alprael.readwithoutme.model.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Book Entity for the Read Without Me database.
 * This entity holds the book and it's contents.
 * There's an id for the book, a book name, an author's name,
 * and a file name that define it's current location.
 * This entity also currently grabs a quiz_id to associate a quiz to certain books.
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

  @NonNull
  @ColumnInfo(name = "resource_image", collate = ColumnInfo.NOCASE)
  private int resImage;

  @NonNull
  @ColumnInfo(name = "quiz_id", collate = ColumnInfo.NOCASE)
  private long quizId;

  /**
   * Allows rest of project to grab an ID of a certain book.
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Allows rest of project to set an ID of the current book (is usually auto generated.)
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Allows rest of project to grab the book name of a certain book.
   * @return
   */
  @NonNull
  public String getBookName() {
    return bookName;
  }

  /**
   * Allows rest of project to set a book name for a certain book.
   * @param bookName
   */
  public void setBookName(@NonNull String bookName) {
    this.bookName = bookName;
  }

  /**
   * Allows rest of project to grab the file path of a certain book.
   * @return
   */
  @NonNull
  public String getFileName() {
    return fileName;
  }

  /**
   * Allows rest of project to set a file path for a certain book.
   * @param fileName
   */
  public void setFileName(@NonNull String fileName) {
    this.fileName = fileName;
  }

  /**
   * Allows rest of project to grab the author name for a certain book.
   * @return
   */
  @NonNull
  public String getAuthorName() { return authorName; }

  /**
   * Allows rest of project to set an author name to a certain book.
   * @param authorName
   */
  public void setAuthorName(@NonNull String authorName) {this.authorName = authorName;}

  /**
   * Allows rest of project to grab the image resource (book cover) of a certain book.
   * (returns int ex: (R.drawable.*.png) comes back as an int.)
   * @return
   */
  @NonNull
  public int getResImage() {
    return resImage;
  }

  /**
   * Allows rest of project to set an image resource (book cover) in the form of an int
   * for a certain book.
   * @param resImage
   */
  public void setResImage(@NonNull int resImage) {
    this.resImage = resImage;
  }

  /**
   * If a certain book has an associated quiz, this allows rest of project grab that quiz ID
   * for a certain quiz associated with the book.
   * @return
   */
  public long getQuizId() {
    return quizId;
  }

  /**
   * Allows rest of project to set a quiz ID to a certain book to associate them.
   * @param quizId
   */
  public void setQuizId(long quizId) {
    this.quizId = quizId;
  }
}

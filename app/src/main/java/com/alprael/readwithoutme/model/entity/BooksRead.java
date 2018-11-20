package com.alprael.readwithoutme.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * BooksRead entity for the Read Without Me database.
 * This entity is the main connection between user and book.
 * It takes in a book_id and a user_id.
 * It does so only when the user has successfully read a book.
 */
@Entity(
    tableName = "booksRead",
    indices = {@Index(value = {"books_read_id", "user_id"}, unique = true)},
    foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "user_id",
        childColumns = "user_id"), @ForeignKey(entity = Book.class,
        parentColumns = "book_id", childColumns = "book_id")})
public class BooksRead {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "books_read_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "user_id")
  private long userId;

  @NonNull
  @ColumnInfo(name = "book_id")
  private long bookId;

  @NonNull
  @ColumnInfo(name = "book_read_time")
  private long bookReadTime;

  /**
   * Allows rest of project to grab an ID of the current booksRead entity.
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Allows rest of project to set an ID for the current booksRead entity
   * (is usually auto generated.)
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Allows rest of project to grab the user ID of a user in relation to the current
   * booksRead entity.
   * @return
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Allows rest of the project to set a user ID in relation to the current booksRead entity
   * (is usually set from an already generated user ID from the user entity.)
   * @param userId
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Allows rest of project to grab the book ID of a book in relation to the current
   * booksRead entity.
   * @return
   */
  public long getBookId() {
    return bookId;
  }

  /**
   * Allows rest of project to set a book ID in relation to the current booksRead entity
   * (is usually set from an already generated book ID from the book entity.)
   * @param bookId
   */
  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  /**
   * Allows rest of project to grab the book read time for a certain user who read a certain book.
   * @return
   */
  public long getBookReadTime() {
    return bookReadTime;
  }

  /**
   * Allows rest of project to set a book read time for a certain book read by a certain user.
   * @param bookReadTime
   */
  public void setBookReadTime(long bookReadTime) {
    this.bookReadTime = bookReadTime;
  }
}

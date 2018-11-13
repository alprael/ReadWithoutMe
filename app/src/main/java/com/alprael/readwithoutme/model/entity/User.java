package com.alprael.readwithoutme.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
    tableName = "users",
    indices = {@Index(value = {"display_name"}, unique = true)})

public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "display_name", collate = ColumnInfo.NOCASE)
  private String displayName;

  @NonNull
  @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE)
  private String email;

  @NonNull
  @ColumnInfo(name = "number_books_read")
  private long numberBooksRead;


  public User() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(@NonNull String displayName) {
    this.displayName = displayName;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  public long getNumberBooksRead() {
    return numberBooksRead;
  }

  public void setNumberBooksRead(long numberBooksRead) {
    this.numberBooksRead = numberBooksRead;
  }

}

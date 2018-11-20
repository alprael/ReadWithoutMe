package com.alprael.readwithoutme.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * User entity for the Read Without Me database.
 * It holds the current user_id, the user's display name and email.
 * A user is created upon a successful sign in through Google Sign In.
 */
@Entity(
    tableName = "users",
    indices = {@Index(value = {"display_name", "user_id"}, unique = true)})
public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  public long id;

  @NonNull
  @ColumnInfo(name = "display_name", collate = ColumnInfo.NOCASE)
  private String displayName;

  @NonNull
  @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE)
  private String email;


  /**
   * Allows rest of project to grab the current user ID.
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Allows rest of project to set an id for the current user.
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Allows rest of project to grab the display name of the current user.
   * @return
   */
  @NonNull
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Allows rest of project to set a display name for the current user.
   * @param displayName
   */
  public void setDisplayName(@NonNull String displayName) {
    this.displayName = displayName;
  }

  /**
   * Allows rest of project grab the email for the current user.
   * @return
   */
  @NonNull
  public String getEmail() {
    return email;
  }

  /**
   * Allows rest of project to set an email for the current user.
   * @param email
   */
  public void setEmail(@NonNull String email) {
    this.email = email;
  }

}

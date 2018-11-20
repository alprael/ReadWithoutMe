package com.alprael.readwithoutme;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * This class initializes the Stetho for further inspection of the SQL database.
 */
public class BookApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}

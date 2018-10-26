package com.alprael.readwithoutme;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class BookApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}

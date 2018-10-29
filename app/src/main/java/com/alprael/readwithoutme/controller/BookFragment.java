package com.alprael.readwithoutme.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.database.Book;
import com.alprael.readwithoutme.model.database.BookDatabase;
import java.util.List;

public class BookFragment extends Fragment {
private WebView webView;
private BookDatabase database;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_book, container, false);
    webView = (WebView) view.findViewById(R.id.simple_webView);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl("file:///android_asset/greenbook.html");
    return view;
  }

}

package com.alprael.readwithoutme.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import com.alprael.readwithoutme.R;
import java.util.Timer;


public class BookFragment extends Fragment {

  private WebView webView;
  private Timer timer;
  private TextView textView;
  private Button button;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_book, container, false);

    webView = (WebView) view.findViewById(R.id.simple_webView);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl("file:///android_asset/books/greenbook.html");

    textView = (TextView) view.findViewById(R.id.timer_text);

    button = (Button) view.findViewById(R.id.quiz_button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
       QuizFragment quizFragment = new QuizFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack("quiz");
        transaction.replace(R.id.frag_container, new QuizFragment());
        transaction.commit();
      }
    });
    return view;
  }
}


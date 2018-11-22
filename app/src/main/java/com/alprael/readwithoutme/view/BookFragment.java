package com.alprael.readwithoutme.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Chronometer;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.controller.MainActivity;
import com.alprael.readwithoutme.model.dao.BooksReadDao;
import com.alprael.readwithoutme.model.database.RWMDatabase;
import com.alprael.readwithoutme.model.entity.Book;
import com.alprael.readwithoutme.model.entity.BooksRead;
import com.alprael.readwithoutme.model.entity.Quiz;
import com.alprael.readwithoutme.model.entity.User;
import java.util.List;

/**
 * Fragment that inflates the contents of the books in a web view and inflates
 * a view for the chronometer.
 * This fragment is also used to view every book in the database, and set timers from the
 * chronometer to each book, and increment the BooksRead entity according to the current user.
 */
public class BookFragment extends Fragment {

  private WebView webView;
  private View view;
  private Chronometer chronometer;
  private boolean running;
  private long pauseOffset;
  private Bundle bundle;
  private long seconds;
  private List<Quiz> listQuiz;
  private Bundle quizBundle, quizBundle1;
  private QuizFragment quizFragment;
  private BooksRead booksRead;
  private Book book;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    bundle = getArguments();
    quizBundle = new Bundle();
    quizFragment = new QuizFragment();
    booksRead = new BooksRead();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_book, container, false);
    setHasOptionsMenu(true);
    initChron();
    initView();
    return view;
  }

  private void initChron() {
    chronometer = view.findViewById(R.id.chronometer);
    chronometer.setFormat("Time: %s");
    chronometer.setBase(SystemClock.elapsedRealtime());
  }

  private void initView() {
    webView = view.findViewById(R.id.book_fragment_webView);
    webView.setWebViewClient(new WebViewClient());
    new QueryTask().execute(getArguments().getLong("book_id"));
  }

  private void startChronometer() {
    if (!running) {
      chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
      chronometer.start();
      running = true;
    }
  }

  private void pauseChronometer() {
    if (running) {
      chronometer.stop();
      pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
      running = false;
    }
  }

  private void resetChronometer() {
    chronometer.setBase(SystemClock.elapsedRealtime());
    pauseOffset = 0;
  }

  private void goToInfo() {
    UserInfoFragment userInfo = new UserInfoFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction()
        .addToBackStack(null);
    transaction.replace(R.id.frag_container, userInfo);
    transaction.commit();
  }

  private void goToQuiz() {
    pauseChronometer();
    quizBundle();
    FragmentTransaction transaction = getFragmentManager().beginTransaction()
        .addToBackStack(null);
    transaction.replace(R.id.frag_container, quizFragment);
    transaction.commit();
  }

  private void goToHome() {
    MainBookFragment mainBookFragment = new MainBookFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack(null);
    transaction.replace(R.id.frag_container, mainBookFragment);
    transaction.commit();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.book_fragment_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.book_fragment_menu_start_button:
        startChronometer();
        break;
      case R.id.book_fragment_menu_pause_button:
        pauseChronometer();
        break;
      case R.id.book_fragment_menu_reset_button:
        resetChronometer();
        break;
      case R.id.book_fragment_menu_quiz_button:
        goToQuiz();
        break;
      case R.id.book_fragment_menu_book_info:
        goToInfo();
        break;
      case R.id.book_fragment_menu_home:
        goToHome();
        break;
    }
    return true;
  }

  private Bundle quizBundle() {
    seconds = pauseOffset / 1000;
    quizBundle.putLong(getString(R.string.seconds_key), seconds);
    quizBundle.putLong("quiz_id", book.getQuizId());
    booksRead.setUserId(((MainActivity) getActivity()).getUserId());
    booksRead.setBookId(getArguments().getLong("book_id"));
    new BookTask().execute(booksRead);
    quizFragment.setArguments(quizBundle);
    return quizBundle;
  }


  private class QueryTask extends AsyncTask<Long, Void, Book> {

    @Override
    protected Book doInBackground(Long... longs) {
      return RWMDatabase.getInstance(getContext()).getBookDao().selectBook(longs[0]);
    }


    @Override
    protected void onPostExecute(Book b) {
      webView.loadUrl("file:///android_asset/books/" + b.getFileName());
      book = b;
    }
  }

  private class BookTask extends AsyncTask<BooksRead, Void, Long> {

    @Override
    protected Long doInBackground(BooksRead... booksReads) {
      RWMDatabase db = RWMDatabase.getInstance(getContext());
      BooksReadDao booksReadDao = db.getBooksReadDao();
      booksReadDao.selectAll();
      booksRead.setBookReadTime(pauseOffset/1000);
      booksRead.setBookName(book.getBookName());
      booksReadDao.insert(booksReads[0]);
      return null;
    }

  }

  private class QuizTask extends AsyncTask<Void, Void, List<Quiz>> {

    @Override
    protected List<Quiz> doInBackground(Void... voids) {
      return RWMDatabase.getInstance(getContext()).getQuizDao().selectAll();
    }

    @Override
    protected void onPostExecute(List<Quiz> quizzes) {
      listQuiz = quizzes;
    }
  }

}




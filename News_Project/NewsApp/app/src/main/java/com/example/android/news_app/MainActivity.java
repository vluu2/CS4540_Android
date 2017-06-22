package com.example.android.news_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.news_app.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mNewsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsTextView = (TextView) findViewById(R.id.news_data);

    }

    private void loadNewsData() {
        new FetchNewsTask().execute();
    }

    private class FetchNewsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }
            String stringQuery = params[0];
            URL newsRequestUrl = NetworkUtils.buildUrl(stringQuery);

            String jsonNewsDataResponse = null;

            try {
                jsonNewsDataResponse = NetworkUtils.getResponseFromHttpUrl(newsRequestUrl);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return jsonNewsDataResponse;
        }

        @Override
        protected void onPostExecute(String newsData) {
            if (newsData != null) {
                mNewsTextView.setText(newsData);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.menu, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh) {
            mNewsTextView.setText("");
            loadNewsData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
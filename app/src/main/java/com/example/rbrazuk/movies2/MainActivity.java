package com.example.rbrazuk.movies2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bt_add_movie) Button btAdd;
    @Bind(R.id.bt_my_movies) Button btMyMovies;
    @Bind(R.id.bt_watch_list) Button btWatchList;

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Firebase.setAndroidContext(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(MainActivity.this,AddMovie.class);
                startActivity(intent);
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.bt_add_movie)
    public void addMovie (View view) {
        mIntent = new Intent(MainActivity.this,AddMovie.class);

        startActivity(mIntent);
    }

    @OnClick(R.id.bt_my_movies)
    public void myMovies (View view) {
        mIntent = new Intent(MainActivity.this,MovieList.class);
        mIntent.putExtra("list","movies");
        startActivity(mIntent);
    }

    @OnClick(R.id.bt_watch_list)
    public void watchList (View view) {
        mIntent = new Intent(MainActivity.this,MovieList.class);
        mIntent.putExtra("list","watchlist");
        startActivity(mIntent);
    }


}

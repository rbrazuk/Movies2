package com.example.rbrazuk.movies2;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieList extends AppCompatActivity {

    @Bind(R.id.lv_movies) ListView lvMovies;
    FirebaseListAdapter<Movie> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://rcbmovieapp.firebaseio.com/");

        String listChoice = getIntent().getStringExtra("list");

        Firebase moviesRef = ref.child(listChoice);

        mAdapter = new FirebaseListAdapter<Movie>(this,Movie.class,R.layout.list_item_movie,moviesRef) {
            @Override
            protected void populateView(View view, Movie movie, int i) {
                //((TextView)view.findViewById(android.R.id.text1)).setText(movie.getTitle());
                //((TextView)view.findViewById(android.R.id.text2)).setText(movie.getYearReleased());

                ((TextView)view.findViewById(R.id.tv_title)).setText(movie.getTitle());
                ((TextView)view.findViewById(R.id.tv_year)).setText(movie.getYearReleased());
                ((TextView)view.findViewById(R.id.tv_rating)).setText(movie.getRating());

            }
        };

        lvMovies.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
            case R.id.action_add:
                Intent intent = new Intent(MovieList.this,AddMovie.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}

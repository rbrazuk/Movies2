package com.example.rbrazuk.movies2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bt_add_movie) Button btAdd;
    @Bind(R.id.bt_my_movies) Button btMyMovies;
    @Bind(R.id.bt_watch_list) Button btWatchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://rcbmovieapp.firebaseio.com/");

        Firebase moviesRef = ref.child("movies");

        Movie movie = new Movie();

        movie.setTitle("American Psycho");

        moviesRef.push().setValue(movie);
    }
}

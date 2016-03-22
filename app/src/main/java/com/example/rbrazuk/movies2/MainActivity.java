package com.example.rbrazuk.movies2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://rcbmovieapp.firebaseio.com/");

        Firebase moviesRef = ref.child("movies");

        Movie movie = new Movie();

        movie.setTitle("American Psycho");

        moviesRef.push().setValue(movie);
    }
}

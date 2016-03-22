package com.example.rbrazuk.movies2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Firebase.setAndroidContext(this);

        Firebase ref = new Firebase("https://rcbmovieapp.firebaseio.com/");

        String listChoice = getIntent().getStringExtra("list");

        Firebase moviesRef = ref.child(listChoice);

        mAdapter = new FirebaseListAdapter<Movie>(this,Movie.class,android.R.layout.two_line_list_item,moviesRef) {
            @Override
            protected void populateView(View view, Movie movie, int i) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(movie.getTitle());
                ((TextView)view.findViewById(android.R.id.text2)).setText(movie.getYearReleased());

            }
        };

        lvMovies.setAdapter(mAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}

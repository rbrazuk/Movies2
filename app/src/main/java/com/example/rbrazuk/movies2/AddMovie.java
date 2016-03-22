package com.example.rbrazuk.movies2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMovie extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Bind(R.id.et_title) EditText etTitle;
    @Bind(R.id.et_director) EditText etDirector;
    @Bind(R.id.et_genre) EditText etGenre;
    @Bind(R.id.bt_save_movie) Button btSave;
    @Bind(R.id.cb_on_watchlist) CheckBox cbOnWatchlist;
    @Bind(R.id.spinner_rating) Spinner ratingSpinner;

    Movie mMovie;
    Firebase moviesRef;
    Firebase watchlistRef;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        ButterKnife.bind(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.ratings,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(adapter);
        ratingSpinner.setOnItemSelectedListener(this);

        Firebase ref = new Firebase("https://rcbmovieapp.firebaseio.com/");

        //moviesRef = ref.child("movies");
        //watchlistRef = ref.child("watchlist");



        mMovie = new Movie();
    }

    @OnClick(R.id.bt_save_movie)
    public void saveMovie(View view) {

        boolean onWatchList = cbOnWatchlist.isChecked();

        if(!onWatchList) {
            mMovie.setTitle(etTitle.getText().toString());
            mMovie.setDirector(etDirector.getText().toString());
            mMovie.setGenre(etGenre.getText().toString());
            mMovie.setOnWatchList(cbOnWatchlist.isChecked());

            moviesRef.push().setValue(mMovie);
        } else if (onWatchList) {
            mMovie.setTitle(etTitle.getText().toString());
            mMovie.setDirector(etDirector.getText().toString());
            mMovie.setGenre(etGenre.getText().toString());
            mMovie.setOnWatchList(cbOnWatchlist.isChecked());

            watchlistRef.push().setValue(mMovie);
        }

        mIntent = new Intent(AddMovie.this,MainActivity.class);
        startActivity(mIntent);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mMovie.setRating(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mMovie.setRating(null);
    }
}

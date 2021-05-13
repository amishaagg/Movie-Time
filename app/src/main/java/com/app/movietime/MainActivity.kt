package com.app.movietime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        top250Movies.setOnClickListener{
            val intent=Intent(this,ListActivity::class.java)
            intent.putExtra(ListActivity.CATEGORY_EXTRA,"Top250Movies")
            startActivity(intent)
        }
        top250Shows.setOnClickListener {
            val intent=Intent(this,ListActivity::class.java)
            intent.putExtra(ListActivity.CATEGORY_EXTRA,"Top250TVs")
            startActivity(intent)
        }
        mostPopularMovies.setOnClickListener {
            val intent=Intent(this,ListActivity::class.java)
            intent.putExtra(ListActivity.CATEGORY_EXTRA,"MostPopularMovies")
            startActivity(intent)
        }
        mostPopularShows.setOnClickListener {
            val intent=Intent(this,ListActivity::class.java)
            intent.putExtra(ListActivity.CATEGORY_EXTRA,"MostPopularTVs")
            startActivity(intent)
        }
    }
}
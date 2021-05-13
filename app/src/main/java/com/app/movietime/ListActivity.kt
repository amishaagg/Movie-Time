package com.app.movietime

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.app.mynews.MySingleton
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), MovieItemClicked {
    private lateinit var mAdapter:MovieAdapter
    companion object{//companion object is like static variable
    const val CATEGORY_EXTRA="category_extra"//const means cant change
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        recyclerView.layoutManager= LinearLayoutManager(this)
        fetchData()
        mAdapter= MovieAdapter(this)
        recyclerView.adapter=mAdapter
    }
    private fun fetchData(){
        val url="https://imdb-api.com/en/API/${intent.getStringExtra(CATEGORY_EXTRA)}/k_5wlokkwa"
        val moviesArray=ArrayList<Movie>()
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val moviesJsonArray=response.getJSONArray("items")
                for(i in 0 until moviesJsonArray.length()){
                    val movieJsonObject=moviesJsonArray.getJSONObject(i)
                    val movie=Movie(movieJsonObject.getString("rank"),movieJsonObject.getString("title"),movieJsonObject.getString("year"),movieJsonObject.getString("image"),movieJsonObject.getString("imDbRating"),movieJsonObject.getString("id"))
                    moviesArray.add(movie);
                }
                mAdapter.updateMovie(moviesArray)

            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"something went wrong", Toast.LENGTH_LONG)
            }
        )
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }


    override fun onIemClicked(item: Movie) {
        val url = "https://www.imdb.com/title/${item.id}/"
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))

    }
}
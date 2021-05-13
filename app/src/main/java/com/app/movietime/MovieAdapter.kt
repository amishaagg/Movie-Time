package com.app.movietime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val listener:MovieItemClicked): RecyclerView.Adapter<MovieViewHolder>() {
    private val items:ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        val viewHolder=MovieViewHolder(view)
        view.setOnClickListener{
            listener.onIemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text="${currentItem.rank} ${currentItem.title}"
        holder.ratingView.text=currentItem.rating
        holder.yearView.text=currentItem.year
        val imgURL=currentItem.imgURL
        Glide.with(holder.itemView.context).load(imgURL).into(holder.imgView)
    }
    fun updateMovie(updatedMovies:ArrayList<Movie>){
        items.clear()
        items.addAll(updatedMovies)
        notifyDataSetChanged()
    }
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView =itemView.title
    val yearView: TextView=itemView.year
    val imgView: ImageView =itemView.poster
    val ratingView: TextView =itemView.rating
}
//made to handle clicking of list items
interface MovieItemClicked{
    fun onIemClicked(item: Movie)
}
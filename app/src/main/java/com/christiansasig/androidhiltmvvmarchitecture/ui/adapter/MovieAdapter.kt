package com.christiansasig.androidhiltmvvmarchitecture.ui.adapter

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.christiansasig.androidhiltmvvmarchitecture.R
import com.christiansasig.androidhiltmvvmarchitecture.databinding.MovieItemBinding
import com.christiansasig.androidhiltmvvmarchitecture.domain.model.Movie


class MovieAdapter(var context: Activity, private var mValues: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ItemViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding)
    }
    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mValues[position]

        holder.itemView.setOnClickListener {
            /*var myIntent = Intent(context, DetailActivity::class.java)
            myIntent.putExtra("movie_id", item.id)
            context.startActivity(myIntent)*/
        }
        holder.bind(item)
    }

    inner class ItemViewHolder(private val itemBinding: MovieItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(entity: Movie) {
            val requestThumbnail: RequestBuilder<Drawable> by lazy { Glide.with(context).asDrawable().sizeMultiplier(0.1f)}
            itemBinding.title.text = entity.title
            itemBinding.description.text = entity.overview
            if(entity.voteAverage != null){
                itemBinding.rating.rating = (entity.voteAverage * 5) /10f
            }

            if(entity.posterPath != null){
                Glide.with(context).load(context.getString(R.string.server_image_url) + entity.posterPath)
                    .fitCenter()
                    .thumbnail(requestThumbnail)
                    .transform(RoundedCorners(context.resources.getDimension(R.dimen.corner_radius).toInt()))
                    .into(itemBinding.image)
            }
        }
    }
}
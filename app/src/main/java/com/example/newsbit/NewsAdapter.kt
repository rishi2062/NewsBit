package com.example.newsbit

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val context: Context, private val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.newsViewHolder>() {
    class newsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsImage = itemView.findViewById<ImageView>(R.id.image)!!
        var newsAuthor = itemView.findViewById<TextView>(R.id.textAuthor)!!
        var newsTitle = itemView.findViewById<TextView>(R.id.textTitle)!!
        var button = itemView.findViewById<Button>(R.id.button_share)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return newsViewHolder(view)
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val item = articles[position]
        holder.newsAuthor.text = item.author
        holder.newsTitle.text = item.title
        Glide.with(context).load(item.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener{
            val queryUrl : Uri = Uri.parse("${item.url}")
            val intent = Intent(ACTION_VIEW,queryUrl)
            context.startActivity(intent)
        }
        holder.button.setOnClickListener{
            val intent = Intent(ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"${item.url}")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}
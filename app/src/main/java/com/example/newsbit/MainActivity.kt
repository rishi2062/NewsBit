package com.example.newsbit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsbit.NewsObject.newsInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val recyclerView : RecyclerView
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getnews()
    }

    private fun getnews() {
        val news = NewsObject.newsInstance.getNews("in",1)
        news.enqueue(object:Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if(news!=null)
                {
                    Log.d("System",news.toString())
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = NewsAdapter(this@MainActivity,news.articles)

                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("System","Error",t)
            }
        })
    }
}
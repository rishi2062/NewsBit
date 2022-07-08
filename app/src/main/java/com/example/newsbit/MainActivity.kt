package com.example.newsbit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.RoundedCorner
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsbit.NewsObject.newsInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var pageNum: Int = 1
    private var size: Int = 1
    private var totalResults : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getnews()
        pageNum++
        Log.d("MainActivity", "currPage : $pageNum")
        Log.d("MainActivity", "currPage : $totalResults")

//        if(totalResults > size)
//        {
//            getnews()
//        }
    }

    private fun getnews() {
        val news = NewsObject.newsInstance.getNews("in","sports")
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("System", news.toString())
                    var size = news.articles.size
                    var totalResults = news.totalResults
                    Log.d("MainActivity", "currPage : $size")
                    Log.d("MainActivity", "currPage : $totalResults")
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = NewsAdapter(this@MainActivity, news.articles)
//                    if(totalResults > size){
//                        getnews()
//                    }

                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("System", "Error", t)
            }
        })
    }

}

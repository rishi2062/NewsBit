package com.example.newsbit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsbit.databinding.FragmentNewsBinding
import com.example.newsbit.databinding.FragmentScienceBinding
import com.example.newsbit.databinding.FragmentSportsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ScienceFragment : Fragment() {
    private var _binding: FragmentScienceBinding? = null
    private val binding  get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScienceBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)

        getnews()
//        pageNum++
//        Log.d("MainActivity", "currPage : $pageNum")
//        Log.d("MainActivity", "currPage : $totalResults")

//        if(totalResults > size)
//        {
//            getnews()
//        }

    }


    private fun getnews() {
        val news = NewsObject.newsInstance.getNews("in","science")
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("System", news.toString())
                    val size = news.articles.size
                    val totalResults = news.totalResults
                    val recyclerView = binding.recyclerView
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = NewsAdapter(requireContext(), news.articles)

                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("System", "Error", t)
            }
        })
    }

}
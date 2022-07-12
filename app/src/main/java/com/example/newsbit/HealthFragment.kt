package com.example.newsbit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsbit.databinding.FragmentHealthBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HealthFragment : Fragment() {
    private var _binding : FragmentHealthBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHealthBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getnews()
    }
    private fun getnews() {
        val news = NewsObject.newsInstance.getNews("in","health")
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("System", news.toString())
                    val size = news.articles.size
                    val totalResults = news.totalResults
                    Log.d("MainActivity", "currPage : $size")
                    Log.d("MainActivity", "currPage : $totalResults")
                    //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    val recyclerView = binding.recyclerView
                    binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerView.adapter = NewsAdapter(requireContext(), news.articles)

                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("System", "Error", t)
            }
        })
    }

}
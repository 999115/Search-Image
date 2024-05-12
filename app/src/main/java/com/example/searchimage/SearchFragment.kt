package com.example.searchimage

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchimage.DTO.Document
import com.example.searchimage.DTO.ResponseData
import com.example.searchimage.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL
import kotlin.math.log

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    var searchDataList = mutableListOf<Document>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        binding.btSearch.setOnClickListener {
            val userSearchWord = binding.etSearch.text

            getResponseData(userSearchWord.toString())
            binding.layoutRecyclerview.isVisible = true
//            Thread({
//                setRecyclerview()
//                Log.d("thread", searchDataList[0].datetime)
//            })
        }
    }

    private fun getResponseData(searchWord: String) {
        val authKey = "KakaoAK 79274b9363e5ac637fd5884e7b4bbb32"
        val responseData = NetWorkClient.apiService.getData(authKey, searchWord)

        responseData.enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        searchDataList = it.documents
                    }
                    Log.d("response", searchDataList[0].datetime)
                    setRecyclerview()
                }
                Log.d("response", response.code().toString())
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                Log.d("response", "onFailure")
                call.cancel()
            }
        })

    }

    private fun setRecyclerview() {
        val adapter = RecyclerviewAdapter(searchDataList)
        binding.layoutRecyclerview.apply {
            layoutManager = GridLayoutManager(activity, 2)
            this.adapter = adapter
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
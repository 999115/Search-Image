package com.example.searchimage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchimage.DTO.Document
import com.example.searchimage.databinding.FragmentMyImageBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MyImageFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private val binding by lazy {
        FragmentMyImageBinding.inflate(layoutInflater)
    }

    private lateinit var searchDataList : MutableList<Document>

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

        searchDataList = mutableListOf( Document("etc","2021-09-17T20:55:33.000+09:00","쇼핑하우","https://shoppinghow.kakao.com/in/product?prodid=C5104559645",640,"http://shop.daumcdn.net/shophow/p/4559645.jpg?ut=20210917205616","https://search2.kakaocdn.net/argon/130x130_85_c/6CfNzGCwPSM",640))

        val adapter = RecyclerviewAdapter(searchDataList)

        binding.layoutRecyclerview.apply {
            layoutManager = GridLayoutManager(activity, 2)
            this.adapter = adapter
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
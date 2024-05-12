package com.example.searchimage

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchimage.DTO.Document
import com.example.searchimage.databinding.LayoutImageBinding
import java.net.URL

class RecyclerviewAdapter(private val dataList: MutableList<Document>) :
    RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            layout.setOnClickListener {

            }
            bind(dataList[position])
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(private val binding: LayoutImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val layout = binding.layoutImage

        fun bind(data: Document) {
            Thread(Runnable {
                val bitmap = BitmapFactory.decodeStream(URL(data.thumbnail_url).openStream())

                binding.ivThumbnailImg.setImageBitmap(bitmap)
                binding.tvSiteName.text = data.display_sitename
            }).start()

        }

    }
}
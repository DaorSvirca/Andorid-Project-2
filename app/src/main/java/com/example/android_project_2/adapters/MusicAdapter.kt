package com.example.android_project_2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cacttus.navigationdrawergr_3.R
import com.example.android_project_2.model.Music

class MusicAdapter(
    private var musics : List<Music>,
    private val onItemClick: (Music) -> Unit
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MusicAdapter.MusicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_music, parent, false)
        return MusicViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(musics[position])
    }


    override fun getItemCount() = musics.size

    fun updateMusics(newMusics : List<Music>) {
        musics = newMusics
        notifyDataSetChanged()
    }


    inner class MusicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeName: TextView = itemView.findViewById(R.id.musicTitle)

        fun bind(music: Music) {
            recipeName.text = music.title
            itemView.setOnClickListener { onItemClick(music) }
        }
    }

}
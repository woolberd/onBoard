package com.example.onboard.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onboard.databinding.ItemLinearBinding
import com.example.onboard.models.NoteAppModel

class NoteAppAdapter(val onItemClick: (model: NoteAppModel) -> Unit) :
    RecyclerView.Adapter<NoteAppAdapter.ViewHolder>() {

    private var list: List<NoteAppModel> = ArrayList()

    fun setList(list: List<NoteAppModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private var binding: ItemLinearBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnLongClickListener {
                onItemClick(list[adapterPosition])
                true
            }
        }

        fun onBind(model: NoteAppModel) {
            binding.cardView.setCardBackgroundColor(Color.parseColor(model.color))
            binding.bigTextView.text = model.title
            binding.smallTextView.text = model.description
            binding.textData.text = model.data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLinearBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
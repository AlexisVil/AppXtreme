package mx.com.evotae.appxtreme.main.tae

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*
import mx.com.evotae.appxtreme.R

class XTTaeAdapter(val context: Context, val taeList: List<TaeItem>) : RecyclerView.Adapter<XTTaeAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var taeTitle : TextView
        init {
            taeTitle = itemView.taeTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var  itemView = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taeTitle.text = taeList[position].toString()
    }

    override fun getItemCount(): Int {
        return taeList.size
    }
}
package mx.com.evotae.appxtreme.main.service.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ItemButtonBinding
import mx.com.evotae.appxtreme.main.service.model.XTServicesModel

class XTServiceAdapter(private val serviceList: List<XTServicesModel>, private val onClickListener: (XTServicesModel) -> Unit): RecyclerView.Adapter<XTServiceAdapter.XTServiceViewHolder>() {
    class XTServiceViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemButtonBinding.bind(view)
        fun render(serviceModel: XTServicesModel, onClickListener: (XTServicesModel) -> Unit){
            binding.tvName.text = serviceModel.name
            binding.tvId.text = serviceModel.id.toString()
            Glide.with(binding.ivPhoto.context).load(serviceModel.photo).into(binding.ivPhoto)

            itemView.setOnClickListener { onClickListener(serviceModel) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XTServiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return XTServiceViewHolder(layoutInflater.inflate(R.layout.item_button, parent, false))
    }

    override fun onBindViewHolder(holder: XTServiceViewHolder, position: Int) {
        val item = serviceList[position]
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = serviceList.size

}
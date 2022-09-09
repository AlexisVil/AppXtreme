package mx.com.evotae.appxtreme.main.service.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ItemButtonBinding
import mx.com.evotae.appxtreme.main.service.model.Services
import mx.com.evotae.appxtreme.main.service.model.ServicesProvider
import mx.com.evotae.appxtreme.main.tae.model.XTTaeModel

class ServiceAdapter(private val servicesList: List<Services>, private val onClickListener: (Services) -> Unit) :
    RecyclerView.Adapter<ServiceAdapter.ServicesViewHolder>() {
    class ServicesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemButtonBinding.bind(view)
        fun render(servicesModel: Services, onClickListener: (Services) -> Unit) {
            binding.tvName.text = servicesModel.name
            binding.tvId.text = servicesModel.id.toString()
            Glide.with(binding.ivPhoto.context).load(servicesModel.photo).into(binding.ivPhoto)
            itemView.setOnClickListener {
                onClickListener(servicesModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServicesViewHolder(layoutInflater.inflate(R.layout.item_button, parent, false))
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        val item = servicesList[position]
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = servicesList.size
}
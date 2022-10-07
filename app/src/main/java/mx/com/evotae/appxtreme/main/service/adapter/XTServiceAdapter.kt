package mx.com.evotae.appxtreme.main.service.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
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
            binding.ivPhoto.loadUrl(serviceModel.photo)
            itemView.setOnClickListener { onClickListener(serviceModel) }
        }
        fun ImageView.loadUrl(url: String){
            val imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry{
                    add(SvgDecoder(this@loadUrl.context))
                }
                .build()
            val request = ImageRequest.Builder(this.context)
                .crossfade(true)
                .memoryCacheKey(url)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .placeholderMemoryCacheKey(url)
                .placeholder(R.drawable.logo_640px)
                .error(R.drawable.logo_640px)
                .data(url)
                .target(this)
                .build()
            imageLoader.enqueue(request)
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
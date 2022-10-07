package mx.com.evotae.appxtreme.main.tae.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.util.CoilUtils
import com.bumptech.glide.Glide
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.ItemButtonBinding
import mx.com.evotae.appxtreme.main.tae.model.XTTaeModel

class XTTaeAdapter(
    private val taeList: List<XTTaeModel>,
    private val onClickListener: (XTTaeModel) -> Unit
) : RecyclerView.Adapter<XTTaeAdapter.XTTaeViewHolder>() {
    class XTTaeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemButtonBinding.bind(view)
        fun render(taeModel: XTTaeModel, onClickListener: (XTTaeModel) -> Unit) {
            binding.tvName.text = taeModel.name
            binding.tvId.text = taeModel.idCarrier.toString()
            binding.ivPhoto.loadUrl(taeModel.photo)
            itemView.setOnClickListener { onClickListener(taeModel) }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): XTTaeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return XTTaeViewHolder(layoutInflater.inflate(R.layout.item_button, parent, false))
    }

    override fun onBindViewHolder(holder: XTTaeViewHolder, position: Int) {
        val item = taeList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = taeList.size


}

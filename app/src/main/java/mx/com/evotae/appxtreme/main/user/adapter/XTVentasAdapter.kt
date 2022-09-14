package mx.com.evotae.appxtreme.main.user.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.evotae.appxtreme.R
import mx.com.evotae.appxtreme.databinding.DialogVentasBinding
import mx.com.evotae.appxtreme.databinding.ItemVentasBinding

class XTVentasAdapter(private val transactionsList: List<String>): RecyclerView.Adapter<XTVentasAdapter.XTVentasViewHolder>() {
    class XTVentasViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val binding = ItemVentasBinding.bind(view)
        fun render(objeto: String){
            binding.apply {
                tvVentas.text = objeto
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): XTVentasAdapter.XTVentasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return XTVentasViewHolder(layoutInflater.inflate(R.layout.item_ventas, parent, false))
    }

    override fun onBindViewHolder(holder: XTVentasAdapter.XTVentasViewHolder, position: Int) {
        val item = transactionsList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = transactionsList.size

}
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meshop.R

class ProductAdapter(private val productList: List<ProductItem>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    data class ProductItem(val productName: String, val imageResId: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = productList.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
        private val productNameTextView: TextView = itemView.findViewById(R.id.productNameTextView)

        fun bind(item: ProductItem) {
            productImageView.setImageResource(item.imageResId)
            productNameTextView.text = item.productName
        }
    }
}


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.meshop.R

class SearchActivity : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_search, container, false)

        val productview = listOf(
            ProductAdapter.ProductItem("Peely Orange", R.drawable.produk1,"Rp. 20000"),
            ProductAdapter.ProductItem("Cipak Koceak", R.drawable.produk2,"Rp. 12000"),
            ProductAdapter.ProductItem("Moskimchi", R.drawable.produk3,"Rp. 13500"),
            ProductAdapter.ProductItem("Sweet & Spacy", R.drawable.produk4,"Rp. 17000"),
            ProductAdapter.ProductItem("Sosis Frozen Koyama", R.drawable.produk5,"Rp. 32000"),
            ProductAdapter.ProductItem("Krezzo Chps", R.drawable.produk6,"Rp. 5000"),
            ProductAdapter.ProductItem("Corn Chips", R.drawable.produk7,"Rp. 8000"),
            ProductAdapter.ProductItem("Pika Caww", R.drawable.produk8,"Rp. 10000"),
            ProductAdapter.ProductItem("Papisang" ,R.drawable.produk9,"Rp. 11000"),
            ProductAdapter.ProductItem("Karai Basreng", R.drawable.produk10,"Rp. 10000"),
            ProductAdapter.ProductItem("Oseng Mercon", R.drawable.produk11,"Rp. 15000"),
            ProductAdapter.ProductItem("Sarjana Jamur", R.drawable.produk12,"Rp. 13000"),
            ProductAdapter.ProductItem("Rendang Tendewon", R.drawable.produk13,"Rp. 25000"),
            ProductAdapter.ProductItem("KualaMas Aceh Gayo", R.drawable.produk14,"Rp. 28000"),
            ProductAdapter.ProductItem("Bakso Sultan Masir", R.drawable.produk15,"Rp. 13000"),
            ProductAdapter.ProductItem("Cookies Crispy", R.drawable.produk16,"Rp. 17000"),
            ProductAdapter.ProductItem("Rengginang Bikin Senang", R.drawable.produk17,"Rp. 12500"),
            ProductAdapter.ProductItem("Kakarak Saltes Egg", R.drawable.produk18,"Rp. 13500")
        )
        recyclerView = view.findViewById(R.id.exploreview)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = ProductAdapter(productview)

        return view
    }


    private fun updateDots(currentPosition: Int) {
        val dotsCount = dotsLayout.childCount
        for (i in 0 until dotsCount) {
            val dot = dotsLayout.getChildAt(i) as ImageView
            dot.setImageResource(if (i == currentPosition) R.drawable.indicator_dot_selected else R.drawable.indicator_dot)
        }
    }
}

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.meshop.R

class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var recyclerView: RecyclerView

    private val images = listOf(R.drawable.karusel1, R.drawable.karusel2, R.drawable.karusel3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val productList = listOf(
            ProductAdapter.ProductItem("Peely Orange", R.drawable.produk1,"Rp. 20000"),
            ProductAdapter.ProductItem("Oseng Mercon", R.drawable.produk11,"Rp. 15000"),
            ProductAdapter.ProductItem("Rengginang Bikin Senang", R.drawable.produk17,"Rp. 12500"),
            ProductAdapter.ProductItem("Sosis Frozen Koyama", R.drawable.produk5,"Rp. 32000"),
            ProductAdapter.ProductItem("Rendang Tendewon", R.drawable.produk13,"Rp. 25000"),
            ProductAdapter.ProductItem("Papisang" ,R.drawable.produk9,"Rp. 11000")
        )
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = ProductAdapter(productList)

        viewPager = view.findViewById(R.id.viewPager)
        dotsLayout = view.findViewById(R.id.dotsLayout)

        val imageSliderAdapter = ImageSliderAdapter(requireContext(), images)
        viewPager.adapter = imageSliderAdapter

        // Add indicator dots
        addDotsIndicator()

        // Set up ViewPager2 onPageChangeListener to update the dots
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDots(position)
            }
        })

        return view
    }


    private fun addDotsIndicator() {
        val dots = arrayOfNulls<ImageView>(images.size)
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(8, 0, 8, 0)

        for (i in images.indices) {
            dots[i] = ImageView(requireContext())
            dots[i]?.setImageResource(R.drawable.indicator_dot)
            dots[i]?.layoutParams = params
            dotsLayout.addView(dots[i])
        }
    }

    private fun updateDots(currentPosition: Int) {
        val dotsCount = dotsLayout.childCount
        for (i in 0 until dotsCount) {
            val dot = dotsLayout.getChildAt(i) as ImageView
            dot.setImageResource(if (i == currentPosition) R.drawable.indicator_dot_selected else R.drawable.indicator_dot)
        }
    }
}

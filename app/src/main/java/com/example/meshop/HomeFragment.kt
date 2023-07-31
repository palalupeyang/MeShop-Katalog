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
import com.example.meshop.SearchActivity

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
            ProductAdapter.ProductItem("Case HP", R.drawable.case_1),
            ProductAdapter.ProductItem("Sarung Wadimor", R.drawable.produk_2),
            ProductAdapter.ProductItem("Cipcek Garut", R.drawable.produk_3),
            ProductAdapter.ProductItem("Olahan Kulit Jeruk", R.drawable.produk_4),
            ProductAdapter.ProductItem("Sepatu Wanita", R.drawable.produk_5),
            ProductAdapter.ProductItem("Sepatu Pria", R.drawable.produk_6)
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

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnSearchClickListener {
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }

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

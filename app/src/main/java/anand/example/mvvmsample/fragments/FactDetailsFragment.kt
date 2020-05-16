package anand.example.mvvmsample.fragments

import anand.example.mvvmsample.R
import anand.example.mvvmsample.databinding.FragmentFactDetailsBinding
import anand.example.mvvmsample.model.FactPalette
import anand.example.mvvmsample.model.Rows
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

/**
 * A simple [Fragment] subclass.
 */
class FactDetailsFragment : Fragment() {
    private lateinit var factItem: Rows
    private lateinit var dataBinding: FragmentFactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            factItem = FactDetailsFragmentArgs.fromBundle(it).factItem
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_fact_details, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.row = factItem
        factItem.imageHref?.let {
            setupBackgroundColor(it)
        }
    }

    private fun setupBackgroundColor(url: String) {
        Picasso.get().load(url).into(object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bitmap?.let {
                    Palette.from(it).generate { palette ->
                        val color = palette?.lightVibrantSwatch?.rgb ?: 0
                        val myPalette = FactPalette(color)
                        dataBinding.palette = myPalette
                    }
                }
            }

        })
    }
}
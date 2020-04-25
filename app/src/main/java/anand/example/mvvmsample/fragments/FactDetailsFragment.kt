package anand.example.mvvmsample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import anand.example.mvvmsample.R
import anand.example.mvvmsample.model.Rows
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_fact_details.*

/**
 * A simple [Fragment] subclass.
 */
class FactDetailsFragment : Fragment() {
    private lateinit var factItem: Rows

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
        return inflater.inflate(R.layout.fragment_fact_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(factItem) {
            imageHref?.let {
                Picasso.get().load(it).placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_download_error).into(factImage)
            }

            title?.let {
                factTitle.text = it
                activity?.title = it
            }

            description?.let {
                factDetail.text = it
            }
        }
    }
}
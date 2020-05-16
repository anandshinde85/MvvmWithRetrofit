package anand.example.mvvmsample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import anand.example.mvvmsample.R
import anand.example.mvvmsample.databinding.FragmentFactDetailsBinding
import anand.example.mvvmsample.model.Rows
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_fact_details.*

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
    }
}
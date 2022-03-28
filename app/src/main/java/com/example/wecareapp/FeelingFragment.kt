package com.example.wecareapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.retrofitwithpost.GetEventsVM
import com.example.wecareapp.model.EventGet
import com.example.wecareapp.recyclerview.RecyclerViewAdapter


class FeelingFragment : Fragment(){

    // TODO: Rename and change types of parameters
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var viewModel: GetEventsVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_feeling2, container, false)
        initRecyclerView(view)
        initViewModel()


        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FeelingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FeelingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun initRecyclerView(view:View){
        var recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(activity)

        recyclerViewAdapter= RecyclerViewAdapter()
        recyclerView.adapter=recyclerViewAdapter

    }
    fun initViewModel() {

        viewModel = ViewModelProvider(this).get( GetEventsVM::class.java)
        viewModel.getUserListObserverable().observe(viewLifecycleOwner, Observer<List<EventGet>?> {
            if(it == null) {

            } else {

                recyclerViewAdapter.EventList = it.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.EventList()
    }


}

fun <T> MutableLiveData<T>.observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<List<EventGet>?>) {

}
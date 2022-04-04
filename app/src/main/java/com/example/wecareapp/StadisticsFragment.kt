package com.example.wecareapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidplot.xy.*
import com.demo.retrofitwithpost.GetEventsVM
import com.example.wecareapp.model.Event
import com.example.wecareapp.model.EventGet
import com.example.wecareapp.model.EventList
import com.example.wecareapp.recyclerview.RecyclerViewAdapter
import com.example.wecareapp.viewmodel.CreateEventVM
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*


class StadisticsFragment : Fragment(){

    // TODO: Rename and change types of parameters

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

        val view=inflater.inflate(R.layout.fragment_stadistics, container, false)

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
            StadisticsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


    fun initViewModel() {

        viewModel = ViewModelProvider(this).get( GetEventsVM::class.java)
        viewModel.getUserListObserverable().observe(viewLifecycleOwner, Observer<List<EventGet>?> {
            if(it == null) {

            } else {
                var array =ArrayList<Int>()
                var domain=ArrayList<Int>()
                array.add(0)
                array.add(0)
                if(it.isNotEmpty())
                for(e in it){

                    var time=e.eventTime.subSequence(0, 2)
                    array.add(time.toString().toInt())
                    array.add(e.eventScore)
                }
                else{
                    array.add(10)
                    array.add(10)

                }
                array.add(0)
                array.add(array[array.size-2]+array[3])

                //Añado al plot
                updatechart(array,domain)

            }
        })
        viewModel.EventList()
    }

    private fun updatechart(array:ArrayList<Int>,domain:ArrayList<Int>) {
        val list: MutableList<Number> = array.toMutableList()
        val list1: MutableList<Number> = domain.toMutableList()


        val domainLabels = arrayOf<Number>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);
        val series1Number = arrayOf<Number>(3,5,8,5);
        val p=Arrays.asList(*series1Number)
        val q=Arrays.asList(*domain.toTypedArray() )
        val series1 : XYSeries = SimpleXYSeries(array,SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED
            ,"Emociones");
        val series1Format = LineAndPointFormatter(Color.BLUE,Color.BLACK,null,null)
        series1Format.setInterpolationParams(
            CatmullRomInterpolator.Params(100,
                CatmullRomInterpolator.Type.Uniform))

    }


}
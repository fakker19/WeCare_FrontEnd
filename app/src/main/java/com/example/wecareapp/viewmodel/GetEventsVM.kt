package com.demo.retrofitwithpost

import android.media.metrics.Event
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wecareapp.api.RetroInstance
import com.example.wecareapp.model.EventGet
import com.example.wecareapp.model.EventList
import com.example.wecareapp.model.UserList
import com.example.wecareapp.services.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetEventsVM: ViewModel() {


    var recyclerListData: MutableLiveData<UserList?>
    lateinit var recyclerListData2: MutableLiveData<List<EventGet>?>

    init {
        recyclerListData = MutableLiveData()
    }

    fun getUserListObserverable(): MutableLiveData<UserList?> {
        return recyclerListData
    }

    fun UserList() {

        val retroInstance =
            RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        val call = retroInstance.getUsersList()
        call.enqueue(object : Callback<UserList> {
            override fun onFailure(call: Call<UserList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<UserList>, response: Response<UserList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }
        })
    }

    fun EventList() {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        val call = retroInstance.getEventList()
        call.enqueue(object : Callback<List<EventGet>>{
            override fun onFailure(call: Call<List<EventGet>?>, t: Throwable) {
                recyclerListData2.postValue(null)
            }

            override fun onResponse(call: Call<List<EventGet>?>, response: Response<List<EventGet>>) {
                if(response.isSuccessful) {
                    recyclerListData2.postValue(response.body())
                } else {
                    recyclerListData2.postValue(null)
                }
            }
        })
    }

    /*fun searchUser(searchText: String) {

        val retroInstance = RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        val call = retroInstance.searchEvent(searchText)
        call.enqueue(object : Callback<Event>{
            override fun onFailure(call: Call<Event>, t: Throwable) {
                recyclerListData.postValue(null)
            }

            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                if(response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }
        })
    }
}

private fun <T> Call<T>.enqueue(callback: Callback<Event>) {

}
     */
}

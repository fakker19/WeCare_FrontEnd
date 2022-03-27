package com.example.wecareapp.model

data class Event (

    val eventName: String,
    val eventDescription: String,
    val eventScore: Int,
    val eventResult: String,
    val eventDetail: String,
    val patientId: Int
)
data class EventResponse (
    val eventId:Int
    )

data class EventGet (


    val eventId:Int,
    val eventName: String,
    val eventDescription: String,
    val eventScore: Int,
    val eventResult: String,
    val eventDetail: String,
    val eventDate: String,
    val eventTime: String
)
data class EventList (
    val data: List<EventGet>
)


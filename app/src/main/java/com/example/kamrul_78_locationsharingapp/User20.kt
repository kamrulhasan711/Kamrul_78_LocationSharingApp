package com.example.kamrul_78_locationsharingapp

import com.google.firebase.firestore.PropertyName

data class User20(
    val userId:String,
    @get:PropertyName("DisplayName")
    @set:PropertyName("DisplayName")
    var displayname:String="",


    @get:PropertyName("email")
    @set:PropertyName("email")
    var email:String ="",

    @get:PropertyName("location")
    @set:PropertyName("location")
    var location:String =""

){
    constructor():this("","","")
}


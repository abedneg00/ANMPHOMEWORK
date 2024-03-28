package com.example.studentapp.model
import com.google.gson.annotations.SerializedName
data class Drink(
    @SerializedName("name")
    var drinkName:String?,
    var type:String?,
    var flavors:List<String>?,
    var photoUrl:String?,
    var manufacturer:DrinkManufacturer?,
)

data class DrinkManufacturer(
    @SerializedName("name")
    var companyName:String?,
    var country:String?,
)
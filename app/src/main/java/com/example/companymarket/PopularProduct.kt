package com.example.companymarket

data class PopularProduct(
    val product_name : String,
    val product_price : Int,
    val product_status : Status,
    val product_content : List<Int>
)

enum class Status {
    Sale, Soldout, Reservation
}

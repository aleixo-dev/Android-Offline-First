package com.nicolas.androidofflinefirst.utils

fun String.formatDate(): String {

    val date = this.split("-", " ")
    return "${date[2]}/${date[1]}/${date[0]}"

}
package com.carlos.network

import android.net.Uri
import retrofit2.http.Url

fun String.getPage(url: String): Int?{
    val url = Uri.parse(url)
    val page = url.getQueryParameter("offset")
    return page?.toIntOrNull()
}
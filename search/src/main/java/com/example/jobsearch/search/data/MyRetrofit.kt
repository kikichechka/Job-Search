package com.example.jobsearch.search.data

import com.example.jobsearch.search.data.dto.AllDataDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

private const val BASE_URL = "https://drive.usercontent.google.com"

class MyRetrofit @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchData: SearchDataApi = retrofit.create(SearchDataApi::class.java)
}

interface SearchDataApi {
    @GET("/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getAllData(): Response<AllDataDto>
}

package com.example.diseomobile.api

import android.content.Context
import android.widget.Toast
import com.example.diseomobile.R
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import javax.inject.Inject

class DolarApiServiceImpl @Inject constructor(
) {

    fun getDolarPrice(
        context: Context,
        onSuccess: (List<DolarPrice>) -> Unit,
        onFail: () -> Unit,
        loadingFinished: () -> Unit,
        errorText : String
    ) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(
                context.getString(R.string.dolar_api_url)
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

        val service = retrofit.create(DolarAPIService::class.java)

        val call = service.getDolarPrice()

        call.enqueue(object : Callback<List<DolarPrice>> {
            override fun onResponse(response: Response<List<DolarPrice>>?, retrofit: Retrofit?) {
                loadingFinished()
                if (response?.body() != null) {
                    onSuccess(response.body())
                } else {
                    onFailure(Exception(errorText))
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, errorText + ": ${t?.message}", Toast.LENGTH_SHORT).show()
                onFail()
                loadingFinished()
            }
        })
    }
}
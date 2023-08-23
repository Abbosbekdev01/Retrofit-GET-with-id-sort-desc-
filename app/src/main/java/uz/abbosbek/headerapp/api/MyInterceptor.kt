package uz.abbosbek.headerapp.api

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requset = chain.request()
            .newBuilder()
//            .addHeader("Connect - Type", "application/json")
//            .addHeader("X-Platform", "Android")
//            .addHeader("X-Auth-Token", "123456789")
            .build()

        return chain.proceed(requset)
    }
}
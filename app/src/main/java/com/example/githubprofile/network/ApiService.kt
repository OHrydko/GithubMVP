package com.example.githubprofile.network

import com.example.githubprofile.model.Repos
import com.example.githubprofile.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{name}")
    fun getUser(@Path("name") name: String): Observable<User>

    @GET("users/{name}/repos")
    fun getRepos(@Path("name") name: String): Observable<List<Repos>>
}
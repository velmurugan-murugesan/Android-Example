package com.example.kotlincoroutines

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.util.*

class MockInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val method = chain.request().method.toUpperCase(Locale.ROOT)
        val uri = chain.request().url.toUri().toString()
        Thread.sleep(500)
        val response = when {
            uri.contains("movielist_401") -> {
                Response.Builder().code(401).protocol(Protocol.HTTP_2).message("401 Unauthorized")
                    .request(chain.request()).body(
                        "".toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
                    ).addHeader("content-type", "application/json").build()
            }
            uri.contains("movielist_200") -> {
                Response.Builder().code(200).protocol(Protocol.HTTP_2).message(movielist)
                    .request(chain.request()).body(
                        movielist.toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
                    ).addHeader("content-type", "application/json").build()
            }

            else -> Response.Builder().code(200).protocol(Protocol.HTTP_2).message(movielist)
                .request(chain.request()).body(
                    movielist.toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
                ).addHeader("content-type", "application/json").build()
        }

       /* response = Response.Builder().code(401).protocol(Protocol.HTTP_2).message(responseString)
            .request(chain.request()).body(
                responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
            ).addHeader("content-type", "application/json").build()*/

        return response
    }

    private val movielist = """
        [{
                "category": "Latest",
                "imageUrl": "https://howtodoandroid.com/images/coco.jpg",
                "name": "Coco",
                "desc": "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar"
            },
            {
                "category": "Latest",
                "imageUrl": "https://howtodoandroid.com/images/terminator_2.jpg",
                "name": "Terminator 2: Judgment Day 3D",
                "desc": "Similar to Cameron's Titanic 3D, Lightstorm Entertainment oversaw the work on the 3D version of Terminator 2, which took nearly a year to finish."
            },
            {
                "category": "Latest",
                "imageUrl": "https://howtodoandroid.com/images/dunkirk.jpg",
                "name": "Dunkirk",
                "desc": "Dunkirk is a 2017 war film written, directed, and co-produced by Christopher Nolan that depicts the Dunkirk evacuation of World War II. "
            },
            {
                "category": "Favorites",
                "imageUrl": "https://howtodoandroid.com/images/lion.png",
                "name": "Lion",
                "desc": "Lion is a 2016 Australian biographical drama film directed by Garth Davis (in his feature debut) and written by Luke Davies, based on the non-fiction book A Long Way Home by Saroo Brierley."
            },
            {
                "category": "High Rated",
                "imageUrl": "https://howtodoandroid.com/images/star_war.jpg",
                "name": "Star Wars: The Last Jedi",
                "desc": "Star Wars: The Last Jedi (also known as Star Wars: Episode VIII – The Last Jedi) is a 2017 American epic space opera film written and directed by Rian Johnson."
            },
            {
                "category": "High Rated",
                "imageUrl": "https://howtodoandroid.com/images/thor_ragnarok.jpg",
                "name": "Thor: Ragnarok",
                "desc": "Thor: Ragnarok is a 2017 American superhero film based on the Marvel Comics character Thor, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures."
            },
            {
                "category": "High Rated",
                "imageUrl": "https://howtodoandroid.com/images/blade_runner_2049.jpg",
                "name": "Blade Runner 2049",
                "desc": "Blade Runner 2049 is a 2017 American science fiction film directed by Denis Villeneuve and written by Hampton Fancher and Michael Green. "
            },
            {
                "category": "High Rated",
                "imageUrl": "https://howtodoandroid.com/images/borg_mcenroe.jpg",
                "name": "Borg McEnroe",
                "desc": "Borg McEnroe also known as Borg vs McEnroe, is a 2017 internationally co-produced multi-language biographical sports drama film focusing on the famous rivalry between famous tennis players "
            },
            {
                "category": "High Rated",
                "imageUrl": "https://howtodoandroid.com/images/wonder.jpg",
                "name": "Wonder",
                "desc": "Wonder is a 2017 American drama film directed by Stephen Chbosky and written by Jack Thorne , Steve Conrad and Stephen Chbosky based on the 2012 novel of the same name by R.J. Palacio."
            }
        ]
    """.trimIndent()



}
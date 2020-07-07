package com.howtodoandroid.masterdetailsmotionlayoutanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val ANIM_DURATION = 250

enum class TRANSITIONSTATE {
    FIRST_SECOND,
    SECOND_THIRD,
    THIRD_SECOND,
    SECOND_FIRST
}

class MainActivity : AppCompatActivity() {

    lateinit var currentTransitionState: TRANSITIONSTATE

    var adapter: MovieAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MovieAdapter()
        recyclerviewMovies.adapter = adapter

        animatedView.setTransitionListener(
            object: MotionLayout.TransitionListener {
                override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

                }

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

                    when(currentTransitionState) {
                        TRANSITIONSTATE.FIRST_SECOND -> {
                            currentTransitionState = TRANSITIONSTATE.SECOND_THIRD
                            p0?.setTransition(p1, R.id.third_set)
                            p0?.setTransitionDuration(ANIM_DURATION)
                            p0?.transitionToEnd()
                        }

                        TRANSITIONSTATE.THIRD_SECOND -> {
                            currentTransitionState = TRANSITIONSTATE.SECOND_FIRST
                            p0?.setTransition(p1, R.id.first_set)
                            p0?.setTransitionDuration(ANIM_DURATION)
                            p0?.transitionToEnd()
                        }

                        TRANSITIONSTATE.SECOND_FIRST -> {
                            android_root.transitionToStart()
                        }

                        else -> {

                        }
                    }
                }
            }
        )

        adapter?.addItemClickListener(object : ListClickListener<Movie>{
            override fun onItemClick(data: Movie, height: Int, y: Int) {
                Glide
                    .with(this@MainActivity)
                    .load(data.image)
                    .centerCrop()
                    .into(imgCover)

                textTitle.text = data.title

                animatedView.also {
                    val set = it.getConstraintSet(R.id.first_set)
                    set.setMargin(R.id.imgCover, ConstraintSet.TOP, y)
                    set.setVisibility(R.id.imgCover, ConstraintSet.VISIBLE)
                    set.setMargin(R.id.textTitle, ConstraintSet.TOP, y)
                    set.applyTo(it)
                    android_root.transitionToEnd()
                    currentTransitionState = TRANSITIONSTATE.FIRST_SECOND
                    it.setTransition(R.id.first_set, R.id.second_set)
                    it.setTransitionDuration(ANIM_DURATION)
                    it.transitionToEnd()
                }
            }
        })

        back_arrow.setOnClickListener {
            currentTransitionState = TRANSITIONSTATE.THIRD_SECOND
            animatedView.setTransition(R.id.third_set,R.id.second_set)
            animatedView.setTransitionDuration(ANIM_DURATION)
            animatedView.transitionToEnd()
        }


        ClientApi.client?.movies?.enqueue(object : Callback<List<Movie>>{
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Log.d("Error", t.message)
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if(response.isSuccessful) {

                    val movieList = response.body()
                    movieList?.let {
                        adapter?.updateMovieList(it)
                    }

                }
            }
        })

    }
}
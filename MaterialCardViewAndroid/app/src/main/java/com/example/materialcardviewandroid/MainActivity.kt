package com.example.materialcardviewandroid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var adapterMovie: AdapterMovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapterMovie = AdapterMovie()

        recyclerviewMovies.adapter = adapterMovie


        val apiInterface = ApiInterface.create().getMovies()


        apiInterface.enqueue( object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>?, response: Response<List<Movie>>?) {

                if(response?.body() != null)
                    adapterMovie.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<List<Movie>>?, t: Throwable?) {

            }
        })

        /*materialCardView.isCheckable = true

        materialCardView.setOnLongClickListener {
            materialCardView.isChecked = !materialCardView.isChecked
            return@setOnLongClickListener true
        }


        val swipeDismissBehavior: SwipeDismissBehavior<View> = SwipeDismissBehavior<View>()
        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END)

        val coordinatorParams = materialCardView.layoutParams as CoordinatorLayout.LayoutParams

        coordinatorParams.behavior = swipeDismissBehavior

        swipeDismissBehavior.setListener(object : SwipeDismissBehavior.OnDismissListener {
            override fun onDismiss(view: View?) {
                Snackbar.make(materialCardView, "Material Cardview dismissed", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Undo") { v -> resetCard(materialCardView) }.show()
            }

            override fun onDragStateChanged(state: Int) {
                onDragStateChanged(state, materialCardView)
            }
        })*/

    }

    private fun onDragStateChanged(state: Int, cardContentLayout: MaterialCardView) {
        when (state) {
            SwipeDismissBehavior.STATE_DRAGGING, SwipeDismissBehavior.STATE_SETTLING -> cardContentLayout.isDragged =
                true
            SwipeDismissBehavior.STATE_IDLE -> cardContentLayout.isDragged = false
            else -> {
            }
        }
    }

    private fun resetCard(cardContentLayout: MaterialCardView) {
        val params = cardContentLayout
            .layoutParams as CoordinatorLayout.LayoutParams
        params.setMargins(0, 0, 0, 0)
        cardContentLayout.alpha = 1.0f
        cardContentLayout.requestLayout()
    }

}
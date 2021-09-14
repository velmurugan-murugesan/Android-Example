package com.example.navigationrailexample

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.navigationrailexample.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, InboxFragment(), "InboxFragment")
        ft.commit()


        mainBinding.navigationRail.headerView?.findViewById<FloatingActionButton>(R.id.fabMain)?.setOnClickListener {
            Toast.makeText(this, "Create a new mail", Toast.LENGTH_SHORT).show()
        }

        val inboxBadge = mainBinding.navigationRail.getOrCreateBadge(R.id.inbox)
        inboxBadge.isVisible = true
        inboxBadge.number = 56
        inboxBadge.badgeGravity = BadgeDrawable.TOP_END
        inboxBadge.backgroundColor = Color.GRAY
        inboxBadge.badgeTextColor = Color.WHITE
        inboxBadge.maxCharacterCount = 2
        inboxBadge.verticalOffset = 10
        inboxBadge.horizontalOffset = 10

        mainBinding.navigationRail.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inbox -> {
                    // Respond to navigation item 1 click
                    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                    ft.replace(R.id.container, InboxFragment(), "InboxFragment")
                    ft.commit()
                    true
                }
                R.id.sent -> {
                    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                    ft.replace(R.id.container, SentFragment(), "SentFragment")
                    ft.commit()
                    true
                }
                R.id.draft -> {
                    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                    ft.replace(R.id.container, DraftFragment(), "SentFragment")
                    ft.commit()
                    true
                }
                R.id.trash -> {
                    val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                    ft.replace(R.id.container, TrashFragment(), "SentFragment")
                    ft.commit()
                    true
                }
                else -> false
            }
        }

    }
}
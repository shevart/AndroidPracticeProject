package com.shevart.androidpracticeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shevart.androidpracticeproject.screen.postslist.PostsListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // as simple as possible
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, PostsListFragment())
            .commit()
    }
}

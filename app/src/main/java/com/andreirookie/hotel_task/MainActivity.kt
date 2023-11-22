package com.andreirookie.hotel_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andreirookie.hotel_task.di.ActivityComponentHolder
import com.andreirookie.hotel_task.di.appComponent
import com.andreirookie.hotel_task.ui.hotel.HotelScreenFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityComponentHolder.getComponent(appComponent).inject(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, HotelScreenFragment())
            .addToBackStack(HotelScreenFragment.TAG)
            .commit()
    }
}
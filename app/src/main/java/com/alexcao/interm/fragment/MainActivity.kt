package com.alexcao.interm.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                R.id.fragment_container_view,
                NewFragment()
            ).commit()
        }

        button = findViewById(R.id.button)

        button.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container_view,
                RedFragment()
            ).commit()
        }

    }
}

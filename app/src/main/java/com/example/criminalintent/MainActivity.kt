package com.example.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

private const val TAG = "MAIN_ACTIVITY"

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // fragment 확인
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            // fragment instantiate
            val fragment = CrimeListFragment.newInstance()
            // manager에 add
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onCrimeSelected(crimeId: UUID) {
        Log.d(TAG, "MainActivity.onCrimeSelected: $crimeId")
        val fragment = CrimeFragment.newInstance(crimeId)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null).commit()
    }
}
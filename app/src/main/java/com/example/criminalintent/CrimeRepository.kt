package com.example.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.criminalintent.database.CrimeDataBase
import java.util.UUID

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context) {

    private val database: CrimeDataBase =
        Room.databaseBuilder(context.applicationContext, CrimeDataBase::class.java, DATABASE_NAME)
            .build()

    private val crimeDao = database.crimeDao()

    fun getCrimes() : LiveData<List<Crime>> = crimeDao.getCrimes()

    fun getCrime(id:UUID): LiveData<Crime?> = crimeDao.getCrime(id)

    // singleton
    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalArgumentException("CrimeRepository must be initialized")
        }
    }
}
package com.bangkit.faniabdullah_jetpack.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity

@Database(entities = [MovieEntity::class, TvShowsEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "Movie-db"
                )
                    .build().apply { INSTANCE = this }
            }
    }
}
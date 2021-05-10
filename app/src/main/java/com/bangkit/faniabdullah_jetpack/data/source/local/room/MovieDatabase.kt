package com.bangkit.faniabdullah_jetpack.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.MovieEntity
import com.bangkit.faniabdullah_jetpack.data.source.local.entity.TvShowsEntity

@Database(entities = [MovieEntity::class, TvShowsEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}
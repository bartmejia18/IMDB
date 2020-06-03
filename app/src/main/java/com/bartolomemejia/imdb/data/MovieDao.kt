package com.bartolomemejia.imdb.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bartolomemejia.imdb.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * from movies where isFavorite = 0 and watchLater = 0")
    fun getCache(): List<Movie>

    @Query("DELETE from movies where isFavorite = 0 and watchLater = 0")
    suspend fun deleteCache()

    @Query("SELECT * from movies where isFavorite = 1")
    fun getFavorites(): List<Movie>

    @Query("SELECT * from movies where watchLater = 1")
    fun getWatchLaterList(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("DELETE from movies where movieId = :id and isFavorite = 1")
    suspend fun deleteMovieFromFavoritesBy(id: Int)
}
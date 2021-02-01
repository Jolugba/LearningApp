package com.tinuade.learningapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tinuade.learningapp.data.entities.Subject

@Dao
interface SubjectsDao {

    @Query("SELECT * FROM Subject WHERE id = :id")
    fun getSubject(id: Int): LiveData<Subject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSubjects(subject: List<Subject>?)

    @Query("select * from SUBJECT")
    fun fetchAllSubjects(): LiveData<List<Subject>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(subject: Subject)


}
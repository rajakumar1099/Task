package com.app.zohotask.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.zohotask.model.DataModel

@Dao
interface AppDao {

    @Query("SELECT * FROM repositories")
    fun getAllRecords(): LiveData<List<DataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(dataModel: List<DataModel>)

    @Query("DELETE FROM repositories")
    fun deleteAllRecords()
}
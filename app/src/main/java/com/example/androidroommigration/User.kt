package com.example.androidroommigration

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    val email: String,
    val username: String,
    @ColumnInfo(name = "created", defaultValue = "0") /* only static value as default */
    val created: Long /* new field */ = System.currentTimeMillis() /* this not work as default value */
    // default value is necessary to migration to add new values for the created column
)

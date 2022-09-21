package com.example.androidroommigration

import androidx.room.*
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [User::class],
    version = 3 /* change of the field "created" to "createdAt"*/,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = UserDatabase.Migration2To3::class),
    ]
)
abstract class UserDatabase : RoomDatabase() {

    abstract val dao: UserDao

    // Possible modifications with AutoMigrationSpec:
    //@DeleteColumn
    //@DeleteTable
    //@RenameTable
    @RenameColumn(tableName = "User", fromColumnName = "created", toColumnName = "createdAt")
    class Migration2To3: AutoMigrationSpec
}
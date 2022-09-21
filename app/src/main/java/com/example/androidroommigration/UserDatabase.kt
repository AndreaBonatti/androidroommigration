package com.example.androidroommigration

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [User::class, School::class],
    version = 4 /* add a new entity/table School */,
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
    class Migration2To3 : AutoMigrationSpec

    companion object {
        val migration3To4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // this is useful to all the operations that AutoMigrationSpec can not do
                // like the creation of a new table
                database.execSQL("CREATE TABLE IF NOT EXISTS school(name CHAR NOT NULL PRIMARY KEY)")
            }
        }
    }
}
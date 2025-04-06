package com.example.viaja.dataBase

import com.example.viaja.entity.Travel
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.viaja.dao.TravelDao
import com.example.viaja.dao.UserDao
import com.example.viaja.entity.User

@Database(
    entities = [User::class, Travel::class],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun travelDao(): TravelDao

    companion object {
        @Volatile
        private var Instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDataBase::class.java, "user_database")
                    .addMigrations(MIGRATION_1_2)  // Aqui você adiciona a migração
                    .build()
                    .also { Instance = it }
            }
        }

        // Exemplo de migração de versão 1 para 2
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Comando SQL para criar a nova tabela
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `travel` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `destino` TEXT NOT NULL, `tipoViagem` TEXT NOT NULL, `dataInicio` TEXT NOT NULL, `dataFinal` TEXT NOT NULL, `orcamento` REAL NOT NULL)"
                )
            }
        }
    }
}

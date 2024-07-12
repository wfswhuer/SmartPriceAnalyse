package com.quyunshuo.androidbaseframemvvm.common.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import android.content.Context
import com.google.gson.Gson
import com.quyunshuo.androidbaseframemvvm.common.R
import java.io.InputStreamReader

data class DatabaseConfig(val db_url: String, val db_user: String, val db_password: String)

class MySQLDatabaseHelper(private val context: Context) {

    private val config: DatabaseConfig by lazy {
        val inputStream = context.resources.openRawResource(R.raw.database_config)
        val reader = InputStreamReader(inputStream)
        Gson().fromJson(reader, DatabaseConfig::class.java)
    }

    suspend fun getConnection(): Connection? {
        return withContext(Dispatchers.IO) {
            try {
                Class.forName("com.mysql.jdbc.Driver")
                DriverManager.getConnection(config.db_url, config.db_user, config.db_password)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    suspend fun queryDatabase(query: String): ResultSet? {
        return withContext(Dispatchers.IO) {
            var connection: Connection? = null
            var statement: Statement? = null
            try {
                connection = getConnection()
                statement = connection?.createStatement()
                statement?.executeQuery(query)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } finally {
                statement?.close()
                connection?.close()
            }
        }
    }
}

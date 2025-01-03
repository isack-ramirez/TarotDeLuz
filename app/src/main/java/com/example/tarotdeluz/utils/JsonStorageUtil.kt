package com.example.tarotdeluz.utils

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime

/**
 * Utility class for handling JSON storage operations
 */
class JsonStorageUtil(private val context: Context) {
    private val TAG = "JsonStorageUtil"

    /**
     * Saves data to internal storage
     */
    fun <T> saveToInternalStorage(data: T, fileName: String) {
        try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { fos ->
                val jsonString = gson.toJson(data)
                Log.d(TAG, "Saving to $fileName: $jsonString")
                fos.write(jsonString.toByteArray())
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error saving to internal storage", e)
        }
    }

    /**
     * Reads data from internal storage
     */
    fun <T> readFromInternalStorage(fileName: String, classOfT: Class<T>): T? {
        return try {
            context.openFileInput(fileName).use { fis ->
                val jsonString = fis.bufferedReader().use { it.readText() }
                Log.d(TAG, "Reading from $fileName: $jsonString")
                gson.fromJson(jsonString, classOfT)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error reading from internal storage", e)
            null
        }
    }

    /**
     * Checks if a file exists in internal storage
     */
    fun internalFileExists(fileName: String): Boolean {
        val exists = context.getFileStreamPath(fileName).exists()
        Log.d(TAG, "Checking if $fileName exists: $exists")
        return exists
    }

    /**
     * Clears contents of a file in internal storage
     */
    fun clearInternalFile(fileName: String) {
        try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { fos ->
                Log.d(TAG, "Clearing file: $fileName")
                fos.write("".toByteArray())
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error clearing file", e)
        }
    }

    private val gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .create()
    }
}

/**
 * Adapter for converting LocalDateTime to/from JSON
 */
private class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {
    override fun write(out: JsonWriter, value: LocalDateTime) {
        out.value(value.toString())
    }

    override fun read(reader: JsonReader): LocalDateTime {
        return LocalDateTime.parse(reader.nextString())
    }
}

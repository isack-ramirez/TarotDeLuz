package com.example.tarotdeluz.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TarotCard(
    var drawnAtTime: LocalDateTime,
    val cardType: TarotCardType,
    val cardArt: String
) {
    fun getName(context: Context): String = context.getString(cardType.nameResId)
    
    fun getDescription(context: Context): String = context.getString(cardType.descriptionResId)

    /**
     * Converts the TarotCard instance to a JSON string
     */
    fun toJson(): String = gsonWithDateTime().toJson(this)

    companion object {
        private fun gsonWithDateTime(): Gson {
            return GsonBuilder()
                .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
                .create()
        }

        /**
         * Creates a TarotCard instance from a JSON string
         */
        fun fromJson(json: String): TarotCard? = try {
            gsonWithDateTime().fromJson(json, TarotCard::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        /**
         * Draws a random card
         */
        fun drawRandom(currentTime: LocalDateTime = LocalDateTime.now()): TarotCard {
            val cardType = TarotCardType.random()
            // TODO: Replace with actual card art resource ID or URL
            val cardArt = "card_art_placeholder"
            return TarotCard(currentTime, cardType, cardArt)
        }
    }
}

class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun write(out: JsonWriter, value: LocalDateTime?) {
        if (value == null) {
            out.nullValue()
        } else {
            out.value(formatter.format(value))
        }
    }

    override fun read(reader: JsonReader): LocalDateTime? {
        val value = reader.nextString()
        return LocalDateTime.parse(value, formatter)
    }
}

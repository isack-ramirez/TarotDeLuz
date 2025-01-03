package com.example.tarotdeluz.ui.Daily

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tarotdeluz.models.TarotCard
import com.example.tarotdeluz.utils.JsonStorageUtil
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DailyViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "DailyViewModel"
    private val jsonStorage = JsonStorageUtil(getApplication<Application>().applicationContext)
    private val DAILY_CARD_FILE = "tarot_de_luz_daily_card.json"

    private val _cardInfo = MutableLiveData<String>()
    private val _isDrawEnabled = MutableLiveData<Boolean>()
    val cardInfo: LiveData<String> = _cardInfo
    val isDrawEnabled: LiveData<Boolean> = _isDrawEnabled

    init {
        _cardInfo.value = ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkSavedCard() {
        Log.d(TAG, "Checking for saved card")
        
        // If no file exists, enable drawing
        if (!jsonStorage.internalFileExists(DAILY_CARD_FILE)) {
            Log.d(TAG, "No saved card file exists")
            enableDrawing()
            return
        }

        // Try to read the saved card
        val savedCard = jsonStorage.readFromInternalStorage(DAILY_CARD_FILE, TarotCard::class.java)
        if (savedCard == null) {
            Log.d(TAG, "Could not read saved card")
            enableDrawing()
            return
        }

        // Check if the card was drawn today
        val now = LocalDateTime.now()
        val cardDate = savedCard.drawnAtTime.toLocalDate()
        val today = now.toLocalDate()
        
        Log.d(TAG, "Card drawn at: ${savedCard.drawnAtTime}")
        Log.d(TAG, "Current time: $now")
        Log.d(TAG, "Card date: $cardDate")
        Log.d(TAG, "Today's date: $today")

        if (cardDate.isEqual(today)) {
            // Card is still valid for today
            Log.d(TAG, "Card is still valid for today")
            displayCard(savedCard)
            disableDrawing()
        } else {
            // It's a new day, clear the old card
            Log.d(TAG, "Card is from a different day, clearing")
            jsonStorage.clearInternalFile(DAILY_CARD_FILE)
            enableDrawing()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun performDrawDaily() {
        // Check if there's a valid card first
        if (!_isDrawEnabled.value!!) {
            Log.d(TAG, "Draw disabled, checking saved card")
            checkSavedCard()
            return
        }

        // Draw and save new card
        Log.d(TAG, "Drawing new card")
        val newCard = TarotCard.drawRandom(LocalDateTime.now())
        displayCard(newCard)
        saveCard(newCard)
        disableDrawing()
    }

    private fun displayCard(card: TarotCard) {
        val context = getApplication<Application>().applicationContext
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        
        val formattedInfo = buildString {
            appendLine("Card Name: ${card.getName(context)}")
            appendLine("Description: ${card.getDescription(context)}")
            appendLine("Drawn At: ${card.drawnAtTime.format(dateFormatter)}")
        }
        
        Log.d(TAG, "Displaying card: $formattedInfo")
        _cardInfo.value = formattedInfo
    }

    private fun saveCard(card: TarotCard) {
        Log.d(TAG, "Saving card drawn at ${card.drawnAtTime}")
        jsonStorage.saveToInternalStorage(card, DAILY_CARD_FILE)
    }

    private fun enableDrawing() {
        _isDrawEnabled.value = true
        _cardInfo.value = ""
    }

    private fun disableDrawing() {
        _isDrawEnabled.value = false
    }
}
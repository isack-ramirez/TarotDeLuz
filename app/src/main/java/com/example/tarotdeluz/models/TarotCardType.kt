package com.example.tarotdeluz.models

import com.example.tarotdeluz.R
import java.security.SecureRandom

enum class TarotCardType(val nameResId: Int, val descriptionResId: Int) {
    // Major Arcana
    FOOL(R.string.card_fool_name, R.string.card_fool_desc),
    MAGICIAN(R.string.card_magician_name, R.string.card_magician_desc),
    HIGH_PRIESTESS(R.string.card_high_priestess_name, R.string.card_high_priestess_desc),
    EMPRESS(R.string.card_empress_name, R.string.card_empress_desc),
    EMPEROR(R.string.card_emperor_name, R.string.card_emperor_desc),
    HIEROPHANT(R.string.card_hierophant_name, R.string.card_hierophant_desc),
    LOVERS(R.string.card_lovers_name, R.string.card_lovers_desc),
    CHARIOT(R.string.card_chariot_name, R.string.card_chariot_desc),
    STRENGTH(R.string.card_strength_name, R.string.card_strength_desc),
    HERMIT(R.string.card_hermit_name, R.string.card_hermit_desc),
    WHEEL_OF_FORTUNE(R.string.card_wheel_of_fortune_name, R.string.card_wheel_of_fortune_desc),
    JUSTICE(R.string.card_justice_name, R.string.card_justice_desc),
    HANGED_MAN(R.string.card_hanged_man_name, R.string.card_hanged_man_desc),
    DEATH(R.string.card_death_name, R.string.card_death_desc),
    TEMPERANCE(R.string.card_temperance_name, R.string.card_temperance_desc),
    DEVIL(R.string.card_devil_name, R.string.card_devil_desc),
    TOWER(R.string.card_tower_name, R.string.card_tower_desc),
    STAR(R.string.card_star_name, R.string.card_star_desc),
    MOON(R.string.card_moon_name, R.string.card_moon_desc),
    SUN(R.string.card_sun_name, R.string.card_sun_desc),
    JUDGEMENT(R.string.card_judgement_name, R.string.card_judgement_desc),
    WORLD(R.string.card_world_name, R.string.card_world_desc),

    // Wands
    ACE_OF_WANDS(R.string.card_ace_wands_name, R.string.card_ace_wands_desc),
    TWO_OF_WANDS(R.string.card_two_wands_name, R.string.card_two_wands_desc),
    THREE_OF_WANDS(R.string.card_three_wands_name, R.string.card_three_wands_desc),
    FOUR_OF_WANDS(R.string.card_four_wands_name, R.string.card_four_wands_desc),
    FIVE_OF_WANDS(R.string.card_five_wands_name, R.string.card_five_wands_desc),
    SIX_OF_WANDS(R.string.card_six_wands_name, R.string.card_six_wands_desc),
    SEVEN_OF_WANDS(R.string.card_seven_wands_name, R.string.card_seven_wands_desc),
    EIGHT_OF_WANDS(R.string.card_eight_wands_name, R.string.card_eight_wands_desc),
    NINE_OF_WANDS(R.string.card_nine_wands_name, R.string.card_nine_wands_desc),
    TEN_OF_WANDS(R.string.card_ten_wands_name, R.string.card_ten_wands_desc),
    PAGE_OF_WANDS(R.string.card_page_wands_name, R.string.card_page_wands_desc),
    KNIGHT_OF_WANDS(R.string.card_knight_wands_name, R.string.card_knight_wands_desc),
    QUEEN_OF_WANDS(R.string.card_queen_wands_name, R.string.card_queen_wands_desc),
    KING_OF_WANDS(R.string.card_king_wands_name, R.string.card_king_wands_desc),

    // Cups
    ACE_OF_CUPS(R.string.card_ace_cups_name, R.string.card_ace_cups_desc),
    TWO_OF_CUPS(R.string.card_two_cups_name, R.string.card_two_cups_desc),
    THREE_OF_CUPS(R.string.card_three_cups_name, R.string.card_three_cups_desc),
    FOUR_OF_CUPS(R.string.card_four_cups_name, R.string.card_four_cups_desc),
    FIVE_OF_CUPS(R.string.card_five_cups_name, R.string.card_five_cups_desc),
    SIX_OF_CUPS(R.string.card_six_cups_name, R.string.card_six_cups_desc),
    SEVEN_OF_CUPS(R.string.card_seven_cups_name, R.string.card_seven_cups_desc),
    EIGHT_OF_CUPS(R.string.card_eight_cups_name, R.string.card_eight_cups_desc),
    NINE_OF_CUPS(R.string.card_nine_cups_name, R.string.card_nine_cups_desc),
    TEN_OF_CUPS(R.string.card_ten_cups_name, R.string.card_ten_cups_desc),
    PAGE_OF_CUPS(R.string.card_page_cups_name, R.string.card_page_cups_desc),
    KNIGHT_OF_CUPS(R.string.card_knight_cups_name, R.string.card_knight_cups_desc),
    QUEEN_OF_CUPS(R.string.card_queen_cups_name, R.string.card_queen_cups_desc),
    KING_OF_CUPS(R.string.card_king_cups_name, R.string.card_king_cups_desc),

    // Swords
    ACE_OF_SWORDS(R.string.card_ace_swords_name, R.string.card_ace_swords_desc),
    TWO_OF_SWORDS(R.string.card_two_swords_name, R.string.card_two_swords_desc),
    THREE_OF_SWORDS(R.string.card_three_swords_name, R.string.card_three_swords_desc),
    FOUR_OF_SWORDS(R.string.card_four_swords_name, R.string.card_four_swords_desc),
    FIVE_OF_SWORDS(R.string.card_five_swords_name, R.string.card_five_swords_desc),
    SIX_OF_SWORDS(R.string.card_six_swords_name, R.string.card_six_swords_desc),
    SEVEN_OF_SWORDS(R.string.card_seven_swords_name, R.string.card_seven_swords_desc),
    EIGHT_OF_SWORDS(R.string.card_eight_swords_name, R.string.card_eight_swords_desc),
    NINE_OF_SWORDS(R.string.card_nine_swords_name, R.string.card_nine_swords_desc),
    TEN_OF_SWORDS(R.string.card_ten_swords_name, R.string.card_ten_swords_desc),
    PAGE_OF_SWORDS(R.string.card_page_swords_name, R.string.card_page_swords_desc),
    KNIGHT_OF_SWORDS(R.string.card_knight_swords_name, R.string.card_knight_swords_desc),
    QUEEN_OF_SWORDS(R.string.card_queen_swords_name, R.string.card_queen_swords_desc),
    KING_OF_SWORDS(R.string.card_king_swords_name, R.string.card_king_swords_desc),

    // Pentacles
    ACE_OF_PENTACLES(R.string.card_ace_pentacles_name, R.string.card_ace_pentacles_desc),
    TWO_OF_PENTACLES(R.string.card_two_pentacles_name, R.string.card_two_pentacles_desc),
    THREE_OF_PENTACLES(R.string.card_three_pentacles_name, R.string.card_three_pentacles_desc),
    FOUR_OF_PENTACLES(R.string.card_four_pentacles_name, R.string.card_four_pentacles_desc),
    FIVE_OF_PENTACLES(R.string.card_five_pentacles_name, R.string.card_five_pentacles_desc),
    SIX_OF_PENTACLES(R.string.card_six_pentacles_name, R.string.card_six_pentacles_desc),
    SEVEN_OF_PENTACLES(R.string.card_seven_pentacles_name, R.string.card_seven_pentacles_desc),
    EIGHT_OF_PENTACLES(R.string.card_eight_pentacles_name, R.string.card_eight_pentacles_desc),
    NINE_OF_PENTACLES(R.string.card_nine_pentacles_name, R.string.card_nine_pentacles_desc),
    TEN_OF_PENTACLES(R.string.card_ten_pentacles_name, R.string.card_ten_pentacles_desc),
    PAGE_OF_PENTACLES(R.string.card_page_pentacles_name, R.string.card_page_pentacles_desc),
    KNIGHT_OF_PENTACLES(R.string.card_knight_pentacles_name, R.string.card_knight_pentacles_desc),
    QUEEN_OF_PENTACLES(R.string.card_queen_pentacles_name, R.string.card_queen_pentacles_desc),
    KING_OF_PENTACLES(R.string.card_king_pentacles_name, R.string.card_king_pentacles_desc);

    companion object {
        private val secureRandom = SecureRandom().apply {
            // Add system-specific entropy
            val deviceEntropy = (System.nanoTime()
                xor Thread.currentThread().id
                xor Runtime.getRuntime().freeMemory()
                xor Runtime.getRuntime().totalMemory()
            ).toString().toByteArray()
            
            setSeed(deviceEntropy)
        }

        /**
         * Returns a random card using enhanced entropy sources
         */
        fun random(): TarotCardType {
            val values = values()
            // Add runtime entropy for each draw
            val runtimeEntropy = (System.nanoTime()
                xor Thread.currentThread().id
                xor android.os.Process.myPid().toLong()
                xor android.os.Process.myTid().toLong()
            ).toString().toByteArray()
            
            secureRandom.setSeed(runtimeEntropy)
            return values[secureRandom.nextInt(values.size)]
        }

        /**
         * Returns a random card from the Major Arcana
         */
        fun randomMajorArcana(): TarotCardType = values().take(22).random()

        /**
         * Returns a random card from a specific suit
         */
        fun randomFromSuit(suit: TarotSuit): TarotCardType {
            val cards = when (suit) {
                TarotSuit.WANDS -> values().slice(22..35)
                TarotSuit.CUPS -> values().slice(36..49)
                TarotSuit.SWORDS -> values().slice(50..63)
                TarotSuit.PENTACLES -> values().slice(64..77)
            }
            return cards.random()
        }
    }
}

enum class TarotSuit {
    WANDS, CUPS, SWORDS, PENTACLES
}

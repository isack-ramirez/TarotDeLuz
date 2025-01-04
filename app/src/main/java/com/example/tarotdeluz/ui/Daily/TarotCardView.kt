package com.example.tarotdeluz.ui.Daily

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.tarotdeluz.R
import com.example.tarotdeluz.models.TarotCard
import com.example.tarotdeluz.models.TarotCardType

class TarotCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val cardFront: FrameLayout
    private val cardBack: ImageView
    private val suitIcon: ImageView
    private val cardName: TextView

    private var isFlipped = false

    init {
        LayoutInflater.from(context).inflate(R.layout.view_tarot_card, this, true)
        
        cardFront = findViewById(R.id.cardFront)
        cardBack = findViewById(R.id.cardBack)
        suitIcon = findViewById(R.id.suitIcon)
        cardName = findViewById(R.id.cardName)

        cardFront.cameraDistance = 8000f
        cardBack.cameraDistance = 8000f
    }

    fun showUndrawnState() {
        if (isFlipped) {
            flipCard()
        }
        cardName.text = ""
        suitIcon.setImageDrawable(null)
    }

    fun displayCard(card: TarotCard) {
        // Set up the front face before flipping
        cardName.text = card.getName(context)
        
        // Set suit icon based on card type
        val suitIconRes = when {
            // Major Arcana (0-21)
            card.cardType.ordinal < 22 -> R.drawable.ic_major_arcana
            // Wands (22-35)
            card.cardType.ordinal in 22..35 -> R.drawable.ic_wands
            // Cups (36-49)
            card.cardType.ordinal in 36..49 -> R.drawable.ic_cups
            // Swords (50-63)
            card.cardType.ordinal in 50..63 -> R.drawable.ic_swords
            // Pentacles (64-77)
            else -> R.drawable.ic_pentacles
        }
        suitIcon.setImageResource(suitIconRes)

        // Flip the card if it's not already flipped
        if (!isFlipped) {
            flipCard()
        }
    }

    private fun flipCard() {
        isFlipped = !isFlipped

        // First half of the animation
        val firstHalf = if (isFlipped) {
            ObjectAnimator.ofFloat(cardBack, "rotationY", 0f, 90f)
        } else {
            ObjectAnimator.ofFloat(cardFront, "rotationY", 0f, 90f)
        }

        // Second half of the animation
        val secondHalf = if (isFlipped) {
            ObjectAnimator.ofFloat(cardFront, "rotationY", -90f, 0f)
        } else {
            ObjectAnimator.ofFloat(cardBack, "rotationY", -90f, 0f)
        }

        firstHalf.duration = 250
        secondHalf.duration = 250

        firstHalf.interpolator = AccelerateDecelerateInterpolator()
        secondHalf.interpolator = AccelerateDecelerateInterpolator()

        // Switch visibility at the middle of the animation
        firstHalf.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                if (isFlipped) {
                    cardBack.visibility = View.INVISIBLE
                    cardFront.visibility = View.VISIBLE
                } else {
                    cardFront.visibility = View.INVISIBLE
                    cardBack.visibility = View.VISIBLE
                }
            }
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        val animSet = AnimatorSet()
        animSet.playSequentially(firstHalf, secondHalf)
        animSet.start()
    }
}

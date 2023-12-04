package com.faqiy.catpark.data

import com.faqiy.catpark.R
import com.faqiy.catpark.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.dice_with_one_dot, R.drawable.dice_1, R.string.cilok),
            Affirmation(R.string.dice_with_two_dot, R.drawable.dice_2,R.string.donat_sate),
            Affirmation(R.string.dice_with_three_dot, R.drawable.dice_3,R.string.dumpling_ayam),
            Affirmation(R.string.dice_with_fourth_dot, R.drawable.dice_4,R.string.kebab),
            Affirmation(R.string.dice_with_five_dot, R.drawable.dice_5,R.string.kue),
            Affirmation(R.string.dice_with_six_dot, R.drawable.dice_6,R.string.leker_pisang),
        )
    }
}
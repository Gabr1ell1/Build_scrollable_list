package com.example.affirmations.model
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

//Declarando a classe Affirmation
data class Affirmation(
    // Anotação que indica que stringResourceId deve ser um ID de recurso de string
    @StringRes val stringResourceId: Int,
    // Anotação que indica que imageResourceId deve ser um ID de recurso drawable
    @DrawableRes val imageResourceId: Int
)

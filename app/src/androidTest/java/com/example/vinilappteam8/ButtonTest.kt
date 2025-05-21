package com.example.vinilappteam8

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class GuestButtonTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testGuestButtonNavigatesCorrectly() {
        // Verifica que el botón "Entrar como Invitado" está visible
        composeTestRule.onNodeWithText("Entrar como Invitado")
            .assertIsDisplayed()
            .performClick()

        // Verifica que el título "VinilApp Team 8 Albums" está visible
        composeTestRule.onNodeWithText("VinilApp Team 8 Albums")
            .assertIsDisplayed()
    }
}

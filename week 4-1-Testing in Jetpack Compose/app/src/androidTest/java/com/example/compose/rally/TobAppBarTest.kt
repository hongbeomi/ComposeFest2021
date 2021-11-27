package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Rule
import org.junit.Test

class TobAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTobAppBarTest() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = {},
                currentScreen = RallyScreen.Accounts
            )
        }
        Thread.sleep(5000)
    }

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNode(
                hasText(
                    RallyScreen.Accounts.name.uppercase()
                ) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_changesTabWhenSelected() {
        composeTestRule.setContent {
            RallyApp()
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("changesTabWhenSelected")

        // initial
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .assertIsSelected()

        // select account tab
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .performClick()
            .assertIsSelected()

        // select bills tab
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .performClick()
            .assertIsSelected()

        // select overview tab
        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .performClick()
            .assertIsSelected()
    }

}
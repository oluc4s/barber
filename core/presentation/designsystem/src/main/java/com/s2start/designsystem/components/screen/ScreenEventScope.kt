package com.s2start.designsystem.components.screen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

data class ScreenEventScope(
    val lifecycleOwner: LifecycleOwner,
    val context: Context,
    val isInsideBottomSheetGragment: Boolean
)

@Composable
internal fun rememberScreenEventScope() : ScreenEventScope {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val isInsideBottomSheetGragment = LocalView.current.isInEditMode
    return ScreenEventScope(lifecycleOwner,context,isInsideBottomSheetGragment)
}
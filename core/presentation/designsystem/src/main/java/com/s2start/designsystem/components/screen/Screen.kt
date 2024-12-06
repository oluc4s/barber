package com.s2start.designsystem.components.screen

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Screen(
    screenState: ScreenState = rememberScreenState(),
    modifier: Modifier = Modifier,
    sheetContent: (@Composable BottomSheetHandlerScope.() -> Unit)? = null,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit,
) {
    observeComposeScreenEvent()
    Column(modifier.fillMaxSize()) {
        if (sheetContent != null) {
            ModalBottomSheetLayout(sheetContent = {
                BottomSheetHandlerScope().run {
                    currentCaller = screenState.bottomSheetState.bottomSheetCaller.value
                    sheetContent()
                    if (!sheetHandled) {
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
            },
                scrimColor = Color.Black.copy(0.8f),
                sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                sheetElevation = 9.dp,
                sheetState = screenState.bottomSheetState.modalBottomSheetState,
                content = {
                    Scaffold(
                        topBar = topBar,
                        bottomBar = bottomBar,
                        floatingActionButton = floatingActionButton,
                        floatingActionButtonPosition = floatingActionButtonPosition,
                        containerColor= containerColor,
                        snackbarHost = snackbarHost
                    ){ innerPadding ->
                        Column(Modifier.padding(innerPadding).navigationBarsPadding()) {
                            content()
                        }
                    }
                })

        } else {
            Scaffold(
                topBar = topBar,
                bottomBar = bottomBar,
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = floatingActionButtonPosition,
                containerColor= containerColor,
                snackbarHost = snackbarHost
            ){ innerPadding ->
                Column(Modifier.padding(innerPadding).navigationBarsPadding()) {
                    content()
                }
            }
        }
    }
}


class BottomSheetHandlerScope {
    var currentCaller: Caller = InitialCaller
    var sheetHandled: Boolean = false

    @Composable
    inline fun <reified T : Caller> handleSheet(noinline sheet: @Composable (T) -> Unit) {
        if (currentCaller is T && currentCaller !is InitialCaller && !sheetHandled) {
            sheet(currentCaller as T)
            sheetHandled = true
        }
    }
}

@Stable
class BottomSheetState(
    val modalBottomSheetState: ModalBottomSheetState,
    val bottomSheetCaller: MutableState<Caller>
) {
    suspend fun show(caller: Caller = DefaultCaller) {
        bottomSheetCaller.value = caller
        modalBottomSheetState.show()
    }

    suspend fun hide() {
        modalBottomSheetState.hide()
    }
}

@Composable
fun rememberScreenState(
    sheetState: BottomSheetState = rememberBottomSheetState()
): ScreenState = remember { ScreenState(sheetState) }


@Stable
class ScreenState(
    val bottomSheetState: BottomSheetState
)


@Composable
fun rememberBottomSheetState(
    sheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
): BottomSheetState {
    val bottomSheetControllerState = rememberSaveable { mutableStateOf<Caller>(InitialCaller) }
    return remember(sheetState, bottomSheetControllerState) {
        BottomSheetState(sheetState, bottomSheetControllerState)
    }.also {
        BottomSheetStateObserver(it)
    }
}

@Composable
private fun BottomSheetStateObserver(sheetState: BottomSheetState) {
    if (sheetState.modalBottomSheetState.currentValue == ModalBottomSheetValue.Hidden) {
        sheetState.bottomSheetCaller.value = InitialCaller
    }
    val eventScope = rememberScreenEventScope()
    LaunchedEffect(sheetState.modalBottomSheetState.currentValue,eventScope) {
        ScreenEvents._onEvent.tryEmit(
            ScreenEvent.ScreenBottomSheetStateChangedEvent(
                sheetState,
                eventScope
            )
        )
    }
}

object ScreenEvents{
    internal val _onEvent = MutableSharedFlow<ScreenEvent>(
        extraBufferCapacity = 2
    )
    val onEvent:SharedFlow<ScreenEvent> = _onEvent
}

sealed class ScreenEvent(open val eventScope: ScreenEventScope){
    data class ScreenBottomSheetStateChangedEvent(
        val bottomsheetState: BottomSheetState,
        override val eventScope: ScreenEventScope
    ): ScreenEvent(eventScope)
}

internal fun observeComposeScreenEvent(){
    ScreenEvents.onEvent
        .onEach { event ->
            when(event){
                is ScreenEvent.ScreenBottomSheetStateChangedEvent -> event.handleEvent()
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
}

private fun ScreenEvent.ScreenBottomSheetStateChangedEvent.handleEvent(){
    if(this.eventScope.lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED){
        val activity = this.eventScope.context.findActivity()
        activity?.let{ Log.e("teste","emit") }
    }
}

fun Context.findActivity(): Activity?{
    var context = this
    while (context is ContextWrapper){
        if(context is Activity) return context
        context = context.baseContext
    }
    return null
}
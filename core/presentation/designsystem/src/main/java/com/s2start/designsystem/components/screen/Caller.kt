package com.s2start.designsystem.components.screen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

interface Caller : Parcelable

@Parcelize
object InitialCaller : Caller

@Parcelize
object DefaultCaller : Caller
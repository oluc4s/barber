package com.s2start.home.presentation.model

data class DropdownItem(val label: String, val icon: Int? = null, val onClick: () -> Unit)
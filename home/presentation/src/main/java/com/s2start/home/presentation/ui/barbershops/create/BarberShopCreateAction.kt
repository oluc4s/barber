package com.s2start.home.presentation.ui.barbershops.create

import com.s2start.home.domain.model.BarberModel

sealed interface BarberShopCreateAction{
    data class CreateBarber(val account: BarberModel):BarberShopCreateAction
}
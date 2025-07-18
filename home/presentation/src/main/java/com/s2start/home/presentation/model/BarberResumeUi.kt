package com.s2start.home.presentation.model

import com.s2start.home.domain.model.BarberModel
import kotlinx.serialization.Serializable

@Serializable
data class BarberResumeUi(
    val barberId:String = "",
    val userId:String = "",
    val name:String,
    val address:String,
    val image: String,
    val services:String,
    val rating:Double = 0.0,
    val distance:Double = 0.0,
    val latitude:Double = 0.0,
    val longitude:Double = 0.0
)

fun BarberModel.toUiModel() = BarberResumeUi(
    barberId = id,
    userId = userId,
    name = name,
    address = address,
    distance = distance,
    latitude = latitude,
    longitude = longitude,
    services = services,
    rating = rating,
    image = "",
)
fun List<BarberModel>.toUiListModel() = this.map {
    BarberResumeUi(
        barberId = it.id,
        userId = it.userId,
        name = it.name,
        address = it.address,
        distance = it.distance,
        latitude = it.latitude,
        longitude = it.longitude,
        services = it.services,
        rating = it.rating,
        image = "")
}


val mockBarberResumeList = arrayOf(
    BarberResumeUi(
        name = "Barbearia Trindade",
        address = "Rua Marechal Falconieri, 538, Europa",
        image = "",
        services = "Corte de cabelo com degradê, barbear tradicional, lavagem e hidratação",
        rating = 5.2,
        distance = 2.5
    ),
    BarberResumeUi(
        name = "Elite Barber Shop",
        address = "Av. Brasil, 1020, Centro",
        image = "",
        services = "Corte executivo, barba alinhada, pigmentação capilar",
        rating = 4.8,
        distance = 3.2
    ),
    BarberResumeUi(
        name = "Estilo & Fios",
        address = "Rua das Palmeiras, 77, São Pedro",
        image = "",
        services = "Corte clássico, barba modelada, relaxamento capilar",
        rating = 4.5,
        distance = 1.8
    ),
    BarberResumeUi(
        name = "King's Barber",
        address = "Praça Central, 305, Vila Nova",
        image = "",
        services = "Corte undercut, barbear completo, sobrancelha",
        rating = 4.9,
        distance = 5.0
    ),
    BarberResumeUi(
        name = "Luxo & Navalha",
        address = "Rua dos Andradas, 54, Jardim das Flores",
        image = "",
        services = "Corte moderno, barba com navalha quente, massagem facial",
        rating = 4.7,
        distance = 2.9
    ),
    BarberResumeUi(
        name = "Barbearia Imperial",
        address = "Alameda dos Pinheiros, 12, Bela Vista",
        image = "",
        services = "Corte degradê americano, tratamento capilar, barbear à vapor",
        rating = 5.0,
        distance = 4.3
    ),
    BarberResumeUi(
        name = "Old School Barber",
        address = "Rua XV de Novembro, 890, Centro Histórico",
        image = "",
        services = "Corte vintage, barba clássica, finalização com pomada",
        rating = 4.6,
        distance = 3.7
    ),
    BarberResumeUi(
        name = "Barber Premium",
        address = "Avenida Atlântica, 210, Copacabana",
        image = "",
        services = "Corte moderno, alisamento, tratamento capilar intensivo",
        rating = 4.9,
        distance = 6.0
    ),
    BarberResumeUi(
        name = "Navalha de Ouro",
        address = "Rua das Flores, 32, Nova Esperança",
        image = "",
        services = "Corte militar, barbear detalhado, hidratação de barba",
        rating = 4.8,
        distance = 2.2
    ),
    BarberResumeUi(
        name = "Estética Masculina",
        address = "Avenida São João, 450, São Bento",
        image = "",
        services = "Corte personalizado, alinhamento de barba, spa facial",
        rating = 5.0,
        distance = 1.5
    )
)


val mockBarberResume = BarberResumeUi(
    name = "Barbearia Trindade",
    address = "Rua Marechal Falconieri, 538, Europa",
    image = "",
    services = "Corte de cabelo com corte inferior, barbear regular, lavagem natural do cabelo",
    rating = 5.2,
    distance = 2.5
)
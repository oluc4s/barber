package com.s2start.home.presentation.model

import com.s2start.core.presentation.designsystem.R

data class BarberUi(
    val name:String,
    val address:String,
    val image:Int,
    val services:String,
    val rating:Double = 0.0
)


val mockBarberList = arrayOf(
    BarberUi(
        name = "Barbearia Trindade",
        address = "Rua Marechal Falconieri, 538, Europa",
        image = R.drawable.img_moc_barber,
        services = "Corte de cabelo com degradê, barbear tradicional, lavagem e hidratação",
        rating = 5.2
    ),
    BarberUi(
        name = "Elite Barber Shop",
        address = "Rua egito, 250, Copacabana",
        image = R.drawable.img_mock_barber2,
        services = "Corte executivo, barba alinhada, pigmentação capilar",
        rating = 4.8
    ),
    BarberUi(
        name = "Estilo & Fios",
        address = "Vila dos Anjos Belo Horizonte",
        image = R.drawable.img_mock_barber3,
        services = "Corte clássico, barba modelada, relaxamento capilar",
        rating = 4.5
    ),
    BarberUi(
        name = "King's Barber",
        address = "R. Alberto Alves de Azevedo, 164 - Castelo",
        image = R.drawable.img_mock_barber4,
        services = "Corte undercut, barbear completo, sobrancelha",
        rating = 4.9
    ),
    BarberUi(
        name = "Luxo & Navalha",
        address = "Av. Portugal, 555 - Santa Amelia",
        image = R.drawable.img_mock_barber5,
        services = "Corte moderno, barba com navalha quente, massagem facial",
        rating = 4.7
    ),
    BarberUi(
        name = "Barbearia Imperial",
        address = "Av. Liége, 213 - Europa",
        image = R.drawable.img_mock_barber6,
        services = "Corte degradê americano, tratamento capilar, barbear à vapor",
        rating = 5.0
    ),
    BarberUi(
        name = "Old School Barber",
        address = "Rua XV de Novembro, 890, Centro Histórico",
        image = R.drawable.img_mock_barber7,
        services = "Corte vintage, barba clássica, finalização com pomada",
        rating = 4.6
    ),
    BarberUi(
        name = "Barber Premium",
        address = "Avenida Atlântica, 210, Copacabana",
        image = R.drawable.img_mock_barber8,
        services = "Corte moderno, alisamento, tratamento capilar intensivo",
        rating = 4.9
    ),
    BarberUi(
        name = "Navalha de Ouro",
        address = "Rua das Flores, 32, Nova Esperança",
        image = R.drawable.img_mock_barber9,
        services = "Corte militar, barbear detalhado, hidratação de barba",
        rating = 4.8
    ),
    BarberUi(
        name = "Estética Masculina",
        address = "Avenida São João, 450, São Bento",
        image = R.drawable.img_mock_barber10,
        services = "Corte personalizado, alinhamento de barba, spa facial",
        rating = 5.0
    )
)


val mockBarber =  BarberUi(
    name = "Barbearia Trindade",
    address = "Rua Marechal falconieri, 538,Europa",
    image = R.drawable.img_moc_barber,
    services = "Corte de cabelo com corte inferior, barbear regular, lavagem natural do cabelo",
    rating = 5.2
)
package com.example.rickandmortylocation.domain.models

class Location(
    val name: String,
    val type: String,
    val countResidents: Int,
    val residentsId: List<String>,
//    val countFemale: Int,
//    val countMale: Int,
//    val countGenderless: Int,
//    val countUnknown: Int
)
package com.example.rickandmortylocation.domain.model

class Location(
    val id: Int,
    val name: String,
    val type: String,
    val countResidents: Int,
    val residentsId: List<String>,
)
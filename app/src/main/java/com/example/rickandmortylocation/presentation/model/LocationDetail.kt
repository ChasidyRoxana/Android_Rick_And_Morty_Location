package com.example.rickandmortylocation.presentation.model

import com.example.rickandmortylocation.domain.model.Gender
import com.example.rickandmortylocation.domain.model.Resident

class LocationDetail(
    val name: String,
    val type: String,
    private val characters: List<Resident>,
) {

    val countFemale: Int = characters.count { it.gender == Gender.Female }
    val countMale: Int = characters.count { it.gender == Gender.Male }
    val countGenderless: Int = characters.count { it.gender == Gender.Genderless }
    val countUnknown: Int = characters.count { it.gender == Gender.Unknown }
}
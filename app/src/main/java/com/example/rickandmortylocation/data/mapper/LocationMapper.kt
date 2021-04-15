package com.example.rickandmortylocation.data.mapper

import com.example.rickandmortylocation.data.model.LocationApi
import com.example.rickandmortylocation.data.model.PageApi
import com.example.rickandmortylocation.data.model.ResidentApi
import com.example.rickandmortylocation.domain.model.Gender
import com.example.rickandmortylocation.domain.model.Location
import com.example.rickandmortylocation.domain.model.Resident
import com.example.rickandmortylocation.domain.model.ResponseInfo

class LocationMapper {

    fun mapToResponseInfo(pageApi: PageApi): ResponseInfo =
        ResponseInfo(
            countLocations = pageApi.info.count,
            locations = pageApi.results.map { mapToLocation(it) }
        )

    fun mapToLocation(locationApi: LocationApi): Location =
        Location(
            id = locationApi.id,
            name = locationApi.name,
            type = locationApi.type,
            countResidents = locationApi.residents?.size ?: 0,
            residentsId = mapResidents(locationApi.residents)
        )

    fun mapToResident(residentApi: ResidentApi): Resident =
        Resident(gender = mapStringToGender(residentApi.gender))

    fun mapGenderToString(gender: Gender): String =
        when (gender) {
            Gender.Female -> GENDER_FEMALE
            Gender.Male -> GENDER_MALE
            Gender.Genderless -> GENDER_GENDERLESS
            Gender.Unknown -> GENDER_UNKNOWN
        }

    private fun mapStringToGender(genderString: String): Gender =
        when (genderString) {
            GENDER_FEMALE -> Gender.Female
            GENDER_MALE -> Gender.Male
            GENDER_GENDERLESS -> Gender.Genderless
            GENDER_UNKNOWN -> Gender.Unknown
            else -> Gender.Unknown
        }

    private fun mapResidents(residentsUrl: List<String>?): List<String> {
        val residentsId: MutableList<String> = mutableListOf()
        if (residentsUrl != null) {
            for (resident in residentsUrl) {
                val residentId = resident.substringAfterLast('/')
                residentsId.add(residentId)
            }
        }
        return residentsId
    }

    companion object {
        private const val GENDER_FEMALE = "Female"
        private const val GENDER_MALE = "Male"
        private const val GENDER_GENDERLESS = "Genderless"
        private const val GENDER_UNKNOWN = "unknown"
    }
}
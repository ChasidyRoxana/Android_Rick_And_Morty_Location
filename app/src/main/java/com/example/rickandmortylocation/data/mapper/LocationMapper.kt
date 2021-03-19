package com.example.rickandmortylocation.data.mapper

import com.example.rickandmortylocation.data.models.LocationApi
import com.example.rickandmortylocation.data.models.PageApi
import com.example.rickandmortylocation.domain.models.Location
import com.example.rickandmortylocation.domain.models.ResponseInfo

class LocationMapper {

    fun mapPageApiToResponseInfo(pageApi: PageApi): ResponseInfo =
        ResponseInfo(
            countLocations = pageApi.info.count,
            locations = pageApi.results.map { mapLocationApi(it) }
        )

    private fun mapLocationApi(locationApi: LocationApi): Location =
        Location(
            name = locationApi.name,
            type = locationApi.type,
            countResidents = locationApi.residents?.size ?: 0,
            residentsId = mapResidents(locationApi.residents)
        )

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
}
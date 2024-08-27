package com.example.jobsearch.search.data.dto

import com.example.jobsearch.search.domain.model.TextButtonModel
import com.example.network.dto.SalaryRemote
import com.example.network.dto.TextButtonRemote

data class TextButtonDto(
    val text: String
)

fun TextButtonDto.mapToModel(): TextButtonModel {
    return TextButtonModel(
        text = text
    )
}

fun TextButtonModel.mapToDto(): TextButtonDto {
    return TextButtonDto(
        text = text
    )
}

fun TextButtonRemote.mapToDto(): TextButtonDto {
    return TextButtonDto(
        text = text
    )
}

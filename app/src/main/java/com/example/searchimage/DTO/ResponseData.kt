package com.example.searchimage.DTO

data class ResponseData(
    val documents: MutableList<Document>,
    val meta: Meta
)
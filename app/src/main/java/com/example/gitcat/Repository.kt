package com.example.gitcat

data class Repository (
    var repName: String,
    var commit: List<RepositoryDetail>
)
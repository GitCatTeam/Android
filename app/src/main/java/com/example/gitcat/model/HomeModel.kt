package com.example.gitcat.model

data class HomeModel(
    var message: String,
    var data: HomeData
)

data class HomeData(
    var catName: String,
    var catImg: String,
    var todayCommitCount: Int,
    var todayScore: Int,
    var ments: ArrayList<String>,
    var nextLevelScore: Int,
    var nextLevelStr: String,
    var catLevel: Int,
    var isLevelUp: Boolean,
    var isGraduate: Boolean,
    var isLeave: Boolean
)
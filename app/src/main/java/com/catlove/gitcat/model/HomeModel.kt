package com.catlove.gitcat.model

data class HomeModel(
    var message: String,
    var data: HomeData
)

data class HomeData(
    var catName: String,
    var catImg: String,
    var todayCommitCount: Int,
    var todayScore: Int,
    var totalScore: Int,
    var currentLevel: Int,
    var currentItem: String,
    var nextLevel: Int,
    var nextLevelItem: String,
    var nextLevelScore: Int,
    var graduScore: Int,
    var ments: ArrayList<String>,
    var isLevelUp: Boolean,
    var isGraduate: Boolean,
    var isLeave: Boolean
)
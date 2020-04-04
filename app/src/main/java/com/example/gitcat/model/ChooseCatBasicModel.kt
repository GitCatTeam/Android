package com.example.gitcat.model


data class DataModel(
    var message: String,
    var data: ChooseCatModel
)

data class ChooseCatModel(
    var normal: ArrayList<ChooseCatSpecialModel>,
    var special: ArrayList<ChooseCatBasicModel>
)

data class ChooseCatBasicModel (
    var id: Int,
    var profileImg: String
)

data class ChooseCatSpecialModel(
    var id: Int
)
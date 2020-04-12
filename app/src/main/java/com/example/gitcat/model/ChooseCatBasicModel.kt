package com.example.gitcat.model


data class DataModel(
    var message: String,
    var data: ChooseCatModel
)

data class ChooseCatModel(
    var isNewExist: Boolean,
    var normal: ArrayList<ChooseCatBasicModel>,
    var special: ArrayList<ChooseCatSpecialModel>,
    var new: ArrayList<CatNewModel>
)

data class ChooseCatBasicModel (
    var id: Int,
    var profileImg: String
)

data class ChooseCatSpecialModel(
    var id: Int,
    var profileImg: String
)
data class CatNewModel(
    var profileImg: String,
    var description: String
)
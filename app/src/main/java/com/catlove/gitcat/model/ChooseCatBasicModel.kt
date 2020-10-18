package com.catlove.gitcat.model


data class DataModel(
    var message: String,
    var data: ChooseCatModel
)

data class ChooseCatModel(
    var isNewExist: Boolean,
    var normal: ArrayList<ChooseCatBasicModel>,
    var special: ArrayList<ChooseCatSpecialModel>,
    var new: ArrayList<ChooseCatNewModel>
)

data class ChooseCatBasicModel (
    var id: Int,
    var profileImg: String,
    var cost: Int,
    var description: String,
    var payType: String,
    var isAvailable: Boolean,
    var isNew: Boolean
)

data class ChooseCatSpecialModel(
    var id: Int,
    var profileImg: String
)
data class ChooseCatNewModel(
    var profileImg: String,
    var description: String
)
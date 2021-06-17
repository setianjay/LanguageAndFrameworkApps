package com.setianjay.languageandframeworkapps

import androidx.annotation.IdRes

data class LanguageAndFrameworkModel(
    @IdRes var poster: Int = 0,
    var title: String = "",
    var detail: String = ""
)
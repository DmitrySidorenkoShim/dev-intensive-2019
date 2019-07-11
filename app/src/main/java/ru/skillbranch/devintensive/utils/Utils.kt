package ru.skillbranch.devintensive.utils


object Utils {

    fun parseFullName(fullName:String?):Pair<String?, String?>{
        if (fullName.isNullOrBlank()) return null to null

        val parts : List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

//        return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, devider: String = " "): String {

        var nameInEng = ""

        payload.map {
            when (it) {
                'а'-> nameInEng+= 'a'
                'б'-> nameInEng+= "b"
                'в'-> nameInEng+= "v"
                'г'-> nameInEng+= "g"
                'д'-> nameInEng+= "d"
                'е'-> nameInEng+= "e"
                'ё'-> nameInEng+= "je"
                'ж'-> nameInEng+= "zh"
                'з'-> nameInEng+= "z"
                'и'-> nameInEng+= "i"
                'й'-> nameInEng+= "y"
                'к'-> nameInEng+= "k"
                'л'-> nameInEng+= "l"
                'м'-> nameInEng+= "m"
                'н'-> nameInEng+= "n"
                'о'-> nameInEng+= "o"
                'п'-> nameInEng+= "p"
                'р'-> nameInEng+= "r"
                'с'-> nameInEng+= "s"
                'т'-> nameInEng+= "t"
                'у'-> nameInEng+= "u"
                'ф'-> nameInEng+= "f"
                'х'-> nameInEng+= "kh"
                'ц'-> nameInEng+= "c"
                'ч'-> nameInEng+= "ch"
                'ш'-> nameInEng+= "sh"
                'щ'-> nameInEng+= "jsh"
                'ъ'-> nameInEng+= "hh"
                'ы'-> nameInEng+= "ih"
                'ь'-> nameInEng+= "jh"
                'э'-> nameInEng+= "eh"
                'ю'-> nameInEng+= "ju"
                'я'-> nameInEng+= "ja"
                'А'-> nameInEng+= "A"
                'Б'-> nameInEng+= "B"
                'В'-> nameInEng+= "V"
                'Г'-> nameInEng+= "G"
                'Д'-> nameInEng+= "D"
                'Е'-> nameInEng+= "E"
                'Ё'-> nameInEng+= "JE"
                'Ж'-> nameInEng+= "ZH"
                'З'-> nameInEng+= "Z"
                'И'-> nameInEng+= "I"
                'Й'-> nameInEng+= "Y"
                'К'-> nameInEng+= "K"
                'Л'-> nameInEng+= "L"
                'М'-> nameInEng+= "M"
                'Н'-> nameInEng+= "N"
                'О'-> nameInEng+= "O"
                'П'-> nameInEng+= "P"
                'Р'-> nameInEng+= "R"
                'С'-> nameInEng+= "S"
                'Т'-> nameInEng+= "T"
                'У'-> nameInEng+= "U"
                'Ф'-> nameInEng+= "F"
                'Х'-> nameInEng+= "KH"
                'Ц'-> nameInEng+= "C"
                'Ч'-> nameInEng+= "CH"
                'Ш'-> nameInEng+= "SH"
                'Щ'-> nameInEng+= "JSH"
                'Ъ'-> nameInEng+= "HH"
                'Ы'-> nameInEng+= "IH"
                'Ь'-> nameInEng+= "JH"
                'Э'-> nameInEng+= "EH"
                'Ю'-> nameInEng+= "JU"
                'Я'-> nameInEng+= "JA"
                ' '-> nameInEng+= devider
                else->""
            }
        }.joinToString("")

        return nameInEng
    }

    fun toInitials(firstName: String?, lastName: String?): String {
        if (firstName?.length == 0 || firstName == "") return "null"
        if (lastName?.length == 0 || lastName == "") return "null"

        val fN = firstName?.get(0)
        val lN = lastName?.get(0)

        return "${fN?.toUpperCase()}${lN?.toUpperCase() ?: ""}"
    }
}
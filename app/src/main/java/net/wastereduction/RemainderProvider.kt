package net.wastereduction

class RemainderProvider {
    companion object{
        val remainderList = listOf<Remainder>(
            Remainder("3d 3h",
                "Plastic",
                "@drawable/img_2",
                "Remember to take out the plastic"),
            Remainder("2d 4h",
                "Carton",
                "pic",
                "Remember to take out the plastic"),
            Remainder("5d 7h",
                "Recycle",
                "pic",
                "Remember to recycle"),
            Remainder("7d 3h",
                "Throw out",
                "pic",
                "Remember to take out the trash"),
        )
    }
}
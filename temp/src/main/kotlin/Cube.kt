class Cube(difficulty: Difficulty) {
    private var cube = ArrayList<Plane>()

    init {
        val index = when (difficulty) {
            Difficulty.VERY_EASY -> 2
            Difficulty.EASY -> 2
            Difficulty.MEDIUM -> 3
            Difficulty.HARD -> 4
            Difficulty.VERY_HARD -> 6
            Difficulty.LUCK_BASED -> 8
        }
        for (i in 0 until index) {
            cube.add(Plane(difficulty))
        }
        calcValues()
    }

    private fun calcValues() {
        cube.forEachIndexed { p, plane ->
            plane.plane.forEachIndexed { r, row ->
                row.forEachIndexed { c, _ ->
                    cube[p].plane[r][c]?.let {
                        val field = it
                        if (field.type == Type.NEAR_MINE) {
                            field.value = calcSurroundings(p, r, c)
                        }
                        if (field.type == Type.EMPTY) {
                            val value = calcSurroundings(p, r, c)
                            if (value > 0) {
                                field.type = Type.NEAR_MINE
                                field.value = value
                            }
                        }
                    }
                }
            }
        }
    }

    private fun calcSurroundings(p: Int, r: Int, c: Int): Int {
        var count = 0
        for (dp in -1..1) {
            for (dr in -1..1) {
                for (dc in -1..1) {
                    if (dp == 0 && dr == 0 && dc == 0) {
                        continue
                    }
                    try {
                        val field = cube[p + dp].plane[r + dr][c + dc]
                        field?.let {
                            if (it.type == Type.MINE) {
                                count++
                            }
                        }
                    } catch (_: Exception) {
                    }
                }
            }
        }
        return count
    }

    fun changeStateOfField(pos: Position, planeNumber: Int, isMarkSelected: Boolean) {
        val field = cube[planeNumber].plane[pos.row][pos.col]
        field?.let {
            if (it.state == State.OPENED) {
                return
            }
            if (isMarkSelected) {
                it.state = when (it.state) {
                    State.CLOSED -> State.MARKED
                    else -> State.CLOSED
                }
                return
            }
            if (it.state == State.MARKED) {
                return
            }
            //Should already be checked after if a bomb was opened
            if (it.type == Type.MINE) {
                it.state = State.OPENED //and loose
                return
            }
            //Open
            openPosition(pos, planeNumber)
        }
    }


    private fun openPosition(pos: Position, planeNumber: Int) {
        val field = cube[planeNumber].plane[pos.row][pos.col]
        field?.let {
            if (it.type == Type.NEAR_MINE){
                it.state = State.OPENED
                return
            }
            //TODO
        }
    }

    fun printFirstPlane() {
        for (plane in cube) {
            plane.plane.forEach { row ->
                row.forEach { col ->
                    col?.let {
                        when (it.type) {
                            Type.MINE -> print("X ")
                            Type.NEAR_MINE -> print("${it.value} ")
                            Type.EMPTY -> print("  ")
                        }
                    }
                }
                println()
            }
            println()
        }
    }

}
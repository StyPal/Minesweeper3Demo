class Plane(difficulty: Difficulty) {
    var plane: Array<Array<Type?>> = Array(0) { arrayOfNulls(0) }
    var state: Array<Array<State?>> = Array(0) { arrayOfNulls(0) }
    private lateinit var minePositions: ArrayList<Position>

    init {
        when (difficulty) {
            Difficulty.VERY_EASY -> generateCube(5, 4)
            Difficulty.EASY -> generateCube(8, 10)
            Difficulty.MEDIUM -> generateCube(9, 14)
            Difficulty.HARD -> generateCube(9, 23)
            Difficulty.VERY_HARD -> generateCube(16, 100)
            Difficulty.LUCK_BASED -> generateCube(16, 255)
        }
    }

    private fun generateCube(siteLength: Int, mineCountPerPlain: Int) {
        plane = Array(siteLength) {
            arrayOfNulls(siteLength)
        }
        state = Array(siteLength) {
            arrayOfNulls(siteLength)
        }
        fillCubeMines()
        fillState()
        minePositions = Generator(plane).generateMines(siteLength, siteLength * siteLength - mineCountPerPlain)
    }

    private fun fillState() {
        state.forEachIndexed { r, row ->
            row.forEachIndexed { c, _ ->
                state[r][c] = State.CLOSED
            }
        }
    }


    private fun fillCubeMines() {
        plane.forEachIndexed { r, row ->
            row.forEachIndexed { c, _ ->
                plane[r][c] = Type.MINE
            }
        }
    }
}


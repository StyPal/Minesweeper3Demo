class Plane(difficulty: Difficulty) {
    lateinit var  plane: Array<Array<Field?>>
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
        fillFields()
        val freePositionCount = siteLength * siteLength - mineCountPerPlain
        minePositions = Generator(plane).generateMines(siteLength, freePositionCount)
    }


    private fun fillFields() {
        plane.forEachIndexed { r, row ->
            row.forEachIndexed { c, _ ->
                plane[r][c] = Field()
            }
        }
    }
}


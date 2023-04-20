class Plane(difficulty: Difficulty) {
    lateinit var plane: Array<Array<Type?>>
    private lateinit var minePositions: ArrayList<Position>
    private var generator = Generator(plane)

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
        fillCubeMines()
        minePositions = generator.generateMines(siteLength, siteLength * siteLength - mineCountPerPlain)
    }


    private fun fillCubeMines() {
        plane.forEachIndexed { r, row ->
            row.forEachIndexed { c, _ ->
                plane[r][c] = Type.MINE
            }
        }
    }
}


class Generator(private var plane: Array<Array<Field?>>) {
    private var random: java.util.Random = java.util.Random()
    private var minePositions = ArrayList<Position>()

    /**
     * Iterates through the levels of the gameCube Array and generates mineCountPerPlain-numbers of
     * positions for mines for each level
     *
     * @param freePosCount Count of Mines free Positions in one plane
     * @param siteLength
     */
    fun generateMines(siteLength: Int, freePosCount: Int): ArrayList<Position> {
        val fieldCount = siteLength * siteLength
        if (freePosCount > fieldCount || freePosCount < 1) {
            throw IllegalArgumentException()
        }
        minePositions = getAllPos(siteLength)
        try {
            for (mine in 0 until freePosCount) {
                val index = random.nextInt(0, minePositions.size)
                val tempPos = minePositions[index]
                val field = plane[tempPos.row][tempPos.col]
                field?.let {
                    it.type = Type.EMPTY
                }
                minePositions.removeAt(index)
            }
        } catch (_: IllegalArgumentException) {
        }
        setNearMines()
        return minePositions
    }

    private fun getAllPos(siteLength: Int): ArrayList<Position> {
        val pos = ArrayList<Position>()
        for (row in 0 until siteLength) {
            for (col in 0 until siteLength) {
                pos.add(Position(row, col))
            }
        }
        return pos
    }

    private fun setNearMines() {
        plane.forEachIndexed { r, rows ->
            rows.forEachIndexed { c, _ ->
                val field = plane[r][c]
                field?.let {
                    if (it.type == Type.EMPTY) {
                        if (isSurrounded(r, c)) {
                            it.type = Type.NEAR_MINE
                        }
                    }
                }
            }
        }
    }

    private fun isSurrounded(r: Int, c: Int): Boolean {
        for (dr in -1..1) {
            for (dc in -1..1) {
                if (dr == 0 && dc == 0) {
                    continue  // Überspringe die aktuelle Position
                }
                val nr = r + dr
                val nc = c + dc

                // Überprüfe, ob die neue Position innerhalb der Grenzen des gameCube liegt
                if (nr >= 0 && nr < plane.size && nc >= 0 && nc < plane[0].size) {
                    val field = plane[nr][nc]
                    field?.let {
                        if (it.type == Type.MINE) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }
}

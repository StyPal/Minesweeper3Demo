class Generator(private var plane: Array<Array<Type?>>) {
    private var random: java.util.Random = java.util.Random()
    private var minePositions = ArrayList<Position>()

    /**
     * Iterates through the levels of the gameCube Array and generates mineCountPerPlain-numbers of
     * positions for mines for each level
     *
     * @param freePosCount Count of Mines free Positions in one plane
     */
    fun generateMines(siteLength: Int, freePosCount: Int): ArrayList<Position> {
        minePositions = getAllPos(siteLength)
        try {
            for (mine in 0 until freePosCount) {
                val index = random.nextInt(0, minePositions.size)
                val tempPos = minePositions[index]
                plane[tempPos.row][tempPos.col] = Type.EMPTY
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
                if (plane[r][c] == Type.EMPTY) {
                    isSurrounded(r, c)
                    plane[r][c] = Type.NEAR_MINE
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
                    if (plane[nr][nc] === Type.MINE) {
                        return true
                    }
                }
            }
        }
        return false
    }
}

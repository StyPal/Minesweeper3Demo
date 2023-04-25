class Cube (difficulty: Difficulty){
    private var cube = ArrayList<Plane>()

    init {
        val index = when(difficulty){
            Difficulty.VERY_EASY -> 2
            Difficulty.EASY -> 3
            Difficulty.MEDIUM -> 3
            Difficulty.HARD -> 4
            Difficulty.VERY_HARD -> 6
            Difficulty.LUCK_BASED -> 8
        }
        for (i in 0 until index){
            cube.add(Plane(difficulty))
        }
        calcValues()
    }

    private fun calcValues() {
        cube.forEachIndexed { p, plane ->
            plane.plane.forEachIndexed { r, row ->
                row.forEachIndexed { c, _ ->
                    cube[p].plane[r][c]?.let {
                        val type = it
                        if (type == Type.NEAR_MINE){
                            type.setValue(calcSurroundings(p,r,c))
                        }
                    }
                }
            }
        }
    }

    private fun calcSurroundings(p: Int, r: Int, c: Int): Int {
        var count = 0
        for (dp in -1 .. 1){
            for (dr in -1 .. 1){
                for (dc in -1 .. 1){
                    if (dp == 0 && dr == 0 && dc == 0){
                        continue
                    }
                    try{
                        val type = cube[p + dp].plane[r + dr][c + dc]
                        if (type == Type.MINE){
                            count++
                        }
                    }catch (_: Exception){
                    }
                }
            }
        }
        return count
    }

    fun openPosition(pos: Position, planeNumber: Int){
        if(cube[planeNumber].plane[pos.row][pos.col] == Type.MINE){
            return
        }
    }

    fun printFirstPlane(){
        for (plane in cube){
            plane.plane.forEachIndexed { r, row ->
                row.forEachIndexed { c, col ->
                    when (col) {
                        Type.MINE -> print("X ")
                        Type.NEAR_MINE -> print("${col.getValue()} ")
                        else -> print("  ")
                    }
                }
                println()
            }
            println()
        }
    }

}
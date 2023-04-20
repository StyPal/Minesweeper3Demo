class Cube (private final var difficulty: Difficulty){
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
    }
}
class Position(var row: Int, var col: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return if (other !is Position) false else (this.row == other.row) && (this.col == other.col)
    }

    fun clone(): Position {
        return Position(row, col)
    }

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + col
        return result
    }
}

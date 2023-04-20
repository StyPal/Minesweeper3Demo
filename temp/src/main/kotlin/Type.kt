enum class Type {
    EMPTY,
    MINE,
    NEAR_MINE;

    private var value = 0
    fun setValue(value: Int) {
        if (this == NEAR_MINE) {
            this.value = value
        } else {
            this.value = 0
        }
    }

    fun getValue(): Int {
        return this.value
    }
}

enum class Type {
    EMPTY,
    MINE,
    NEAR_MINE {
        private var value: Int = 0;
        fun add(){
            value++
        }
    };
}


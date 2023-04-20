import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException

class GeneratorTest {

    private lateinit var generator: Generator
    private lateinit var plane: Array<Array<Type?>>
    private val siteLength = 3
    private val mineCount = 2

    @BeforeEach
    fun setUp() {
        plane = Array(siteLength) {
            arrayOfNulls(siteLength)
        }
        plane.forEachIndexed { r, row ->
            row.forEachIndexed { c, _ ->
                plane[r][c] = Type.MINE
            }
        }
        generator = Generator(plane)
    }

    @Test
    fun generateMines() {
        val result = generator.generateMines(3, 7)
        assertEquals(result.size, mineCount)
        var isCorrect = true
        result.forEach { pos ->
            if (plane[pos.row][pos.col] != Type.MINE){
                isCorrect = false
            }
        }
        assertTrue(isCorrect)
    }
}
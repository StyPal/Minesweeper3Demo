import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
        val result = generator.generateMines(3, siteLength * siteLength - mineCount)
        // Check if the size of the ArrayList of the Mine Positions is the same as mineCount
        assertEquals(result.size, mineCount)

        // Iterates through the ArrayList of the mine positions
        result.forEach { pos ->
            if (plane[pos.row][pos.col] != Type.MINE) {
                fail()
            } else {
                assertTrue(true)
            }
        }

        // Checks if there is a mine is not in the ArrayList
        plane.forEachIndexed { r, row ->
            row.forEachIndexed { c, _ ->
                var b = false
                if (plane[r][c] == Type.MINE) {
                    result.forEach { positions ->
                        if (positions.row == r && positions.col == c) {
                            b = true
                        }
                    }
                    if (!b) {
                        fail()
                    } else {
                        assertTrue(true)
                    }
                }
            }
        }
    }
}
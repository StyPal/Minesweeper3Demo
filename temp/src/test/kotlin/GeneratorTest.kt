import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GeneratorTest {

    private lateinit var generator: Generator
    private lateinit var plane: Array<Array<Field?>>
    private val siteLength = 3
    private val mineCount = 2

    @BeforeEach
    fun setUp() {
        plane = Array(siteLength) {
            arrayOfNulls(siteLength)
        }
        plane.forEachIndexed { r, row ->
            row.forEachIndexed { c, _ ->
                plane[r][c] = Field()
            }
        }
        generator = Generator(plane)
    }

    private fun getResult(): ArrayList<Position>{
        return generator.generateMines(3, siteLength * siteLength - mineCount)
    }

    @Test
    fun generateMines_size() {
        val result = getResult()
        // Check if the size of the ArrayList of the Mine Positions is the same as mineCount
        assertEquals(result.size, mineCount)
    }

    @Test
    fun generateMines_contentOfArrayList(){
        val result = getResult()
        // Iterates through the ArrayList of the mine positions
        result.forEach { pos ->
            if (plane[pos.row][pos.col]?.type != Type.MINE) {
                fail()
            } else {
                assertTrue(true)
            }
        }

    }

    @Test
    fun generateMines_temp(){
        val result = getResult()
        // Checks if there is a mine is not in the ArrayList
        plane.forEachIndexed { r, row ->
            row.forEachIndexed { c, _ ->
                var b = false
                if (plane[r][c]?.type == Type.MINE) {
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
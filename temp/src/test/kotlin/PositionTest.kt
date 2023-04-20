import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PositionTest {

    @Test
    fun testEquals() {
        val pos1 = Position(1,3)
        val pos2 = pos1
        val pos3 = Position(1,3)
        val pos4 = Position(2,4)
        assertEquals(pos1.row, pos2.row)
        assertEquals(pos1.col, pos2.col)
        assertEquals(pos2.row, pos3.row)
        assertEquals(pos2.col, pos3.col)
        assertNotEquals(pos3.row,pos4.row)
        assertNotEquals(pos3.col,pos4.col)
    }

    @Test
    fun testClone() {
        val pos1 = Position(1,3)
        val pos2 = pos1.clone()
        assertEquals(pos1.row, pos2.row)
        assertEquals(pos1.col, pos2.col)
    }
}
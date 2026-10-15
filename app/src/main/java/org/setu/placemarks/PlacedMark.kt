
package org.setu.placemarks

data class PlacedMark(
    val id: Long = 0L,
    val title: String,
    val desc: String,
    val x: Double,
    val y: Double
)

class PlacedMarkList {

    private val marks = mutableListOf<PlacedMark>()
    private var nextId = 1L

    fun findAll(): List<PlacedMark> {
        return marks.toList()
    }

    fun findOne(id: Long): PlacedMark? {
        return marks.find { it.id == id }
    }

    fun create(mark: PlacedMark) {
        marks.add(mark.copy(id = nextId++))
    }

    fun update(mark: PlacedMark) {
        val index = marks.indexOfFirst { it.id == mark.id }

        if (index != -1) {
            marks[index] = mark
        }
    }

    fun delete(id: Long) {
        marks.removeAll { it.id == id }
    }
}

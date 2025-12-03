package twothousandtwentyone.day4

data class Field(val number: Int, var marked: Boolean = false)

data class Board(val fields: Array<Array<Field>>) {

    companion object {
        fun parse(text: List<String>): Board {
            return Board(Array(text.size) { row ->
                text[row].trim().split("\\s+".toRegex()).map { num -> Field(num.toInt()) }.toTypedArray()
            })
        }
    }


    fun hasWon(): Boolean {
        if (fields.any { row -> row.all { it.marked } }) {
            return true
        }
        for (i in fields.indices) {
            if (fields.all { row -> row[i].marked }) {
                return true
            }
        }
        return false
    }

    fun unmarkedSum() = fields.sumOf { row -> row.filter { !it.marked }.sumOf { it.number } }

    fun markNum(num: Int) =
        fields.forEach { row -> row.filter { it.number == num }.forEach { it.marked = true } }

}



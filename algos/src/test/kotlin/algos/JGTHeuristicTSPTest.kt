package algos

import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import org.jgrapht.Graph
import org.jgrapht.alg.tour.GreedyHeuristicTSP
import org.jgrapht.graph.AsSubgraph
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleWeightedGraph
import org.junit.jupiter.api.Test

class JGTHeuristicTSPTest {

    class Table(
        private val width: Int,
        private val height: Int,
        private val flattenTable: List<String>
    ) {

        constructor(table: List<Array<String>>) :
                this(table[0].size, table.size, table.flatMap { it.toList() })

        init {
            val dimension = width * height

            assert(dimension == flattenTable.size) {
                "Table dimension $width x $height ($dimension) should be equal array size (${flattenTable.size})"
            }
        }

        fun forEachCellsIndexed(body: (x: Int, y: Int, newline: Boolean, cell: String) -> Unit) {
            var prevLine = 0
            flattenTable.forEachIndexed { index, s ->
                val (x, y) = index1to2(index)

                val newline = if (prevLine != x) {
                    prevLine = x

                    true
                } else {
                    false
                }

                body(x, y, newline, s)
            }
        }

        fun subTable(skipLeft: Int, skipTop: Int): Table {
            val newFlattenTable = mutableListOf<String>()

            flattenTable.forEachIndexed { index, s ->
                val (x, y) = index1to2(index)

                if (x >= skipLeft && y >= skipTop) {
                    newFlattenTable += s
                }
            }

            return Table(width - skipLeft, height - skipTop, newFlattenTable)
        }

        fun firstLine(): List<String> {
            return flattenTable.subList(0, width)
        }

        private fun index1to2(index: Int): Pair<Int, Int> {
            return index / width to index % width
        }

        private fun index2to1(a: Int, b: Int): Int {
            return (a * width) + b
        }
    }

    @Test
    fun `test test test`() {
        val res = javaClass.getResourceAsStream("/adj.csv")!!

        val csvTable: List<Array<String>> = res.reader(Charsets.UTF_8).use { reader ->
            val parser = CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build()

            val csvReader = CSVReaderBuilder(reader)
                .withCSVParser(parser)
                .build()

            csvReader.use {
                it.readAll()
            }
        }

        val table = Table(csvTable)

        val g: Graph<String, DefaultWeightedEdge> = SimpleWeightedGraph(DefaultWeightedEdge::class.java)

        val map = table.firstLine()
            .asSequence()
            .drop(1)
            .map(String::trim)
            .filterNot(String::isBlank)
            .mapIndexed { i, it -> i to it }
            .associate { it }

        map.entries.forEach { (_, c) ->
            g.addVertex(c)
        }

        val weights = table.subTable(3, 3)

        weights.forEachCellsIndexed { x, y, _, cell ->
            val xv = map[x]
            val yv = map[y]

            if (xv != yv) {
                val e = DefaultWeightedEdge()

                g.addEdge(xv, yv, e)
                g.setEdgeWeight(e, cell.toDouble())
            }
        }

        val subGraph = AsSubgraph(g, setOf("Sierra", "Foxtrot", "Delta", "Tango"))

        val tsp = GreedyHeuristicTSP<String, DefaultWeightedEdge>()

        val tour = tsp.getTour(subGraph)

        println(tour.graph)
    }
}

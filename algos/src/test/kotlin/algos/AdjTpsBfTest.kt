package algos

import org.junit.jupiter.api.Test

class AdjTpsBfTest {

    @Test
    fun `one two three`() {
        val graph = AdjGraph(4)

        graph.addEdge(0, 0, 0.0)
        graph.addEdge(0, 1, 0.0)
        graph.addEdge(0, 2, 0.0)
        graph.addEdge(0, 3, 0.0)
        graph.addEdge(1, 0, 0.0)
        graph.addEdge(1, 1, 0.0)
        graph.addEdge(1, 2, 0.0)
        graph.addEdge(1, 3, 0.0)
        graph.addEdge(2, 0, 0.0)
        graph.addEdge(2, 1, 0.0)
        graph.addEdge(2, 2, 0.0)
        graph.addEdge(2, 3, 0.0)
        graph.addEdge(3, 0, 0.0)
        graph.addEdge(3, 1, 0.0)
        graph.addEdge(3, 2, 0.0)
        graph.addEdge(3, 3, 0.0)

        val result = TpsSolution.find(graph, intArrayOf(0, 1))
    }
}
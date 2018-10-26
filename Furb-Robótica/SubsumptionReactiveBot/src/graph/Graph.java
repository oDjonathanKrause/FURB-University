package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private boolean isDirected;
	private List<Node> nodes = new ArrayList();
	private List<Edge> edges = new ArrayList();

	public Graph(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public void addEdge(String id, float value, Node orig, Node dest) {
		Edge edge = new Edge(id, value, orig, dest);
		orig.edges.add(edge);
		dest.edges.add(edge);
		edges.add(edge);

		if (!nodes.contains(orig))
			nodes.add(orig);
		if (!nodes.contains(dest))
			nodes.add(dest);
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	@Override
	public String toString() {
		String grafoString = "Grafo: \n";

		for (Edge e : edges) {
			grafoString += "(" + e.getOrig().getId() + ", " + e.getDest().getId() + ")\n";
		}

		return grafoString;
	}

	// Gets e sets
	public boolean isDirected() {
		return isDirected;
	}

	public void setDirected(boolean isDirected) {
		this.isDirected = isDirected;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	

}

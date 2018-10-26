package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private float dist;
	private String id, status;
	private Node parent;
	public List<Edge> edges;
	List<Node> adjNodes;

	public Node(String id) {
		this.id = id;
		this.adjNodes = new ArrayList();
		this.edges = new ArrayList();
	}
	
	public Edge getEdgeByNode(Node n) {
		return null;
	}

	public List<Node> getAdjs(boolean isDirected) {
		for (Edge edge : edges) {
			// Se a origem da aresta atual for this, add o vértice de destino na lista
			if (edge.getOrig().equals(this))
				this.adjNodes.add(edge.getDest());
			// Se o destino da aresta atual for this e o grafo não for dirigido, add o vértice de origem na lista
			else if (!isDirected && edge.getDest().equals(this))
				this.adjNodes.add(edge.getOrig());
		}

		return this.adjNodes;
	}

	// Gets e sets
	public Node() { 	}
	public float getDist() {
		return dist;
	}

	public void setDist(float dist) {
		this.dist = dist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public List<Node> getAdjNodes() {
		return adjNodes;
	}

	public void setAdjNodes(List<Node> adjNodes) {
		this.adjNodes = adjNodes;
	}
}

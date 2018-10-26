package graph;

public class Edge {

    private String id;
    private float value;
    private Node orig, dest;

    public Edge(String id, float value, Node orig, Node dest)
    {
        this.id = id;
        this.value = value;
        this.dest = dest;
        this.orig = orig;
    }
    
    // Gets e sets
    public Edge() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public Node getOrig() {
		return orig;
	}

	public void setOrig(Node orig) {
		this.orig = orig;
	}

	public Node getDest() {
		return dest;
	}

	public void setDest(Node dest) {
		this.dest = dest;
	}
}

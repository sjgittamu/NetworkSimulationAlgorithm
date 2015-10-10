package graph;


public class Edge implements Comparable<Edge>{
	int sourceNode;
	int targetNode;
	Integer weight;
	
	public Edge(int sourceNode, int targetNode, int weight) {
		super();
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		this.weight = weight;
	}
	public Edge(int weight) {
		super();
		this.weight = weight;
	}
	public int getTargetNode() {
		return targetNode;
	}
	public void setTargetNode(int targetNode) {
		this.targetNode = targetNode;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return targetNode + "," + weight;
	}
	public int getSourceNode() {
		return sourceNode;
	}
	public void setSourceNode(int sourceNode) {
		this.sourceNode = sourceNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sourceNode;
		result = prime * result + targetNode;
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (sourceNode != other.sourceNode)
			return false;
		if (targetNode != other.targetNode)
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	@Override
	public int compareTo(Edge e) {
		return weight.compareTo(e.getWeight());
	}
	
	
	
}

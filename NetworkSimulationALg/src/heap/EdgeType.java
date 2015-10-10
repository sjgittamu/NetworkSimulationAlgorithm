package heap;

public class EdgeType implements Comparable<EdgeType>{
	Integer h;			//vertex
	Integer d;			//d[v] vector
	
	public EdgeType(Integer h, Integer d) {
		super();
		this.h = h;
		this.d = d;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public Integer getD() {
		return d;
	}

	public void setD(Integer d) {
		this.d = d;
	}

	@Override
	public int compareTo(EdgeType o) {
		// TODO Auto-generated method stub
		return d.compareTo(o.d); 
	}
	
}

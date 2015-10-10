package maxBandwidthPath;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DijkstraMaxCapPath {
	List<Edge> fringes = new ArrayList<Edge>(); //the weight here would be d[v] and not absolute edge weight
	private static int MAX_SIZE;
	int infinity = Integer.MIN_VALUE;
	int[] status ;
	int[] d ;
	int[] dad ;
 	
	public DijkstraMaxCapPath(int size) {
		super();
		MAX_SIZE = size;
		status = new int[MAX_SIZE];
		d = new int[MAX_SIZE];
		dad = new int[MAX_SIZE];
		
	}
	
	public long dikjskta(int source,Graph graph, int destination){
		//1.
		long startTime = System.currentTimeMillis();
		for(int v=1;v<MAX_SIZE;v++){
			status[v]=Status.UNSEEN.getValue();
		//	dad[v] = 0;
		///	d[v] = -999999; //not doing these as following class logic and thus an extra step of if() inside the for loop
		}
		//2.
		status[source] = Status.INTREE.getValue();
		d[source] = infinity;//-infinity
		dad[source] = 0;
		//3.
		List<Edge> edges = graph.getGraph().get(source);
		for (Edge edge : edges) {
			int v = edge.getTargetNode();
			status[v] = Status.FRINGE.getValue();
			dad[v] = source;
			d[v] = edge.getWeight();
			fringes.add(new Edge(source,v, d[v]));
		}
		//4.
		Iterator<Edge> iter = fringes.iterator(); 
		while(iter.hasNext()){
			Edge fr = getMaxtWeightEdge(fringes);
			int v = fr.getTargetNode();
			d[v] = fr.getWeight();
			if(d[v] == infinity){		//if there are more than one connected components in graph
				break;
			}
			status[v] = Status.INTREE.getValue(); 
			//System.out.println("Intree for :"+v);
			/*if(v==destination){
				//System.out.println("found destination, terminating...");
				return System.currentTimeMillis() - startTime;
			}*/
			//iter.remove();//delete as it became in tree and no more fringe
			List<Edge> edge = graph.getGraph().get(v);
			for (Edge edge1 : edge) {
				int w = edge1.getTargetNode();
				int maxCap = Math.max(d[w], Math.min(d[v],edge1.getWeight()));
				if(status[w] == Status.UNSEEN.getValue()){
					status[w] = Status.FRINGE.getValue();
					dad[w] = v;
					d[w] = d[v] + edge1.getWeight();
					fringes.add(new Edge(v, w, d[w]));
				}else if(status[w] == Status.FRINGE.getValue() && maxCap > d[w]){
					int originalWt = d[w];
					d[w] = maxCap;
					dad[w] = v;
					//update with the new d[w] value
					for (Edge edge2 : fringes) {
						if(edge2.equals(new Edge(v, w, originalWt))){ //or should it be just compared to edge1?
							edge2.setWeight(d[w]);       //? doubt will it be modified when i modify below as the scope of edge is finished | now fixed
						}
					}
				}
			}
		}
		return System.currentTimeMillis() - startTime;
		//System.out.println("The distance vector from source : "+source +"is : "+d[]);
		//TODO : what to return now? whole path maybe
	}

	private Edge getMaxtWeightEdge(List<Edge> fringes) {
		int max = Integer.MIN_VALUE;
		Edge maxEdge = new Edge(max);
	    for(int i=0; i<fringes.size(); i++){
	        if(fringes.get(i).getWeight() > maxEdge.getWeight()){
	        	maxEdge = fringes.get(i);
	        }
	    }
	    fringes.remove(maxEdge);    //removing here instead of up
	    return maxEdge;
	}
}

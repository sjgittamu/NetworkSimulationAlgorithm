package maxBandwidthPath;

import graph.Edge;
import graph.Graph;
import heap.MHeap;
import heap.EdgeType;

import java.util.List;

public class DijkstraMaxCapPathHeap {
	private static int MAX_SIZE;
	int infinity = Integer.MIN_VALUE;
	int[] status ;
	int[] d ;
	int[] dad ;
	MHeap<EdgeType> fringes = new MHeap<EdgeType>(); //the weight here would be d[v] and not absolute edge weight
 	
	public DijkstraMaxCapPathHeap(int size) {
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
			EdgeType type = new EdgeType(v,d[v]);
			fringes.add(type);
		}
		//4.
		MHeap<Edge> heap = new MHeap<Edge>(); 
		for (List<Edge> e : graph.getGraph().values()) {
				//e.iterator();
		}
		//int size = fringes.getHeapSize(); 
		while(!fringes.isEmpty()){
			EdgeType type = fringes.peek(); 
			int v = type.getH();
			d[v] = type.getD();
			if(d[v] == infinity){		//if there are more than one connected components in graph
				break;
			}
			status[v] = Status.INTREE.getValue(); 
			//System.out.println("Intree for :"+v);
			/*if(v==destination){
				//System.out.println("found destination, terminating...");
				return System.currentTimeMillis() - startTime;
			}*/
			fringes.remove();    //delete the max value
			//iter.remove();//delete as it became in tree and no more fringe
			List<Edge> edge = graph.getGraph().get(v);
			for (Edge edge1 : edge) {
				int w = edge1.getTargetNode();
				int maxCap = Math.max(d[w], Math.min(d[v],edge1.getWeight()));			
				if(status[w] == Status.UNSEEN.getValue()){
					status[w] = Status.FRINGE.getValue();
					dad[w] = v;
					d[w] = d[v] + edge1.getWeight();
					fringes.add(new EdgeType(w, d[w]));
				}else if(status[w] == Status.FRINGE.getValue() && maxCap > d[w]){
					d[w] = maxCap;
					dad[w] = v;
					//update with the new d[w] value
					fringes.removeType(new EdgeType(w, 0));	// as while removing u dont check the d[v] value
					fringes.add(new EdgeType(w, d[w]));
				}
			}
		}
		return System.currentTimeMillis() - startTime;
		//System.out.println("The distance vector from source : "+source +"is : "+d[]);
		//TODO : what to return now? whole path maybe
	}
}

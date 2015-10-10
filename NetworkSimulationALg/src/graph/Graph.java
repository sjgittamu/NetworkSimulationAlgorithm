package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	static int MAX_NODES = 5000;
	Map<Integer, List<Edge>> graph;
	//here we just store the destination vertex in egde as we dont want to replicate the source vertex value so many times in adjacency list
	Set<Edge> edges;
	
	public Graph() {
		super();
		this.graph =  new HashMap<Integer, List<Edge>>();
		edges = new HashSet<Edge>();
	}
	public Map<Integer, List<Edge>> getGraph() {
		return graph;
	}
	public void setGraph(Map<Integer, List<Edge>> graph) {
		this.graph = graph;
	}
	
	public Set<Edge> getDistinctEdges(){
		return edges;
	}
	public void createGraph(int nodes, int numEdges, int maxWeight){
		List<Edge> edgeToVertices;
		for(int j=1; j<=nodes ; j++){
			if(graph.get(j) == null){
				edgeToVertices = new ArrayList<Edge>(numEdges);
			}else{
				edgeToVertices = graph.get(j);
			}
			int size = edgeToVertices.size();
			for(int i=1; i <= numEdges-size ; i++){
				boolean vertexAdded = addVertex(nodes, edgeToVertices, j, numEdges,maxWeight);
				int count =0;
				while(!vertexAdded && j != MAX_NODES && count<50){
					vertexAdded = addVertex(nodes, edgeToVertices, j, numEdges,maxWeight);
					count++;
				}
			}
			graph.put(j, edgeToVertices);
		}
		//return graph;
	}
	private boolean addVertex(int nodes, List<Edge> edgeToVertices, int j, int numEdges, int maxWeight) {
		int nextVertex;
		int edgeWeight;
		nextVertex = j+(int) Math.round((Math.random() * (nodes-j)));		//select some vertex which is at least after j
		//nextVertex = nextVertex>j ? 0:nextVertex;
		edgeWeight = (int) Math.round((Math.random() * maxWeight));
		Edge edge = new Edge(edgeWeight);
		//edge.setWeight();
		while(nextVertex == j){									//vertex pointing to itself 
			nextVertex = (int) Math.round((Math.random() * nodes));
		}
		List<Edge> neighborList = graph.get(nextVertex);
		//while(neighborList.contains(arg0))
		if(neighborList == null){								// ADD this in the neighbors list also
			neighborList = new ArrayList<Edge>(numEdges);
			edge.setTargetNode(j);
			edge.setSourceNode(nextVertex);
			neighborList.add(edge);
			graph.put(nextVertex, neighborList);
		}else if(neighborList.size() >= numEdges){			//cant add more
			//addVertex(nodes, edgeToVertices, j, numEdges, maxWeight);
			return false;
		}
		else if(neighborList.size() < numEdges){			//only if neighbor has capacity
			if(neighborList.contains(new Edge(nextVertex, j, 0))){
				return false;
			}
			else{
				edge.setTargetNode(j);
				edge.setSourceNode(nextVertex);
				neighborList.add(edge);
				graph.put(nextVertex, neighborList);
			}
		}
		Edge edge1 = new Edge(edgeWeight);
		//edge1.setWeight();
		edge1.setTargetNode(nextVertex);
		edge1.setSourceNode(j);
		edgeToVertices.add(edge1);
		edges.add(edge);
		return true;
	}
	
	public boolean connectSourceDetination(int source, int dest, int maxWeight, int nodes){
		int edgeNum = (int) Math.round((Math.random() * 50));			// add a random num of egdes between 1 and 50
		int edgeWeight;
		int nextVertex;
		int lastVertex = source;
		while(edgeNum > 0){
			edgeWeight = (int) Math.round((Math.random() * maxWeight));
			if(edgeNum != 1){
				nextVertex = (int) Math.round((Math.random() * (nodes)));
				graph.get(lastVertex).add(new Edge(lastVertex, nextVertex, edgeWeight));
				lastVertex = nextVertex;
			}else{
				graph.get(lastVertex).add(new Edge(lastVertex, dest, edgeWeight));
			}
			edgeNum--;			
		}
		return true;
	}

}

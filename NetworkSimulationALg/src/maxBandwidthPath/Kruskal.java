package maxBandwidthPath;

import graph.Edge;
import java.util.Stack;
import graph.Graph;
import heap.MHeap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Kruskal {
	//MaxHeap heap = new MaxHeap();
	private static int MAX_SIZE;
	int[] rank;
	int[] dad;
	List<Edge> edges = new ArrayList<Edge>();
	
	
	public Kruskal(int size) {
		super();
		MAX_SIZE = size;
		rank = new int[MAX_SIZE];
		dad = new int[MAX_SIZE];

	}
	public long performKruskal(Graph graph, int source, int destination){
		//sortedArray = heap.sort();
		//1. convert the heap list to edges list and pass that list to the for loop somehow in edges
		long startTime = System.currentTimeMillis();
		MHeap<Edge> heap = saveGraphInHeap(graph);
		long time = System.currentTimeMillis() - startTime;
		System.out.println("Time for Saving in heap: "+time);
		Set<Edge> maxSpanningTree = new HashSet<Edge>();
		//long heapTime = System.currentTimeMillis() - startTime;
	//	System.out.println("size "+ heap.getSize()+"Time taken in heap sort: "+ heapTime);
		//List<Edge> finalEdges = new ArrayList<Edge>();
		
		makeSet(); // initialize
		Edge edge;
		//for(int i=sortedEdges.size(); i>0; i--){ // because it is max capacity path so we need biggest edge first
		while( (edge=heap.remove()) != null){
			int r1 = find(edge.getSourceNode());
			int r2 = find(edge.getTargetNode());
			if(r1 != r2){
				maxSpanningTree.add(edge); //added in list instead of tree as its not gonna be used
				union (r1,r2);
			}
			if(maxSpanningTree.size() == MAX_SIZE-2){
			//if(edge.getTargetNode() == destination){
				return System.currentTimeMillis() - startTime;
			}
		}
		
		return System.currentTimeMillis() - startTime;
		
	}
	private MHeap<Edge> saveGraphInHeap(Graph graph) {
		MHeap<Edge> heap = new MHeap<Edge>(); 
		for (Edge edge : graph.getDistinctEdges()) {
			heap.add(edge);			
		}
		return heap;
	}
	private void makeSet(){
		for (int i=1 ; i <MAX_SIZE ; i++) {
			rank[i] = 0;
			dad[i] = 0;
		}
	}
	private void union(int r1, int r2){
		if(rank[r1] > rank[r2])
			dad[r2] = r1;
		else if(rank[r1] < rank[r2])
			dad[r1] = r2;
		else if(rank[r1] == rank[r2]){
			dad[r2] = r1;
			rank[r1]++;
		}
	}
	private int find(int r){
		Stack<Integer> H = new Stack<Integer>();
		int h = r;
		int w;
		while(dad[h] != 0){
			H.push(h);
			h=dad[h];
		}
		while(!H.empty()){
			w=H.pop();
			dad[w] = h;
		}
		return h;
	}
}

package test;

import graph.Graph;
import maxBandwidthPath.DijkstraMaxCapPath;
import maxBandwidthPath.DijkstraMaxCapPathHeap;
import maxBandwidthPath.Kruskal;

public class RunNetwork {
	public static void main(String[] args) {
		int nodes = 5000;
		DijkstraMaxCapPathHeap dij = new DijkstraMaxCapPathHeap(nodes+1);
		DijkstraMaxCapPath dij1 = new DijkstraMaxCapPath(nodes+1);
		Kruskal krus = new Kruskal(nodes+1);
		int sparseEdges;
		if(Integer.parseInt(args[0]) == 0){
			sparseEdges = 6;
			System.out.println("Running for a set of Sparse graphs");
		}
		else{
			sparseEdges = 1000;
			System.out.println("Running for a set of Dense graphs");
		}
		int maxWeight = 1000;
		long dijArrAvgTotal =0 ;
		long dijHeapAvgTotal =0 ;
		long krusAvgTotal =0 ;
		
		for(int j=1; j<=5;j++){
			Graph graph = new Graph();
			long[] dijArray=new long[5];
			long[] dijHeap=new long[5];
			long[] krusk=new long[5];
			long dijArrAvg =0 ;
			long dijHeapAvg =0 ;
			long krusAvg =0 ;
			System.out.println("Running for Graph: "+j);
			graph.createGraph(nodes, sparseEdges,maxWeight);
			for(int i=0; i<5;i++){
				int source =(int) Math.round((Math.random() * (nodes)));
				int dest = (int) Math.round((Math.random() * (nodes)));
				graph.connectSourceDetination(source, dest, maxWeight, nodes);
				System.out.println("source : "+source+" and destination : "+dest);

				dijArray[i] = dij1.dikjskta(source, graph,dest);
				dijArrAvg += dijArray[i];
				System.out.println("Dijkstra without heap: "+dijArray[i]);

				dijHeap[i] = dij.dikjskta(source, graph,dest);
				dijHeapAvg +=dijHeap[i];
				System.out.println("Dijkstra with heap : "+ dijHeap[i]);

				krusk[i] = krus.performKruskal(graph, source, dest);
				krusAvg += krusk[i];
				System.out.println("Kruskal: "+krusk[i]);
			}
			dijArrAvgTotal += dijArrAvg/5;
			dijHeapAvgTotal += dijHeapAvg/5;
			krusAvgTotal += krusAvg/5;
			System.out.println("Average of Dijkstra without heap: "+dijArrAvg/5+" and Dijkstra with heap : "+dijHeapAvg/5+" and Kruskal: "+krusAvg/5);
			System.out.println(" ");
		}
		System.out.println("");
		System.out.println("Accross all the above graphs, Averages are: ");
		System.out.println("Average of Dijkstra without heap: "+dijArrAvgTotal/5+" and Dijkstra with heap : "+dijHeapAvgTotal/5+" and Kruskal: "+krusAvgTotal/5);

	}

}

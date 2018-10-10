package com.example.demo.graph;

public class Edge {

    Vertex edgeEndNode;

    EdgeType edgeType;

    boolean visited;


    public Edge(Vertex edgeEndNode, EdgeType edgeType) {
        this.edgeEndNode = edgeEndNode;
        this.edgeType = edgeType;
    }
}

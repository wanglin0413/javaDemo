package com.example.demo.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    int orgType;
    String orgUid;

    boolean hidden;
    boolean limit;
    boolean isRoot;
    boolean isLeaf;

    List<Vertex> hidden_whiteList = new ArrayList<>();

    List<String> ancestors = new ArrayList<>();

    List<Edge> edges;

    public Vertex(String orgUid) {
        this.orgUid = orgUid;
    }

    public void add(Edge edge){

        edges.add(edge);
    }

}

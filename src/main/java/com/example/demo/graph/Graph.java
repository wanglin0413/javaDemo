package com.example.demo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {

    static Map<String, Vertex> vertexMap = new HashMap<>();

    static Set<Edge> allEdges = new HashSet<>();

    public static Map<String, Vertex> createGraph(){

        Map<String, Vertex> vertexMap = new HashMap<>();

        List<String> edges = new ArrayList<>();


        edges.add("A");
        edges.add("AB");
        edges.add("A");
        edges.add("AC");
        edges.add("AB");
        edges.add("ABA");
        edges.add("AB");
        edges.add("ABB");
        edges.add("ABB");
        edges.add("ABBA");
        edges.add("AC");
        edges.add("ACA");
        edges.add("A");
        edges.add("A1");
        edges.add("AC");
        edges.add("AC1");
        edges.add("ACA");
        edges.add("ACA1");

        while (!edges.isEmpty()){
            String beginOrg = edges.remove(0);
            String endOrg = edges.remove(0);

            Vertex begin = vertexMap.get(beginOrg);
            Vertex end = vertexMap.get(endOrg);
            if(begin == null){
                begin = new Vertex(beginOrg);
                vertexMap.put(begin.orgUid, begin);
            }
            if( end == null){
                end = new Vertex(endOrg);
                vertexMap.put(end.orgUid, end);
            }
            begin.add(new Edge(end, EdgeType.CHILD));
            end.add(new Edge(begin, EdgeType.PARENT));
        }

        return vertexMap;
    }


    public static boolean hasHiddenWhitePath(String staff, String org){
        return false;
    }

    public static void findAllEdges(String start){

        Vertex startVer = vertexMap.get(start);
        List<Edge> edges = startVer.edges;
        if(startVer.limit){
            for(Edge each : edges){
                List<Edge> path = new ArrayList<>();
                if(each.edgeType == EdgeType.LIMIT_WHITELIST){
                    iteratorOrgOneWay(each, path);
                }else if(each.edgeType == EdgeType.PARENT){
                    iteratorOrgOneWay(each, path, EdgeType.PARENT);
                }
            }
        }else {
            for(Edge each : edges){
                List<Edge> path = new ArrayList<>();
                iteratorOrgEdges(each, path);
            }
        }
    }



    public static void iteratorOrgEdges(Edge edge, List<Edge> path){

        Vertex endVer = edge.edgeEndNode;

        List<Edge> edges = endVer.edges;
        for(Edge each : edges){
            if(edge.edgeType == EdgeType.LIMIT_WHITELIST){
                iteratorOrgOneWay(each, path, each.edgeType);
            }
            if(edge.edgeType == EdgeType.PARENT){
                iteratorOrgEdges(edge, path);
            }
        }
    }

    private static void iteratorOrgOneWay(Edge each, List<Edge> path) {
    }

    public static void iteratorOrgOneWay(Edge edge, List<Edge> path, EdgeType type){
        Vertex endVer = edge.edgeEndNode;
        if(endVer.isRoot || endVer.isLeaf){
          allEdges.addAll(path);
          return;
        }
        List<Edge> edges1 = endVer.edges;
        for(Edge each: edges1){
            if(each.edgeType == type){
                path.add(each);
                iteratorOrgOneWay(each, path, type);
            }
        }

    }

    public static void main(String[] args) {
        Map<String, Vertex> map = createGraph();
    }
}

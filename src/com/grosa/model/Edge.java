package com.grosa.model;

public class Edge {

    private Vertex startPoint;
    private Vertex endPoint;

    public Edge(Vertex startPoint, Vertex endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Vertex getStartPoint() {
        return startPoint;
    }

    public Vertex getEndPoint() {
        return endPoint;
    }
}

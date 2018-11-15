package com.grosa.serial.model;

public class Edge {

    private Vertex startPoint;
    private Vertex endPoint;

    public Edge(Vertex startPoint, Vertex endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }

    public Vertex getStartPoint() {
        return startPoint;
    }

    public Vertex getEndPoint() {
        return endPoint;
    }
}

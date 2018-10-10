package com.example.demo.graph;

public enum EdgeType {

    PARENT,
    BETWEEN_COMPANY_PARENT,
    CHILD,
    HIDDEN_WHITELIST,
    LIMIT_WHITELIST;

    public boolean isEabPath(){
        return this.equals(PARENT) || this.equals(CHILD);

    }
}

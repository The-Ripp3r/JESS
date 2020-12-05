package com.example.jess.models;

public class Community {

    private String name;
    private String description;
    private int numMembers;

    public Community(String name,
                     String description,
                     int numMembers) {
        this.name = name;
        this.description = description;
        this.numMembers = numMembers;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumMembers() {
        return numMembers;
    }

    @Override
    public String toString() {
        return "Community{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", numMembers=" + numMembers +
                '}';
    }
}

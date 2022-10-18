package edu.hit.java.exp3;

public class StudentInfo {
	private String id;
    private String name;
    private float bmi;
    private float height;
    private float weight;
    
    public StudentInfo() {}
    public String getID() { return id; }
    public float getBMI() { return bmi; }
    public String getName() { return name; }
    public float getHeight() { return height; }
    public float getWeight() { return weight; }
    
    public void setID(String id) { this.id = id; }
    public void setBMI(float bmi) { this.bmi = bmi; }
    public void setName(String name) { this.name = name; }
    public void setHeight(float height) { this.height = height; }
    public void setWeight(float weight) { this.weight = weight; }
}
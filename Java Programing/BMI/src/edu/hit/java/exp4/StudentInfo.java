package edu.hit.java.exp4;

public class StudentInfo {
	private String id;
    private String name;
    private float bmi;
    private float height;
    private float weight;
    private String condHealth;
    
    public StudentInfo(String id, String name, float height, float weight) {
    	this.id = id;
    	this.name = name;
    	this.height = height;
    	this.weight = weight;
    	this.bmi = (float) (this.weight / Math.pow(this.height / 100, 2.0));
    	this.condHealth = checkHealth(bmi);
    }
    public String getID() { return id; }
    public float getBMI() { return bmi; }
    public String getName() { return name; }
    public float getHeight() { return height; }
    public float getWeight() { return weight; }
    public String getHealthCondition() { return condHealth; }
    
    public void setID(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setHeight(float height) { this.height = height; }
    public void setWeight(float weight) { this.weight = weight; }
    public void setHealthCondition(String condHealth) { this.condHealth = condHealth; }
    
    private String checkHealth(float bmi) {
		if (bmi <= 18.5)
			return "Underweight";
		else if (bmi <= 23)
			return "Normal Range";
		else if (bmi <= 25)
			return "Overweight--At Risk";
		else if (bmi <= 30)
			return "Overweight--Moderately Obese";
		else
			return "Overweight--Severely Obese";
	}
}
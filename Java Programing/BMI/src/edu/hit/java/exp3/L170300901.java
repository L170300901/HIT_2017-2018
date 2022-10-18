package edu.hit.java.exp3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class L170300901 {
	private static Scanner in = new Scanner(System.in);
	private static ArrayList<StudentInfo> listStudents = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("Welcome To The Students' Healthy Information System!\n");
		printMenu();
		in.close();
	}

	private static void printMenu() {
		int selMenu;

		while (true) {
			System.out.println("1. Create students at random");
			System.out.println("2. Print students' information");
			System.out.println("3. Sort the students by IDs");
			System.out.println("4. Sort the students by Names");
			System.out.println("5. Sort the students by Heights");
			System.out.println("6. Sort the students by Weights");
			System.out.println("7. Sort the students by BMIs");
			System.out.println("8. Print statics");
			System.out.println("9. Exit the students' healthy information system\n");
			System.out.print("Please input the number you want to do: ");

			selMenu = Integer.parseInt(in.nextLine());
			switch (selMenu) {
			case 1:
				inputStudents();
				break;
			case 2:
				printStudents();
				break;
			case 3:
				sortByID();
				break;
			case 4:
				sortByName();
				break;
			case 5:
				sortByHeight();
				break;
			case 6:
				sortByWeight();
				break;
			case 7:
				sortByBMI();
				break;
			case 8:
				printStatics();
				break;
			case 9:
				System.out.println("Goodbye! Thank you for using.");
				return;
			default:
				System.out.print("You input the wrong number. Please input again.");
				break;
			}
			System.out.print("\n\n");
		}
	}

	private static void inputStudents() {
		int numInputStudents, i;
		Random rand = new Random();

		System.out.print("\nPlease input the numbers of the students: ");
		numInputStudents = Integer.parseInt(in.nextLine());

		for (i = 0; i < numInputStudents; i++) {
			StudentInfo student = new StudentInfo();

			student.setID("Hit" + (int) getRandomValueRange(1170000000, 1171000000));
			student.setName(getGeneratedRandomName(3));
			student.setHeight(getRandomValueRange(140, 210));
			student.setWeight(getRandomValueRange(30, 150));
			student.setBMI(calculateBMI(student.getHeight(), student.getWeight()));

			listStudents.add(student);
		}
	}

	private static float getRandomValueRange(int min, int max) {
		return (float) ((Math.random() * (max - min + 1)) + min);
	}

	private static String getGeneratedRandomName(int length) {
		char[] randName = new char[length];

		for (int i = 0; i < length; i++)
			randName[i] = (char) Math.round((int) getRandomValueRange((int) 'a', (int) 'z'));
		randName[0] = Character.toUpperCase(randName[0]);

		return String.valueOf(randName);
	}

	public static float calculateBMI(float height, float weight) {
		return (float) (weight / Math.pow(height / 100, 2.0));
	}

	public static void printStudents() {
		StudentInfo student = new StudentInfo();

		for (int i = 0; i < listStudents.size(); i++) {
			System.out.printf(listStudents.get(i).getID() + "\t" + listStudents.get(i).getName() + "\t"
					+ String.format("%.2f", listStudents.get(i).getHeight()) + "\t"
					+ String.format("%.2f", listStudents.get(i).getWeight()) + "\t"
					+ String.format("%.2f", listStudents.get(i).getBMI()) + "\t");
			checkHealth(listStudents.get(i).getBMI());
		}
	}

	public static void checkHealth(float bmi) {
		if (bmi <= 18.5)
			System.out.println("Underweight");
		else if (bmi <= 23)
			System.out.println("Normal Range");
		else if (bmi <= 25)
			System.out.println("Overweight--At Risk");
		else if (bmi <= 30)
			System.out.println("Overweight--Moderately Obese");
		else
			System.out.println("Overweight--Severely Obese");
	}

	public static void sortByID() {
		listStudents.sort(Comparator.comparing(StudentInfo::getID));
	}

	public static void sortByName() {
		listStudents.sort(Comparator.comparing(StudentInfo::getName));
	}

	public static void sortByHeight() {
		listStudents.sort(Comparator.comparing(StudentInfo::getHeight));
	}

	public static void sortByWeight() {
		listStudents.sort(Comparator.comparing(StudentInfo::getWeight));
	}

	public static void sortByBMI() {
		listStudents.sort(Comparator.comparing(StudentInfo::getBMI));
	}

	public static void printStatics() {
		float maxBMI, minBMI, sumBMI;
		float maxHeight, minHeight, sumHeight;
		float maxWeight, minWeight, sumWeight;

		// Initialize
		maxBMI = minBMI = sumBMI = listStudents.get(0).getBMI();
		maxHeight = minHeight = sumHeight = listStudents.get(0).getHeight();
		maxWeight = minWeight = sumWeight = listStudents.get(0).getWeight();

		// Calculate
		for (int i = 1; i < listStudents.size(); i++) {
			StudentInfo currStudent = listStudents.get(i);

			sumBMI += currStudent.getBMI();
			sumHeight += currStudent.getHeight();
			sumWeight += currStudent.getWeight();

			if (maxBMI < currStudent.getBMI())
				maxBMI = currStudent.getBMI();
			if (minBMI > currStudent.getBMI())
				minBMI = currStudent.getBMI();
			if (maxHeight < currStudent.getHeight())
				maxHeight = currStudent.getHeight();
			if (minHeight > currStudent.getHeight())
				minHeight = currStudent.getHeight();
			if (maxWeight < currStudent.getWeight())
				maxWeight = currStudent.getWeight();
			if (minWeight > currStudent.getWeight())
				minWeight = currStudent.getWeight();
		}

		// Print result
		float avgBMI = sumBMI / listStudents.size();
		float avgHeight = sumHeight / listStudents.size();
		float avgWeight = sumWeight / listStudents.size();

		System.out.println("Average of height: " + String.format("%.2f", avgHeight) + "\n" + "Biggest of height: "
				+ String.format("%.2f", maxHeight) + "\n" + "Smallest of height: " + String.format("%.2f", minHeight)
				+ "\n");
		System.out.println("Average of weight: " + String.format("%.2f", avgWeight) + "\n" + "Biggest of weight: "
				+ String.format("%.2f", maxWeight) + "\n" + "Smallest of weight: " + String.format("%.2f", minWeight)
				+ "\n");
		System.out.println("Average of BMI: " + String.format("%.2f", avgBMI) + "\n" + "Biggest of BMI: "
				+ String.format("%.2f", maxBMI) + "\n" + "Smallest of BMI: " + String.format("%.2f", minBMI) + "\n");
	}
}

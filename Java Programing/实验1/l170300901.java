package edu.java.exp1; 

import java.util.ArrayList; 

import java.util.Arrays; 

import java.util.Comparator; 

import java.util.Scanner; 

public class l170300901  {  

	static Scanner in = new Scanner(System.in);   

    // 기존의 ids, names, heights, weights, bmis 배열 대신 Student 데이터 클래스 이용 

    public static final class Student { 
    	
    private String id; 
    private String name; 
    private float height; 
    private float weight; 
    private float bmi; 
    public Student() {} 

       // 이클립스에서는 Ctrl + Space를 이용해 getter와 setter 메서드를 자동 완성할 수 있습니다. 

      // 또는 Source -> Generate Getters and Setters...를 이용할 수도 있습니다. 
     
    public float getBmi() { 

          return bmi; 

       } 

       public String getId() { 

          return id; 

       } 

       public void setId(String id) { 

          this.id = id; 

       } 

       public String getName() { 

          return name; 

       } 

       public void setName(String name) { 

          this.name = name; 
       } 

       public void setHeight(float height) { 

          this.height = height; 

       } 
        public void setBmi(float bmi) { 

          this.bmi = bmi; 

       } 

       public float getHeight() { 

          return height; 

       } 

       public float getWeight() { 

          return weight; 

       } 

       public void setWeight(float weight) { 

         this.weight = weight; 

       } 

     } 

    // 기존의 StudentsSum과 sortedIndex 대신 ArrayList 컨테이너에 일괄 저장 

    private static final ArrayList<Student> students = new ArrayList<>(); 

    // 배열로도 가능 

    private static final Student[] students2 = new Student[3000]; 

    public static void main(String[] args) 

    {  

       System.out.println("Welcome To The Students' Healthy Information System!\n");  

      menu();    

       in.close();  

    }    

    public static void menu()  

    {    

       // 불필요한 input 지역변수 제거 

       while(true) 

       {    

          System.out.println("1. Input students' information"); 

          System.out.println("2. Print students' information"); 

          System.out.println("3. Sort the students by IDs"); 

          System.out.println("4. Sort the students by Names"); 

          System.out.println("5. Sort the students by Heights"); 

          System.out.println("6. Sort the students by Weights"); 

          System.out.println("7. Sort the students by BMIs"); 

          System.out.println("8. Exit the students' healthy information system\n"); 

          System.out.print("Please input the number you want to do: ");    

          switch(Integer.parseInt(in.nextLine()))   

          {    

             case 1: inputStudents(); break;    

             case 2: printStudents(); break;    

             case 3: sortByIDs(); break; // start, end가 전체 범위로만 이용하는 것 같아 그냥 뺐습니다.    

             case 4: sortByNames(); break;    

             case 5: sortByHeights(); break;    

             case 6: sortByWeights(); break;    

             case 7: sortByBMI(); break;   

             case 8: System.out.println("Goodbye! Thank you for using."); return;    

             default: System.out.print("You input the wrong number. Please input again."); break;  

          }   

          System.out.println(); 

       } 

    }    

    public static void inputStudents()  

    {    

       System.out.print("Please input the numbers of the students: ");  

       int from = students.size() + 1; 

       int to = from + Integer.parseInt(in.nextLine()); 

       System.out.println();      

       for(int i = from; i < to; i++)   

       { 

          Student student = new Student(); 

          System.out.print("Please input the ID of the No." + i + " student: "); 

          student.setId(in.nextLine()); 

          System.out.print("\nPlease input the name of the No." + i + " student: ");    

          student.setName(in.nextLine());      

          System.out.print("\nPlease input the height of the No." + i + " student: ");  

          student.setHeight(Float.parseFloat(in.nextLine()));   

          System.out.print("\nPlease input the weight of the No." + i + " student: ");    

          student.setWeight(Float.parseFloat(in.nextLine()));       

          System.out.println();     

          student.setBmi(calcBMI(student.getWeight(), student.getHeight()));  

          checkHealth(student.getBmi()); 

          // 배열에 추가 

         students2[i-1] = student; 

          // 리스트에 추가 

          students.add(student); 

       }      

    } 

    public static float calcBMI(float weight, float height) { 

       return weight / (height * height); 

    } 

    public static void checkHealth(float bmis)  

    {   

       if(bmis <= 18.5)    

       System.out.println("Underweight");   

       else if(bmis <= 23)    

       System.out.println("Normal Range");   

       else if(bmis <= 25)    

       System.out.println("Overweight--At Risk");  

       else if(bmis <= 30)    

       System.out.println("Overweight--Moderately Obese");   

       else    

       System.out.println("Overweight--Severely Obese"); 

    }      

    public static void printStudents()  

   {   

       for(int i = 0; i < students.size(); i++)   

       System.out.printf(students.get(i).getId() + "\t" + students.get(i).getName() + "\t%.2f\t%.2f\t%.2f\n", students.get(i).getHeight(), students.get(i).getWeight(), students.get(i).getBmi()); 

       // 배열은 아래처럼 

       //for(int i = 0; i < students.size(); i++) 

       // System.out.printf(students2[i].getId() + "\t" + students2[i].getName() + "\t%.2f\t%.2f\t%.2f\n", students2[i].getHeight(), students2[i].getWeight(), students2[i].getBmi()); 

    } 

    public static void sortByIDs()  

    {  

       students.sort(Comparator.comparing(Student::getId)); 

       // 반대 방향 정렬하려면 아래처럼 

       // students.sort(Comparator.comparing(Student::getId).reversed()); 

       Arrays.sort(students2, 0, students.size(), Comparator.comparing(Student::getId)); 

    }    

    public static void sortByNames()  

    {  

       students.sort(Comparator.comparing(Student::getName)); 

       Arrays.sort(students2, 0, students.size(), Comparator.comparing(Student::getName)); 

    }    

    public static void sortByHeights()  

    {   

       students.sort(Comparator.comparing(Student::getHeight)); 

       Arrays.sort(students2, 0, students.size(), Comparator.comparing(Student::getHeight)); 

    }  

    public static void sortByWeights()  

    {  

       students.sort(Comparator.comparing(Student::getWeight)); 

       Arrays.sort(students2, 0, students.size(), Comparator.comparing(Student::getWeight)); 

    } 

    public static void sortByBMI()  

    {   

       students.sort(Comparator.comparing(Student::getBmi)); 

       Arrays.sort(students2, 0, students.size(), Comparator.comparing(Student::getBmi)); 

    } 

 } 



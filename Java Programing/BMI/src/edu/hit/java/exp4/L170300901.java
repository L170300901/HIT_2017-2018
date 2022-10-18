package edu.hit.java.exp4;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class L170300901 extends JFrame {

	public static ArrayList<StudentInfo> students = new ArrayList<StudentInfo>();
	private JTextArea textAreaStudentsInfoMain = new JTextArea();
	private JPanel contentPane;
	//private static final String ProgramLanguage = "Korean";
	private static final String ProgramLanguage = "Chinese";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					L170300901 frame = new L170300901();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public L170300901() {
		setTitle((ProgramLanguage == "Chinese") ? "学生信息输入" : "학생 정보 입력");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 620);
		setResizable(false);

		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenuItem menuItemPrintStudentsInfo = new JMenuItem((ProgramLanguage == "Chinese") ? "学生信息显示及录入" : "학생 정보 출력");
		JMenu menuMenu = new JMenu((ProgramLanguage == "Chinese") ? "菜单" : "메뉴");
		JMenuItem menuItemSortStudentsInfo = new JMenuItem((ProgramLanguage == "Chinese") ? "学生信息排序" : "학생 정보 정렬");
		JMenu menuMaintenanceStudentsInfo = new JMenu((ProgramLanguage == "Chinese") ? "学生信息维护" : "학생 정보 유지보수");
		JMenu menuStatisticsBMI = new JMenu((ProgramLanguage == "Chinese") ? "BMI 统计" : "BMI 통계");
		JMenuItem menuItemSearchStudentInfo = new JMenuItem((ProgramLanguage == "Chinese") ? "查询" : "검색");
		JMenuItem menuItemModifyStudentInfo = new JMenuItem((ProgramLanguage == "Chinese") ? "修改" : "수정");
		JMenuItem menuItemDeleteStudentInfo = new JMenuItem((ProgramLanguage == "Chinese") ? "删除" : "삭제");
		JMenuItem menuItemShowStatistics = new JMenuItem((ProgramLanguage == "Chinese") ? "统计数据显示" : "통계 데이터 출력");
		JMenuItem menuItemChartBMI = new JMenuItem((ProgramLanguage == "Chinese") ? "BMI 图表" : "BMI 그래프");

		setJMenuBar(menuBar);
		menuBar.add(menuItemPrintStudentsInfo);
		menuBar.add(menuMenu);
		menuMenu.add(menuItemSortStudentsInfo);
		menuMenu.add(menuMaintenanceStudentsInfo);
		menuMenu.add(menuStatisticsBMI);
		menuMaintenanceStudentsInfo.add(menuItemSearchStudentInfo);
		menuMaintenanceStudentsInfo.add(menuItemModifyStudentInfo);
		menuMaintenanceStudentsInfo.add(menuItemDeleteStudentInfo);
		menuStatisticsBMI.add(menuItemShowStatistics);
		menuStatisticsBMI.add(menuItemChartBMI);

		menuItemSortStudentsInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSortPanel();
			}
		});
		menuItemSearchStudentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSearchStudentPanel();
			}
		});
		menuItemModifyStudentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showModifyPanel();
			}
		});
		menuItemDeleteStudentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete();
			}
		});
		menuItemShowStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showStaticsPanel();
			}
		});
		menuItemChartBMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFreeChart chart = ChartFactory.createXYBarChart("BMI Statistics", "Intervals", false,
						"Number of Students", getBMIDataset(), PlotOrientation.VERTICAL, true, false, false);
				ChartFrame frame = new ChartFrame("BMI Statistics", chart);
				frame.pack();
				frame.setVisible(true);
			}
		});

		// Toolbar
		JToolBar toolBar = new JToolBar();
		JButton btnBack = new JButton((ProgramLanguage == "Chinese") ? "返回" : "뒤로");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Back();
			}
		});
		menuBar.add(toolBar);
		toolBar.add(btnBack);

		// ContentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Input Student Information
		JScrollPane jp = new JScrollPane(textAreaStudentsInfoMain);
		JLabel labelInputStudentInfo = new JLabel((ProgramLanguage == "Chinese") ? "<输入学生信息>" : "<학생 정보 입력>");
		JLabel labelStudentID = new JLabel((ProgramLanguage == "Chinese") ? "学号" : "학생 ID");
		JLabel labelStudentName = new JLabel((ProgramLanguage == "Chinese") ? "姓名" : "이름");
		JLabel labelStudentHeight = new JLabel((ProgramLanguage == "Chinese") ? "身高" : "신장");
		JLabel labelStudentWeight = new JLabel((ProgramLanguage == "Chinese") ? "体重" : "몸무게");
		JTextField textFieldStudentID = new JTextField();
		JTextField textFieldStudentName = new JTextField();
		JTextField textFieldStudentHeight = new JTextField();
		JTextField textFieldStudentWeight = new JTextField();

		jp.setBounds(180, 42, 600, 500);
		labelInputStudentInfo.setBounds(15, 10, 100, 37);
		labelStudentID.setBounds(15, 46, 65, 15);
		labelStudentName.setBounds(15, 74, 65, 15);
		labelStudentHeight.setBounds(15, 98, 65, 15);
		labelStudentWeight.setBounds(15, 123, 65, 15);
		textFieldStudentID.setBounds(70, 43, 100, 21);
		textFieldStudentName.setBounds(70, 71, 100, 21);
		textFieldStudentHeight.setBounds(70, 95, 100, 21);
		textFieldStudentWeight.setBounds(70, 123, 100, 21);
		textAreaStudentsInfoMain.setBounds(180, 42, 400, 500);

		textFieldStudentID.setColumns(10);
		textFieldStudentName.setColumns(10);
		textFieldStudentHeight.setColumns(10);
		textFieldStudentWeight.setColumns(10);

		contentPane.add(jp);
		contentPane.add(labelInputStudentInfo);
		contentPane.add(labelStudentID);
		contentPane.add(labelStudentName);
		contentPane.add(labelStudentHeight);
		contentPane.add(labelStudentWeight);
		contentPane.add(textFieldStudentID);
		contentPane.add(textFieldStudentName);
		contentPane.add(textFieldStudentHeight);
		contentPane.add(textFieldStudentWeight);

		JButton btnSaveStudentsInfo = new JButton((ProgramLanguage == "Chinese") ? "保存数据" : "정보 저장");
		btnSaveStudentsInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentInfo student = new StudentInfo(textFieldStudentID.getText(), textFieldStudentName.getText(),
						Float.parseFloat(textFieldStudentHeight.getText()),
						Float.parseFloat(textFieldStudentWeight.getText()));

				if (isExistStudentID(textFieldStudentID.getText()))
					JOptionPane.showMessageDialog(null, (ProgramLanguage == "Chinese") ? "学号重复！" : "중복되는 학생 ID가 존재합니다!",
							(ProgramLanguage == "Chinese") ? "提示信息" : "프롬프트 메시지", JOptionPane.INFORMATION_MESSAGE);
				else {
					students.add(student);
					JOptionPane.showMessageDialog(null, (ProgramLanguage == "Chinese") ? "保存成功" : "저장 성공",
							(ProgramLanguage == "Chinese") ? "提示信息" : "프롬프트 메시지", JOptionPane.INFORMATION_MESSAGE);
					printStudentsInfo();
				}
			}
		});
		btnSaveStudentsInfo.setBounds(15, 155, 150, 30);
		contentPane.add(btnSaveStudentsInfo);

		JButton btnGenerateRandomStudentsInfo = new JButton((ProgramLanguage == "Chinese") ? "随机获取学生" : "학생 정보 랜덤 생성");
		btnGenerateRandomStudentsInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentInfo student[] = new StudentInfo[200];
				student = GetGeneratedStudents(200, 175, 175, 60, 60);
				for (int i = 0; i < 200; i++)
					students.add(student[i]);
				printStudentsInfo();
			}
		});
		btnGenerateRandomStudentsInfo.setBounds(15, 190, 150, 30);
		contentPane.add(btnGenerateRandomStudentsInfo);

		JButton btnPrintStudentsInfo = new JButton((ProgramLanguage == "Chinese") ? "显示学生信息" : "학생 정보 갱신");
		btnPrintStudentsInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printStudentsInfo();
			}
		});
		btnPrintStudentsInfo.setBounds(180, 5, 150, 30);
		contentPane.add(btnPrintStudentsInfo);
	}

	public void printStudentsInfo() {
		StringBuilder sb = new StringBuilder();
		for (StudentInfo student : students)
			sb.append(GetStudentInfoString(student) + "\r\n");
		textAreaStudentsInfoMain.setText(sb.toString());
	}

	public static IntervalXYDataset getBMIDataset() {
		/*
    	 * cntRangeBMI[] : An array that counted the number of students in each BMI range.
         * cntRangeBMI[0]: (BMI of Student)<=14
         * cntRangeBMI[1]: (BMI of Student)>14 && (BMI of Student)<=15
         * cntRangeBMI[2]: (BMI of Student)>15 && (BMI of Student)<=16
         * cntRangeBMI[3]: (BMI of Student)>16 && (BMI of Student)<=17
         * cntRangeBMI[4]: (BMI of Student)>17 && (BMI of Student)<=18
         * cntRangeBMI[5]: (BMI of Student)>18 && (BMI of Student)<=19
         * cntRangeBMI[6]: (BMI of Student)>19 && (BMI of Student)<=20
         * cntRangeBMI[7]: (BMI of Student)>20 && (BMI of Student)<=21
         * cntRangeBMI[8]: (BMI of Student)>21 && (BMI of Student)<=22
         * cntRangeBMI[9]: (BMI of Student)>22
         */
		int cntRangeBMI[] = new int[10];
		XYSeriesCollection seriesCollection = new XYSeriesCollection();
		XYSeries series = new XYSeries("BMI Statistics");

		for (StudentInfo student : students) {
			double bmi = ((student.getBMI() <= 14.0) ? 14.0 : ((student.getBMI() > 22.0) ? 23.0 : student.getBMI()));
			int index = (int) Math.ceil(bmi - 14.0);
			cntRangeBMI[index]++;
		}

		for (int i = 0; i < cntRangeBMI.length; i++)
			series.add(14 + i, cntRangeBMI[i]);
		seriesCollection.addSeries(series);

		return new XYBarDataset(seriesCollection, 0.9);
	}

	public ArrayList<StudentInfo> getStudentsInfoFromFile(String fileName) {
		BufferedReader reader = null;
		File file = new File(fileName);
		ArrayList<StudentInfo> temp = new ArrayList<StudentInfo>();

		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				String[] token = tempString.split(",");
				StudentInfo st = new StudentInfo(token[0], token[1], Float.parseFloat(token[2]),
						Float.parseFloat(token[3]));
				temp.add(st);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return temp;
	}

	public void saveStudentsInfoToFile(ArrayList<StudentInfo> students, String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
			for (StudentInfo student : students) {
				writer.write(String.format("%s,%s,%.2f,%.2f,%.2f,%s\r\n", student.getID(), student.getName(),
						student.getHeight(), student.getWeight(), student.getBMI(), student.getHealthCondition()));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static float getStudentsAverageBMI(List<StudentInfo> students) {
		float sum = 0;
		if (students == null || students.isEmpty())
			return 0.0f;

		for (StudentInfo student : students)
			sum += student.getBMI();
		return sum / students.size();
	}

	public double getStudentsMiddleBMI(List<StudentInfo> students) {
		if (students == null || students.isEmpty())
			return 0.0f;

		students.sort(new BMIComparator());
		if (students.size() % 2 == 0)
			return (students.get(students.size() / 2 - 1).getBMI() + students.get(students.size() / 2).getBMI()) / 2;
		else
			return students.get(students.size() / 2).getBMI();
	}

	public static float getStudentsVarianceBMI(List<StudentInfo> students) {
		if (null == students || students.isEmpty())
			return 0.0f;

		float sum = 0.0f, average = getStudentsAverageBMI(students);
		for (StudentInfo student : students)
			sum += Math.pow((student.getBMI() - average), 2.0);

		return sum / students.size();
	}

	public List<Float> Mode(List<StudentInfo> students) {
		Map<Float, Integer> map = new HashMap<>();
		for (StudentInfo student : students) {
			if (map.containsKey(student.getBMI()))
				map.put(student.getBMI(), map.get(student.getBMI()) + 1);
			else
				map.put(student.getBMI(), 1);
		}

		Iterator iterator = map.entrySet().iterator();
		int maxCount = Integer.MIN_VALUE;
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			if (maxCount < (int) entry.getValue())
				maxCount = (int) entry.getValue();
		}
		if (maxCount == 1)
			return null;

		List<Float> list = new ArrayList<>();
		iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			if ((int) entry.getValue() == maxCount)
				list.add((float) entry.getKey());
		}

		return list;
	}

	private boolean isExistStudentID(String id) {
		for (StudentInfo student : students)
			if (id.equals(student.getID()))
				return true;
		return false;
	}

	private void showStaticsPanel() {
		this.setContentPane(new StaticsPanel());
		setVisible(true);
	}

	private void showSortPanel() {
		this.setContentPane(new SortPanel());
		setVisible(true);
	}

	private void showSearchStudentPanel() {
		this.setContentPane(new SearchPanel());
		setVisible(true);
	}

	private void showModifyPanel() {
		this.setContentPane(new ModifyPanel());
		setVisible(true);
	}

	public void sortStudents(Comparator<StudentInfo> c) {
		Collections.sort(students, c);
	}

	private class HeightComparator implements Comparator<StudentInfo> {
		@Override
		public int compare(StudentInfo st1, StudentInfo st2) {
			if (st1.getHeight() > st2.getHeight())
				return 1;
			else if (st1.getHeight() < st2.getHeight())
				return -1;
			return 0;
		}
	}

	private class NameComparator implements Comparator<StudentInfo> {
		@Override
		public int compare(StudentInfo st1, StudentInfo st2) {
			return st1.getName().compareTo(st2.getName());
		}
	}

	private class IdComparator implements Comparator<StudentInfo> {
		@Override
		public int compare(StudentInfo st1, StudentInfo st2) {
			return st1.getID().compareTo(st2.getID());
		}
	}

	private class WeightComparator implements Comparator<StudentInfo> {
		@Override
		public int compare(StudentInfo st1, StudentInfo st2) {
			if (st1.getWeight() > st2.getWeight())
				return 1;
			else if (st1.getWeight() < st2.getWeight())
				return -1;
			return 0;
		}
	}

	private class BMIComparator implements Comparator<StudentInfo> {
		@Override
		public int compare(StudentInfo st1, StudentInfo st2) {
			if (st1.getBMI() > st2.getBMI())
				return 1;
			else if (st1.getBMI() < st2.getBMI())
				return -1;
			return 0;
		}
	}

	class StaticsPanel extends JPanel {
		public StaticsPanel() {
			JTextArea textArea = new JTextArea();
			textArea.setBounds(143, 10, 281, 241);
			this.add(textArea);

			List<Float> mode = Mode(students);
			StringBuilder sb = new StringBuilder();
			if (mode != null && !mode.isEmpty()) {
				sb.append(String.format("Average:%.2f\nMiddle:%.2f\n", getStudentsAverageBMI(students),
						getStudentsMiddleBMI(students)));
				for (float m : mode)
					sb.append(String.format("%.2f ", m));
				sb.append(String.format("Variance:%.2f\n", getStudentsVarianceBMI(students)));
			} else
				sb.append(String.format("Average:%.2f\nMiddle:%.2f\nThere is no mode\nVariance:%.2f\n",
						getStudentsAverageBMI(students), getStudentsMiddleBMI(students),
						getStudentsVarianceBMI(students)));

			textArea.setText(sb.toString());
		}
	}

	class SortPanel extends JPanel {
		public SortPanel() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			this.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(this);
			this.setLayout(null);

			JTextArea textAreaSortedStudentsInfo = new JTextArea();
			textAreaSortedStudentsInfo.setBounds(143, 10, 400, 500);
			JScrollPane jp = new JScrollPane(textAreaSortedStudentsInfo);
			jp.setBounds(180, 42, 600, 500);
			this.add(jp);

			JLabel labelSortMethod = new JLabel((ProgramLanguage == "Chinese") ? "排序选择" : "<정렬 방법 선택>");
			labelSortMethod.setBounds(15, 20, 100, 15);
			this.add(labelSortMethod);

			JRadioButton radioBtnSortByID = new JRadioButton((ProgramLanguage == "Chinese") ? "学号" : "학생 ID");
			JRadioButton radioBtnSortByName = new JRadioButton((ProgramLanguage == "Chinese") ? "姓名" : "이름");
			JRadioButton radioBtnSortByHeight = new JRadioButton((ProgramLanguage == "Chinese") ? "身高" : "신장");
			JRadioButton radioBtnSortByWeight = new JRadioButton((ProgramLanguage == "Chinese") ? "体重" : "몸무게");
			JRadioButton radioBtnSortByBMI = new JRadioButton((ProgramLanguage == "Chinese") ? "BMI" : "BMI");
			ButtonGroup btnGroup = new ButtonGroup();

			btnGroup.add(radioBtnSortByID);
			btnGroup.add(radioBtnSortByName);
			btnGroup.add(radioBtnSortByHeight);
			btnGroup.add(radioBtnSortByWeight);
			btnGroup.add(radioBtnSortByBMI);

			radioBtnSortByID.setBounds(15, 41, 100, 23);
			this.add(radioBtnSortByID);
			radioBtnSortByID.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sortStudents(new IdComparator());
					StringBuilder sb = new StringBuilder();
					for (StudentInfo student : students)
						sb.append(GetStudentInfoString(student) + "\r\n");
					textAreaSortedStudentsInfo.setText(sb.toString());
				}
			});

			radioBtnSortByName.setBounds(15, 66, 100, 23);
			this.add(radioBtnSortByName);
			radioBtnSortByName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sortStudents(new NameComparator());
					StringBuilder sb = new StringBuilder();
					for (StudentInfo student : students)
						sb.append(GetStudentInfoString(student) + "\r\n");
					textAreaSortedStudentsInfo.setText(sb.toString());
				}
			});

			radioBtnSortByHeight.setBounds(15, 91, 100, 23);
			this.add(radioBtnSortByHeight);
			radioBtnSortByHeight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sortStudents(new HeightComparator());
					StringBuilder sb = new StringBuilder();
					for (StudentInfo student : students)
						sb.append(GetStudentInfoString(student) + "\r\n");
					textAreaSortedStudentsInfo.setText(sb.toString());
				}
			});

			radioBtnSortByWeight.setBounds(15, 117, 100, 23);
			this.add(radioBtnSortByWeight);
			radioBtnSortByWeight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sortStudents(new WeightComparator());
					StringBuilder sb = new StringBuilder();
					for (StudentInfo student : students)
						sb.append(GetStudentInfoString(student) + "\r\n");
					textAreaSortedStudentsInfo.setText(sb.toString());
				}
			});

			radioBtnSortByBMI.setBounds(15, 142, 121, 23);
			this.add(radioBtnSortByBMI);
			radioBtnSortByBMI.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sortStudents(new BMIComparator());
					StringBuilder sb = new StringBuilder();
					for (StudentInfo student : students)
						sb.append(GetStudentInfoString(student) + "\r\n");
					textAreaSortedStudentsInfo.setText(sb.toString());
				}
			});
		}
	}

	class ModifyPanel extends JPanel {
		public ModifyPanel() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			this.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(this);
			this.setLayout(null);

			JLabel labelTargetStudentID = new JLabel((ProgramLanguage == "Chinese") ? "查询学生学号" : "수정할 학생 ID");
			labelTargetStudentID.setBounds(35, 35, 100, 15);
			this.add(labelTargetStudentID);

			JTextField textFieldStudentID = new JTextField();
			JTextField textFieldStudentName = new JTextField();
			JTextField textFieldStudentHeight = new JTextField();
			JTextField textFieldStudentWeight = new JTextField();

			textFieldStudentID.setBounds(170, 35, 120, 21);
			this.add(textFieldStudentID);
			textFieldStudentID.setColumns(10);

			JLabel labelModifyStudentName = new JLabel((ProgramLanguage == "Chinese") ? "姓名" : "이름");
			labelModifyStudentName.setBounds(35, 70, 54, 15);
			this.add(labelModifyStudentName);

			textFieldStudentName.setBounds(170, 70, 120, 21);
			this.add(textFieldStudentName);
			textFieldStudentName.setColumns(10);

			JLabel labelModifyStudentHeight = new JLabel((ProgramLanguage == "Chinese") ? "身高" : "신장");
			labelModifyStudentHeight.setBounds(35, 105, 54, 15);
			this.add(labelModifyStudentHeight);

			textFieldStudentHeight.setBounds(170, 105, 120, 21);
			this.add(textFieldStudentHeight);
			textFieldStudentHeight.setColumns(10);

			JLabel labelModifyStudentWeight = new JLabel((ProgramLanguage == "Chinese") ? "体重" : "몸무게");
			labelModifyStudentWeight.setBounds(35, 140, 54, 15);
			this.add(labelModifyStudentWeight);

			textFieldStudentWeight.setBounds(170, 140, 120, 21);
			this.add(textFieldStudentWeight);
			textFieldStudentWeight.setColumns(10);

			JButton button = new JButton((ProgramLanguage == "Chinese") ? "修改" : "수정");
			button.setBounds(35, 198, 255, 30);
			this.add(button);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i;
					for (i = 0; i < students.size(); i++) {
						if (students.get(i).getID().equals(textFieldStudentID.getText())) {
							StudentInfo student = new StudentInfo(textFieldStudentID.getText(),
									textFieldStudentName.getText(), Float.parseFloat(textFieldStudentHeight.getText()),
									Float.parseFloat(textFieldStudentWeight.getText()));

							students.remove(i);
							students.add(i, student);
							break;
						}
					}

					if (i < students.size()) {
						JOptionPane.showMessageDialog(null, (ProgramLanguage == "Chinese") ? "修改成功！" : "수정 성공!",
								(ProgramLanguage == "Chinese") ? "提示信息" : "프롬프트 메시지", JOptionPane.INFORMATION_MESSAGE);
						printStudentsInfo();
					} else
						JOptionPane.showMessageDialog(null,
								(ProgramLanguage == "Chinese") ? "未找到此学生！" : "해당 학생을 찾을 수 없습니다!",
								(ProgramLanguage == "Chinese") ? "提示信息" : "프롬프트 메시지", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
	}

	class DeletePanel extends JPanel {
		public DeletePanel() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			this.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			this.setLayout(null);

			JLabel label = new JLabel((ProgramLanguage == "Chinese") ? "查询学生学号" : "삭제할 학생 ID");
			label.setBounds(283, 200, 100, 15);
			this.add(label);

			JTextField textFieldStudentID = new JTextField();
			textFieldStudentID.setBounds(418, 200, 100, 20);
			this.add(textFieldStudentID);
			textFieldStudentID.setColumns(10);

			JButton button = new JButton((ProgramLanguage == "Chinese") ? "删除" : "삭제");
			button.setBounds(283, 250, 235, 30);
			this.add(button);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i;
					for (i = 0; i < students.size(); i++) {
						if (students.get(i).getID().equals(textFieldStudentID.getText())) {
							students.remove(i);
							break;
						}
					}

					if (i < students.size()) {
						JOptionPane.showMessageDialog(null, (ProgramLanguage == "Chinese") ? "删除成功！" : "삭제 성공!",
								(ProgramLanguage == "Chinese") ? "提示信息" : "프롬프트 메시지", JOptionPane.INFORMATION_MESSAGE);
						printStudentsInfo();
					} else
						JOptionPane.showMessageDialog(null,
								(ProgramLanguage == "Chinese") ? "未找到此学生！" : "해당 학생을 찾을 수 없습니다!",
								(ProgramLanguage == "Chinese") ? "提示信息" : "프롬프트 메시지", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
	}

	class SearchPanel extends JPanel {
		public SearchPanel() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			setBorder(new EmptyBorder(5, 5, 5, 5));
			this.setLayout(null);

			JLabel labelSearchStudentID = new JLabel((ProgramLanguage == "Chinese") ? "查询学生学号" : "학생 ID");
			labelSearchStudentID.setBounds(35, 36, 50, 15);
			this.add(labelSearchStudentID);

			JTextField textFieldStudentID = new JTextField();
			textFieldStudentID.setBounds(100, 35, 150, 20);
			this.add(textFieldStudentID);
			textFieldStudentID.setColumns(10);

			JButton btnSearch = new JButton((ProgramLanguage == "Chinese") ? "查询" : "검색");
			btnSearch.setBounds(280, 35, 100, 20);
			this.add(btnSearch);

			JLabel labelSearchResult = new JLabel((ProgramLanguage == "Chinese") ? "结果" : "결과");
			labelSearchResult.setBounds(35, 70, 50, 15);
			this.add(labelSearchResult);

			JTextField textFieldStudentName = new JTextField();
			textFieldStudentName.setBounds(100, 70, 500, 20);
			this.add(textFieldStudentName);
			textFieldStudentName.setColumns(10);
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i;
					for (i = 0; i < students.size(); i++) {
						if (students.get(i).getID().equals(textFieldStudentID.getText())) {
							textFieldStudentName.setText(GetStudentInfoString(students.get(i)));
							break;
						}
					}

					if (i >= students.size())
						textFieldStudentName.setText((ProgramLanguage == "Chinese") ? "查无此人！" : "학생을 찾을 수 없습니다!");
				}
			});
		}
	}

	private void Back() {
		this.setContentPane(contentPane);
		setVisible(true);
	}

	private void Delete() {
		this.setContentPane(new DeletePanel());
		setVisible(true);
	}

	private String GetStudentInfoString(StudentInfo student) {
		return String.format("%s\t%s\t%.2f\t%.2f\t%.2f\t%s", student.getID(), student.getName(), student.getHeight(),
				student.getWeight(), student.getBMI(), student.getHealthCondition());
	}

	public StudentInfo[] GetGeneratedStudents(int N, float minHeight, float maxHeight, float minWeight,
			float maxWeight) {
		StudentInfo students[] = new StudentInfo[N];

		for (int i = 0; i < 200; i++) {
			String id = String.valueOf((int) GetRandomValueRange((float) 10000, (float) 20000));
			while (isExistStudentID(id))
				id = String.valueOf((int) GetRandomValueRange((float) 10000, (float) 20000));

			double x = new Random().nextGaussian();
			double y = new Random().nextGaussian();
			StudentInfo st = new StudentInfo(id, GetGeneratedRandomName(6),
					(float) (GetRandomValueRange(minHeight, maxHeight) + x * 0.1),
					(float) (GetRandomValueRange(minWeight, maxWeight) + y * 5));
			students[i] = st;
		}

		return students;
	}

	private static float GetRandomValueRange(float min, float max) {
		return (float) ((Math.random() * (max - min + 1)) + min);
	}

	private static String GetGeneratedRandomName(int length) {
		char[] randName = new char[length];

		for (int i = 0; i < length; i++)
			randName[i] = (char) Math.round((int) GetRandomValueRange((int) 'a', (int) 'z'));
		randName[0] = Character.toUpperCase(randName[0]);

		return String.valueOf(randName);
	}
}

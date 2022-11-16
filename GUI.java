package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.joda.time.LocalDate;

import date.Date;
import randomWord.RandomWord;


public class GUI {

	public static void mainGui() {
		//Frame for calendar
		JFrame frame = new JFrame("Din Kalender");
		frame.setBounds(0, 0, 1520, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		String[] createFilenameForMemo = { "monday.txt", "tuesday.txt", "wednesday.txt", "thursday.txt", "friday.txt", "saturday.txt", "sunday.txt" };
		String[] nameOfWeekday = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		LocalDate[] dateOfWeekday = { Date.mondayDate, Date.tuesdayDate, Date.wednesdayDate, Date.thursdayDate, Date.fridayDate, Date.saturdayDate,Date.sundayDate };
	
		//Main panel that will contain panels for weekdays.
		JPanel mainPanelForCalendar = new JPanel();

		//Loop which creates panels for each weekday.
		for (int i = 0; i < 7; i++)
			addGroupOfComponentsForWeekdays(mainPanelForCalendar, createFilenameForMemo[i], nameOfWeekday[i], dateOfWeekday[i]);
		//Adds word-randomizer to main panel.  
		addGroupOfComponentsForWordRandomizer(mainPanelForCalendar);
		
		//Adds main panel to frame.
		frame.add(mainPanelForCalendar);
		frame.setVisible(true);
	}

	private static void addGroupOfComponentsForWeekdays(JPanel containerForWeekdays, String fileNames, String dayName, LocalDate dateOfWeekday) {
		//Method that contains group of components for calendar.
		JPanel panelForComponents = new JPanel();
		panelForComponents.setLayout(new BoxLayout(panelForComponents, BoxLayout.PAGE_AXIS));

		JLabel displayWeekdayName = new JLabel(dayName);
		JLabel displayDateOfWeekday = new JLabel(dateOfWeekday.toString());
		JButton buttonForAddFunctionToMemo = new JButton("Add+");
		JTextField memoTextField = new JTextField("Skriv text..");
		JTextArea memoTextArea = new JTextArea();
		JScrollPane memoTextPane = new JScrollPane(memoTextArea);

		//Layout for components
		displayWeekdayName.setFont(new Font("Verdana", Font.BOLD, 18));
		displayDateOfWeekday.setFont(new Font("Verdana", Font.PLAIN, 15));
		memoTextArea.setBorder(BorderFactory.createTitledBorder("Memo"));
		panelForComponents.setBackground(Color.lightGray);
		memoTextArea.setLineWrap(true);
		memoTextArea.setEditable(false);
		memoTextPane.setPreferredSize(new Dimension(180, 100));
		memoTextField.setPreferredSize(new Dimension(180, 50));
		
		//If statement that changes the background color of the current day.
		if (dateOfWeekday == Date.todaysDate) {
			panelForComponents.setBackground(Color.GRAY);
		}
		
		//Adds components to panel and create space between.
		panelForComponents.add(displayWeekdayName);
		panelForComponents.add(Box.createRigidArea(new Dimension(0, 20)));
		panelForComponents.add(displayDateOfWeekday);
		panelForComponents.add(Box.createRigidArea(new Dimension(0, 350)));
		panelForComponents.add(memoTextPane);
		panelForComponents.add(Box.createRigidArea(new Dimension(0, 50)));
		panelForComponents.add(memoTextField);
		panelForComponents.add(Box.createRigidArea(new Dimension(0, 5)));
		panelForComponents.add(buttonForAddFunctionToMemo);
		panelForComponents.setVisible(true);

		//Adds listener, writer and reader for memo in container.
		addButtonListenerForMemo(buttonForAddFunctionToMemo, memoTextArea, memoTextField);
		addTextWriterForMemo(buttonForAddFunctionToMemo, memoTextField, fileNames);
		addTextReaderForMemo(memoTextArea, fileNames);

		containerForWeekdays.add(panelForComponents);
	}

	private static void addGroupOfComponentsForWordRandomizer(JPanel containerWordRandomizer) {
		
		JPanel panelForRandomizer = new JPanel();
		JLabel randomWord = new JLabel("Lär dig ett ord!");
		JButton buttonForWordRandomizer = new JButton("Tryck här!");
		
		//Layout for components.
		panelForRandomizer.setLayout(new BoxLayout(panelForRandomizer, BoxLayout.PAGE_AXIS));
		randomWord.setPreferredSize(new Dimension(180, 50));
		buttonForWordRandomizer.setPreferredSize(new Dimension(180, 200));
		
		panelForRandomizer.add(Box.createRigidArea(new Dimension(0, 20)));
		panelForRandomizer.add(buttonForWordRandomizer);
		panelForRandomizer.add(Box.createRigidArea(new Dimension(0, 20)));
		panelForRandomizer.add(randomWord);
		
		addButtonListenerForRandomizer(buttonForWordRandomizer, randomWord);
		
		containerWordRandomizer.add(panelForRandomizer);
	}
	
	private static void addButtonListenerForMemo(JButton addMemoButton, JTextArea textAreaForMemo, JTextField textFieldMemo) {
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textAreaForMemo.setText(textFieldMemo.getText());
				textFieldMemo.setText("Skriv igen");
			}
		};
		addMemoButton.addActionListener(buttonListener);
	}

	private static void addTextWriterForMemo(JButton addMemoButton, JTextField textFieldMemo, String fileName) {
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter memoWriter = new BufferedWriter(new FileWriter(fileName));
					memoWriter.write(textFieldMemo.getText());
					memoWriter.close();
					System.out.println("File Written Successfully");
				} catch (Exception a) {
					a.getStackTrace();
				}
			}
		};
		addMemoButton.addActionListener(buttonListener);
	}

	private static void addTextReaderForMemo(JTextArea textAreaForMemo, String fileName) {

		try {
			BufferedReader memoTextReader = new BufferedReader(new FileReader(fileName));
			String memo;
			memo = memoTextReader.readLine();
			memoTextReader.close();
			textAreaForMemo.setText(memo);
			System.out.println("File read Successfully.");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private static void addButtonListenerForRandomizer(JButton buttonForRandomizer, JLabel randomWord) {
		ActionListener listenerForRandomizer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					randomWord.setText(RandomWord.randomizer());
				} catch (FileNotFoundException a) {
					a.printStackTrace();
				}
			}
		};
		buttonForRandomizer.addActionListener(listenerForRandomizer);
	}
}


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
		JFrame frame = new JFrame("Din Kalender");
		frame.setBounds(0, 0, 1520, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		String[] files = { "monday.txt", "tuesday.txt", "wednesday.txt", "thursday.txt", "friday.txt", "saturday.txt", "sunday.txt" };
		String[] dayName = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		LocalDate[] dayOfWeek = { Date.monday, Date.tuesday, Date.wednesday, Date.thursday, Date.friday, Date.saturday,Date.sunday };
		
		
		JPanel mainPanel = new JPanel();

		for (int i = 0; i < 7; i++)
			addGroupOfComponents(mainPanel, files[i], dayName[i], dayOfWeek[i]);

		wordRandomizer(mainPanel);
		frame.add(mainPanel);
		frame.setVisible(true);
	}

	private static void addGroupOfComponents(JPanel container, String files, String dayName, LocalDate dayOfWeek) {

		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.PAGE_AXIS));

		JLabel weekday = new JLabel(dayName);
		JLabel date = new JLabel(dayOfWeek.toString());
		JButton button = new JButton("Add+");
		JTextField textField = new JTextField("Skriv text..");
		JTextArea textArea = new JTextArea();
		JScrollPane textPane = new JScrollPane(textArea);

		weekday.setFont(new Font("Verdana", Font.BOLD, 18));
		date.setFont(new Font("Verdana", Font.PLAIN, 15));
		textArea.setBorder(BorderFactory.createTitledBorder("Memo"));
		dayPanel.setBackground(Color.lightGray);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textPane.setPreferredSize(new Dimension(180, 100));
		textField.setPreferredSize(new Dimension(180, 50));

		if (dayOfWeek == Date.day) {
			dayPanel.setBackground(Color.GRAY);
		}

		dayPanel.add(weekday);
		dayPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		dayPanel.add(date);
		dayPanel.add(Box.createRigidArea(new Dimension(0, 350)));
		dayPanel.add(textPane);
		dayPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		dayPanel.add(textField);
		dayPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		dayPanel.add(button);
		dayPanel.setVisible(true);

		addButtonListener(button, textArea, textField);
		addTextWriter(button, textField, files);
		addTextReader(textArea, files);

		container.add(dayPanel);
	}

	private static void wordRandomizer(JPanel wordContainer) {
		JPanel wordPanel = new JPanel();
		JLabel randomWord = new JLabel("Lär dig ett ord!");
		JButton randomizer = new JButton("Tryck här!");
		
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.PAGE_AXIS));
		randomWord.setPreferredSize(new Dimension(180, 50));
		randomizer.setPreferredSize(new Dimension(180, 200));
		
		wordPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		wordPanel.add(randomizer);
		wordPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		wordPanel.add(randomWord);
		
		addButtonListenerRand(randomizer, randomWord);
		
		wordContainer.add(wordPanel);
	}
	
	private static void addButtonListener(JButton button, JTextArea textArea, JTextField textField) {
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textField.getText());
				textField.setText("Skriv igen");
			}
		};
		button.addActionListener(buttonListener);
	}

	private static void addTextWriter(JButton button, JTextField textField, String files) {
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BufferedWriter output = new BufferedWriter(new FileWriter(files));
					output.write(textField.getText());
					output.close();
					System.out.println("File Written Successfully");
				} catch (Exception a) {
					a.getStackTrace();
				}
			}
		};
		button.addActionListener(buttonListener);
	}

	private static void addTextReader(JTextArea textArea, String files) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(files));
			String memo;
			memo = reader.readLine();
			reader.close();
			textArea.setText(memo);
			System.out.println("File read Successfully.");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private static void addButtonListenerRand(JButton random, JLabel randomWord) {
		ActionListener randomListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				randomWord.setText(RandomWord.word);
			}
		};
		random.addActionListener(randomListener);
	}
}



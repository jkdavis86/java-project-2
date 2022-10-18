package project2;

//Jake Davis
//Project 2: Netflix GUI

import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton; 
import javax.swing.JLabel; 
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

//Initialization of Shows, string used for random movies, text field, and radio button for more global access.
public class MainPanel extends JPanel{ 
	private Shows myShows;	
	private String rando;
	private JTextField textField;
	private JRadioButton rdbtnNewRadioButton;

	public MainPanel() { 
		
		//Reads shows from file and places them in Shows/ShowWeeks
		setLayout(null);
		myShows = new Shows("myShows","./project2/netflixAllWeeksGlobalProcessed.txt");
		//System.out.println(myShows);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("./project2/netflix_2014.png"));
		lblNewLabel.setBounds(299, 41, 196, 99);
		add(lblNewLabel);
		
		setBackground(new Color(128, 128, 128)); 
		setPreferredSize(new Dimension(800, 500));
	
		
		JLabel lblNewLabel_1 = new JLabel("Browse shows here:");
		lblNewLabel_1.setBounds(321, 195, 148, 16);
		add(lblNewLabel_1);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(6, 221, 190, 26);
		add(textArea_1);
		
		rdbtnNewRadioButton = new JRadioButton("Only top rated shows");
		rdbtnNewRadioButton.setBounds(592, 191, 180, 23);
		rdbtnNewRadioButton.setSelected(false);
		add(rdbtnNewRadioButton);
		
		//Randomly generates a show title
		JButton btnNewButton = new JButton("Random Show");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rando = myShows.randomShow().getShowTitle();
				textArea_1.setText(rando);
			}
		});
		btnNewButton.setForeground(new Color(139, 0, 0));
		btnNewButton.setBounds(29, 190, 117, 29);
		add(btnNewButton);
		
		textField = new JTextField("2022-09-04");
		textField.setBounds(6, 302, 190, 29);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Week:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(52, 277, 80, 16);
		add(lblNewLabel_2);
		
		//Takes every show title found in the user-input week and places them in the combo box as strings.
		JComboBox comboBox = new JComboBox();
		ArrayList<ShowWeek> moviesInWeek = myShows.getOneWeek("2022-09-04");
		String [] data = new String[moviesInWeek.size()];
		int index = 0;
		for (ShowWeek sw : moviesInWeek) {
			data[index] = sw.getShowTitle();
			index++;
		}
		comboBox.setModel(new DefaultComboBoxModel(data));
		comboBox.setBounds(16, 388, 180, 27);
		add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Get week of movies");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ShowWeek> moviesInWeek = myShows.getOneWeek(textField.getText());
				if (moviesInWeek != null && moviesInWeek.size() > 0) {
					String [] data = new String[moviesInWeek.size()];
					int index = 0;
					for (ShowWeek sw : moviesInWeek) {
						data[index] = sw.getShowTitle();
						index++;
					}
					comboBox.setModel(new DefaultComboBoxModel(data));
				}
			}
		});
		btnNewButton_1.setForeground(new Color(128, 0, 0));
		btnNewButton_1.setBounds(16, 332, 167, 29);
		add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(321, 218, 454, 258);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		//Allows user to only see top watched movies and shows with the use of a radio button.
		JButton btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton.isSelected()) {
					textArea.setText(myShows.getTopShows().toString());
				}
				else {
					textArea.setText(myShows.toString());
				
				}
			}
		});
		btnNewButton_2.setForeground(new Color(128, 0, 0));
		btnNewButton_2.setBounds(617, 150, 117, 29);
		add(btnNewButton_2);
		if (rdbtnNewRadioButton.isSelected()) {
			textArea.setText(myShows.getTopShows().toString());
		}
		else {
			textArea.setText(myShows.toString());
		}
	}
		
	//Saves info to the same file it was originally read from.
	public void doClose() {
		myShows.doWrite("./project2/netflixAllWeeksGlobalProcessed.txt");
	}
}


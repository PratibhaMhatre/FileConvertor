import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class FileConversionEntryPage extends JPanel implements ActionListener {

	private static final long serialVersionUID = 2853914798605190348L;
	JFrame fileConversionMainframe;
	ButtonGroup radioGroup;	

	public FileConversionEntryPage() {

		fileConversionMainframe = new JFrame();
		fileConversionMainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fileConversionMainframe.setSize(300, 300);
		fileConversionMainframe.setVisible(true);
		// fileConversionMainframe.getContentPane().setBackground(Color.CYAN);
		fileConversionMainframe.setResizable(false);

		JLabel fileConversionToolLabel = new JLabel("FILE CONVERSION TOOL",
				SwingConstants.CENTER);

		JLabel selectionTypeLabel = new JLabel("Select Converion Type",
				SwingConstants.CENTER);

		JRadioButton csvToXmlRadio = new JRadioButton("CSV to XML", true);
		JRadioButton xmlToCsvRadio = new JRadioButton("XML to CSV", false);
		JRadioButton jsonToCsvRadio = new JRadioButton(
				FileConversionConstants.JSON_TO_CSV, false);

		JButton submitButton = new JButton("Submit");
		ImageIcon arrow = new ImageIcon("old_edit_redo.png");
		submitButton.setIcon(arrow);
		radioGroup = new ButtonGroup();

		setLayout(null);

		add(fileConversionToolLabel);
		add(selectionTypeLabel);
		add(csvToXmlRadio);
		add(xmlToCsvRadio);
		add(jsonToCsvRadio);
		add(submitButton);

		radioGroup.add(csvToXmlRadio);
		radioGroup.add(xmlToCsvRadio);
		radioGroup.add(jsonToCsvRadio);

		Font headerFont = fileConversionToolLabel.getFont();

		fileConversionToolLabel.setFont(new Font(headerFont.getFontName(),
				Font.BOLD, 15));

		fileConversionToolLabel.setBounds(50, 10, 200, 20);
		selectionTypeLabel.setBounds(20, 70, 150, 20);
		csvToXmlRadio.setBounds(30, 100, 100, 20);
		xmlToCsvRadio.setBounds(30, 130, 100, 20);
		jsonToCsvRadio.setBounds(30, 160, 100, 20);
		submitButton.setBounds(100, 210, 80, 20);
		submitButton.addActionListener(this);

	}

	public static void main(String[] args) {
		FileConversionEntryPage fileConversionEntryObj = new FileConversionEntryPage();
		fileConversionEntryObj.fileConversionMainframe.getContentPane().add(
				fileConversionEntryObj);

	}

	public void actionPerformed(ActionEvent e) {

		Enumeration<AbstractButton> allRadioButton = radioGroup.getElements();
		while (allRadioButton.hasMoreElements()) {
			JRadioButton tempSelectedButton = (JRadioButton) allRadioButton
					.nextElement();
			if (tempSelectedButton.isSelected()
					&& tempSelectedButton.getText().equalsIgnoreCase(
							FileConversionConstants.JSON_TO_CSV)) {
				this.fileConversionMainframe.setVisible(false);
				this.fileConversionMainframe.dispose();

				JsonToCsvPage jsonToCsvFrameObj = new JsonToCsvPage();
				jsonToCsvFrameObj.setVisible(true);

			}
		}

	}

}

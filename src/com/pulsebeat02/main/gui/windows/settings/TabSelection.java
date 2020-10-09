package com.pulsebeat02.main.gui.windows.settings;

import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class TabSelection extends JPanel {
	
	private boolean isHovered = false;
	private boolean isClicked = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public TabSelection(String txt, Image ico) throws IOException {

		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		ImageIcon img = new ImageIcon(ico.getScaledInstance(34, 34, Image.SCALE_DEFAULT));

		setLayout(null);

		JLabel text = new JLabel(txt);
		text.setFont(new Font("Gadugi", Font.BOLD, 12));
		text.setBounds(40, 0, 95, 40);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		add(text);

		JLabel icon = new JLabel("");
		icon.setBounds(5, 3, 34, 34);
		icon.setIcon(img);
		add(icon);

		setVisible(true);
		setSize(140, 40);
		setPreferredSize(new Dimension(140, 40));

		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(200, 200, 200));
				isHovered = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				if (isHovered) {
					
					setBackground(new Color(240, 240, 240));
					isHovered = false;
					
				} else if (isClicked) {
					
					isHovered = true;
					
				}
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
//				System.out.println("waddup my knee grows");
//				
//				for (int i = 0; i < ChangeSettings.tabs.length; i++) {
//					
//					if (ChangeSettings.tabs[i].getBackground().equals(new Color(200, 200, 200)) && !ChangeSettings.tabs[i].isHovered) {
//						
//						ChangeSettings.tabs[i].setBackground(new Color(240, 240, 240));
//						
//					}
//					
//				}
//				
//				setBackground(new Color(200, 200, 200));
//				isClicked = true;
			}
			
		});

	}

}

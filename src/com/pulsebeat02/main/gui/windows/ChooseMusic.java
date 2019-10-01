package com.pulsebeat02.main.gui.windows;

import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;

import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.gui.windows.thread.ManageThreads;
import com.pulsebeat02.main.gui.windows.thread.MusicThread;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class ChooseMusic extends JDialog implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int currentValue = 0;

	static Account account;

	/**
	 * Launch the application.
	 */
	public static void start() {
		try {
			ChooseMusic dialog = new ChooseMusic();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChooseMusic() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChooseMusic.class.getResource("/com/pulsebeat02/main/resources/images/music.jpg")));
		setTitle("Choose Menu Music");

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.add(0, "Select Song");
		listModel.add(1, "Calm");
		listModel.add(2, "Dreamy");
		listModel.add(3, "Sad");
		listModel.add(4, "Uplifting");
		listModel.add(5, "Choose From Files:");
		listModel.add(6, "Turn Off Background Music");

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("deprecation")
					@Override
					public void mousePressed(MouseEvent e) {
						if (currentValue == 1) {
							
							ManageThreads.musicThread.stop();
							ManageThreads.musicThread = new MusicThread();
							ManageThreads.musicThread.currentMusic = MenuMusic.calm;
							ManageThreads.musicThread.start();

						}

						else if (currentValue == 2) {
							
							ManageThreads.musicThread.stop();
							ManageThreads.musicThread = new MusicThread();
							ManageThreads.musicThread.currentMusic = MenuMusic.dreamy;
							ManageThreads.musicThread.start();

						}

						else if (currentValue == 3) {
							
							ManageThreads.musicThread.stop();
							ManageThreads.musicThread = new MusicThread();
							ManageThreads.musicThread.currentMusic = MenuMusic.sad;
							ManageThreads.musicThread.start();

						}

						else if (currentValue == 4) {
							
							ManageThreads.musicThread.stop();
							ManageThreads.musicThread = new MusicThread();
							ManageThreads.musicThread.currentMusic = MenuMusic.uplifting;
							ManageThreads.musicThread.start();

						}

						else if (currentValue == 5) {
							
							ManageThreads.musicThread.stop();
							ManageThreads.musicThread = new MusicThread();
							ManageThreads.musicThread.currentMusic = MenuMusic.chooseFile;
							ManageThreads.musicThread.start();

						}

						else if (currentValue == 6) {
							
							ManageThreads.musicThread.stop();
							ManageThreads.musicThread = new MusicThread();
							ManageThreads.musicThread.currentMusic = MenuMusic.off;
							ManageThreads.musicThread.start();

						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(0, 0, 434, 2);
		getContentPane().add(scrollPane1);

		JLabel lblNewLabel = new JLabel("Change/Turn Off Music:");
		lblNewLabel.setBounds(0, 2, 434, 88);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));

		JList<String> list = new JList<String>(listModel);
		list.setBounds(2, 100, 432, 128);
		getContentPane().add(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				currentValue = list.getSelectedIndex();
				account.accountMusic = currentValue;

			}
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

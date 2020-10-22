package com.pulsebeat02.shoeraffleservice.application;

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

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;
import com.pulsebeat02.shoeraffleservice.application.account.Account;
import com.pulsebeat02.shoeraffleservice.application.thread.MusicThread;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class ChooseMusic extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    int currentValue = 0;

    public ChooseMusic(Account a) {
	this.setIconImage(Toolkit.getDefaultToolkit()
		.getImage(ChooseMusic.class.getResource("/com/pulsebeat02/shoeraffleservice/resources/images/music.jpg")));
	this.setTitle("Choose Menu Music");

	this.setBounds(100, 100, 450, 300);
	this.getContentPane().setLayout(null);

	DefaultListModel<String> listModel = new DefaultListModel<String>();
	listModel.add(0, "Select Song");
	listModel.add(1, "Calm");
	listModel.add(2, "Dreamy");
	listModel.add(3, "Sad");
	listModel.add(4, "Uplifting");
	listModel.add(5, "Choose From Files (Must be MP3 File)");
	listModel.add(6, "Turn Off Background Music");

	JPanel buttonPane = new JPanel();
	buttonPane.setBounds(0, 228, 434, 33);
	buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	this.getContentPane().add(buttonPane);

	JButton okButton = new JButton("OK");
	okButton.addMouseListener(new MouseAdapter() {
	    @SuppressWarnings("deprecation")
	    @Override
	    public void mousePressed(MouseEvent e) {

		switch (currentValue) {

		case 1:
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.stop();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread = new MusicThread();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.currentMusic = MenuMusic.calm;
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.start();
		    break;

		case 2:
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.stop();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread = new MusicThread();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.currentMusic = MenuMusic.dreamy;
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.start();
		    break;

		case 3:
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.stop();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread = new MusicThread();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.currentMusic = MenuMusic.sad;
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.start();
		    break;

		case 4:
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.stop();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread = new MusicThread();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.currentMusic = MenuMusic.uplifting;
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.start();
		    break;

		case 5:
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.stop();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread = new MusicThread();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.currentMusic = MenuMusic.chooseFile;
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.start();
		    break;

		case 6:
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.stop();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread = new MusicThread();
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.currentMusic = MenuMusic.off;
		    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.musicThread.start();
		    break;

		}
	    }
	});
	okButton.setActionCommand("OK");
	buttonPane.add(okButton);
	this.getRootPane().setDefaultButton(okButton);

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

	JScrollPane scrollPane1 = new JScrollPane();
	scrollPane1.setBounds(0, 0, 434, 2);
	this.getContentPane().add(scrollPane1);

	JLabel lblNewLabel = new JLabel("Change/Turn Off Music:");
	lblNewLabel.setBounds(0, 2, 434, 88);
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
	this.getContentPane().add(lblNewLabel);

	JList<String> list = new JList<String>(listModel);
	list.setBounds(2, 100, 432, 128);
	list.addListSelectionListener(new ListSelectionListener() {
	    public void valueChanged(ListSelectionEvent e) {

		currentValue = list.getSelectedIndex();
		a.accountMusic = currentValue;

	    }
	});
	this.getContentPane().add(list);
    }
}

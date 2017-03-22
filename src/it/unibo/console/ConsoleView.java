package it.unibo.console;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import it.unibo.qactors.action.AsynchActionResult;

public class ConsoleView extends JFrame {

	private JButton start;
	private JButton stop;
	private JLabel image;
	private JToolBar button;
	
	private Console qaconsole;
	
	public ConsoleView(Console qaconsole){
		
		this.qaconsole = qaconsole;
		initView();
	}
	 
	
	int imgheight;
	int  imgwidth;
	private void initView() {
		start = new JButton();
		stop = new JButton();
		
		imgheight = 700;
		imgwidth = (int)(imgheight*1.26);
		
		try {
		    Image sta = ImageIO.read(getClass().getResource("Play.png"));
		    start.setIcon(new ImageIcon(sta.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		    start.setMinimumSize(new Dimension(100, 200));
		    Image sto = ImageIO.read(getClass().getResource("Stop.png"));
		    stop.setIcon(new ImageIcon(sto.getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		
		image = new JLabel();
		image.setMinimumSize(new Dimension(imgwidth, imgheight));
		
		button = new JToolBar();
		button.add(start);
		button.add(stop);
		
		
		add(button, BorderLayout.NORTH);
		add(image, BorderLayout.CENTER);
		this.setMinimumSize(new Dimension(imgwidth, imgheight));
    	pack();
    	this.setVisible(true);
    	setListeners();

	}

	private void setListeners()
    {
		start.addActionListener(e -> {
			qaconsole.emit("startControl", "startControl");
		});
		
		stop.addActionListener(e -> {
			qaconsole.emit("stopControl", "stopControl");
		});
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("close pressed");
				qaconsole.getQActorContext().terminateQActorSystem(qaconsole);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
    }
	
	
	public void setImage(Image photo){
		try {
			//AsynchActionResult asynres = qaconsole.senseEvents(99999999, "showphoto : showphoto");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image.setIcon(new ImageIcon(photo.getScaledInstance(imgwidth, imgheight, Image.SCALE_SMOOTH)));
	}
}

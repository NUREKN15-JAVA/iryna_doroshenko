package ua.nure.kn156.doroshenko.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ua.nure.kn156.doroshenko.db.DaoFactory;
import ua.nure.kn156.doroshenko.db.HsqldbUserDao;
import ua.nure.kn156.doroshenko.db.UserDao;
import ua.nure.kn156.doroshenko.util.Messages;

public class MainFrame extends JFrame {
	
	private static final int FrameHeight = 600;
	private static final int FrameWidth = 800;
	private JPanel contentPanel;
	private JPanel browsePanel;
	private AddPanel addPanel;
	private UserDao dao;

	public MainFrame(){
		super();
		//dao = new HsqldbUserDao();
		dao = DaoFactory.getInstanse().getUserDao();
		initialize();
	}
	
	public UserDao getDao(){
		return dao;
	}

	private void initialize() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FrameWidth, FrameHeight);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}

	private JPanel getContentPanel() {
		if(contentPanel == null){
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
			
		}
		
		return contentPanel;
	}

	private JPanel getBrowsePanel() {
		if (browsePanel==null){
			browsePanel = new BrowsePanel(this);
		}
		((BrowsePanel)browsePanel).initTable();
		return browsePanel;
	}
	public void showBrowsePanel() {
		showPanel(getBrowsePanel());
	}

	public static void main(String[] args) {
		MainFrame frame =new MainFrame();
		frame.setVisible(true);

	}

	public void showAddPanel() {
		showPanel(getAddPanel());
		
	}

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
		
		
	}

	private AddPanel getAddPanel() {
		if (addPanel == null){
			addPanel = new AddPanel(this);
		}
	
		return addPanel;
	}

}


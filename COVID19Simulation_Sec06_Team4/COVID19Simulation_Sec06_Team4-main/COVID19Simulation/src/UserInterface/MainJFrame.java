package UserInterface;

import java.awt.Color;
import java.awt.Frame;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import COVID19.City1;
import COVID19.COVID19JPanel;
import COVID19.Person1;
import Helper.Variables;
import SARS.City2;
import SARS.SARSJPanel;
import SARS.Person2;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainJFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	double defaultMax = 50;
    double defaultMin = 50;
    private COVID19JPanel cOVID19JPanel; 
    private SARSJPanel sARSJPanel;

    public MainJFrame() {
        initComponents();
        
        
        
        getContentPane().setBackground(new Color(205, 133, 63));
    	setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setTitle("COVID-19 VS SARS Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
    }
    /**
     * Initialize exposed person
     */
    private static void initExposed() {
        List<Person1> citizens = City1.getInstance().getPersonList();
        Person1 person1;
        //Randomly pick a person to get exposed
        for (int i = 0; i < Variables.Initial_Infected_Num; i++){
            for (int j = 0; j < citizens.size(); j++) {
                person1 = citizens.get(new Random().nextInt(citizens.size() - 1));
                if (person1.getStatus().equals(Person1.Status.Negative.toString())){
                        person1.getExposed();
                        break;
                }
            }
        }
    }
    
    
    private static void initExposed2() {
        List<Person2> citizens = City2.getInstance().getPersonList();
        Person2 person2;
        //Randomly pick a person to get exposed
        for (int i = 0; i < Variables.Initial_Infected_Num; i++){
            for (int j = 0; j < citizens.size(); j++) {
                person2 = citizens.get(new Random().nextInt(citizens.size() - 1));
                if (person2.getStatus().equals(Person1.Status.Negative.toString())){
                        person2.getExposed();
                        break;
                }
            }
        }
    }
    
    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
    	//COVID19
    	cOVID19JPanel = new COVID19JPanel();
    	splitPanel.setRightComponent(cOVID19JPanel);
    	cOVID19JPanel.setVisible(true);
    	initExposed();
    	Thread covidPanelThread = new Thread(cOVID19JPanel);
    	covidPanelThread.start();
    	
    	//SARS
    	sARSJPanel = new SARSJPanel();
    	sARSJPanel.setVisible(true);
    	initExposed2();
        Thread sarsPanelThread = new Thread(sARSJPanel);
        sarsPanelThread.start();
        
    }


    private void btnCOVID19ActionPerformed(java.awt.event.ActionEvent evt) {
        
        splitPanel.setRightComponent(cOVID19JPanel);
        
    }

    private void btnSARSActionPerformed(java.awt.event.ActionEvent evt) {
    	 
        splitPanel.setRightComponent(sARSJPanel);
         
     }

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {           
                new MainJFrame().setVisible(true);
            }    
    

    
    
    
    private void initComponents() {

        splitPanel = new javax.swing.JSplitPane();
        displayJPanel = new javax.swing.JPanel();
        displayJPanel.setBackground(new Color(105, 105, 105));
        controlJpanel = new javax.swing.JPanel();
        controlJpanel.setBackground(new Color(105, 105, 105));
        btnSARS = new javax.swing.JButton();
        btnSARS.setText("SARS");
        btnSARS.setBackground(new Color(105, 105, 105));
        btnSARS.setFont(new Font("Dialog", Font.BOLD, 20));
        javax.swing.JButton btnCOVID19 = new javax.swing.JButton();
        btnCOVID19.setText("COVID19");
        btnCOVID19.setBackground(new Color(0, 0, 0));
        btnCOVID19.setFont(new Font("Dialog", Font.BOLD, 20));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        splitPanel.setPreferredSize(new java.awt.Dimension(1000, 800));

        displayJPanel.setPreferredSize(new java.awt.Dimension(850, 800));

        javax.swing.GroupLayout displayJPanelLayout = new javax.swing.GroupLayout(displayJPanel);
        displayJPanelLayout.setHorizontalGroup(
        	displayJPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 634, Short.MAX_VALUE)
        );
        displayJPanelLayout.setVerticalGroup(
        	displayJPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 574, Short.MAX_VALUE)
        );
        displayJPanel.setLayout(displayJPanelLayout);

        splitPanel.setRightComponent(displayJPanel);

        controlJpanel.setPreferredSize(new java.awt.Dimension(150, 800));

       
        btnSARS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSARSActionPerformed(evt);
            }
        });

        btnCOVID19.setMaximumSize(new java.awt.Dimension(70, 29));
        btnCOVID19.setMinimumSize(new java.awt.Dimension(70, 29));
        btnCOVID19.setPreferredSize(new java.awt.Dimension(70, 29));
        btnCOVID19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCOVID19ActionPerformed(evt);
            }
        });
        
        JButton btnStart = new JButton("Start Simulations");
        btnStart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		btnStartActionPerformed(evt);
        		btnStart.setEnabled(false);
        	}
        });
        btnStart.setFont(new Font("Arial", Font.PLAIN, 15));
        btnStart.setPreferredSize(new Dimension(70, 29));

        javax.swing.GroupLayout controlJpanelLayout = new javax.swing.GroupLayout(controlJpanel);
        controlJpanelLayout.setHorizontalGroup(
        	controlJpanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(controlJpanelLayout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(controlJpanelLayout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnSARS, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnCOVID19, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        controlJpanelLayout.setVerticalGroup(
        	controlJpanelLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(controlJpanelLayout.createSequentialGroup()
        			.addGap(214)
        			.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
        			.addGap(47)
        			.addComponent(btnCOVID19, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addGap(48)
        			.addComponent(btnSARS, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(120, Short.MAX_VALUE))
        );
        controlJpanel.setLayout(controlJpanelLayout);

        splitPanel.setLeftComponent(controlJpanel);

        getContentPane().add(splitPanel, java.awt.BorderLayout.CENTER);
        splitPanel.setDividerLocation(0.5);

        pack();
    }

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlJpanel;
    private javax.swing.JButton btnSARS;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JSplitPane splitPanel;
}

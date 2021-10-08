package COVID19;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.border.LineBorder;

import Helper.Variables;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class COVID19JPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	public COVID19JPanel() {
        super();        
        setForeground(new Color(112, 128, 144));
        setBorder(new LineBorder(new Color(0, 0, 0), 4));
        this.setBackground(new Color(0x444444));
        
        //add Quarantine button
        JButton btnQuarantine = new JButton("Quarantine");
        btnQuarantine.setFont(new Font("Arial", Font.PLAIN, 16));
        add(btnQuarantine);
        
        //add Quarantine label
        JLabel quarantineLabel=new JLabel("False");
        quarantineLabel.setForeground(Color.WHITE);
        quarantineLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(quarantineLabel);
        
        //Click button to change value
        btnQuarantine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(!Variables.isQuarantine()) {
                Variables.setQuarantine(true);
                quarantineLabel.setText("True");
                System.out.println("======Quarantine is changed to True======");
            	} else {
            		Variables.setQuarantine(false);
            		quarantineLabel.setText("False");
            		System.out.println("======Quarantine is changed to False======");
            	}
         }
        });
        
        //add WearMask button
        JButton btnWearMask = new JButton("WearMask");
        btnWearMask.setFont(new Font("Arial", Font.PLAIN, 16));
        add(btnWearMask);
        
        //add WearMask label
        JLabel maskLabel = new JLabel("False");
        maskLabel.setForeground(Color.WHITE);
        maskLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(maskLabel);
        
        //Click button to change value
        btnWearMask.addActionListener(new java.awt.event.ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(!Variables.isWearMask()) {
                    Variables.setWearMask(true);
                    maskLabel.setText("True");
                    System.out.println("======WearMask is changed to True======");
                	} else {
                		Variables.setWearMask(false);
                		maskLabel.setText("False");
                		System.out.println("=====WearMask is changed to False======");
                	}
            }
        });
        
        // add GovernmentAction button
        JButton btnGovernmentAction = new JButton("Government Action");
        btnGovernmentAction.setFont(new Font("Arial", Font.PLAIN, 16));
        add(btnGovernmentAction);
        
        //add GovernmentAction label
        JLabel governmentLabel=new JLabel("False");
        governmentLabel.setForeground(Color.WHITE);
        governmentLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(governmentLabel);
        
        //Click button to change value
        btnGovernmentAction.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(!Variables.isGovernmentAction()) {
                    Variables.setGovernmentAction(true);
                    governmentLabel.setText("True");
                    System.out.println("======GovernmentAction is changed to True======");
                	} else {
                		Variables.setGovernmentAction(false);
                		governmentLabel.setText("False");
                        System.out.println("======GovernmentAction is changed to False======");
                		
                	}
            }
        });

    }
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //Draw person point
        List<Person1> citizens = City1.getInstance().getPersonList();
        if (citizens == null) {
            return;
        }
        //Different color stands for people in different status
        for (Person1 person1 : citizens) {
            if (person1.getStatus().equals(Person1.Status.Negative.toString())) {
                g.setColor(new Color(0xdddddd));
            }
            if (person1.getStatus().equals(Person1.Status.Exposed.toString())) {
                g.setColor(new Color(0xF5E011));
            }
            if(person1.getStatus().equals(Person1.Status.Positive.toString())){
                g.setColor(new Color(0xCE2323));
            }
            if(person1.getStatus().equals(Person1.Status.Recovered.toString())){
                g.setColor(new Color(0x6B54FF48, true));
            }
            if(person1.getStatus().equals(Person1.Status.Dead.toString())){
                g.setColor(new Color(50, 130, 217));
            }
            person1.updateStatus();
            g.fillOval(person1.getX(), person1.getY(), 3, 3);

        }

        int captionStartOffsetY = 50;
        int captionSize = 24;

        //Get the population of different status
        int NegativePop = City1.getInstance().getPopulation(Person1.Status.Negative.toString());
        int ExposedPop = City1.getInstance().getPopulation(Person1.Status.Exposed.toString());
        int PositivePop = City1.getInstance().getPopulation(Person1.Status.Positive.toString());
        int DeadPop = City1.getInstance().getPopulation(Person1.Status.Dead.toString());
        int RecoveredPop = City1.getInstance().getPopulation(Person1.Status.Recovered.toString());
        
        //Display the data
        g.setColor(Color.PINK);
        g.drawString("COVID-19 Simulation", 20, 20);
        g.setColor(Color.WHITE);
        g.drawString("Total Population：" + Variables.Total_Population, 920, captionStartOffsetY);
        g.setColor(new Color(221, 221, 221));
        g.drawString("Negative：" + NegativePop, 920, captionStartOffsetY + captionSize);
        g.setColor(new Color(255, 238, 0));
        g.drawString("Exposed：" + ExposedPop, 920, captionStartOffsetY + 2 * captionSize);
        g.setColor(new Color(255, 0, 0));
        g.drawString("Confirmed：" + PositivePop, 920, captionStartOffsetY + 3 * captionSize);
        g.setColor(new Color(0, 255, 0));
        g.drawString("Recovered：" + RecoveredPop, 920, captionStartOffsetY + 4 * captionSize);
        g.setColor(new Color(70, 130, 180));
        g.drawString("Deaths：" + DeadPop, 920, captionStartOffsetY + 5 * captionSize);
        g.setColor(new Color(255, 255, 255));
        g.drawString("Time ：" + (int) (dayTime / 10) + " Days", 920, captionStartOffsetY + 6 * captionSize);

        
        //Print data records for analysis
        if((dayTime / 10.0) % 1 == 0 ){
            System.out.println("------COVID19_Sim DAY " + dayTime / 10 + " ------");
            System.out.println("Negative number: " + NegativePop);
            System.out.println("Exposed number: " + ExposedPop);
            System.out.println("Confirmed number: " + PositivePop);
            System.out.println("Recovered number: " + RecoveredPop);
            System.out.println("Death number: " + DeadPop);
        }
    }

    public static int dayTime = 0;//simulate time, 0 means day0 and 1 means day1
    public java.util.Timer timer = new Timer();
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            COVID19JPanel.this.repaint();
            dayTime++;
        }
    }
   @Override
    public void run() {
        timer.schedule(new MyTimerTask(), 0, 100);
    }
 
}

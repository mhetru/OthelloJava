package othello.util;

import othello.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Msgbox extends JDialog implements ActionListener {
    // attributs
    private boolean id = false;   //permet de connaître le bouton utilisé
    private JButton ok, can;

    /**
     * Constructeur
     */
    Msgbox(JFrame fr, String msg, boolean okcan) {
	    
	    // constructeur hérité
	    super(fr, "Message", true);
	    
	    
	    // gestionnaire de positionnement
	    getContentPane().setLayout(new BorderLayout());
	    
	    // ligne de message
	    getContentPane().add(BorderLayout.CENTER,new JLabel(msg,JLabel.CENTER));
	    
	    // boutons
	    JPanel p = new JPanel();
	    
	    p.setLayout(new FlowLayout());
	    
	    ok = new JButton("OK");
	    
	    p.add(ok);
	    
	    ok.addActionListener(this);
	    
	    if (okcan) {
	        can = new JButton("Annuler");
	        p.add(can);
	        can.addActionListener(this);
	    }
	
	    getContentPane().add(BorderLayout.SOUTH,p);
	    
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    
	    // dimensions et positionnement
	    
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    setLocation((d.width - getSize().width)/2,(d.height-getSize().height)/2);
	    
	    setResizable(false);
	    
	    pack();
	    
	    // affichage
	    show();
    }

	/**
	 * Méthodes
	 */
    public void actionPerformed(ActionEvent e) {
	    if(e.getSource() == ok) {
	        id = true;
	        setVisible(false);
	    }
	    else if(e.getSource() == can) {
	        id = false;
	        setVisible(false);
	    }
    }

    public static void affMsg(JFrame fr, String msg) {
	    Msgbox message = new Msgbox(fr, msg, false);
	    message.dispose();
    }

    public static boolean affQuest(JFrame fr, String msg) {
         Msgbox message = new Msgbox(fr, msg, true);
         boolean rep = message.id;
         message.dispose();
         return rep;
    }

	/*public static void main(String argv[]) {
		JFrame f = new JFrame("Ma fenetre");
		f.setBounds(0,0,360,430);
		f.setDefaultCloseOperation(f.DO_NOTHING_ON_CLOSE);
		f.pack();
		f.show();
		boolean reponse = Msgbox.affQuest(f, "Etes vous sur de vouloir quitter ?");
		if (reponse) {
			System.out.println("Oui");
		} else {
			System.out.println("Non");
		}
	}*/

}
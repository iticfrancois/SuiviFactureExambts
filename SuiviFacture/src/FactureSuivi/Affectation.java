package FactureSuivi;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

 
public class Affectation extends JFrame {
  
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
  
	/*Construit la liste déroulante*/
	
	private JLabel label1 = new JLabel("Ligue");
	private JLabel label2 = new JLabel("Etat");	
	JLabel affectation = new JLabel("");
	
	@SuppressWarnings("rawtypes")
	private JComboBox listeLigue = new JComboBox();
	private static JTextField etat = new JTextField("");
	

	private JButton affecter = new JButton("AFFECTER");
	private JButton retour = new JButton("Retour");


	@SuppressWarnings("unchecked")
	public Affectation(){
	  
		/*Construit la fenetre en y integrant la liste deroulante*/
		this.setTitle("Affectation");
		this.setSize(320, 320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setLayout(new BorderLayout());

		JPanel top = new JPanel();
		top.setBackground(Color.white);
		top.setLayout(null);
		
		label1.setBounds(40, 20, 80, 30);
		listeLigue.setBounds(110,20, 150, 30);
		top.add(label1);
		top.add(listeLigue);
		
		label2.setBounds(40, 65, 80, 30);
		Font police = new Font("Arial", Font.BOLD, 14);
		etat.setFont(police);
		etat.setBounds(110, 70, 150, 30);
		etat.setForeground(Color.BLACK);
		container.add(label2);
		container.add(etat);
		
		affectation.setBounds(30, 110, 280, 30);
		top.add(affectation);
		
		retour.setBounds(1, 1, 10, 10);
		container.add(retour);
		
		retour.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Accueil choix = new Accueil();
				choix.affiche();
				dispose();
			}
		}
		);
		
		affecter.setBounds(35, 260, 250, 20);
		top.add(affecter);
		
		affecter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				RequeteSQL affectefacture = new RequeteSQL();
				affectefacture.update_table(listeLigue.getSelectedIndex()+1, etat.getText());
				affectation.setText("Mise a jour etat facture de \n" +
				affectefacture.toString(listeLigue.getSelectedIndex()+1, "ligue", "nom_ligue"));
			}	
		}	
		);
		
		container.add(top);
		this.setContentPane(container);
		
		RequeteSQL nomLigue = new RequeteSQL();
		
		/*Ajoute des objet dans la liste*/
		
		for (int i = 1; i <= 5; i++)
		{
			listeLigue.addItem(nomLigue.toString(i, label1.getText(), "nom_ligue"));
		}	
		
	}  
	
  
	public void affiche()
	{
		setVisible(true);
	}	

	public static void main(String[] args)
	{
	
	}
}
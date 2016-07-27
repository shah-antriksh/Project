import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.iterator.ExtendedIterator;

/* Here we are first declaring our class that will act as the
 * base for other panels or in other terms the base for CardLayout.
 */

public class Mapping_App3 {
	private static final String CARD_HOME = "Card Home";
	private static final String CARD_REGISTER = "Card Register";
	private static final String CARD_ADD_MAPPER = "Card Add Mapper";

	private static void createAndShowGUI() {
		JFrame frame = new JFrame();
		frame.setTitle("Sementic Web");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// This JPanel is the base for CardLayout for other JPanels.
		final JPanel contentPane = new JPanel();
		contentPane.setLayout(new CardLayout(50, 50));
		//contentPane.setBackground(Color.blue);

		Window1 win1 = new Window1();
		contentPane.add(win1, CARD_HOME);
		Window2 win2 = new Window2();
		contentPane.add(win2, CARD_ADD_MAPPER);
		Window3 win3 = new Window3();
		contentPane.add(win3, CARD_REGISTER);
		

		final JPanel buttonPanel = new JPanel();
		final JButton MapperButton = new JButton("Add Mapper");
		final JButton RegisterButton = new JButton("Register");
		final JButton HomeButton = new JButton("Home");
		buttonPanel.add(MapperButton);
		buttonPanel.add(HomeButton);
		buttonPanel.add(RegisterButton);
		
		HomeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            	MapperButton.setVisible(true);
            	RegisterButton.setVisible(true);
            	CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, CARD_HOME);
            }
        });
		
		 RegisterButton.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	            	//buttonPanel.setVisible(false);
	            	MapperButton.setVisible(true);
	            	RegisterButton.setVisible(false);
	                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
	                cardLayout.show(contentPane, CARD_REGISTER);
	            }
	        });
		 MapperButton.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent ae)
	            {
	            	//buttonPanel.setVisible(false);
	            	MapperButton.setVisible(false);
	            	RegisterButton.setVisible(true);
	                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
	                cardLayout.show(contentPane, CARD_ADD_MAPPER);
	            }
	        });

		// Adding the contentPane (JPanel) and buttonPanel to JFrame.
		frame.add(contentPane, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.PAGE_END);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String... args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}

class Window3 extends JPanel {

	private JLabel IDLabel,First_NameLabel, Last_NameLabel, EmailLabel, MobileLabel, DOBLabel, GenderLabel, RefrencesLabel, AddressLabel,TitleLabel,Preffered_locationLabel,CompetenceLabel,WorkExperienceLabel;
	private JTextField IDTextField,First_NameTextField, Last_NameTextField, EmailTextField, MobileTextField, DOBTextField, GenderTextField, RefrencesTextField, AddressTextField,Preffered_locationTextField,CompetenceTextField,WorkExperienceTextField;
	private JButton Submit;
	FileReader reader1; 
	OntModel model1;
	private String OntoPath = "Ontologies/JobSeeker.owl";

	public Window3() {
		init1();
	}

	private void init1()
	{
		IDLabel  = new JLabel("Enter ID");
		First_NameLabel= new JLabel("Enter First Name");
		Last_NameLabel= new JLabel("Enter Last Name");
		EmailLabel= new JLabel("Enter Email");
		MobileLabel= new JLabel("Enter Mobile Number");
		DOBLabel=new JLabel("Enter Date of Birth");
		GenderLabel= new JLabel("Enter Gender");
		RefrencesLabel= new JLabel("Enter Refrences");
		AddressLabel= new JLabel("Enter Address");
		Preffered_locationLabel = new JLabel("Enter Preffered Location");
		CompetenceLabel = new JLabel("Enter Competence");
		WorkExperienceLabel = new JLabel("Enter Work Experience");
		
		TitleLabel = new JLabel("Enter details");
		
		IDTextField  = new JTextField(15);
		First_NameTextField= new JTextField(15);
		Last_NameTextField= new JTextField(15);
		EmailTextField= new JTextField(15);
		MobileTextField= new JTextField(15);
		DOBTextField= new JTextField(15);
		GenderTextField= new JTextField(15);
		RefrencesTextField= new JTextField(15);
		AddressTextField= new JTextField(15);
		Preffered_locationTextField= new JTextField(15);
		CompetenceTextField= new JTextField(15);
		WorkExperienceTextField= new JTextField(15);
		
		
		Submit = new JButton("Submit");
		
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					reader1 = new FileReader(OntoPath);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				model1 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
				model1.read(reader1, null);
				
				// Get Class Object
				
				OntClass jseeker=model1.getOntClass("http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#Job_Seeker");
				Iterator i=jseeker.listDeclaredProperties();
				while (i.hasNext())
				{
					OntProperty op = (OntProperty) i.next();
					System.out.println(op.getURI());
				}
				String ns ="http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#";
				Individual jseeker1=jseeker.createIndividual(ns+IDTextField.getText());
				
				//Declaration of All Properties of JobSeeker Class
			    OntProperty fname=model1.getOntProperty(ns+"hasFirst_Name");
			    OntProperty lname=model1.getOntProperty(ns+"hasLast_Name");
			    OntProperty email=model1.getOntProperty(ns+"hasEmail");
			    OntProperty mno=model1.getOntProperty(ns+"hasMobileNo");
			    OntProperty dob=model1.getOntProperty(ns+"hasDate_of_Birth");
			    OntProperty gender=model1.getOntProperty(ns+"hasGender");
			    OntProperty ref=model1.getOntProperty(ns+"hasReferences");
			    OntProperty adrs=model1.getOntProperty(ns+"hasAddress");
			    OntProperty gpl=model1.getOntProperty(ns+"has_Preffered_location");
			    OntProperty comp=model1.getOntProperty(ns+"has_competence"); 
			    OntProperty wrkexp=model1.getOntProperty(ns+"hasWorkExperience"); 
			    
			    // Add Value into Individual
				jseeker1.addLiteral(fname, First_NameTextField.getText());
			    jseeker1.addLiteral(lname, Last_NameTextField.getText());
			    jseeker1.addLiteral(email, EmailTextField.getText());
			    //jseeker1.addLiteral(country, CountryTextField.getText());
			    jseeker1.addLiteral(mno, 12345);
			    jseeker1.addLiteral(dob, DOBTextField.getText());
			    jseeker1.addLiteral(gender, GenderTextField.getText());
			    jseeker1.addLiteral(ref, RefrencesTextField.getText());
			    jseeker1.addLiteral(adrs, AddressTextField.getText());
			    jseeker1.addLiteral(gpl, Preffered_locationTextField.getText());
			    jseeker1.addLiteral(comp, CompetenceTextField.getText());
			    jseeker1.addLiteral(wrkexp, WorkExperienceTextField.getText());

			    FileWriter out=null;
			    try{
			    out=new FileWriter("Ontologies/JobSeeker.owl");
			    
			    model1.write(out, "RDF/XML-ABBREV");
			    System.out.println("Wrote to file");
			    } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    finally {
			        if (out != null)
						try {
							out.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        }

			}
		});
		JPanel jp = new JPanel();
	    GridBagLayout layout = new GridBagLayout();
		jp.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    
		gbc.gridx = 0;
	    gbc.gridy = 0;
		jp.add(TitleLabel,gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		jp.add(First_NameLabel,gbc);
		gbc.gridx = 1;
		jp.add(First_NameTextField,gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		jp.add(Last_NameLabel,gbc);
		gbc.gridx = 1;
		jp.add(Last_NameTextField,gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		jp.add(EmailLabel,gbc);
		gbc.gridx = 1;
		jp.add(EmailTextField,gbc);
		
		gbc.gridy = 4;
		gbc.gridx = 0;
		jp.add(MobileLabel,gbc);
		gbc.gridx = 1;
		jp.add(MobileTextField,gbc);

		gbc.gridy = 5;
		gbc.gridx = 0;
		jp.add(DOBLabel,gbc);
		gbc.gridx = 1;
		jp.add(DOBTextField,gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;
		jp.add(GenderLabel,gbc);
		gbc.gridx = 1;
		jp.add(GenderTextField,gbc);
		
		gbc.gridy = 7;
		gbc.gridx = 0;
		jp.add(RefrencesLabel,gbc);
		gbc.gridx = 1;
		jp.add(RefrencesTextField,gbc);
		
		gbc.gridy = 8;
		gbc.gridx = 0;
		jp.add(AddressLabel,gbc);
		gbc.gridx = 1;
		jp.add(AddressTextField,gbc);
		
		
		gbc.gridy = 9;
		gbc.gridx = 0;
		jp.add(Preffered_locationLabel,gbc);
		gbc.gridx = 1;
		jp.add(Preffered_locationTextField,gbc);
		
		gbc.gridy = 10;
		gbc.gridx = 0;
		jp.add(CompetenceLabel,gbc);
		gbc.gridx = 1;
		jp.add(CompetenceTextField,gbc);
		
		gbc.gridy = 11;
		gbc.gridx = 0;
		jp.add(WorkExperienceLabel,gbc);
		gbc.gridx = 1;
		jp.add(WorkExperienceTextField,gbc);
		
		
		gbc.gridy = 12;
		gbc.gridx = 0;
		jp.add(IDLabel,gbc);
		gbc.gridx = 1;
		jp.add(IDTextField,gbc);
		
		add(jp);
		add(Submit);
	}
		
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class Window2 extends JPanel implements ActionListener {

	private JLabel labelRollno;
	private JLabel Onto1FileName_Label, Onto1NameSpace_Label, Onto2FileName_Label, Onto2NameSpace_Label;
	private JTextField Onto1FileName_TextField, Onto1NameSpace_TextField, Onto2FileName_TextField,
			Onto2NameSpace_TextField;
	private JButton Load_Ontology, Map_Items;
	private JComboBox OntoCheckBox1, OntoCheckBox2;
	private Container container;
	private String OntoPath = "Ontologies/";
	FileReader reader1, reader2;
	OntModel model1, model2;
	String onto_flag="Empty";
	ArrayList<String> OntoMapping1 = new ArrayList<String>();
	ArrayList<String> OntoMapping2 = new ArrayList<String>();
	public Window2() {
		init();
	}

	private void init() {
		Onto1FileName_Label = new JLabel("Enter Ontology 1 File Name");
		Onto1FileName_TextField = new JTextField("JobSeeker");
		Onto1NameSpace_Label = new JLabel("Enter Ontology1 Name Space");
		Onto1NameSpace_TextField = new JTextField("http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#");

		Onto2FileName_Label = new JLabel("Enter Ontology 2 File Name");
		Onto2FileName_TextField = new JTextField(15);
		Onto2NameSpace_Label = new JLabel("Enter Ontology 2 Name space");
		Onto2NameSpace_TextField = new JTextField(15);

		Load_Ontology = new JButton("Load Ontology");

		OntoCheckBox1 = new JComboBox();
		OntoCheckBox2 = new JComboBox();
		Map_Items = new JButton("Add items");
		
		Load_Ontology.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					try
					{
					reader1 = new FileReader(OntoPath + Onto1FileName_TextField.getText() + ".owl");
					reader2 = new FileReader(OntoPath + Onto2FileName_TextField.getText() + ".owl");
					model1 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
					model2 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
					model1.read(reader1, null);
					model2.read(reader2, null);
					
					}
					catch(Exception E)
					{
						System.out.println(E);
					}
					Iterator prplist = model1.listAllOntProperties();
					while (prplist.hasNext()) {
						OntProperty tempprop = (OntProperty) prplist.next();
						Object temp = tempprop.getLocalName();
						OntoCheckBox1.addItem(temp);
					}
					prplist = model2.listAllOntProperties();
					while (prplist.hasNext()) {
						OntProperty tempprop = (OntProperty) prplist.next();
						Object temp = tempprop.getLocalName();
						OntoCheckBox2.addItem(temp);
					}
					Onto1FileName_TextField.setEditable(false);
					Onto2FileName_TextField.setEditable(false);
					Onto1NameSpace_TextField.setEditable(false);
					Onto2NameSpace_TextField.setEditable(false);
					Load_Ontology.setEnabled(false);
					onto_flag="Filled";
					

				} catch (Exception E) {
					System.out.println("Invalid Filenames");
					Onto1FileName_TextField.setEditable(true);
					Onto2FileName_TextField.setEditable(true);
					Onto1NameSpace_TextField.setEditable(true);
					Onto2NameSpace_TextField.setEditable(true);
					Load_Ontology.setEnabled(true);

				}
			}
		});

		Map_Items.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				OntoMapping1.add((String) OntoCheckBox1.getSelectedItem());
				OntoMapping2.add((String) OntoCheckBox2.getSelectedItem());				
				OntoCheckBox1.removeItemAt(OntoCheckBox1.getSelectedIndex());
				OntoCheckBox2.removeItemAt(OntoCheckBox2.getSelectedIndex());
				
				if (OntoCheckBox1.getItemCount() == 0) {
					OntoCheckBox2.removeAllItems();
					Map_Items.setEnabled(false);
					onto_flag="Empty";
				}
				if (OntoCheckBox2.getItemCount() == 0) {
					OntoCheckBox1.removeAllItems();
					Map_Items.setEnabled(false);
					onto_flag="Empty";
							
				}
				if(onto_flag.equals("Empty"))
				{
					Onto1FileName_TextField.setEditable(true);
					Onto2FileName_TextField.setEditable(true);
					Onto1NameSpace_TextField.setEditable(true);
					Onto2NameSpace_TextField.setEditable(true);
					Load_Ontology.setEnabled(true);
					System.out.println(OntoMapping1);
					System.out.println(OntoMapping2);
				}
			}

		});
		
		JPanel jp = new JPanel();
	    GridBagLayout layout = new GridBagLayout();
		jp.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    
		gbc.gridx = 0;
	    gbc.gridy = 0;
		jp.add(Onto1FileName_Label,gbc);
		
		gbc.gridx = 1;
		jp.add(Onto1FileName_TextField,gbc);
		
		gbc.gridx = 2;
		jp.add(Onto1NameSpace_Label,gbc);
		
		gbc.gridx = 3;
		jp.add(Onto1NameSpace_TextField,gbc);
		
		gbc.gridx = 0;
	    gbc.gridy = 1;
		jp.add(Onto2FileName_Label,gbc);
		
		gbc.gridx = 1;
		jp.add(Onto2FileName_TextField,gbc);
		
		gbc.gridx = 2;
		jp.add(Onto2NameSpace_Label,gbc);
		
		gbc.gridx = 3;
		jp.add(Onto2NameSpace_TextField,gbc);
		
		gbc.gridx = 1;
	    gbc.gridy = 2;
		jp.add(Load_Ontology,gbc);	
	    
		gbc.gridx = 1;
	    gbc.gridy = 3;
		jp.add(OntoCheckBox1,gbc);
		gbc.gridx = 2;
	    jp.add(OntoCheckBox2,gbc);
	    gbc.gridx = 3;
	    jp.add(Map_Items,gbc);
	    
	    JScrollPane scrollPane = new JScrollPane(jp);
	    add(jp);
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

class Window1 extends JPanel {
	
	public Window1 ()
	{
		init();
	}
	public void init()
	{
		JPanel jp = new JPanel();
	    GridBagLayout layout = new GridBagLayout();
		jp.setLayout(layout);
		add(jp);
	}
}

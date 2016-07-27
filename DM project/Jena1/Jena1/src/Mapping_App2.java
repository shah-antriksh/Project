
// : c14:ComboBoxes.java
// Using drop-down lists.
// <applet code=ComboBoxes width=200 height=125></applet>
// From 'Thinking in Java, 3rd ed.' (c) Bruce Eckel 2002
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Scanner;

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

public class Mapping_App2 extends JApplet {

	private JLabel Onto1FileName_Label, Onto1NameSpace_Label, Onto2FileName_Label, Onto2NameSpace_Label;
	private JTextField Onto1FileName_TextField, Onto1NameSpace_TextField, Onto2FileName_TextField,
			Onto2NameSpace_TextField;
	private JButton Load_Ontology, Map_Items;
	private JComboBox OntoCheckBox1, OntoCheckBox2;
	private Container container;
	private String OntoPath = "../Ontologies/";
	FileReader reader1, reader2;
	OntModel model1, model2;
	String onto_flag="Empty";
	ArrayList<String> OntoMapping1 = new ArrayList<String>();
	ArrayList<String> OntoMapping2 = new ArrayList<String>();
	
	public void init() {

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

					reader1 = new FileReader(OntoPath + Onto1FileName_TextField.getText() + ".owl");
					reader2 = new FileReader(OntoPath + Onto2FileName_TextField.getText() + ".owl");
					model1 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
					model2 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
					model1.read(reader1, null);
					model2.read(reader2, null);

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
	    
	    container = getContentPane();
	    container.setLayout(new FlowLayout());
	    container.add(jp);
	}

	public static void main(String[] args) {
		run(new Mapping_App2(), 200, 125);
	}

	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(applet);
		frame.setSize(width, height);
		applet.init();
		applet.start();
		frame.setVisible(true);
	}
}

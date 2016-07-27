
// : c14:ComboBoxes.java
// Using drop-down lists.
// <applet code=ComboBoxes width=200 height=125></applet>
// From 'Thinking in Java, 3rd ed.' (c) Bruce Eckel 2002
// www.BruceEckel.com. See copyright notice in CopyRight.txt.

import java.awt.Container;
import java.awt.FlowLayout;
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

public class Map extends JApplet {
	ArrayList a1, a2;

	private JLabel OntoFileName_Label = new JLabel("Enter Ontology File Name");
	private JTextField OntoFileName_TextField = new JTextField(15);
	private JLabel OntoNameSpace_Label = new JLabel("Enter Ontology File Name");
	private JTextField OntoNameSpace_TextField = new JTextField(15);
	private JButton Load_Ontology = new JButton("Load Ontology");

	private JComboBox OntoCheckBox1 = new JComboBox();
	private JComboBox OntoCheckBox2 = new JComboBox();

	private JButton Map_Items = new JButton("Add items");
	
	public void setup1() {

		final JTextField t = new JTextField(15);
		final JComboBox c = new JComboBox();
		final JComboBox c1 = new JComboBox();
		final JButton b = new JButton("Add items");

		a1 = new ArrayList();
		a2 = new ArrayList();
		a2.add("C");
		a1.add("A");
		a1.add("E");
		Iterator i1 = a1.iterator();
		while (i1.hasNext()) {
			Object element = i1.next();
			c.addItem(element);
		}
		Iterator i2 = a1.iterator();
		while (i2.hasNext()) {
			Object element = i2.next();
			c1.addItem(element);
		}

		/*
		 * for (int i = 0; i < 4; i++) c.addItem(description[count++]);
		 */

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (c.getItemCount() == 0) {
					c1.removeAllItems();
					b.disable();
				} else if (c1.getItemCount() == 0) {
					c.removeAllItems();
					b.disable();
				} else {
					System.out.println(c.getSelectedItem());
					System.out.println(c1.getSelectedItem());
					c.removeItemAt(c.getSelectedIndex());
					c1.removeItemAt(c1.getSelectedIndex());
				}

			}

		});

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(t);
		cp.add(c);
		cp.add(c1);
		cp.add(b);

	}

	public void init() {
		FileReader reader = null;
		try {
			reader = new FileReader("JobSeeker");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		model.read(reader, null);
		String ns = "http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#";
		setup1();
	}

	public static void main(String[] args) {
		run(new JApplet(), 200, 125);
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

	public void dummy() {
		a1 = new ArrayList();
		a2 = new ArrayList();
		a2.add("C");
		a1.add("A");
		a1.add("E");
		Iterator i1 = a1.iterator();

		while (i1.hasNext()) {
			Object element = i1.next();
			OntoCheckBox1.addItem(element);
		}
		Iterator i2 = a1.iterator();
		while (i2.hasNext()) {
			Object element = i2.next();
			OntoCheckBox2.addItem(element);
		}

		Load_Ontology.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		/*
		 * c.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { t.setText("index: " +
		 * c.getSelectedIndex() + "   " + ((JComboBox)
		 * e.getSource()).getSelectedItem()); } });
		 */

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(OntoFileName_Label);
		cp.add(OntoFileName_TextField);
		cp.add(Load_Ontology);
		cp.add(OntoNameSpace_Label);
		cp.add(OntoNameSpace_TextField);
		cp.add(OntoCheckBox1);
		cp.add(OntoCheckBox1);
		cp.add(Map_Items);
	}

}

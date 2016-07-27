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

public class HelloWorld1 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		String file="JobSeeker.owl";
		try {
			FileReader reader=new FileReader(file);
			OntModel model=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			model.read(reader,null);
			String ns="http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#";
		   //Individual jseeker1=jseeker.createIndividual(ns+"asp03");

		    Iterator prplist=model.listAllOntProperties();
		     // Code to print all Properties
		    while(prplist.hasNext()){
		    	OntProperty tempprop=(OntProperty) prplist.next();
		    	System.out.println(tempprop.getLocalName());
		    }
		   
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
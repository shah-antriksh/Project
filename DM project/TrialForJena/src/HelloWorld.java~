import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file="Ontology/JobSeeker.owl";
		try {
			FileReader reader=new FileReader(file);
			OntModel model=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			model.read(reader,null);
			String ns="http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#";
		    OntClass jseeker=model.getOntClass("http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#Job_Seeker");
		   //Individual jseeker1=jseeker.createIndividual(ns+"asp03");
		    Iterator a=jseeker.listDeclaredProperties();
		    // Declaration of all Properties of Job Seeker
		    OntProperty fname=model.getOntProperty(ns+"hasFirst_Name");
		    OntProperty lname=model.getOntProperty(ns+"hasLast_Name");
		    OntProperty email=model.getOntProperty(ns+"hasEmail");
		    OntProperty country=model.getOntProperty(ns+"livesInCountry");
		    OntProperty mno=model.getOntProperty(ns+"hasMobileNo");
		    OntProperty dob=model.getOntProperty(ns+"hasDate_of_Birth");
		    OntProperty gender=model.getOntProperty(ns+"hasGender");
		    OntProperty ref=model.getOntProperty(ns+"hasReferences");
		    OntProperty adrs=model.getOntProperty(ns+"hasAddress");
		    //Scanner sc=new Scanner(System.in);
		    // Add Value to Property
		    
		   /* jseeker1.addLiteral(fname, "Antriksh");
		    jseeker1.addLiteral(lname, "Shah");
		    jseeker1.addLiteral(email, "as@mit.edu");
		    jseeker1.addLiteral(country, "India");
		    jseeker1.addLiteral(mno, -23452);
		    jseeker1.addLiteral(dob, "1991/05/03");
		    jseeker1.addLiteral(gender, "Male");
		    jseeker1.addLiteral(ref, "Dharam Uandkat");
		    jseeker1.addLiteral(adrs, "IIIT B");*/
		    System.out.println("Added Perfectly");
		    FileWriter out=null;
		    try{
		    out=new FileWriter("Ontology/JobSeeker.owl");
		    
		    model.write(out, "RDF/XML-ABBREV");
		    System.out.println("Wrote to file");
		    }
		    finally {
		        if (out != null) 
		           out.close();
		        }
		    
		   Iterator itr=jseeker.listInstances();
		   while(itr.hasNext()){
			   Individual ind=(Individual)itr.next();
			   //OntResource ors=(OntResource) ind.getPropertyValue(adrs);
			   System.out.println(ind.getLocalName());
			   Iterator adrsit=ind.listPropertyValues(adrs);
			   while(adrsit.hasNext()){
				   System.out.println(adrsit.next());
			   }
			   System.out.println(ind.getURI());
		   }
		   // SPARQL Query Demo Code
		   // Create a new query
		    String queryString =        
		      "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		      "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+
		      "PREFIX ns: <http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#> " +
		        "select (STR(?object) as ?label) "+
		    
		        "where { "+
		         "?subject ns:hasEmail ?object  "+
		        "} \n ";
		    Query query=QueryFactory.create(queryString);
		    
		    QueryExecution qe=QueryExecutionFactory.create(query,model);
		    System.out.println("Executed");
		    ResultSet rs=qe.execSelect();
		    ResultSetFormatter.out(System.out, rs, query);
		    qe.close();
		    
		    //Setting equivalent  Property
		    System.out.println("My First name is fname....Enter your property accordingly");
		    Scanner sc=new Scanner(System.in);
		    String fname_st=sc.next();
		    OntProperty fname_alt=model.createOntProperty(ns.concat(fname_st));
		    Iterator prplist=model.listAllOntProperties();
		    
		    fname.setEquivalentProperty(fname_alt);
		    System.out.println("Added Equ Prop");
		    boolean status=fname.hasEquivalentProperty(fname_alt);
		    System.out.println(status);
		    
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

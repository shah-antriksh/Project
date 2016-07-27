import java.io.FileReader;
import java.util.Iterator;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;

public class dbTrial_sparql
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String fileName = "/home/shatroopa/workspace/sparql_27apr/src/ontologies/JobSeeker.owl";
		String fileName2 = "/home/shatroopa/workspace/sparql_27apr/src/ontologies/JobProvider.owl";
		try 
		{
			FileReader reader = new FileReader(fileName);
			OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			model.read(reader, null);
			String namespace = "http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#";
		    OntClass jseeker = model.getOntClass("http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#Job_Seeker");
		    
		    FileReader reader2 = new FileReader(fileName2);
			OntModel model2 = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			model2.read(reader2, null);
			String namespace2 = "http://www.semanticweb.org/ontologies/2016/2/JobProvider.owl#";
		    OntClass jprofile = model2.getOntClass("http://www.semanticweb.org/ontologies/2016/2/JobProvide.owl#JobVacancy");
		
		    /*Iterator<?> classIter1 = (Iterator<?>) model.listClasses();
			//while(classIter1.hasNext())
			//{
				OntClass ontClass = (OntClass) classIter1.next();
				String uri = ontClass.getURI();
				if(uri != null)
					System.out.println(uri);
			//}
*/			
		    
		    //Query on the seeker db
		    // Create a new query 
		    String queryString_seeker =        
		    		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		    			      "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+
		    			      "PREFIX ns: <http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#> " +
		    			        "select (STR(?object) as ?has_years) "+
		    			    
		    			        "where { "+
		    			         "?subject ns:work_Exp ?object  "+
		    			        "} \n ";
		    
		    Query query = QueryFactory.create(queryString_seeker);
		    
		    //query execution
		    QueryExecution qe = QueryExecutionFactory.create(query, model);
		    System.out.println("Executed");
		    ResultSet rs1 = qe.execSelect();
	    	ResultSetFormatter.out(System.out, rs1, query);
	    	
	    	qe.close();
	    	
	    	//query on available job profiles
	    	String queryString_profile =        
		    		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
		    			      "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "+
		    			      "PREFIX ns: <http://www.semanticweb.org/ontologies/2016/2/JobProvide.owl#> " +
		    			        "select STR(?required_years) ?name ?description"+
		    			    
		    			        "where { "+
		    			        "OPTIONAL{?subject ns:Job_Name ?name.  "+
		    			        "?subject ns:Job_Description_ ?description.}  "+
		    			         "?subject ns:requires_work_experience ?required_years.  "+
		    			        "} \n ";
		    
		    Query queryj = QueryFactory.create(queryString_profile);
		    
		    //query execution
		    QueryExecution qej = QueryExecutionFactory.create(queryj, model2);
		    System.out.println("Executed");
		    ResultSet rs2 = qej.execSelect();
	    	ResultSetFormatter.out(System.out, rs2, queryj);
	    	
	    	qe.close();
	    	
	    	
	    	//seeker iterator
	    	while(rs1.hasNext()){
	    		// job iterator
	    		  while(rs2.hasNext()){
	    			  if(Integer.parseInt(((Object) rs1).getString(2)) >= Integer.parseInt(((Object) rs1).getString(2))){
	    		       // print corresponding job description from rs2
	    		     }
	    		  }

	    		// this will move resultSet cursor to first position.
	    		rs2.first();
	    		}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}

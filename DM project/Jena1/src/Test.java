import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String filename=rpm.getFile_name();
		String ns="http://www.semanticweb.org/ontologies/2016/3/Tcs.owl#";
		FileReader read = null;
		try {
			read = new FileReader("Ontologies/Tcs.owl");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OntModel smodel=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		smodel.read(read, null);
		Iterator i=smodel.listIndividuals();
		Individual ik=smodel.getIndividual("http://www.semanticweb.org/ontologies/2016/3/Tcs.owl#tcs02");
		OntProperty ip=smodel.getOntProperty(ns+"has_position_name");
		System.out.println(ik.getPropertyValue(ip).asLiteral().toString());

		while(i.hasNext()){
			Individual j=(Individual)i.next();
			System.out.println(j.getURI());
			OntProperty ipj=smodel.getOntProperty(ns+"has_position_name");
			System.out.println(j.getPropertyValue(ip).asLiteral().toString());
		}
		
		
	}

}

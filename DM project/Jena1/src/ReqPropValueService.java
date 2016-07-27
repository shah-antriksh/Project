import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;

public class ReqPropValueService {
	public ReqPropValueModel getRequiredValue(ReqPropModel rpm,String inst_url){
		String filename=rpm.getFile_name();
		String ns=null;
		FileReader read = null;
		try {
			read = new FileReader("Ontologies/"+filename+".owl");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OntModel smodel=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		smodel.read(read, null);
		ns=smodel.getNsPrefixURI(filename);
		System.out.println("Name Space: "+ns);
		
		Individual i=smodel.getIndividual(inst_url);
		System.out.println("Instance URL: "+inst_url);
		String jname=rpm.getJob_name();
		String wrklcn=rpm.getWork_Location();
		String comp=rpm.getRequires_competence();
		String exp=rpm.getRequires_work_experience();
		String orgname=rpm.getHasOrganizationName();
		String orgemail=rpm.getHasOrgEmail();
		String mage=rpm.getMax_age();
		
		// Prop Value Assgin
		OntProperty jname_prop=smodel.getOntProperty(ns+jname);
		System.out.println(ns+jname);
		String jname_val=i.getPropertyValue(jname_prop).asLiteral().toString();
		System.out.println("Job Name is:"+jname_val+"And Prop Name is"+jname);
		OntProperty wrklcn_prop=smodel.getOntProperty(ns+wrklcn);
		String wrklcn_val=i.getPropertyValue(wrklcn_prop).asLiteral().toString();
		
		OntProperty comp_prop=smodel.getOntProperty(ns+comp);
		String comp_val=i.getPropertyValue(comp_prop).asLiteral().toString();
		
		OntProperty exp_prop=smodel.getOntProperty(ns+exp);
		int exp_val=i.getPropertyValue(exp_prop).asLiteral().getInt();
		System.out.println("Exp Val:"+exp_val+"And Prop Name is"+exp);
		OntProperty orgname_prop=smodel.getOntProperty(ns+orgname);
		String orgname_val=i.getPropertyValue(orgname_prop).asLiteral().toString();
		System.out.println("Org Name Val"+orgname_val);
		OntProperty orgemail_prop=smodel.getOntProperty(ns+orgemail);
		String orgemail_val=i.getPropertyValue(orgemail_prop).asLiteral().toString();
		
		OntProperty mage_prop=smodel.getOntProperty(ns+mage);
		int mage_val=i.getPropertyValue(mage_prop).asLiteral().getInt();
		System.out.println("M Age is:"+mage_val+"And Prop Name is"+mage);
		System.out.println("We have Every thing"+jname_val+" "+wrklcn_val+" "+mage_val+" "+comp_val+" ");
	
		
		return new ReqPropValueModel(jname_val, wrklcn_val, comp_val, exp_val, orgemail_val, orgname_val, mage_val);
		
	}
}

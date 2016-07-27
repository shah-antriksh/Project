import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;

public class ReqPropService {
	
	public ArrayList<ReqPropModel> getRequiredProplist(){
		// Initialization of Model and Read Model
		String ns="http://www.semanticweb.org/ontologies/2016/2/JobProvide.owl#";
		FileReader read = null;
		try {
			read = new FileReader("Ontologies/JobProvide.owl");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OntModel smodel=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		smodel.read(read, null);
		// Initialize Req Property that we need 
		ArrayList<String> cmpnylist=new ArrayList<String>();
		ArrayList<String> reqprplist=new ArrayList<String>();
		reqprplist.add("Job_Name");
		reqprplist.add("Work_Location");
		reqprplist.add("Requires_competence");
		reqprplist.add("requires_work_experience");
		reqprplist.add("hasOrgEmail");
		reqprplist.add("hasOrganizationName");
		reqprplist.add("Max_age");
		
		OntProperty jname=smodel.getOntProperty(ns+"Job_Name");
		Iterator i=jname.listEquivalentProperties();
		// To Find the list of Company Col with us 
		while(i.hasNext()){
			OntProperty a=(OntProperty) i.next();
			String temp=a.getComment("EN");
			cmpnylist.add(temp);
			
		}
		//Retriving and Creating Arraylist of our Required object
		int size=cmpnylist.size();
		String ourmatch=null;
		ArrayList<ReqPropModel> rtnmodel=new ArrayList<ReqPropModel>();
		for(int j=0;j<size;j++){
			ArrayList<String> myprop=new ArrayList<String>();
			
			ourmatch=cmpnylist.get(j);
			String fname=ourmatch;
			System.out.println("---------------Prop List for "+ourmatch+"----------------");
			for(String prplist : reqprplist){
				OntProperty mt=smodel.getOntProperty(ns+prplist);
				Iterator i1=mt.listEquivalentProperties();
				while(i1.hasNext()){
					OntProperty eqprop=(OntProperty) i1.next();
					String cmnt=eqprop.getComment("EN");
					if(cmnt.equals(ourmatch)){
						System.out.println(eqprop.getLocalName());
						myprop.add(eqprop.getLocalName());
					}
				}
				
			}
			ReqPropModel rptemp=new ReqPropModel(fname, myprop.get(0), myprop.get(1),myprop.get(2) ,myprop.get(3),myprop.get(4), myprop.get(5), myprop.get(6));
			rtnmodel.add(rptemp);
		}
		return rtnmodel;
	}
	public static void main(String [] args)
	{
		ReqPropService rps = new ReqPropService();
		ArrayList<ReqPropModel> abc = rps.getRequiredProplist();
		System.out.println(abc.size());
		
		
	}

}

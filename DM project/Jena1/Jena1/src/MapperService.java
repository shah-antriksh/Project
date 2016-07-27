package com.smartjob.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class MapperService {
		
		public MapperService(){
			
		}
		public boolean setEquviProperty(ArrayList<String> base,ArrayList<String> target,OntModel tmodel,String trgt_ns,String t_file) throws IOException{
			// Creation of Source Model
			FileReader read=new FileReader("Ontologies/JobProvide.owl");
			OntModel smodel=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			smodel.read(read, null);
			
			// Actual logic for Mapping
			int size=target.size();
			String our_ns="http://www.semanticweb.org/ontologies/2016/2/JobProvide.owl#";
			for(int i=0;i<size;i++){
				OntProperty srcprp=smodel.getOntProperty(our_ns+base.get(i));
				OntProperty trgprp=smodel.createOntProperty(our_ns+target.get(i));
				srcprp.setEquivalentProperty(trgprp);
				trgprp.setComment(trgt_ns,"EN");
				System.out.println("Comments: "+trgprp.getComment("EN"));
				
			}
			this.saveModel(smodel,"Ontologies/JobProvide.owl");
			
			
			return true;
		}
		public boolean saveModel(OntModel m,String path) throws IOException{
			   FileWriter out=null;
			   
			    try {
					out=new FileWriter(path);
					 m.write(out, "RDF/XML-ABBREV");
					 System.out.println("Wrote to file");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    finally {
			        if (out != null) 
			           out.close();
			        }
			    
			   
			    
			return false;
		}

}

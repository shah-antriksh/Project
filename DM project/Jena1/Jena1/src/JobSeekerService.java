package com.smartjob.service;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;

public class JobSeekerService {
	
		public JobSeekerModel getRequiredInstanceVal(String id){
			// Creating Model to read Instance
			String ns="http://www.semanticweb.org/ontologies/2016/2/JobSeeker.owl#";
			FileReader read = null;
			try {
				read = new FileReader("Ontologies/JobSeeker.owl");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			OntModel smodel=ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
			smodel.read(read, null);
			
			Individual m=smodel.getIndividual(ns+id);
			OntProperty wrkex=smodel.getOntProperty(ns+"hasWorkExperience");
			OntProperty cmptnce=smodel.getOntProperty(ns+"has_competence");
			OntProperty prlocatn=smodel.getOntProperty(ns+"has_Preffered_location");
			String cmp=m.getPropertyValue(cmptnce).asLiteral().toString();
			String plcation=m.getPropertyValue(prlocatn).asLiteral().toString();
			String wrkexp=m.getPropertyValue(wrkex).asLiteral().toString();
			
			
			return new JobSeekerModel(wrkexp, plcation, cmp);
			
		}

}

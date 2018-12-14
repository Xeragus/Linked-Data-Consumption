package io.bobz;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.PropertyImpl;
import org.apache.jena.riot.RDFParser;
import org.apache.jena.vocabulary.RDFS;

public class EntryController {
	public static void main(String[] args) {
		Model cityModel = ModelFactory.createDefaultModel();
		Model countryModel = ModelFactory.createDefaultModel();
		Model currencyModel = ModelFactory.createDefaultModel();
		String cityUrl = "http://dbpedia.org/resource/Buenos_Aires";
		
		RDFParser.source(cityUrl)
				.httpAccept("application/ld+json")
				.parse(cityModel.getGraph());
//		cityModel.write(System.out, "TURTLE");
		
		Resource city = cityModel.getResource(cityUrl);
		String countryUrl = city.getProperty(new PropertyImpl("http://dbpedia.org/ontology/country"))
									.getObject().toString();
//		System.out.println(countryUrl);
		
		RDFParser.source(countryUrl)
				.httpAccept("application/ld+json")
				.parse(countryModel.getGraph());
//		countryModel.write(System.out, "TURTLE");
		
		Resource country = countryModel.getResource(countryUrl);
		String currencyUrl = country.getProperty(new PropertyImpl("http://dbpedia.org/ontology/currency"))
									.getObject().toString();
//		System.out.println(currencyUrl);
		
		RDFParser.source(currencyUrl)
				.httpAccept("application/ld+json")
				.parse(currencyModel.getGraph());
		
		Resource currency = currencyModel.getResource(currencyUrl);
		String comment = currency.getProperty(RDFS.comment).getString();
		String label = currency.getProperty(RDFS.label).getString();
		System.out.println(comment);
		System.out.println(label);
		
	}
}

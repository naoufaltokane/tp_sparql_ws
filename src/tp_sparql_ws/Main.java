package tp_sparql_ws;

import java.util.Iterator;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSparql();
	}

	private static void testSparql() {
		// TODO Auto-generated method stub
		FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
		Model model = FileManager.get().loadModel("src/rdfu.rdf");
		
		String queryString =
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
			"PREFIX vCard: <http://www.w3.org/2001/vcard-rdf/3.0#> " +
				//2
			"SELECT  * WHERE {?subject ?predicate ?object }LIMIT 3";
		
		Query query = QueryFactory.create(queryString);
		QueryExecution ms = QueryExecutionFactory.create(query, model);
		try {
			ResultSet res=ms.execSelect();
			while(res.hasNext()) {
				QuerySolution soln =res.nextSolution();
				//Literal name = soln.getLiteral("x");
				System.out.println(soln+"\t");
			}
		}finally {
			ms.close();
		}
	}

}

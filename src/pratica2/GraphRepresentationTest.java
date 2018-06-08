package pratica2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import exception.GraphLibraryException;
import library.GraphLibrary;
import model.Edge;
import model.Graph;
import model.WeightedEdge;

public class GraphRepresentationTest {
	
	GraphLibrary manager;
	Graph<Edge> grafoSemPeso;
	Graph<WeightedEdge> grafoComPeso;
	
	@Before
	public void setup() throws FileNotFoundException, GraphLibraryException {
		
		manager = new GraphLibrary();
		grafoSemPeso = manager.readGraph("src/pratica2/casesGraphRepresentationTest/grafoSemPeso.txt");
		grafoComPeso = manager.readWeightedGraph("src/pratica2/casesGraphRepresentationTest/grafoComPeso.txt");
		
	}
	
	@Test
	public void graphRepresentationTest1() {
		String saida = readSaida("src/pratica2/casesGraphRepresentationTest/saidaAMGrafoSemPeso.txt");
		assertEquals(saida.trim(), manager.graphRepresentation(grafoSemPeso, "AM").trim());
	}
	
	@Test
	public void graphRepresentationTest1ComPeso() {
		String saida = readSaida("src/pratica2/casesGraphRepresentationTest/saidaAMGrafoComPeso.txt");
		assertEquals(saida.trim(), manager.graphRepresentation(grafoComPeso, "AM").trim());
	}

	@Test
	public void graphRepresentationTest2() {
		String saida = readSaida("src/pratica2/casesGraphRepresentationTest/saidaALGrafoSemPeso.txt");
		assertEquals(saida, manager.graphRepresentation(grafoSemPeso, "AL"));
	}
	
	@Test
	public void graphRepresentationTest2ComPeso() {
		String saida = readSaida("src/pratica2/casesGraphRepresentationTest/saidaALGrafoComPeso.txt");
		assertEquals(saida.trim(), manager.graphRepresentation(grafoComPeso, "AL").trim());
	}
	
	@Test
	public void graphRepresentationTest3() {
		assertNull(manager.graphRepresentation(grafoSemPeso, ""));
	}
	
	@Test
	public void graphRepresentationTest4() {
		assertEquals("Exception", testException(null, "AM"));
	}
	
	@Test
	public void graphRepresentationTest5() {
		assertEquals("Exception", testException(null, "AL"));
	}
	
	@Test
	public void graphRepresentationTest6() {
		assertEquals("Exception", testException(null, null));
	}
	
	
	private String testException(Graph graph, String type){
		try {
			 String teste = manager.graphRepresentation(graph, type);
		} catch (Exception e) {
			return "Exception";
		}
		
		return "";
	}
	
	
	private String readSaida(String path) {
		String retorno = "";
		
		Scanner sc = getScanner(path);
		while (sc.hasNext()) {
			String line = sc.nextLine();
			retorno += line + System.lineSeparator();
		}
		
		return retorno;
	}
	
	private Scanner getScanner(String path) {
		File file = new File(path);
		try {
			return new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

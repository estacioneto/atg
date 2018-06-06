package pratica2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import manager.GraphManager;
import model.Graph;

public class GraphRepresentationTest {

	
	GraphManager manager = new GraphManager();
	Graph graph1 = manager.readGraph("src/test/java/pratica2/casesGraphRepresentationTest/grafo1.txt");
	
	@Test
	public void graphRepresentationTest1() {
		String saida = readSaida("src/test/java/pratica2/casesGraphRepresentationTest/saidaAMGrafo1.txt");
		assertEquals(saida.trim(), manager.graphRepresentation(graph1, "AM").trim());
	}

	@Test
	public void graphRepresentationTest2() {
		String saida = readSaida("src/test/java/pratica2/casesGraphRepresentationTest/saidaALGrafo1.txt");
		assertEquals(saida, manager.graphRepresentation(graph1, "AL"));
	}
	
	@Test
	public void graphRepresentationTest3() {
		assertNull(manager.graphRepresentation(graph1, ""));
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
			retorno += line + "\r\n";
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

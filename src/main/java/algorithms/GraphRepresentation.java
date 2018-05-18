package algorithms;

import model.Graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.List;

/**
 * Classe que implementa os algoritmos para encontrar a representação gráfica de um grafo
 */
public class GraphRepresentation {

    /**
     * Gera representação gráfica em Lista de Adjacência
     * 
     * @param {Graph} graph - objeto que representa o grafo
     * 
     * @return {String} representação do grafo
     */
    public static String graphRepresentationAL(Graph graph) {
        double[][] matrix = graph.getNodesAsMatrix();
        String rep = "";

        matrix = fillMatrixWithIndexed(matrix, graph.getOrderedNodes());

        for (var i = 1; i < matrix.length ; i++){
            rep += (int) matrix[i][0] + " - ";
            for (var j = 1; j < matrix[i].length; j++){
                double weight = matrix[i][j];
                int node = (int) matrix[0][j];
                if (weight == 0.0) continue;
                rep += node + "";
                if (graph.isWeighted()) {
                    rep+= "(" + getNumberForOutput(weight) + ")";
                }
                rep += " ";
            }
            rep += System.lineSeparator();
        }
        return rep;        
    }

    /**
     * Preenche a primeira linha e coluna da matriz de adjacência (preenchida com 0's por padrão)
     * com os números do vértices, servindo de indíce
     * 
     * @param {double[][]} matrix - matrix de adjacência do grafo
     * @param {List<Integer>} nodes - lista contendo nós do grafo
     * 
     * @return {double[][]} matriz preenchida
     */
    public static double[][] fillMatrixWithIndexed(double[][] matrix, List<Integer> nodes){
        for (var index = 0; index < nodes.size(); index++){
            matrix[0][index+1] = nodes.get(index);
            matrix[index+1][0] = nodes.get(index);
        }
        return matrix;
    }

    /**
     * Formata número dependendo de suas casas decimais
     * 
     * @param {double} number - número a ser formatado
     * 
     * @return {String} número formatado em String
     */
    public static String getNumberForOutput(double number){
        String response;
        if (number % 1 == 0){
            response = (int) number + "";
        }else {
            response = number + "";
        }
        return response;
    }

    /**
     * Gera representação gráfica em Matriz de Adjacência
     * 
     * @param {Graph} graph - objeto que representa o grafo
     * 
     * @return {String} representação do grafo
     */
    public static String graphRepresentationAM(Graph graph) {
        double[][] matrix = graph.getNodesAsMatrix();
        String rep = "";

        matrix = fillMatrixWithIndexed(matrix, graph.getOrderedNodes());
        // String 
        
        for (var i = 0; i < matrix.length; i++){
            // rep += nodes.get(i) + " ";
            for (var j = 0; j < matrix[i].length;j++){
                if (i == 0 && j == 0){
                    rep += "  ";
                    continue;
                }
                rep += getNumberForOutput(matrix[i][j]) + " ";
            }
            rep += System.lineSeparator();
        }
        return rep;
    }
}

package Grafo;
/*@author flavio */
public interface Aresta_Interface <T>{
	//Identidade Única da aresta
	int getId();

	Vertice_Interface <T> getV1();
	Vertice_Interface <T> getV2();
	double getPeso();
	boolean ehDirigida();

	//Retorna o vértice oposto ao vértice v nesta aresta
	Vertice_Interface <T> getOposto(Vertice_Interface <T> v);

}

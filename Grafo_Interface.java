package Grafo;
/* @author flavio*/
public interface Grafo_Interface <T>{

	int getNumeroVertices();
	int getNumeroArestas();

	boolean ehDirigido();

	void adicionarVertice(Vertice v);
	void adicionarAresta(Aresta a);
	void removerVertice(Vertice v);
	void removerAresta(Aresta a);

	//Retorna o vértice que tem essa identificação
	Vertice<T> getVertice (int id);

	//Retorna a aresta que tem essa identificação
	Vertice<T> getAresta (int id);

	boolean ehConexo();
	boolean ehCompleto();
	boolean ehArvore();

	Vertice<T>[] getVertices();
	Aresta<T>[] getArestas();


    Aresta<T>[] getArestasIncidentes(Vertice v);
    Vertice<T>[] getVerticesAdjacentes(Vertice v);

}
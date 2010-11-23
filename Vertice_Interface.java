package Grafo;
/* @author flavio */
public interface Vertice_Interface <T> {

	//Identidade Única do vértice
   int getId();
   //Informação que vai estar associada ao vértice (Nome de Rua, por exemplo)
   T getInfo();
   //Configuraro ID quando for preciso
   void setId(int id);
   //Configurar a informação quando for preciso
   void setInfo(T info);

}

package Grafo;
/*@author flavio
 */
public class Main {
/* 
*/
    public static void main(String[] args) {
      Vertice_Interface <String> flavio = new Vertice ("asdasdad");
      Grafo <String> labirinto = new Grafo(false,50);

      System.out.println(flavio.getInfo());
      int [][]matriz;
      int [][] matriz2;
      matriz = new int [10][10];
      matriz2 = new int [15][15];
      for (int z=0;z <10;z++){
          for (int v=0; v<10;v++){
              matriz2[z][v]= matriz[z][v];
          }
      }
    }
}

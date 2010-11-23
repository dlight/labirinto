package Grafo;
/* @author flavio*/
public class Grafo <T> implements Grafo_Interface <T> {
    //public static int count_size;
    private int size;
    private int numerodeVertices=0;
    private int numerodeArestas=0;
    private boolean ehDirigido;
    private int [] last_arest = new int [2];
    private Aresta <T> [][] matriz;
    private Vertice <T> [] listaVert;

    public Grafo(boolean eh_dirigido,int tamanho){
        size =tamanho;
        ehDirigido= eh_dirigido;
        matriz = new Aresta [tamanho][tamanho];
        listaVert = new Vertice [tamanho];
    }

    public int getNumeroVertices(){
        return numerodeVertices;}
    public int getNumeroArestas(){
        return numerodeVertices;
    }
    public boolean ehDirigido(){
        return ehDirigido;}
    public void adicionarVertice(Vertice v){
        numerodeVertices = numerodeVertices +1;
        Aresta [][] novamatriz = new Aresta  [size+1][size+1];
        Vertice [] novalistaVert = new Vertice [size+1];
        for (int i = 0;i<size;i++){
            novalistaVert[i]=listaVert[i];
            for (int j = 0;j<size;j++){
                novamatriz[i][j]=matriz[i][j];
            }
        }
     novalistaVert[size]=v;
     size=size+1;
     this.matriz = new Aresta [size][size];
     this.listaVert = new Vertice [size];
     for (int i = 0;i<size;i++){
                listaVert[i]=novalistaVert[i];
                for (int j = 0;j<size;j++){
                matriz[i][j]=novamatriz[i][j];
            }
        }

    }//com imp.
    public void adicionarAresta(Aresta a){
        if(matriz[a.getV1().id][a.getV2().id] != null || matriz[a.getV2().id][a.getV1().id] != null ){
            System.out.println("A Aresta Já Existe!!");
        }
        else{matriz[a.getV1().id][a.getV2().id]=a;
        numerodeArestas=numerodeArestas+1;
        last_arest[0]= a.getV1().id;
        last_arest[1]=a.getV2().id;
        }
    }//com imp.
    public void removerVertice(Vertice v){

     }//sem imp.
    public void removerAresta(Aresta a){
        int idremovido = a.getId();
        (matriz[last_arest[0]][last_arest[1]]).setId(idremovido);
         numerodeArestas=numerodeArestas-1;
    }
    /*Meia implementação falta dizer
     qnd a aresta de id maior substitui de menor removida e quando cria outra
     matriz*/

      

    }//asdjkhsjkdh

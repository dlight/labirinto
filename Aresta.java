package Grafo;
/* @author flavio */
public class Aresta <T> implements Aresta_Interface <T>{
     private static int countId =0;
     private int id;
     private double peso;
     private boolean dirigida;
     private  Vertice <T> v1;
     private  Vertice <T> v2;

     public Aresta(){
         this.peso=0;
         this.id=countId;
         countId++;
         this.dirigida=false;
     }

     public Aresta(double peso,boolean dirigida,Vertice <T> v1,Vertice <T>v2){
         this.peso=peso;
         this.dirigida=dirigida;
         if(v1.id >v2.id ){
            this.v1=v1;
            this.v2=v2;
         }
         else{this.v1=v2;
            this.v2=v1;
         }
         if (v1.id ==v2.id ){
             System.out.println("Vertices Iguais!");
         }
         this.id=countId;
         countId++;

     }

     public int getId(){
         return id;
     }
     public void setId(int id){
        this.id=id;
     }
     public double getPeso(){
         return peso;
     }
     public boolean ehDirigida(){
         return dirigida;
     }
     public Vertice <T> getV1(){
         return v1;
     }
     public Vertice <T> getV2(){
         return v2;
     }
     public Vertice <T> getOposto(Vertice_Interface <T> v){
         if (this.v1.getId() == v.getId()){
             return v2;
         }
         else{
             return v1;
         }
     }
}

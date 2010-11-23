package Grafo;
/* @author flavio*/
public class Vertice <T> implements Vertice_Interface <T> {
    public static int countid = 0;
    public int id;
    private T info;

   public  Vertice(){
        this.id=countid;
        countid++;
    }
   public Vertice(T info){
      this.id=countid;
        countid++;
        this.info=info;
   }

    public int getId(){
        return id;
    }
    public T getInfo(){
        return info;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setInfo(T info){
        this.info=info;
    }
}

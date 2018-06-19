package kanguru;

public class Corrida {
    public int colocacao[] = new int[5];
    public int tamCorrida = ((int)(Math.random()*20)+80);
    public boolean acabou=false;
    int aux;

    public synchronized void chegada(int canguru){
        this.colocacao[aux] = canguru;
        aux++;
        if(aux==5){
            acabou=true;
            podium();
        }
        
    } 
    public void podium(){
        System.out.println("Órdem de Chegada:");
        for(int i=0;i<5;i++)
            System.out.println("Jack "+this.colocacao[i]);
    }
}

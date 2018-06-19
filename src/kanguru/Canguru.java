package kanguru;

public class Canguru extends Thread{
    private int DistPercorrida;
    private int DistMaxPercorrida;
    private boolean chegou;
    private Corrida corrida;
    public int id;
    public boolean pula;
    
    public Canguru(String nome,Corrida corrida,int id){
        super(nome);
        this.corrida = corrida;
        this.id = id;
    }
    public int getDistPercorrida() {
        return DistPercorrida;
    }
    public void setDistPercorrida(int DistPercorrida) {
        this.DistPercorrida = DistPercorrida;
    }
    public int getDistMaxPercorrida() {
        return DistMaxPercorrida;
    }
    public void setDistMaxPercorrida(int DistMaxPercorrida) {
        this.DistMaxPercorrida = DistMaxPercorrida;
    }
    
    public synchronized void Pula(){
        int pulou;
        setDistPercorrida((int)(Math.random()*10)+1);
        pulou = getDistMaxPercorrida();
        pulou += getDistPercorrida();
        setDistMaxPercorrida(pulou);
        System.out.println("Nome: "+Thread.currentThread().getName()+"\nPulou : "
                + getDistPercorrida()+ "\nTotal percorrido : "+ getDistMaxPercorrida()+"\n");
        if(this.DistMaxPercorrida >= this.corrida.tamCorrida){
            chegou = true;
            corrida.chegada(this.id);
        }
        pula = true;
        notify();
    }
    
    public synchronized void espera(){
        
        while(!this.pula){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.pula = false;
    }    
 
    @Override
    public void run(){
        while(!chegou){
            Pula();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            espera();
        }
        
        
    }
}

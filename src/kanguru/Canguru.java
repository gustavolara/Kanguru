package kanguru;

public class Canguru extends Thread{
    private int DistPercorrida;
    private int DistMaxPercorrida;
    public boolean chegou;
    private Corrida corrida;
    public int id;
    
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
 
    @Override
    public void run(){
        do{
            if(corrida.ordem == this.id-1){
                corrida.Pula(this);
            }
            else{
                try {
                    corrida.espera(this);
                }catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }while(!this.chegou);
    }
}

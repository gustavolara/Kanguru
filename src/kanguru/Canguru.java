package kanguru;

public class Canguru extends Thread{
    private int id;
    private int DistMaxPercorrida;
    public boolean chegou;
    private Corrida corrida;
        
    public Canguru(String nome,Corrida corrida,int id){
        super(nome);
        this.corrida = corrida;
        this.id = id;
    }

    public int getIdCanguru() {
        return id;
    }
    public int getDistMaxPercorrida() {
        return DistMaxPercorrida;
    }
    public void setDistMaxPercorrida(int DistMaxPercorrida) {
        this.DistMaxPercorrida = DistMaxPercorrida;
    }  
    // Se for a vez do canguru: Pula, se não for: Espera
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

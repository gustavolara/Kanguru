package kanguru;

public class Corrida {
    public int colocacao[] = new int[5];
    public int tamCorrida = ((int)(Math.random()*20)+80);
    public boolean acabou=false;
    int aux;
    public int ordem;

    public void chegada(int canguru){
        this.colocacao[aux] = canguru;
        aux++;
        if(aux==5){
            acabou=true;
            podium();
        }   
    } 
    
    public void podium(){
        System.out.println("Ordem de Chegada:");
        for(int i=0;i<5;i++)
            System.out.println("Jack "+this.colocacao[i]);
    }
    
    public synchronized void Pula(Canguru canguru){
        int pulou;
        canguru.setDistPercorrida((int)(Math.random()*10)+1);
        pulou = canguru.getDistMaxPercorrida();
        pulou += canguru.getDistPercorrida();
        canguru.setDistMaxPercorrida(pulou);
        System.out.println("Nome: "+Thread.currentThread().getName()+"\nPulou : "
                + canguru.getDistPercorrida()+ "\nTotal percorrido : "+ canguru.getDistMaxPercorrida()+"\n");
        if(canguru.getDistMaxPercorrida() >= this.tamCorrida){
            canguru.chegou = true;
            this.chegada(canguru.id);
        }
        
        if(!this.acabou){
            do{
                this.ordem++;
                this.ordem = this.ordem % 5;
            }while(this.verifaSeJaChegou());
        }
        
        notifyAll();
    }
  
    public boolean verifaSeJaChegou()
    {
        for(int i = 0;i < this.colocacao.length;i++){
            if(this.colocacao[i] - 1 == this.ordem){
                return true;
            }
        }
        
        return false;
    }
    
    public synchronized void espera(Canguru canguru){
        
        while((canguru.id-1) != this.ordem){
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

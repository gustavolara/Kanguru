package kanguru;

public class Corrida {
    public int colocacao[] = new int[5];
    public int tamCorrida = ((int)(Math.random()*20)+80);
    public boolean acabou=false;
    int aux;
    public int ordem;
    //Recebe o Id da ordem de chegada dos Cangurus
    public void chegada(int canguru){
        this.colocacao[aux] = canguru;
        aux++;
        if(aux==5){
            acabou=true;
            podium();
        }   
    } 
    // Apresenta a Ordem de Chegada dos Cangurus ao final da corrida.
    public void podium(){
        System.out.println("Ordem de Chegada:");
        for(int i=0;i<5;i++)
            System.out.println("Jack "+this.colocacao[i]);
    }
    
    public synchronized void Pula(Canguru canguru){
        //Pula
        int pulou = ((int)(Math.random()*10)+1);
        System.out.println("Nome: "+Thread.currentThread().getName()+"\nPulou : "
                + pulou + "\nTotal percorrido : "+ (canguru.getDistMaxPercorrida()+pulou)+"\n");
        canguru.setDistMaxPercorrida(canguru.getDistMaxPercorrida() + pulou);
        //Verifica se chegou; Se sim coloca no vetor da ordem e mata a thread
        if(canguru.getDistMaxPercorrida() >= this.tamCorrida){
            canguru.chegou = true;
            this.chegada(canguru.id);
        }
        //Passa a vez; Se o canguru da vez ja chegou passa a vez novamente
        if(!this.acabou){
            do{
                this.ordem++;
                this.ordem = this.ordem % 5;
            }while(this.verifaSeJaChegou());
        }
        //Avisa que pulou
        notifyAll();
    }
    //Verifica se o Canguru da vez já terminou a corrida, caso tenha terminado passa a vez para o próximo
    public boolean verifaSeJaChegou(){
        for(int i = 0;i < this.colocacao.length;i++){
            if(this.colocacao[i] - 1 == this.ordem){
                return true;
            }
        }
        return false;
    }
    //Mantém o canguru em espera enquanto não for a vez dele de Pular
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

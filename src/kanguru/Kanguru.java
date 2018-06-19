package kanguru;

public class Kanguru {
    public static void main(String[] args) {
        Corrida corrida = new Corrida();
        System.out.println("Tamanho da Corrida : "+ corrida.tamCorrida + "\n\n");
        Canguru jack1 = new Canguru("Jack 1",corrida,1);
        Canguru jack2 = new Canguru("Jack 2",corrida,2);
        Canguru jack3 = new Canguru("Jack 3",corrida,3);
        Canguru jack4 = new Canguru("Jack 4",corrida,4);
        Canguru jack5 = new Canguru("Jack 5",corrida,5);
        //Canguru camera = new Canguru("Camera",corrida,buffer);
        
        jack1.start();
        jack2.start();
        jack3.start();
        jack4.start();
        jack5.start();
        //camera.start();
        
        
    }
    
}
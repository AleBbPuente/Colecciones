import java.util.*;

public class deck {

    private HashMap<String, String> palos = new HashMap<String, String>();
    private ArrayList<card> juego = new ArrayList<card>();
    private String strFormat = "Quedan %s";

    int e ;

    deck() {

    }

    public ArrayList<card> getJuego() {
        return juego;
    }

    public void initPalos() {
        palos.put("corazon", "rojo");
        palos.put("diamane", "rojo");
        palos.put("pica", "negro");
        palos.put("trebol", "negro");
    }

    public void init() {
        for (Map.Entry<String, String> palo : palos.entrySet()) {
            for (int i = 1; i <= 13; i++) {
                card card = new card(palo.getKey(), palo.getValue());
                card.setValor(i);
                juego.add(card);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(juego);
        System.out.println("Se mezcló el Deck");
    }

    public void head() throws Exception {

        var card = juego.get(juego.size() - 1);
        juego.remove(card);
        System.out.println(card.toString());
        System.out.println(String.format(strFormat, juego.size()));

        if (juego.isEmpty())
            throw new Exception("No quedan cartas");
        return ;

    }

    public card pick() throws Exception{

        var card = randomCard();
        juego.remove(card);
        System.out.println(card.toString());
        System.out.println(String.format(strFormat, juego.size()));

        e = juego.size();
        if(e == 0){
            throw new Exception("Ya no quedan cartas");
        }
        return card;
    }

    public void hand() throws Exception{
        var card = new ArrayList<card>();
        if (juego.isEmpty()){
            throw new Exception( "ya no hay cartas :(");
        }
        else if (juego.size()<5){
            throw new Exception(" quedan menos de 5 cartas");
        }
        else {
            for (int i =1; i<=5; i++ ){
                card.add(pick() );
            }
        }
    }


    private void printHand(ArrayList<card> cards) {
        for (var card : cards) System.out.println(card.toString());
    }

    private card randomCard() {

        var max = juego.size() -1;
        var rnd = (int) Math.floor(Math.random()*(0-max+1)+max);
        return juego.get(rnd);
    }

    public void showMenu() {
        Scanner leer = new Scanner(System.in);

        boolean salir =false;

        int opcion;

        while (!salir){
            System.out.println("Bienvenido a Poker!\n" +
                    "Selecciona una opción:\n" +
                    "1 Mezclar deck\n" +
                    "2 Sacar una carta\n" +
                    "3 Carta al azar\n" +
                    "4 Generar una mano de 5 cartas\n" +
                    "0 Salir");
            try {

                System.out.print("Introduce una opcion:");
            opcion = leer.nextInt();


                switch (opcion) {
                    // Mezclar deck//
                    case 1:
                        shuffle();
                        break;
                    //Sacar una carta//
                    case 2:
                        head();
                        break;
                    //Carta al azar//
                    case 3:
                        pick();
                        break;
                    //Generar una mano de 5 cartas//
                    case 4:
                        hand();
                        break;
                    //Salir//
                    case 0:
                        System.out.println("Hasta la proxima");
                        salir=true;
                        break;
                    default:
                        throw new IllegalStateException("Opcion no valida: " + opcion);
                }

            } catch (InputMismatchException e) {
                System.out.println("Opcion no valida");
                leer.next();
            }
            catch (Exception e ){
                e.printStackTrace();
            }
        }
    }
}

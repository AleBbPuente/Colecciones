import java.util.*;

public class deck {

    private HashMap<String, String> palos = new HashMap<String, String>();
    private ArrayList<card> juego = new ArrayList<card>();
    private String strFormat = "Quedan %s";

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

    public void head() {

        var card = juego.get(juego.size() - 1);
        juego.remove(card);
        System.out.println(card.toString());
        System.out.println(String.format(strFormat, juego.size()));

    }

    public void pick() {

        var card = randomCard();
        juego.remove(card);
        System.out.println(card.toString());
        System.out.println(String.format(strFormat, juego.size()));

    }

    public void hand() {
        if (juego.size() <= 5) {
            for (var card : juego) {
                printHand(juego);
            }
        } else {
            var cards = new ArrayList<card>();
            card card;
            for (int i = 1; i <= 5; i++) {
                card = randomCard();
                juego.remove(card);
                cards.add(card);
            }
            printHand(cards);
            System.out.println(String.format(strFormat, juego.size()));
        }
    }

    private void printHand(ArrayList<card> cards) {
        for (var card : cards) System.out.println(card.toString());
    }

    private card randomCard() {

        var rnd = (int) Math.floor(Math.random() * (1 - juego.size() + 1) + juego.size());
        return juego.get(rnd);
    }

    public void showMenu() {
        Scanner leer = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("Bienvenido a Poker!\n" +
                    "Selecciona una opción:\n" +
                    "1 Mezclar deck\n" +
                    "2 Sacar una carta\n" +
                    "3 Carta al azar\n" +
                    "4 Generar una mano de 5 cartas\n" +
                    "0 Salir");
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida\n");
                    break;
            }

        } while (opcion != 0);
    }

    static void checkmyCards(int cards) {
        if (cards == 0) {
            throw new ArithmeticException("Las cartas se acabaron");
        }
        else {
            System.out.println("Aun quedan cartas !");
        }
    }
}
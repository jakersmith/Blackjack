import java.util.InputMismatchException;
import java.util.Scanner;

public class Gameplay {
    Deck deck = new Deck();
    Player player = new Player();
    Player dealer = new Player("Dealer");
    boolean bust = false;
    boolean win = false;
    int bet;

    public Gameplay() {
        boolean playing = true;
        while(playing) {
            if (deck.getDeckSize() < 12) {
                deck = new Deck();
            }
            System.out.println("---BEGINNING OF BETTING PHASE---");
            System.out.println("Your account balance is: " + "$" + player.getAccount());
            System.out.print("How much would you like to bet? ");
            bettingPhase();
            dealingPhase();
            Scanner input = new Scanner(System.in);
            try {
                if (player.getAccount() == 0) {
                    Thread.sleep(2000);
                    System.out.println("You are out of money. GAME OVER");
                }
                Thread.sleep(3000);
                System.out.print("\nType 'Y' or 'Yes' to keep playing or anything else to exit: ");
                try {
                    String keepPlaying = input.next();
                    playing = keepPlaying.equalsIgnoreCase("y") || keepPlaying.equalsIgnoreCase("yes");
                    System.out.println();
                } catch (InputMismatchException ex) {
                    System.out.println("\nInvalid input. Type 'Y' or 'Yes' to keep playing or anything else to exit: ");
                    System.out.println();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void bettingPhase() {
            boolean done = false;
            while (!done) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    bet = scanner.nextInt();
                    if (bet > 0 && bet <= player.getAccount()) {
                        done = true;
                        player.bet(bet);
                    }
                    else {
                        throw new InputMismatchException();
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid bet. Please try again.");
                    System.out.print("How much would you like to bet? ");
                }
            }
        }

    public void dealingPhase() {
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());

        System.out.println("\n --- DEALER'S HAND ---");
        System.out.println("Unknown card");
        printHand(dealer, 1);

        System.out.println("\n --- YOUR HAND ---");
        printHand(player, 0);

        int cardNumber = 2;
        int handValue = player.getHand().get(0).getValue() + player.getHand().get(1).getValue();
        while (true) {
            if (handValue <= 21) {
                System.out.println("\nHand value: " + handValue);
                Scanner scanner = new Scanner(System.in);
                System.out.print("Type 'HIT' for another card or type anything else to stay: ");
                String input = scanner.next();
                System.out.println();
                if (input.equalsIgnoreCase("HIT")) {
                    player.addCard(deck.draw());
                    handValue += player.getHand().get(cardNumber).getValue();
                    cardNumber += 1;
                    for (int i = 0; i < player.getHand().size(); i++) {
                        System.out.println(player.getHand().get(i).getValueName() + " of " + player.getHand().get(i).getSuit());
                    }
                }
                else {
                    break;
                }
            }
            else {
                try {
                    Thread.sleep(1000);
                    System.out.println("\nHand value: " + handValue);
                    Thread.sleep(1000);
                    System.out.println("You bust!");
                    bust = true;
                    win = false;
                    break;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        int dealerHandValue = dealer.getHand().get(0).getValue() + dealer.getHand().get(1).getValue();
        int dealerHandIndex = 2;
        if (!bust) {
            System.out.println("\n--- DEALER'S HAND ---");
            printHand(dealer, 0);
        }
        while (dealerHandValue < 17 && !bust) {
            dealer.addCard(deck.draw());
            dealerHandValue += dealer.getHand().get(dealerHandIndex).getValue();
            System.out.println(dealer.getHand().get(dealerHandIndex).getValueName() + " of " + dealer.getHand().get(dealerHandIndex).getSuit());
        }
        try {
            if (!bust) {
                System.out.println("\nDealer's hand value: " + dealerHandValue);
                if (dealerHandValue > 21) {
                    Thread.sleep(1000);
                    System.out.println("DEALER BUSTS! YOU WIN!!!\n");
                    win = true;
                } else if (handValue == dealerHandValue) {
                    System.out.println("It's a TIE!");
                    win = true;
                    player.bet(bet);
                }
                else {
                    System.out.println("Your hand value: " + handValue + "\n");
                    if (handValue > dealerHandValue) {
                        System.out.println("You WIN!!!\n");
                        win = true;
                    } else {
                        System.out.println("You LOSE\n");
                        win = false;
                    }
                }
            }
            if (win) {
                player.addWinnings(bet);
            }
            System.out.println("Your account balance: " + "$" + player.getAccount());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        player.discardHand();
        dealer.discardHand();
        bust = false;
    }

    private static void printHand(Player player, int beginningIndex) {
        try {
            for (int i = beginningIndex; i < player.getHand().size(); i++) {
                Thread.sleep(1000);
                System.out.println(player.getHand().get(i).getValueName() + " of " + player.getHand().get(i).getSuit());
            }
            Thread.sleep(2000);
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }

}

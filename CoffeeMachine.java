package machine;
import java.util.*;
public class CoffeeMachine {

    static int waterPre = 400;
    static int milkPre = 540;
    static int beansPre = 120;
    static int cupsPre = 9;
    static int moneyPre= 550;

    public static void main(String[] args) {

        boolean stop = false;
        while (!stop) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = askUser();
            System.out.println();
            switch (action) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    printRemain();
                    break;
                case "exit":
                    stop = true;
                    break;
            }
        }
    }

    public static String askUser(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input.trim().toLowerCase();
    }

    public static void buy(){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String action = askUser();
        boolean irrelevant = false;
        Coffee_type ct = Coffee_type.NOTHING;
        switch (action) {
            case "1":
                 ct = Coffee_type.ESPRESSO;
                break;
            case "2":
                ct = Coffee_type.LATTE;
                break;
            case "3":
                ct = Coffee_type.CAPPUCCINO;
                break;
            default:
                irrelevant = true;
        }
        if (!irrelevant)
            makeCoffee(ct);
    }

    public static void makeCoffee(Coffee_type ct){
        if (waterPre < ct.getWater())
            System.out.println("Sorry, not enough water!\n");
        else if (milkPre < ct.getMilk())
            System.out.println("Sorry, not enough milk!\n");
        else if(beansPre < ct.getBeans())
            System.out.println("Sorry, not enough beans!\n");
        else{
            moneyPre += ct.getCost();
            waterPre -= ct.getWater();
            beansPre -= ct.getBeans();
            milkPre -= ct.getMilk();
            cupsPre -= 1;
            System.out.println("I have enough resources, making you a coffee!\n");
        }
    }

    public enum Coffee_type{
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6),
        NOTHING(0, 0, 0, 0);

        public final int water;
        public final int milk;
        public final int beans;
        public final int cost;

        Coffee_type(int water, int milk, int beans, int cost) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cost = cost;
        }

        public int getWater(){
            return this.water;
        }

        public int getMilk(){
            return this.milk;
        }

        public int getBeans(){
            return this.beans;
        }
        public int getCost(){
            return this.cost;
        }
    }

    public static void fill(){
        System.out.println("Write how many ml of water do you want to add:");
        int waterAdd = Integer.parseInt(askUser());
        System.out.println("Write how many ml of water do you want to add:");
        int milkAdd = Integer.parseInt(askUser());
        System.out.println("Write how many ml of water do you want to add:");
        int beansAdd = Integer.parseInt(askUser());
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        int cupsAdd = Integer.parseInt(askUser());
        waterPre +=  waterAdd;
        milkPre += milkAdd;
        beansPre += beansAdd;
        cupsPre += cupsAdd;

        System.out.println();
    }

    public static void take(){
        System.out.println("I gave you $" + moneyPre);
        System.out.println();
        moneyPre = 0;
    }

    public static void printRemain(){
        System.out.println("The coffee machine has:");
        System.out.println(waterPre + " of water");
        System.out.println(milkPre + " of milk");
        System.out.println(beansPre + " of coffee beans");
        System.out.println(cupsPre + " of disposable cups");
        System.out.println("$" + moneyPre + " of money");
        System.out.println();
    }
}

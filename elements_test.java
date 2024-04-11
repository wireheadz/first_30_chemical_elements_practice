import java.util.Scanner;
import java.util.ArrayList;
public class elements_test {
    private static int methodCount; // record how many time infinite mode's method run
    private static int streak; // record user's streak
    private static int count; // record how many elements user practiced
    private static int longestStreak = -1;
    private static int correct = 0;
    private static Scanner mysc = new Scanner(System.in);
    private static String[] symbolArr = new String[] { "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg",
            "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn" };
    private static String[] fullNameArr = new String[] { "hydrogen", "helium", "lithium", "berylium", "boron", "carbon",
            "nitrogen", "oxygen", "fluorine", "neon", "sodium", "magnesium", "aluminium", "silicon", "phosphorus",
            "sulfur", "chlorine", "argon", "potassium", "calcium", "scandium", "titanium", "vanadium", "chromium",
            "manganese", "iron", "cobalt", "nickel", "copper", "zinc" };
    private static ArrayList<element> list = new ArrayList<element>(); 

    public static void main(String[] args) {
        Scanner logicScanner = new Scanner(System.in);   
        String input;

        initialize();
        input = logicScanner.nextLine();

        while (!input.equalsIgnoreCase("I") && !input.equalsIgnoreCase("C") && !input.equalsIgnoreCase("quit")) {
            System.out.println("Please enter a correct letter(I or C)");
            input = logicScanner.nextLine();
        }
        if (input.equalsIgnoreCase("I")) {
            randomElement();
        }
        else if (input.equalsIgnoreCase("C")) {
            randomElement(3);
        }
        else if (input.equalsIgnoreCase("quit")) {
            System.exit(0);
        }

        logicScanner.close();
        
    }
    private static void initialize() {
        for (int i = 0; i < 30; i ++) {
            list.add(new element(symbolArr[i], fullNameArr[i], 0));
        }
        System.out.println("--- --- --- Welcome to elements test --- --- ---");
        System.out.println("What mode do you want to play?");
        System.out.println("Infinite mode(I) Challenge mode(C)");
        System.out.println("Type 'quit' to quit anytime you want");
    }

    private static void randomElement() {
        if (methodCount == 30) { // if method run 30 times, refresh all elements's count
            for (int i = 0; i < 30; i ++) {
                list.get(i).setCount(0);
            }
            methodCount = 0;
        }
        int idx = (int) (Math.random() * 30);
        while (list.get(idx).getCount() != 0) { // if this elements count != 0 which means already appear once, change another
            idx = (int) (Math.random() * 30);
        }
        System.out.println("What is the name of chemical symbol " + list.get(idx).getSymbol());
        list.get(idx).setCount(1);
        String answer = mysc.nextLine();
        if (answer.replaceAll(" ", "").equalsIgnoreCase(list.get(idx).getName())) {
            correct ++;
            System.out.println("You are correct! Current streak is: " + ++streak);
            if (streak > longestStreak) {
                longestStreak = streak;
            }   
        } 
        else if(answer.equalsIgnoreCase("quit")) {
            System.out.println("You practiced " + count + " element(s) with " + correct + " correct(S) today, good job.");
            System.out.println("Longest streak: " + longestStreak);
            System.exit(0);
        }
        else {
            streak = 0;
            System.out.println("You are wrong, correct answer is " + fullNameArr[idx]);
        }
        count ++;
        methodCount ++;
        randomElement(); // recursion
    }
    private static void randomElement(int life) {
        if (life == 0) {
            System.out.println("Game Over. You have " + correct + " correct(s) out of " + count + "\nYour score is " + (int)(100.0 / count * correct));
            System.exit(0);
        }
        if (methodCount == 30) { // if method run 30 times, refresh all elements's count
            System.out.println("You already done all elements.\nYou have " + correct + " correct(s) out of " + count + "\nYour score is " + (int)(100.0 / count * correct));
            System.exit(0);
        }
        int idx = (int) (Math.random() * 30);
        while (list.get(idx).getCount() != 0) { // if this elements count != 0 which means already appear once, change another
            idx = (int) (Math.random() * 30);
        }
        System.out.println("What is the name of chemical symbol " + list.get(idx).getSymbol());
        list.get(idx).setCount(1);
        String answer = mysc.nextLine();
        if (answer.replaceAll(" ", "").equalsIgnoreCase(list.get(idx).getName())) {
            streak ++;
            System.out.println("You are correct! Current streak is: " + streak);
            if (streak > longestStreak) {
                longestStreak = streak;
            }
            correct ++;
            count ++;
            methodCount ++;
            randomElement(life);
        } 
        else if(answer.equalsIgnoreCase("quit")) {
            System.out.println("You quit the challenge mode, your score won't be recorded");
            System.exit(0);
        }
        else {
            streak = 0;
            System.out.println("You are wrong, correct answer is " + fullNameArr[idx]);
            count ++;
            methodCount ++;
            randomElement(--life);
        }
    }
}

class element{
    private String symbol;
    private String name;
    private int count;
    public element(String s, String n, int c) {
        symbol = s;
        name = n;
        count = c;
    }
    public String getSymbol() {
        return this.symbol;
    } 
    public String getName() {
        return this.name;
    } 
    public int getCount() {
        return this.count;
    } 
    public void setCount(int c) {
        this.count = c;
    }
}
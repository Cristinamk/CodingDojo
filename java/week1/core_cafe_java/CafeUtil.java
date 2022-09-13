import java.util.ArrayList;
import java.text.DecimalFormat;;
public class CafeUtil {

    DecimalFormat df = new DecimalFormat("0.00");

    public int getStreakGoal(){
        int sum = 0;
        for (int i=1;i<=10;i++){
            sum += i;
        }
        return sum;
    }

    public double getOrderTotal(double[] arr){
        double sum = 0.0;
        for (int i=0; i<arr.length; i++){
            sum += arr[i];
        }
        return sum;
    }

    public void displayMenu(ArrayList<String> menuItems){
        for (int i=0;i<menuItems.size();i++){
            System.out.println(i + " " + menuItems.get(i));
        }
    }

    
    public void printPriceChart(String product,double price, int maxQuantity){
        String results = String.format("%s Coffee Grounds\n",product);
        double discountPerPurchase = 1.5;
        for (int i=0;i<maxQuantity;i++){
            String this_string = String.format("%s - $%s\n", i+1, df.format((price*(i+1))-discountPerPurchase*i));
            results += this_string;
        }
        System.out.println(results);
    }

    public boolean displayMenu(ArrayList<String> products, ArrayList<Double> prices){
        if (products.size() == prices.size()) {
            for (int i=0;i<products.size();i++){
                System.out.println(String.format("%s %s - %s", i,products.get(i),df.format(prices.get(i))));
            }
            return true;
        }
        return false;
    }
}

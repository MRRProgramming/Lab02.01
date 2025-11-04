import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BMICalculator is used to calculate the users BMI value and print it to them after
 * taking height and weight inputs
 * @author mradhakrishnan
 * @version 11.03.25
 */
public class BMICCalculator {

    /** Convert English to metric units, perform the BMI calculation.
     * NOTE: this method must properly handle bad data */
    /**
     * Convert English to metric units, perform the BMI calculation.
     * NOTE: this method must be given SANITIZED data but flag unrealistic data
     * @param inches
     * @param pounds
     * @return a person's BMI represented as kg/m^2
     */
    public static double computeBMI(int inches, int pounds) {
        double inch = 0.0254;
        double pound = 0.454;
        double meters = inches*inch;
        double kg = pounds*pound;
        return kg/Math.pow(meters,2);

    }
    /** Uses a Scanner to prompt the user for info, process the
     * feet/inches conversion, calls the computeBMI method and prints the
     * correct information.
     * */
    /**
     * Uses a Scanner to prompt the user for info, process the
     * feet/inches conversion, calls the computeBMI method and prints the
     * correct information.
     * @param args
     */
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");
        Scanner input = new Scanner(System.in);
        Pattern pattern = Pattern.compile("\\d+'\\d+\"");
        String extractedHeight, extractedWeight;
        Matcher matcher = null;
        int feet = 0;
        int inches = 0;
        int pounds = 0;
        int weight;
        while (true) {
            System.out.print("Enter your height in feet and inches (Ex 6'1\"): ");
            matcher = pattern.matcher(input.nextLine());
            if (matcher.find()) {
                // no negative values

                extractedHeight = matcher.group();
                feet = Integer.parseInt(extractedHeight.substring(0, extractedHeight.indexOf("'")));
                inches = Integer.parseInt(extractedHeight.substring(extractedHeight.indexOf("'") + 1,extractedHeight.length()-1));
                // invalid ranges:
                // feet 0 to 15, inclusive [0,15]
                // inches 0 to 11, inclusive [0,11]
                if (feet > 15 || inches > 11)
                    System.out.println("Invalid input - Height format is incorrect");
                else {
                    inches += 12*feet;
                    break;
                }
            } else {
                System.out.println("Invalid input - Height format is incorrect");
            }
        }
        while (true) {
            try {
                System.out.print("Enter your weight in pounds: ");
                extractedWeight = input.nextLine().trim().replaceAll("[^0-9]", "");
                pounds = Integer.parseInt(extractedWeight);
                if (pounds >1500)
                    System.out.println("Invalid input - Weight is unrealistic");
                else
                    break;
            }
            catch (Exception e) {
                System.out.println("Invalid input - Weight format is incorrect");
            }
        }
        System.out.println("Your BMI, expressed as weight(kg)/height(m)^2: "+ df.format(computeBMI(inches, pounds)) +" kg/m^2");
    }
}

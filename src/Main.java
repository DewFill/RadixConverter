import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        try {
            int sourceBase = sc.nextInt();
            String numberString = sc.next();
            int newBase = sc.nextInt();
            if (sourceBase < 1 || sourceBase > 36 || newBase < 1 || newBase > 36) {
                throw new Exception();
            }
//##########################################################################
            if (numberString.contains(".")) {
                String convertedIntPartToDecimal = convert(sourceBase, numberString.substring(0, numberString.indexOf('.')), 10);
                String doublePart = numberString.substring(numberString.indexOf('.'));

                double convertedDoublePart = 0;
                for (int i = 1; i < doublePart.length(); i++) {
                    convertedDoublePart += Character.getNumericValue(doublePart.charAt(i)) / Math.pow(sourceBase, i);
                }
                String convertedNumberToDecimal = Double.toString(Integer.parseInt(convertedIntPartToDecimal)  + Double.parseDouble(Double.toString(convertedDoublePart)));
                String convertedIntToRadix = convert(10, convertedNumberToDecimal.substring(0, convertedNumberToDecimal.indexOf('.')), newBase);
                StringBuilder convertedDoubleToRadix = new StringBuilder();
                double convertedDoublePartForFOR = Double.parseDouble(convertedNumberToDecimal) - Integer.parseInt(convertedIntPartToDecimal);
                for (int i = 0; i < 5; i++) {
                    convertedDoublePartForFOR *= newBase;
                    if ((int) convertedDoublePartForFOR > 9) {
                        convertedDoubleToRadix.append((char) (96 + (int) convertedDoublePartForFOR - 9));
                    } else {
                        convertedDoubleToRadix.append((int) convertedDoublePartForFOR);
                    }
                    convertedDoublePartForFOR -= (int) convertedDoublePartForFOR;
                }
                System.out.println(convertedIntToRadix + '.' + convertedDoubleToRadix);

            } else {
                System.out.println(convert(sourceBase, numberString, newBase));
            }
        } catch (Exception e) {
            System.out.println("error");
        }

//##########################################################################
    }
    public static String convert(int fromBase, String numberString, int toBase){
        StringBuilder newNumber = new StringBuilder();
        if (fromBase == 1) {
            int decimalNumber = 0;
            int numberInt = Integer.parseInt(numberString, 10);
            while (numberInt != 0) {
                numberInt /= 10;
                decimalNumber++;
            }
            newNumber = new StringBuilder(Integer.toString(decimalNumber, toBase));
        } else if (toBase == 1) {
            int decimalNumber = Integer.parseInt(numberString, 10);
            for (int i = 0; i < decimalNumber; i++) {
                newNumber.append("1");
            }
        } else {
            int decimalNumber = Integer.parseInt(numberString, fromBase);
            newNumber = new StringBuilder(Integer.toString(decimalNumber, toBase));
        }
        return newNumber.toString();

    }

}

import java.util.Scanner;
// A basic calculator using methods for recursion

public class Main {
    public static float num1, num2, ans;
    public static String opp;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

//        // Prompt the user for input
//        System.out.print("Enter an arithmetic expression: ");
//        String input = scanner.nextLine();
//
//        // Evaluate the expression
//        int result = evaluateExpression(input);
//        System.out.println("Result: " + result);

        //simpleCalculator();
        advancedCalculator();
        //calculator();
    }
    public static void calculator() {
        System.out.println("\n\n\n\n\n\n\n\n");

        collectData(); // Collects data and calls the operator method

        System.out.println("\n" + num1 + " " + opp + " " + num2 + " is equal to " + ans);

        System.out.println("\nDo you want to start again (Y/N)?");
        Scanner scanOption1 = new Scanner(System.in);

        String option1 = scanOption1.nextLine();

        /*var loop = (option1.equals("Y") || option1.equals("y")) ? calculator(): (option1.equals("N") || option1.equals("n")) ? System.out.println("\n") : System.out.println("Enter a correct input");

        if (var.type == bool) {
            calculator();
        } else {
            String op = loop.nextLine();
        }
        System.out.println(op);*/

        if (option1.equals("Y") || option1.equals("y")) {
            calculator();
        } else if (option1.equals("N") || option1.equals("n")){
            System.out.println("\n");
        } else {
            System.out.println("Enter a correct input");
        }

    }
    public static void operatorOption(String choice1, float num1, float num2) {

        switch (choice1) {
            default:
                System.out.println("Warning!! Input the correct symbol.");
                break;
            case "*":
                ans = num1 * num2;
                opp = "multiplied by";
                break;
            case "/":
                ans = num1 / num2;
                opp = "divided by";
                break;
            case "-":
                ans = num1 - num2;
                opp = "subtracted from";
                break;
            case "+":
                ans = num1 + num2;
                opp = "added to";
                break;
        }
    }
    public static void collectData() {
        System.out.print("Enter the first number: ");
        Scanner scanNum1 = new Scanner(System.in);
        num1 = scanNum1.nextFloat();

        System.out.print("Select (+, -, /, *): ");
        Scanner scanChoice1 = new Scanner(System.in);

        String choice1 = scanChoice1.nextLine();

        System.out.print("Enter the second number: ");
        Scanner scanNum2 = new Scanner(System.in);
        num2 = scanNum2.nextFloat();

        operatorOption(choice1, num1, num2);

    }
    public static void advancedCalculator() {
        char func = ' ';
        char hint, hint2 = ' ';
        String[] bNums = new String[10];
        String bNum;
        String[] aNums = new String[10];
        String aNum;

        System.out.print("What do you want to calculate: ");
        Scanner scanCalc = new Scanner(System.in);

        String calc = scanCalc.nextLine();

        for (int i = 0; i < calc.length(); i++) {
            hint = calc.charAt(i);
            bNums[i].charAt(i) = calc.charAt(i);
            if (i == 0) {
                bNum = bNums[i];
            }
            if (hint == '+') {
                func = '+';
            } else if (hint == '-') {
                func = '-';
            } else if (hint == '*') {
                func = '*';
            } else if (hint == '/') {
                func = '/';
            } else if (hint == ' ') {
                continue;
            } else if (i == 0) {
                System.out.println();
            } else {
                hint2 += hint;
            }
        }

        System.out.println(func);
    }
    public static void simpleCalculator() {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for input
        System.out.print("Enter an arithmetic operation (e.g., '5 + 10'): ");
        String input = scanner.nextLine();

        // Split the input into operands and operator
        String[] parts = input.split("\\s+");
        int operand1 = Integer.parseInt(parts[0]);
        String operator = parts[1];
        int operand2 = Integer.parseInt(parts[2]);

        // Perform the operation
        int result = 0;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    System.out.println("Error: Division by zero");
                    return;
                }
                break;
            default:
                System.out.println("Invalid operator");
                return;
        }

        // Print the result
        System.out.println("Result: " + result);

        scanner.close();
    }
    public static int evaluateExpression(String expression) {
        // Remove whitespace
        expression = expression.replaceAll("\\s+", "");

        // Evaluate parentheses first
        while (expression.contains("(")) {
            int startIndex = expression.lastIndexOf("(");
            int endIndex = expression.indexOf(")", startIndex);
            String subExpression = expression.substring(startIndex + 1, endIndex);
            int subResult = evaluateExpression(subExpression);
            expression = expression.substring(0, startIndex) + subResult + expression.substring(endIndex + 1);
        }

        // Evaluate multiplication and division
        while (expression.contains("*") || expression.contains("/")) {
            int multiplyIndex = expression.indexOf("*");
            int divideIndex = expression.indexOf("/");

            if ((multiplyIndex < divideIndex && multiplyIndex != -1) || divideIndex == -1) {
                int operand1End = findOperandEnd(expression, multiplyIndex - 1, -1);
                int operand2Start = findOperandStart(expression, multiplyIndex + 1, 1);
                int operand1 = Integer.parseInt(expression.substring(0, operand1End + 1));
                int operand2 = Integer.parseInt(expression.substring(operand2Start));
                expression = expression.substring(0, operand1End + 1) + (operand1 * operand2) + expression.substring(operand2Start);
            } else {
                int operand1End = findOperandEnd(expression, divideIndex - 1, -1);
                int operand2Start = findOperandStart(expression, divideIndex + 1, 1);
                int operand1 = Integer.parseInt(expression.substring(0, operand1End + 1));
                int operand2 = Integer.parseInt(expression.substring(operand2Start));
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                expression = expression.substring(0, operand1End + 1) + (operand1 / operand2) + expression.substring(operand2Start);
            }
        }

        // Evaluate addition and subtraction
        while (expression.contains("+") || expression.contains("-")) {
            int additionIndex = expression.indexOf("+");
            int subtractionIndex = expression.indexOf("-");

            if ((additionIndex < subtractionIndex && additionIndex != -1) || subtractionIndex == -1) {
                int operand1End = findOperandEnd(expression, additionIndex - 1, -1);
                int operand2Start = findOperandStart(expression, additionIndex + 1, 1);
                int operand1 = Integer.parseInt(expression.substring(0, operand1End + 1));
                int operand2 = Integer.parseInt(expression.substring(operand2Start));
                expression = expression.substring(0, operand1End + 1) + (operand1 + operand2) + expression.substring(operand2Start);
            } else {
                int operand1End = findOperandEnd(expression, subtractionIndex - 1, -1);
                int operand2Start = findOperandStart(expression, subtractionIndex + 1, 1);
                int operand1 = Integer.parseInt(expression.substring(0, operand1End + 1));
                int operand2 = Integer.parseInt(expression.substring(operand2Start));
                expression = expression.substring(0, operand1End + 1) + (operand1 - operand2) + expression.substring(operand2Start);
            }
        }

        // The remaining expression should be a single number
        return Integer.parseInt(expression);
    }

    private static int findOperandEnd(String expression, int startIndex, int direction) {
        while (startIndex >= 0 && startIndex < expression.length() && Character.isDigit(expression.charAt(startIndex))) {
            startIndex += direction;
        }
        return startIndex - direction;
    }

    private static int findOperandStart(String expression, int startIndex, int direction) {
        while (startIndex >= 0 && startIndex < expression.length() && Character.isDigit(expression.charAt(startIndex))) {
            startIndex -= direction;
        }
        return startIndex + direction;
    }



}
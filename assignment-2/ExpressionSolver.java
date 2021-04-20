import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExpressionSolver {
    public static void main(String[] args) {
        String expression = getExpression();
        String postfix = infixToPostfix(expression);
        double result = evaluatePostfix(postfix);

        JOptionPane.showMessageDialog(null,
            "The result of the expression is:\n" +
            "Infix: "   + expression    + "\n" +
            "Postfix: " + postfix       + "\n" +
            "Result: "  + result
        );
    }

    public static String getExpression() {
        String str = JOptionPane.showInputDialog(null, "Please enter a numerical infix expression between 3 and 20 characters:");
        boolean valid = validateInfixExpression(str);

        // If validation fails, show an error pane and run function again
        if (!valid) {
            JFrame error = new JFrame();
            JOptionPane.showMessageDialog(error, "Only the following characters are valid: +, -, *, /, ^, (, ) and numbers 0-9");
            return getExpression();
        }

        return str;
    }

    public static boolean validateInfixExpression(String expression) {
        // Use ReGex to validate for correct characters.
        boolean chars = Pattern.compile("^[\\d+*\\/\\^(\\)\\-]*$").matcher(expression).matches();

        // Validate length.
        boolean length = expression.length() >= 3 && expression.length() <= 20;

        // Validate for balanced brackets by counting and comparing the number of left and right parenthesises.
        int left = 0, right = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') left++;
            else if (c == ')') right++;
        }

        // Validate for single digit numbers.
        char initial = expression.charAt(0);

        // Check if first character is a minus sign.
        if (initial == '-') return false;

        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If the previous and current chars are digits, then a double digit number is present.
            if (Character.isDigit(initial) && Character.isDigit(c)) return false;

            // Fail validation based on rules that compare the previous and current character:
            // 1. Digit followed by left parenthesis
            // 2. Left parenthesis followed by right parenthesis
            // 3. Two minus signs together
            // 4. Operator followed by a minus sign (use precedence() function to check if operator)
            // This offers basic validation for minus numbers and parenthesises
            if (Character.isDigit(initial) && c == '(' ||
                initial == ')' && c == '(' ||
                initial == '-' && c == '-' ||
                precedence(initial) != 0 && c == '-') return false;

            initial = c;
        }

        if (chars && length && left == right) return true;

        return false;
    }

    public static String infixToPostfix(String infix) {
        StringBuffer postfix = new StringBuffer(infix.length());
        Stack stack = new ArrayStack();

        for (int i = 0; i < infix.length(); ++i) {
            char c = infix.charAt(i);

            // If current character is operand then append to postfix expression.
            if (Character.isLetterOrDigit(c)) {
                postfix.append(String.valueOf(c));
            }

            // If current character is left parenthesis then push onto stack.
            else if (c == '(') {
                stack.push((Object) c);
            }

            // If current character is right parenthesis then while the stack is not empty
            // and the top of the stack is not a left parenthesis, then append the value of the popped stack.
            else if (c == ')') {
                while (!stack.isEmpty() && (char) stack.top() != '(') {
                    postfix.append(String.valueOf(stack.pop()));
                }
                stack.pop();
            }

            // If current character is an operator then while the stack is not empty
            // and the precedence of the operator is less than or equal to precedence of the operator on the
            // top of the stack append the value of the popped stack. Then push the result to the stack.
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence((char) stack.top())) {
                    postfix.append(String.valueOf(stack.pop()));
                }
                stack.push((Object) c);
            }
        }

        // Create the postfix string by popping the stack, while it is not empty.
        while (!stack.isEmpty()) {
            postfix.append(String.valueOf(stack.pop()));
        }

        return postfix.toString();
    }

    public static int precedence(char operator){
        switch (operator){
            case '^':
                return 3;
            case '/':
            case '*':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    public static double evaluatePostfix(String postfix) {
        double x, y, result = 0;
        Stack stack = new ArrayStack();

        for (int i = 0; i < postfix.length(); ++i) {
            char c = postfix.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                // If the current character is a number, then push it on the stack.
                stack.push((Object) c);
            } else {
                // Get values of operands from the stack.
                y = Double.parseDouble(String.valueOf(stack.pop()));
                if (stack.isEmpty()) break;
                x = Double.parseDouble(String.valueOf(stack.pop()));

                // Calculate based on current operator.
                switch (c) {
                    case '*':
                        result = x * y;
                        System.out.printf("%.1f * %.1f = %.1f\n", x, y, result);
                        stack.push(result);
                        break;
                    case '+':
                        result = x + y;
                        System.out.printf("%.1f + %.1f = %.1f\n", x, y, result);
                        stack.push(result);
                        break;
                    case '-':
                        result = x - y;
                        System.out.printf("%.1f - %.1f = %.1f\n", x, y, result);
                        stack.push(result);
                        break;
                    case '/':
                        result = x / y;
                        System.out.printf("%.1f / %.1f = %.1f\n", x, y, result);
                        stack.push(result);
                        break;
                    case '^':
                        result = Math.pow(x, y);
                        System.out.printf("%.1f ^ %.1f = %.1f\n", x, y, result);
                        stack.push(result);
                        break;
                    default:
                        break;
                }
            }
        }

        return result;
    }
}

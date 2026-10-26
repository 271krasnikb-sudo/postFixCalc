import java.util.Scanner;

/** STUDENT FILE: Complete evaluate. Leave all other project files unchanged. */
public class PostfixEvaluator {
    /**
     * Evaluates a space-separated postfix expression using integer arithmetic.
     * Supported operators: +, -, *, / (integer division).
     *
     * @throws PostFixException for blank, malformed, or invalid expressions
     * @throws ArithmeticException for division by zero
     */
    public static int evaluate(String expression) {
        BoundedStackInterface<Integer> stack = new ArrayStack<>(100);
        try (Scanner tokens = new Scanner(expression)) {
            
        while(tokens.hasNext()){
            String next =tokens.next();
            if(Character.isDigit(next.charAt(0))){
                try{
                    Integer.parseInt(next);
                } catch (Exception e) {
                    throw new PostFixException("Equation has invalid expressions");
                }
                stack.push(Integer.parseInt(next));
            }else{
                Integer a = null;
                Integer b = null;
                try {
                    a = stack.top();
                    stack.pop();
                    b = stack.top();
                    stack.pop();

                } catch (Exception e) {
                   throw new PostFixException("Equation is malformed, not enough intergers");
                }

                if(!(next.charAt(0)=='/'|| next.charAt(0)=='*'|| next.charAt(0)=='-'|| next.charAt(0)=='+')){
                    throw new PostFixException("Equation has invalid expressions");
                }
                if(next.charAt(0)=='/'){
                    if(a==0){
                        throw new ArithmeticException("Cannot divide by zero");
                    }
                    stack.push(b/a);

                } else if(next.charAt(0)=='*'){
                    stack.push(b*a);
                } else if(next.charAt(0)=='-'){
                    stack.push(b-a);
                } else if(next.charAt(0)=='+'){
                    stack.push(b+a);
                }
            }
        }

        if(stack.isEmpty()){
            throw new PostFixException("Equation is malformed, not enough intergers");
        }

        Integer result = stack.top();
        stack.pop();
        if(!stack.isEmpty()){
            throw new PostFixException("Equation is malformed, too many intergers");
        }

        return result;
        }
    }
}

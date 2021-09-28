import java.util.*;
public class expression{
    public static void main(String[] args){
        String str = "2 + 6 * 4 / 8 - 3";
        // int answer = evaluateInfix(str);
        // System.out.println(answer);
        // String infix = "a*(b-c+d)/e";
        // String postfix = evaluateInfixToPostfix(infix);
        // String prefix = evaluateInfixToPrefix(infix);
        // System.out.println(postfix);
        // System.out.println(prefix);
        // int answer = evaluatePostfix("264*8/+3-");
        // System.out.println(answer);
        // String infix = evaluatePostfixToInfix("264*8/+3-");
        // System.out.println(infix);
        // String prefix = evaluatePostfixToPrefix("264*8/+3-");
        // System.out.println(prefix);
        int answer = evaluatePrefix("-+2/*6483");
        System.out.println(answer);
        String infix = evaluatePrefixToInfix("-+2/*6483");
        System.out.println(infix);
        String postfix = evaluatePrefixToPostfix("-+2/*6483");
        System.out.println(postfix);

    }
    public static int evaluatePrefix(String str){
        Stack<Integer> vStack =  new Stack();
        for(int i = str.length() - 1 ;i>=0;i--){
            char ch = str.charAt(i);
            if(ch >= '0' && ch<='9'){
                vStack.push(ch - '0');
            }else{
                int val2 = vStack.pop();
                int val1 = vStack.pop();
                int res = solve(val2,val1,ch);
                vStack.push(res);
            }
        }
        return vStack.pop();
    }
    public static String evaluatePrefixToInfix(String str){
        Stack<String> vStack =  new Stack();
        for(int i = str.length() - 1 ;i>=0;i--){
            char ch = str.charAt(i);
            if(ch >= '0' && ch<='9'){
                vStack.push(""+ch);
            }else{
                String val2 = vStack.pop();
                String val1 = vStack.pop();
                String res = '(' + val2 + ch + val1 + ')';
                vStack.push(res);
            }
        }
        return vStack.pop();
    }

    public static String evaluatePrefixToPostfix(String str){
        Stack<String> vStack =  new Stack();
        for(int i = str.length() - 1 ;i>=0;i--){
            char ch = str.charAt(i);
            if(ch >= '0' && ch<='9'){
                vStack.push(""+ch);
            }else{
                String val2 = vStack.pop();
                String val1 = vStack.pop();
                String res = val2 + val1 + ch;
                vStack.push(res);
            }
        }
        return vStack.pop();
    }

    public static int evaluatePostfix(String str){
        Stack<Integer> vStack =  new Stack();
        for(int i = 0; i < str.length();i++){
            char ch = str.charAt(i);
            if(ch >= '0' && ch<='9'){
                vStack.push(ch - '0');
            }else{
                int val2 = vStack.pop();
                int val1 = vStack.pop();
                int res = solve(val1,val2,ch);
                vStack.push(res);
            }
        }
        return vStack.pop();
    }
    public static String evaluatePostfixToInfix(String str){
        Stack<String> vStack =  new Stack();
        for(int i = 0; i < str.length();i++){
            char ch = str.charAt(i);
            if(ch >= '0' && ch<='9'){
                vStack.push(""+ch);
            }else{
                String val2 = vStack.pop();
                String val1 = vStack.pop();
                String res = '(' + val1 + ch + val2 + ')';
                vStack.push(res);
            }
        }
        return vStack.pop();
    }
    public static String evaluatePostfixToPrefix(String str){
        Stack<String> vStack =  new Stack();
        for(int i = 0; i < str.length();i++){
            char ch = str.charAt(i);
            if(ch >= '0' && ch<='9'){
                vStack.push(""+ch);
            }else{
                String val2 = vStack.pop();
                String val1 = vStack.pop();
                String res = ch + val1 + val2;
                vStack.push(res);
            }
        }
        return vStack.pop();
    }
    public static String evaluateInfixToPrefix(String str){
        Stack<String> operand = new Stack();
        Stack<Character> operator = new Stack();
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if(c == ' '){
                continue;
            }else if(c >= 'a' && c<= 'z'){
                operand.push("" + c);
            }else if(c == '('){
                operator.push(c);
            }else if(c == ')'){
                while(operator.peek() != '('){
                    String val2 = operand.pop();
                    String val1 = operand.pop();
                    char op = operator.pop();
                    //int val = solve(val1,val2,op);
                    String val = op+val1 + val2;
                    operand.push(val);    
                }
                operator.pop();
            }else if(c == '*' || c =='/' || c == '+' || c =='-'){
                while(operator.size() > 0 && operator.peek() != '(' && priority(operator.peek()) >= priority(c)){
                    String val2 = operand.pop();
                    String val1 = operand.pop();
                    char op = operator.pop();
                    //int val = solve(val1,val2,op);
                    String val = op+val1 + val2;
                    operand.push(val);
                }
                //if(priority(operator.peek()) < priority(c)){
                operator.push(c);
                //}
                // }else{
                //     int val2 = operand.pop();
                //     int val1 = operand.pop();
                //     char op = operator.pop();
                //     int val = solve(val1,val2,op);
                //     operand.push(val);
                //     operator.push(c); 
                // }
            }
        }

        while(operator.size() > 0){
            String val2 = operand.pop();
            String val1 = operand.pop();
            char op = operator.pop();
            String val = op+val1 + val2;;
            operand.push(val);
        }

        return operand.peek();
        //if(priority(operator.peek()) < priority(c)){
    }

    public static String evaluateInfixToPostfix(String str){
        Stack<String> operand = new Stack();
        Stack<Character> operator = new Stack();
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if(c == ' '){
                continue;
            }else if(c >= 'a' && c<= 'z'){
                operand.push("" + c);
            }else if(c == '('){
                operator.push(c);
            }else if(c == ')'){
                while(operator.peek() != '('){
                    String val2 = operand.pop();
                    String val1 = operand.pop();
                    char op = operator.pop();
                    //int val = solve(val1,val2,op);
                    String val = val1 + val2 + op;
                    operand.push(val);    
                }
                operator.pop();
            }else if(c == '*' || c =='/' || c == '+' || c =='-'){
                while(operator.size() > 0 && operator.peek() != '(' && priority(operator.peek()) >= priority(c)){
                    String val2 = operand.pop();
                    String val1 = operand.pop();
                    char op = operator.pop();
                    //int val = solve(val1,val2,op);
                    String val = val1 + val2 + op;
                    operand.push(val);
                }
                //if(priority(operator.peek()) < priority(c)){
                operator.push(c);
                //}
                // }else{
                //     int val2 = operand.pop();
                //     int val1 = operand.pop();
                //     char op = operator.pop();
                //     int val = solve(val1,val2,op);
                //     operand.push(val);
                //     operator.push(c); 
                // }
            }
        }

        while(operator.size() > 0){
            String val2 = operand.pop();
            String val1 = operand.pop();
            char op = operator.pop();
            //String val = solve(val1,val2,op);
            String val = val1 + val2 + op;
            operand.push(val);
        }

        return operand.peek();
        //if(priority(operator.peek()) < priority(c)){
    }
    public static int evaluateInfix(String str){
        Stack<Integer> operand = new Stack();
        Stack<Character> operator = new Stack();
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if(c == ' '){
                continue;
            }else if(c >= '0' && c<= '9'){
                operand.push(c - '0');
            }else if(c == '('){
                operator.push(c);
            }else if(c == ')'){
                while(operator.peek() != '('){
                    int val2 = operand.pop();
                    int val1 = operand.pop();
                    char op = operator.pop();
                    int val = solve(val1,val2,op);
                    operand.push(val);    
                }
                operator.pop();
            }else if(c == '*' || c =='/' || c == '+' || c =='-'){
                while(operator.size() > 0 && operator.peek() != '(' && priority(operator.peek()) >= priority(c)){
                    int val2 = operand.pop();
                    int val1 = operand.pop();
                    char op = operator.pop();
                    int val = solve(val1,val2,op);
                    operand.push(val);
                }
                //if(priority(operator.peek()) < priority(c)){
                operator.push(c);
                //}
                // }else{
                //     int val2 = operand.pop();
                //     int val1 = operand.pop();
                //     char op = operator.pop();
                //     int val = solve(val1,val2,op);
                //     operand.push(val);
                //     operator.push(c); 
                // }
            }
        }

        while(operator.size() > 0){
            int val2 = operand.pop();
            int val1 = operand.pop();
            char op = operator.pop();
            int val = solve(val1,val2,op);
            operand.push(val);
        }

        return operand.peek();
        //if(priority(operator.peek()) < priority(c)){
    }

    public static int priority(char op){
        if(op == '/' || op == '*') return 2;
        else if(op == '+' || op== '-') return 1;
        else return 0;
    }

    public static int solve(int val1,int val2,char op){
        int val = 0;
        if(op == '+'){
            val = val2 + val1;
        }else if(op == '-'){
            val = val1 - val2;
        }else if(op == '*'){
            val = val1 * val2;
        }else if(op == '/'){
            val = val1 / val2;
        }else{
            val = 0;
        }
        return val;
    }
}
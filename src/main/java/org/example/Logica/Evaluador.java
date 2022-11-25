package org.example.Logica;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Evaluador {

    // Retorna un numero que significa la prioridad que tiene al momento de realizar la operacion
    public static int prioridad(char ch)
    {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    public static String infixToPostfix(String cadena)
    {
        StringBuilder resultado = new StringBuilder();
        Deque<Character> pila = new ArrayDeque<>();

        for (int i = 0; i < cadena.length(); ++i) {
            char c = cadena.charAt(i);

            if (Character.isLetterOrDigit(c))
                resultado.append(c);

            else if (c == '(')
                pila.push(c);

            //  Si el carácter escaneado es un ')', se desempila y se saca de la pila hasta que se encuentra un '('.
            else if (c == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.peek());
                    pila.pop();
                }

                pila.pop();
            }
            else // Se encontro un operando
            {
                resultado.append(' ');
                while (!pila.isEmpty() && prioridad(c) <= prioridad(pila.peek())) {

                    resultado.append(pila.peek());
                    pila.pop();
                }
                pila.push(c);
            }
        }

        // Desempila todos los operandos de la pila
        while (!pila.isEmpty()) {
            if (pila.peek() == '(')
                return "Invalid Expression";
            resultado.append(pila.peek());
            pila.pop();
        }

        return resultado.toString();
    }

    public static int evaluatePostfix(String cadena)
    {
        Stack<Integer> pila = new Stack<>();

        for(int i = 0; i < cadena.length(); i++)
        {
            char c = cadena.charAt(i);

            if(c == ' ')
                continue;

            // Si el caracter escaneado es numero, extrae el numero hasta que se encuentre un espacio en blanco,
            // luego lo empila
            else if(Character.isDigit(c))
            {
                int n = 0;

                // Extrae el caracter y lo almacena
                while(Character.isDigit(c))
                {
                    n = n * 10 + (c - '0');
                    i++;
                    c = cadena.charAt(i);
                }
                i--;

                pila.push(n);
            }

            // Si el valor es un operando, entonces desempila dos elementos aplicando el operador
            else
            {
                int val1 = pila.pop();
                int val2 = pila.pop();

                switch(c)
                {
                    case '+':
                        pila.push(val2+val1);
                        break;

                    case '-':
                        pila.push(val2- val1);
                        break;

                    case '/':
                        pila.push(val2/val1);
                        break;

                    case '*':
                        pila.push(val2*val1);
                        break;
                    case '^':
                        pila.push((int) Math.pow(val2, val1));
                }
            }
        }
        return pila.pop();
    }

}

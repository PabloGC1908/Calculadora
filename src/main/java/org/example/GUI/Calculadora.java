package org.example.GUI;

import org.example.Logica.Evaluador;

import javax.swing.*;
import java.util.Objects;

public class Calculadora extends JFrame {
    private JPanel calculadoraPanel;
    private JTextArea areaTextoEntrante;
    private JButton botonEliminarCaracter;
    private JButton boton0;
    private JButton botonIgual;
    private JButton boton7;
    private JButton boton8;
    private JButton boton9;
    private JButton boton4;
    private JButton boton5;
    private JButton boton6;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JButton botonResta;
    private JButton botonMultiplicacion;
    private JButton botonSuma;
    private JButton botonDivision;
    private JButton botonAParentesis;
    private JButton botonCParentesis;
    private JButton botonLimpiarTotal;
    private JTextArea textAreaResultado;
    private JButton botonPotencia;


    public Calculadora() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
            System.err.println("Error");
        }

        setContentPane(calculadoraPanel);
        setTitle("Calculadora");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        boton1.addActionListener(e -> almacenarDato('1'));
        boton2.addActionListener(e -> almacenarDato('2'));
        boton3.addActionListener(e -> almacenarDato('3'));
        boton4.addActionListener(e -> almacenarDato('4'));
        boton5.addActionListener(e -> almacenarDato('5'));
        boton6.addActionListener(e -> almacenarDato('6'));
        boton7.addActionListener(e -> almacenarDato('7'));
        boton8.addActionListener(e -> almacenarDato('8'));
        boton9.addActionListener(e -> almacenarDato('9'));
        boton0.addActionListener(e -> almacenarDato('0'));

        botonSuma.addActionListener(e -> almacenarDato('+'));
        botonResta.addActionListener(e -> almacenarDato('-'));
        botonMultiplicacion.addActionListener(e -> almacenarDato('*'));
        botonDivision.addActionListener(e -> almacenarDato('/'));
        botonPotencia.addActionListener(e -> almacenarDato('^'));
        botonAParentesis.addActionListener(e -> almacenarDato('('));
        botonCParentesis.addActionListener(e -> almacenarDato(')'));

        botonEliminarCaracter.addActionListener(e -> areaTextoEntrante.
                setText(areaTextoEntrante.getText().substring(0, areaTextoEntrante.getText().length() - 1)));

        botonIgual.addActionListener(e -> {
            if (!Objects.equals(textAreaResultado.getText(), "")){
                textAreaResultado.selectAll();
                textAreaResultado.replaceSelection("");
            }

            String cadenaTotal = getTexto();
            int resultado = hacerOperacion(cadenaTotal);

            textAreaResultado.append(String.valueOf(resultado));
        });

        botonLimpiarTotal.addActionListener(e -> {
            if (!textAreaResultado.getText().equals("") && areaTextoEntrante.getText().equals("")) {
                textAreaResultado.selectAll();
                textAreaResultado.replaceSelection("");
            }

            areaTextoEntrante.selectAll();
            areaTextoEntrante.replaceSelection("");
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        areaTextoEntrante = new JTextArea();
    }

    private int hacerOperacion(String cadena) {
        String resultado = Evaluador.infixToPostfix(cadena);
        return Evaluador.evaluatePostfix(resultado);
    }

    private void almacenarDato(char caracter) {
        areaTextoEntrante.append(String.valueOf(caracter));
    }

    private String getTexto() {
        return areaTextoEntrante.getText();
    }
}

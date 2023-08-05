import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
public class CurrencyConverter extends JFrame implements ActionListener{
    private final JComboBox<String>fromCurrencyComboBox;
    private final JComboBox<String>toCurrencyComboBox;
    private final JTextField amountTextField;
    private final JLabel resultLabel;

    private final String[] currencies = {"USD","EUR","INR"};
    private final double[] exchangeRates = {1.0,0.85,74.7};
    public CurrencyConverter(){
        super("Currency Converter");
        setLayout(new FlowLayout());

        fromCurrencyComboBox = new JComboBox<>(currencies);
        toCurrencyComboBox = new JComboBox<>(currencies);
        amountTextField = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        resultLabel = new JLabel();

        add(new JLabel("Convert"));
        add(amountTextField);
        add(fromCurrencyComboBox);
        add(new JLabel("to"));
        add(toCurrencyComboBox);
        add(convertButton);
        add(resultLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,150);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    public void actionPerformed(ActionEvent e){
        try{
            double amount = Double.parseDouble(amountTextField.getText());
            int fromCurrencyIndex = fromCurrencyComboBox.getSelectedIndex();
            int toCurrencyIndex = toCurrencyComboBox.getSelectedIndex();
            double convertedAmount = amount*exchangeRates[toCurrencyIndex]/exchangeRates[fromCurrencyIndex];
            DecimalFormat df = new DecimalFormat("#.##");
            resultLabel.setText(df.format(convertedAmount)+" " + currencies[toCurrencyIndex]);
        }catch (NumberFormatException ex){
            resultLabel.setText("Invalid input");
        }
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}
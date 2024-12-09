package Bank;
import javax.swing.*;
import java.awt.event.*;

public class CreditCalculator {
	public static void main(String[] args) {
        // Создание главного окна
        JFrame frame = new JFrame("Банковский кредит");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Создание панели для элементов
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        // Поле для ввода имени
        JLabel nameLabel = new JLabel("Имя клиента:");
        nameLabel.setBounds(10, 20, 120, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField(20);
        nameField.setBounds(150, 20, 200, 25);
        panel.add(nameField);

        // Список для выбора типа кредита
        JLabel creditTypeLabel = new JLabel("Тип кредита:");
        creditTypeLabel.setBounds(10, 60, 120, 25);
        panel.add(creditTypeLabel);

        String[] creditTypes = {"Ипотека", "Потребительский кредит", "Автокредит"};
        JComboBox<String> creditTypeBox = new JComboBox<>(creditTypes);
        creditTypeBox.setBounds(150, 60, 200, 25);
        panel.add(creditTypeBox);

        // Список для выбора срока кредита
        JLabel termLabel = new JLabel("Срок кредита:");
        termLabel.setBounds(10, 100, 120, 25);
        panel.add(termLabel);

        String[] terms = {"12 месяцев", "24 месяца", "36 месяцев"};
        JComboBox<String> termBox = new JComboBox<>(terms);
        termBox.setBounds(150, 100, 200, 25);
        panel.add(termBox);

        // Поле для ввода суммы кредита
        JLabel amountLabel = new JLabel("Сумма кредита:");
        amountLabel.setBounds(10, 140, 120, 25);
        panel.add(amountLabel);

        JTextField amountField = new JTextField(20);
        amountField.setBounds(150, 140, 200, 25);
        panel.add(amountField);

        // Кнопка для расчета
        JButton calculateButton = new JButton("Рассчитать");
        calculateButton.setBounds(10, 180, 150, 25);
        panel.add(calculateButton);

        // Поле для вывода результата
        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(10, 220, 360, 120);
        resultArea.setEditable(false);
        panel.add(resultArea);

        // Логика для кнопки "Рассчитать"
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Получаем данные из полей
                    String name = nameField.getText().trim();
                    String creditType = (String) creditTypeBox.getSelectedItem();
                    String term = (String) termBox.getSelectedItem();
                    double amount = Double.parseDouble(amountField.getText().trim());

                    // Проверка на корректность введенных данных
                    if (name.isEmpty()) {
                        throw new IllegalArgumentException("Введите имя!");
                    }
                    
                    if (amount <= 0) {
                        throw new IllegalArgumentException("Сумма кредита должна быть больше 0!");
                    }

                    // Определение процентной ставки для выбранного типа кредита
                    double interestRate;
                    if ("Ипотека".equals(creditType)) {
                        interestRate = 6.5;
                    } else if ("Потребительский кредит".equals(creditType)) {
                        interestRate = 12.0;
                    } else if ("Автокредит".equals(creditType)) {
                        interestRate = 8.0;
                    } else {
                        throw new IllegalStateException("Неизвестный тип кредита");
                    }

                    // Определение срока кредита
                    int months;
                    if ("12 месяцев".equals(term)) {
                        months = 12;
                    } else if ("24 месяца".equals(term)) {
                        months = 24;
                    } else if ("36 месяцев".equals(term)) {
                        months = 36;
                    } else {
                        throw new IllegalStateException("Неизвестный срок кредита");
                    }

                    // Расчет общей суммы выплаты процентов
                    double totalInterest = (amount * (interestRate / 100)) * (months / 12.0);

                    // Формирование строки результата
                    String result = String.format("Здравствуйте, %s!\n" +
                                                  "Тип кредита: %s\n" +
                                                  "Срок кредита: %s\n" +
                                                  "Сумма кредита: %.2f руб.\n" +
                                                  "Процентная ставка: %.2f%%\n" +
                                                  "Сумма выплаты процентов: %.2f руб.",
                                                  name, creditType, term, amount, interestRate, totalInterest);

                    // Отображение результата в текстовой области
                    resultArea.setText(result);

                } catch (NumberFormatException ex) {
                    // Обработка ошибок при неправильном вводе суммы кредита
                    JOptionPane.showMessageDialog(panel, "Введите корректную сумму кредита!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    // Обработка ошибок ввода имени или суммы
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalStateException ex) {
                    // Обработка ошибок при неизвестных типах кредита или сроках
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}

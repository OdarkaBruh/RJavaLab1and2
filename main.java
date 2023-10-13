import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


//Фактично, тут все пов'язане з Лабою №2
public class main{
	
	//шрифти
	public static Font myfontTabs = new Font("serif", Font.PLAIN, 20);
	public static Font myfontLabels = new Font("serif", Font.PLAIN, 23);
	
	//розмірність екрану
	public static int x = 1500;
	public static int y = 900;
	
	//стандартний відступ
	public static int StandartBorder = 20;
	
	//номер заліковки
	public static int number = 1511;;
	
	//створення фрейму, табів і панелей обох "сторінок"
	public static JFrame frame = new JFrame();
	public static JTabbedPane tabbedPane = new JTabbedPane();
	public static JPanel panelLab1 = new JPanel();
	public static JPanel panelLab2 = new JPanel();
	
	//основна промальовка фрейму
	public static void frame() {
		frame.add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelLab1.setBackground(Color.white);
		panelLab2.setBackground(Color.white);
		
		panelLab1.setLayout(null);
		panelLab2.setLayout(null);
		
		tabbedPane.setFocusable(false);
		
		tabbedPane.setFont(myfontTabs);
	}
	
	//Перша "сторінка"
	public static void lab1() {
		//Завдання
		int c2 = number%2;
		int c3 = number%3;
		int c5 = number%5;
		int c7 = number%7;
		
		//Оформлення полів і кнопок
		JTextArea t1 = new JTextArea();
		t1.setText(String.format(" C2: %1$d   - \n C3: %2$d \n C5: %3$d   \\\n С7: %4$d   double\n\n      Маємо: (i/j)/(j-2)", c2,c3,c5,c7));
		t1.setFont(myfontLabels);
		t1.setBounds(StandartBorder, StandartBorder, 250, 200);
		t1.setBackground(Color.decode("#E2E2E2"));
		t1.setEditable(false);
		
		JLabel l1 = new JLabel("n = ");
		l1.setFont(myfontLabels);
		l1.setBounds(StandartBorder*2+250, StandartBorder, 50, 50);
		
		JTextField tf1 = new JTextField();
		tf1.setFont(myfontLabels);
		tf1.setBounds(StandartBorder*4+250, StandartBorder, 50, 30);
		tf1.setBorder(BorderFactory.createEmptyBorder());
		tf1.setBackground(Color.decode("#E2E2E2"));
		
		JLabel l2 = new JLabel("m = ");
		l2.setFont(myfontLabels);
		l2.setBounds(StandartBorder*2+250, StandartBorder*3, 50, 50);
		
		JTextField tf2 = new JTextField();
		tf2.setFont(myfontLabels);
		tf2.setBounds(StandartBorder*4+250, StandartBorder*3, 50, 30);
		tf2.setBorder(BorderFactory.createEmptyBorder());
		tf2.setBackground(Color.decode("#E2E2E2"));
		
		JLabel l3 = new JLabel("a = ");
		l3.setFont(myfontLabels);
		l3.setBounds(StandartBorder*2+250, StandartBorder*5, 50, 50);
		
		JTextField tf3 = new JTextField();
		tf3.setFont(myfontLabels);
		tf3.setBounds(StandartBorder*4+250, StandartBorder*5, 50, 30);
		tf3.setBorder(BorderFactory.createEmptyBorder());
		tf3.setBackground(Color.decode("#E2E2E2"));
		
		JLabel l4 = new JLabel("b = ");
		l4.setFont(myfontLabels);
		l4.setBounds(StandartBorder*2+250, StandartBorder*7, 50, 50);
		
		JLabel l5 = new JLabel("b має бути >=3");
		l5.setFont(myfontLabels);
		l5.setBounds(StandartBorder*2+250, StandartBorder*6, 150, 150);
		
		JTextField tf4 = new JTextField();
		tf4.setFont(myfontLabels);
		tf4.setBounds(StandartBorder*4+250, StandartBorder*7, 50, 30);
		tf4.setBorder(BorderFactory.createEmptyBorder());
		tf4.setBackground(Color.decode("#E2E2E2"));
		
		//Текст для виводу помилок або результату
		JTextArea errorMessage = new JTextArea();
		errorMessage.setFont(myfontTabs);
		errorMessage.setBounds(StandartBorder*2, StandartBorder+270, 900, 100);
		errorMessage.setEditable(false);
		
		//Кнопка
		JButton b1 = new JButton("Порахувати");
		b1.setFont(myfontTabs);
		b1.setBounds(StandartBorder*3, StandartBorder+210, 150, 50);
		b1.setFocusable(false);
		
		//так як кнопка і все це static, то прописуємо код події тут, не виносячи в окремий метод
		b1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		        	
		        	//Перевід всього, що було вписано в поля в формат double (це наші a, b, n, m)
		        	double result1 = Double.parseDouble(tf1.getText());
		        	double result2 = Double.parseDouble(tf2.getText());
		        	double result3 = Double.parseDouble(tf3.getText());
		        	double result4 = Double.parseDouble(tf4.getText());
		        	
		        	//перевірка чи буде b<=2 (тобто в якийсь момент циклу буде ділення на нуль)
		        	if (result4<=2) {
		        		errorMessage.setText("B має бути >=3, спробуйте знов");
		        		return;
		        	}
		        	
		        	//Перевірка чи виконається цикл хоч раз
		        	else if (result1<result3) {
		        		errorMessage.setText("n має бути більшим за a, щоб цикл відбувся хоча б 1 раз");
		        		return;
		        	}
		        	else if (result2<result4) {
		        		errorMessage.setText("m має бути більшим за b, щоб цикл відбувся хоча б 1 раз");
		        		return;
		        	}
		        	
		        	//Якщо все без помилок - рахуємо і виводимо результат
		        	double result = calculateLab1(result1,result2,result3,result4);
		        	errorMessage.setText("Результат: "+ result);
		        	
		        //вивід тексту помилки
		        } catch (NumberFormatException eee) {
		        	errorMessage.setText("Ви допустили помилку при введені данних.\nСкоріш за все, ви вставили якийсь символ замість чисел або залишили поле пустим.\n   Спробуйте знову.");
		        	return;
		        }
		    }
		});
		
		//Створення всіх лейблів
		panelLab1.add(l1);
		panelLab1.add(l2);
		panelLab1.add(l3);
		panelLab1.add(l4);
		panelLab1.add(l5);
		
		//Створення всіх полів введення
		panelLab1.add(tf1);
		panelLab1.add(tf2);
		panelLab1.add(tf3);
		panelLab1.add(tf4);
		
		panelLab1.add(errorMessage);
		panelLab1.add(b1);
		
		panelLab1.add(t1);
	}
	
	//Обрахування за завданням
	public static double calculateLab1(double n, double m, double a, double b) {
		double result = 0;
		int c3 = number%3;
		
		for (double i = a; i <=n; i++) {
			for (double j = b; j<=m; j++) {
				result =+ (i/j)/(j-c3);
			}
		}
		return result;
	}
	
	
	//Друга сторінка: Лабораторна №2
	public static void lab2() {
		int c5 = number%5;
		int c7 = number%7;
		int c11 = number%11;
		
		//Вивід умови завдання
		JTextArea t1 = new JTextArea();
		t1.setText(String.format(" C5: %1$d   C=Bт \n C7: %2$d   float\n C11: %3$d\n\n      Маємо: (i/j)/(j-2)", c5,c7,c11));
		t1.setFont(myfontLabels);
		t1.setBounds(StandartBorder, StandartBorder, 250, 200);
		t1.setBackground(Color.decode("#E2E2E2"));
		t1.setEditable(false);
		panelLab2.add(t1);
		
		//Виклик іншого класу, де виконана більшість інших обчислень
		matrix.frame();
	}
	
	public static void main(String[] args) {
		frame();
		lab1();
		lab2();
		
		tabbedPane.addTab("Лабораторна №1", panelLab1);
		tabbedPane.addTab("Лабораторна №2", panelLab2);
		
		frame.setSize(x, y);
		frame.setVisible(true);
	}
}
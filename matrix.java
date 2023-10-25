import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


//Фактично, тут все пов'язане з Лабою №1
public class matrix {
	
	//Створення панелей, panel1 - матиме числа звичайної матриці, panel2 - транспонованної
	public static JPanel panel1 = new JPanel();
	public static JPanel panel2 = new JPanel();
	
	public static Font myfontMatrix = new Font("serif", Font.PLAIN, 15);
	public static Font myfontLabels = main.myfontLabels;
	
	public static int standartBorder = main.StandartBorder;
	
	//Створення матриць
	public static float[][] TranspositedMatrix = new float [4][4];
	public static float[][] startingMatrix = 
		 { { 1, 2, 3, 4 }, 
		   { 3, 4, 5, 7 },
		   { 12, -32, 32, (float) 3.22 },
		   { (float) 13.23, 23, -5, (float) -5.6}};
	 
	 public static void frame() {
		 //Оформлення
		 createLabel("Початкова Матриця: ", 0);
		 createLabel("Транспортована Матриця: ", 1);
		
		 panel1.setBounds(standartBorder*2+250, standartBorder*3, 200, 200);
		 panel1.setLayout(new GridLayout(4,4));
		
		 panel2.setBounds(standartBorder*3+250+200, standartBorder*3, 200, 200);
		 panel2.setLayout(new GridLayout(4,4));
		 
		 createJTextField(startingMatrix, panel1);
		 matrixTransposition(TranspositedMatrix,startingMatrix);
		 createJTextField(TranspositedMatrix,panel2);
		 task();
		 
		 main.panelLab2.add(panel1);
		 main.panelLab2.add(panel2);
		 }
	 
	 
	 //Створення всіх лейблів (Текст "початкова матриця" і "
	 private static void createLabel(String text, int x) {
		 JLabel l1 = new JLabel(text);
		 l1.setFont(myfontMatrix);
		 l1.setBounds(standartBorder*(x+1)*3+250+x*150, standartBorder, 200, 30);
		 main.panelLab2.add(l1);
	 }
	 
	 //Вивід лейблів з данними (для виводу min, max, result), де x - просто відступ
	 private static void createLabelValue(String text, float value, int x) {
		 JLabel l1 = new JLabel(text);
		 l1.setFont(myfontLabels);
		 l1.setBounds(standartBorder*3, standartBorder*(x+1)+200, 250, 50);
		 l1.setText(text+value);
		 main.panelLab2.add(l1);
	 }
	 
	 //JTextField в парі з GridLayout в panel1 і panel2 використано для відображення саме ЗНАЧЕННЯ матриць 
	 //(тобто кожна ячейка-JTextField тримає одне число певного рядку і колонки)
	 private static void createJTextField(float[][] matrix, JPanel panel12) {
		 for (int i = 0; i <4; i++) {
			 for (int j = 0; j<4; j++) {
				 JTextField tf = new JTextField();
				 tf.setFont(myfontMatrix);
				 tf.setEditable(false);
				 tf.setText(Float.toString(matrix[i][j]));
				 panel12.add(tf);
				}
			}
	}
	
	//Транспонування матриці
	private static void matrixTransposition(float[][] m1, float[][] m2) {
		for (int i = 0; i <4; i++) {
			for (int j = 0; j<4; j++) {
				m1[i][j] = m2[j][i];
			}
		}
	}
	
	//Виконання завдання:
	//Обчислити суму найбільших елементів в рядках матриці з парними номерами та найменших елементів в рядках матриці з непарними номерами
	
	//ПРИМІТКА: ми дивимся чи парне з точки зору людини, не самої java, так як java має нумерацію з 0, а ми з 1. 
	//Тобто число під matrix[0][0] ми вважаємо НЕпарним, бо воно йде першим; а matrix[0][1] - парне, бо друге.
	private static void task() {
		float min;
		float max;
		float result_min = 0;
		float result_max = 0;
		//х - відступ
		int x = 0;
		
		//par - чи парне число зараз буде
		for (int i = 0; i <4; i++) {
			//Ставимо парність як false, бо новий рядок.
			boolean par = false;
			max = startingMatrix[i][1];
			min = startingMatrix[i][0];
			for (int j = 0; j<4; j++) {
				//Перевірка на парність
				if (par) {
					//якщо парне - шукаємо max
					if (startingMatrix[i][j]>max) {
						max = startingMatrix[i][j];
						}
				}
					
				else {
					//якно НЕпарне - min
					if (startingMatrix[i][j]<min) {
						min = startingMatrix[i][j];
						}
					}
				//Після кожного елементу ми інвертуємо парність
				par = !par;
				}
				
			//В кінці рядку додача результатів за рядок
			result_min += min;
			result_max += max;
			
			//Створення лейблів з min/max в конкретно в ЦЬОМУ рядку.
			x++;
			createLabelValue("Min: ", min, x);
			x++;
			createLabelValue("Max: ", max, x);
			x++;
			
		}
		
		//Створення лейблу з фінальними результатами
		createLabelValue("Результат Min: ", result_min, 15);
		createLabelValue("Результат Max: ", result_max, 17);
	}
}

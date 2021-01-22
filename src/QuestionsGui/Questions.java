package QuestionsGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Qns {

	private String qns, c1, c2, c3, c4, an;

	public Qns(String a, String b, String c, String d, String e, String f) {
		qns = a;
		c1 = b;
		c2 = c;
		c3 = d;
		c4 = e;
		an = f;

	}

	public String getq() {
		return qns;
	}

	public String getc1() {
		return c1;
	}

	public String getc2() {
		return c2;
	}

	public String getc3() {
		return c3;
	}

	public String getc4() {
		return c4;
	}

	public String getan() {
		return an;
	}

}

public class Questions extends JFrame implements ActionListener {

	final static int ANSWERS = 4;
	private static final int MAX_QNS = 20;
	private int currentQuestion, numberOfQuestions, score;
	private Qns[] qns;
	private int ans;

	Font f = new Font("Serif", Font.BOLD, 18);
	JLabel counter, question;
	JButton[] answers;

	JButton next;
	JPanel panel1, answersPanel;

	public Questions() throws IOException {

		qns = new Qns[MAX_QNS];
		numberOfQuestions = 0;
		currentQuestion = 0;
		score = 0;
		ans = 0;

		setLayout(new BorderLayout());

		display();
		reader();
		questionNumber();
	}

	public void display() {

		panel1 = new JPanel(new GridLayout(2, 1));
		answersPanel = new JPanel(new GridLayout(4, 1));
		counter = new JLabel("", JLabel.CENTER);
		counter.setBackground(Color.white);
		counter.setFont(f);
		counter.setOpaque(true);
		panel1.add(counter);

		question = new JLabel("", JLabel.CENTER);
		question.setBackground(Color.white);
		question.setFont(f);
		question.setOpaque(true);
		panel1.add(question);

		add(panel1, BorderLayout.NORTH);

		answers = new JButton[ANSWERS];
		for (int i = 0; i < 4; i++) {
			answers[i] = new JButton();
			answers[i].setFont(f);
			answers[i].setBackground(Color.white);
			answers[i].setOpaque(true);
			answers[i].addActionListener(this);
			answersPanel.add(answers[i]);
		}

		add(answersPanel, BorderLayout.CENTER);

		next = new JButton("Next");
		next.setBackground(Color.white);
		next.setFont(f);
		next.addActionListener(this);
		next.setEnabled(false);
		add(next, BorderLayout.SOUTH);

	}

	public void reader() throws IOException {
		int n = 0;
		String q, w, e, r, t, y;
		BufferedReader file = new BufferedReader(new FileReader("questions.txt"));
		q = file.readLine();
		while (q != null) {

			w = file.readLine();
			e = file.readLine();
			r = file.readLine();
			t = file.readLine();
			y = file.readLine();
			qns[n] = new Qns(q, w, e, r, t, y);
			q = file.readLine();
			n++;

		}
		numberOfQuestions = n;
		file.close();
	}

	public void questionNumber() {

		if (currentQuestion > numberOfQuestions) {

			System.exit(0);
		}

		if (currentQuestion == numberOfQuestions) {
//			System.out.println("CHECK");

			for (int i = 0; i < 4; i++) {
				answers[i].setVisible(false);
			}
			question.setText("");
			counter.setText("Final Score is " + score + "/ " + numberOfQuestions);
			next.setText("Finish");
			currentQuestion++;

		} else {

			question.setText(qns[currentQuestion].getq());
			answers[0].setText(qns[currentQuestion].getc1());
			answers[1].setText(qns[currentQuestion].getc2());
			answers[2].setText(qns[currentQuestion].getc3());
			answers[3].setText(qns[currentQuestion].getc4());
			ans = (Integer.parseInt(qns[currentQuestion].getan())) - 1;
			if (currentQuestion != 0) {
				next.setEnabled(false);
			}
			currentQuestion++;
		}

	}

	public void update() {

		for (int i = 0; i < 4; i++) {

			answers[i].setEnabled(false);
			if (i == ans) {

				answers[i].setBackground(Color.green);
			} else {

				answers[i].setBackground(Color.red);

			}
		}
		next.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == answers[ans]) {

			score++;
			counter.setText("Correct Answer " + score + "/ " + numberOfQuestions);
			update();

		} else {

			counter.setText("Incorrect Answer " + score + "/ " + numberOfQuestions);
			update();
		}

		if (e.getSource() == next) {

			for (int i = 0; i < 4; i++) {

				answers[i].setBackground(Color.white);
				answers[i].setEnabled(true);

			}

			counter.setText(score + "/ " + numberOfQuestions);
			questionNumber();
		}
	}
}
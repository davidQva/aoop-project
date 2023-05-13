import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_W = 800;
	static final int SCREEN_H = 800;
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS = (SCREEN_W*SCREEN_H)/UNIT_SIZE;
	static final int DELAY = 90;
	
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int foodConsumed;
	int foodx;
	int foody;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	public GamePanel() 
	{
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_W,SCREEN_H));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

	public void startGame() 
	{
		newFood();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) 
	{
		if(running) {
	
			for (int i = 0; i < SCREEN_H/UNIT_SIZE; i++) 
			{
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_H); // skapa ett rutnät baserat på storleken man valt som unit size.
				g.drawLine(0, i*UNIT_SIZE,SCREEN_W , i*UNIT_SIZE);
			}
			g.setColor(Color.green);
			g.fillOval(foodx, foody, UNIT_SIZE, UNIT_SIZE); // gör en cirkel som är grön som läggs in random på banan.
		
			for (int i = 0; i < bodyParts; i++) 
			{
				if (i == 0) 
				{
					g.setColor(Color.red);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);	//rita ut orm på banan
				}
				else 
				{
					g.setColor(new Color(160,0,70));
					g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); //kroppen är en annan nyans av röd
				}
			}
			g.setColor(Color.green);
			g.setFont(new Font("Ink Free",Font.BOLD, 30));
			FontMetrics metric = getFontMetrics(g.getFont());
			g.drawString("Score: " + foodConsumed, (SCREEN_W - metric.stringWidth("Score: " + foodConsumed))/2, g.getFont().getSize());
		}
		else 
		{
			gameOver(g);
		}
	}
	
	public void newFood() 
	{
		foodx = random.nextInt((int)(SCREEN_W/UNIT_SIZE))*UNIT_SIZE; //lägg in koordinater för äpplena inom spel banan på random plats.
		foody = random.nextInt((int)(SCREEN_H/UNIT_SIZE))*UNIT_SIZE;
	}
	
	public void move() 
	{
		for (int i = bodyParts; i > 0; i--) 
		{
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) 
		{
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;	
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;	
		}
	}
	
	public void checkFood() 
	{
		if((x[0] == foodx) && (y[0] == foody)) 
		{
			bodyParts++;
			foodConsumed++;
			newFood();
		}
	}
	
	public void checkCollisions() 
	{
		for (int i = bodyParts; i >0 ; i--) 
		{
			if((x[0] == x[i]) && (y[0] == y[i])) //kolla om ormen(huvud) krockar med sig själv(kroppen)
			{
				running = false;
			}
		}
		//Kollar om ormens huvud träffar vänstra kanten 
		if (x[0] < 0) 
		{
			running = false;
		}
		//Kollar om ormens huvud träffar högra kanten
		if (x[0] > SCREEN_W) 
		{
			running = false;
		}
		//Kollar om ormens huvud träffar toppen kanten
		if(y[0] < 0) 
		{
			running = false;
		}
		//Kollar om ormens huvud träffar nedre kanten
		if(y[0] > SCREEN_H) 
		{
			running = false;
		}
		
		if (running == false) // om ormen träffar sin kropp med huvudet eller passerar kanterna så ska den stanna.
		{
			timer.stop();
		}
		
	}
	
	public void gameOver(Graphics g) 
	{
		//Score 
		g.setColor(Color.green);
		g.setFont(new Font("Ink Free",Font.BOLD, 30));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: " + foodConsumed, (SCREEN_W - metrics.stringWidth("Score: " + foodConsumed))/2, g.getFont().getSize());
		//GameOver text
		g.setColor(Color.blue);
		g.setFont(new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metric = getFontMetrics(g.getFont());
		g.drawString("Game Over bub", (SCREEN_W - metric.stringWidth("Game Over bub"))/2, SCREEN_H/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if(running) 
		{
			move();
			checkFood();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e) 
		{
			switch(e.getKeyCode())  // kontroll av ormen, om inte ormen går åt motsatt håll, görs ändringen av riktning
			{
			case KeyEvent.VK_LEFT:
				if(direction != 'R') 
				{
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') 
				{
					direction = 'R';
				}
				break;	
			case KeyEvent.VK_DOWN:
				if(direction != 'U') 
				{
					direction = 'D';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') 
				{
					direction = 'U';
				}
				break;	
				
			}
		}
	}
}

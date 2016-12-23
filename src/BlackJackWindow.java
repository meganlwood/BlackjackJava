import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BlackJackWindow extends JFrame {
	
	private JPanel container;
	
	private JPanel userCardsPanel;
	private JPanel bettingPanel;
	private JPanel bottomSectionPanel;
	private JPanel centerPanel;
	private JPanel dealerPanel;
	private JPanel dealerCardsPanel;
	//private JPanel dealerInfoPanel;
	private JPanel hitAndStayButtonPanel;
	private JPanel moneyPanel;
	//private JLabel bettingLabel;
	private JLabel betAmountLabel;
	private JLabel currentMoneyLabel;
	private JTextField betTextField;
	private JButton betButton;
	//private JLabel[] userCardsLabels;
	private ArrayList<JLabel> userCardsLabels = new ArrayList<JLabel>();
	private ArrayList<JLabel> dealerCardsLabels = new ArrayList<JLabel>();
	private JButton hitMe;
	private JButton stay;
	private ArrayList<Card> userCards = new ArrayList<Card>();
	private volatile ArrayList<Card> dealerCards = new ArrayList<Card>();
	private Deck deck;
	private GridLayout gl;
	private GridLayout dgl;
	private final int defaultCardsGridAmount = 6;
	private boolean userBusts = false;
	private Font blackjackFont;
	
	private volatile int betAmount = 0;
	private volatile int currentMoney = 2000;
	
	public BlackJackWindow() throws IOException {
		initializeVariables();
		createGUI();
		addEvents();
		setSize(700,600);
		setVisible(true);
		setup();
	}
	
	public void initializeVariables() throws IOException {
		container = new BackgroundPanel(ImageIO.read(new File("assets/otherImages/tableBackground.jpeg")));
		bottomSectionPanel = new JPanel(new GridLayout(2,1));
		bettingPanel = new JPanel();
		gl = new GridLayout(1,defaultCardsGridAmount);
		dgl = new GridLayout(1,6);
		userCardsPanel = new JPanel(gl);
		bottomSectionPanel.add(bettingPanel);
		bottomSectionPanel.add(userCardsPanel);
		//userCardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		deck = new Deck();
		hitMe = new JButton("Hit me");
		stay = new JButton("Stay");
		centerPanel = new JPanel(new GridLayout(2,1));
		
		betAmountLabel = new JLabel("Bet Amount: " + betAmount);
		currentMoneyLabel = new JLabel("You have: " + currentMoney);
		betTextField = new JTextField(10);
		betButton = new JButton("Place Bet");
		JLabel label2 = new JLabel("Enter bet amount: ");

		label2.setOpaque(false);
		bettingPanel.add(label2);
		bettingPanel.add(betTextField);
		bettingPanel.add(betButton);
		//centerPanel.add(betAmountLabel);
		//centerPanel.add(currentMoneyLabel);
		
		dealerPanel = new JPanel(new GridLayout(1,1));
		dealerCardsPanel = new JPanel(new GridLayout(1,6));
		//dealerInfoPanel = new JPanel();
		dealerPanel.add(dealerCardsPanel);
		//dealerPanel.add(dealerInfoPanel);
		
		hitAndStayButtonPanel = new JPanel();
		hitAndStayButtonPanel.add(hitMe);
		hitAndStayButtonPanel.add(stay);
		centerPanel.add(hitAndStayButtonPanel);
		
		moneyPanel = new JPanel();
		moneyPanel.add(betAmountLabel);
		moneyPanel.add(currentMoneyLabel);
		centerPanel.add(moneyPanel);
		
		
		for (int i = 0; i < defaultCardsGridAmount; i++) {
			userCardsLabels.add(new JLabel());
			userCardsPanel.add(userCardsLabels.get(userCardsLabels.size()-1));
		}
		
		for (int i = 0; i < 6; i++) {
			dealerCardsLabels.add(new JLabel());
			dealerCardsPanel.add(dealerCardsLabels.get(dealerCardsLabels.size()-1));
		}
		
		blackjackFont = null;
		try {
			blackjackFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/trench100free.ttf"));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		blackjackFont = blackjackFont.deriveFont(Font.BOLD, 26);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(blackjackFont);
		
		label2.setFont(blackjackFont);
		label2.setForeground(Color.WHITE);
		
		//currentMoneyLabel.setFont(blackjackFont);
		
		//userCardsLabels = new JLabel[6];
		
		//bettingPanel = new JPanel();
		//userCardsPanel.add(bettingPanel);
		
		//for (int i = 0; i < 6; i++) {
			//userCardsLabels[i] = new JLabel();
			//userCardsLabels[i].setIcon(new ImageIcon(new Card(10, "clubs").getImage()));
			//userCardsPanel.add(userCardsLabels[i]);
			//userCardsLabels[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			//JLabel l = new JLabel();
			//l.setIcon(new ImageIcon(new Card(10, "clubs").getImage()));
			//userCardsPanel.
			
		//}
		//userCardsPanel = new JPanel(new GridLayout(1,6));
		//l.setIcon(new ImageIcon((new Card("10", "clubs")).getImage()));
		//userCardsPanel.add(l);
	}
	
	public void createGUI() {
		centerPanel.setBackground(Color.BLUE);
		dealerPanel.setBackground(Color.RED);
		bottomSectionPanel.setBackground(Color.YELLOW);
		
		container.setOpaque(false);
		bottomSectionPanel.setOpaque(false);
		centerPanel.setOpaque(false);
		dealerPanel.setOpaque(false);
		//dealerInfoPanel.setOpaque(false);
		bettingPanel.setOpaque(false);
		hitAndStayButtonPanel.setOpaque(false);
		moneyPanel.setOpaque(false);
		userCardsPanel.setOpaque(false);
		dealerCardsPanel.setOpaque(false);
		//betAmountLabel.setOpaque(false);
		//currentMoneyLabel.setOpaque(false);
		betTextField.setOpaque(false);
		userCardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		dealerCardsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,20));

		betAmountLabel.setFont(blackjackFont);
		betTextField.setFont(blackjackFont);
		currentMoneyLabel.setFont(blackjackFont);
		betButton.setFont(blackjackFont);
		hitMe.setFont(blackjackFont);
		stay.setFont(blackjackFont);
		
		betAmountLabel.setForeground(Color.WHITE);
		currentMoneyLabel.setForeground(Color.WHITE);
		
		
		container.setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.add(bottomSectionPanel, BorderLayout.SOUTH);
		container.add(centerPanel);
		container.add(dealerPanel, BorderLayout.NORTH);
		add(container);
	}
	
	public void resetCards() throws IOException {
		gl = new GridLayout(1,defaultCardsGridAmount);
		dgl = new GridLayout(1,6);
		userCardsPanel.validate();
		dealerCardsPanel.validate();
		userCards.removeAll(userCards);
		dealerCards.removeAll(dealerCards);
		
		//userCardsLabels.clear();
		//dealerCardsLabels.clear();
		
		for (int i = 0; i < defaultCardsGridAmount; i++) {
			userCardsLabels.get(i).setIcon(null);
			//userCardsPanel.add(userCardsLabels.get(userCardsLabels.size()-1));
		}
		for (int i = defaultCardsGridAmount; i < userCardsLabels.size(); i++) {
			userCardsPanel.remove(userCardsLabels.get(i));
			
		}
		userCardsLabels.subList(defaultCardsGridAmount, userCardsLabels.size()).clear();
		System.out.println("num user components:" + userCardsPanel.countComponents());
		
		for (int i = 0; i < 6; i++) {
			dealerCardsLabels.get(i).setIcon(null);
			//dealerCardsPanel.add(dealerCardsLabels.get(dealerCardsLabels.size()-1));
		}
		System.out.println("Dealercardslabels.size = " + dealerCardsLabels.size());
		for (int i = 6; i < dealerCardsLabels.size(); i++) {
			System.out.println("Removing: " + dealerCardsLabels.get(i));
			dealerCardsPanel.remove(dealerCardsLabels.get(i));
		}
		dealerCardsLabels.subList(6, dealerCardsLabels.size()).clear();
		System.out.println("num dealer components:" + dealerCardsPanel.countComponents());
		
		setup();

	}
	
	public void setup() throws IOException {
		userBusts = false;
		betAmountLabel.setVisible(false);
		currentMoneyLabel.setText("You have: " + currentMoney);
		userCardsPanel.setVisible(false);
		dealerCardsPanel.setVisible(false);
		hitAndStayButtonPanel.setVisible(false);
		bettingPanel.setVisible(true);
		//centerPanel.setVisible(false);
		
		dealerCards.add(deck.next());
		dealerCards.add(deck.next());
		
		//userCards.add(deck.next());
		
		for (int i = 0; i < 2; i++) {
			userCards.add(deck.next());
			JLabel l2 = userCardsLabels.get(userCards.size()-1);
			try {
				l2.setIcon(new ImageIcon(userCards.get(userCards.size()-1).getImage()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		//Label dl = dealerCardsLabels.get(0);
		dealerCardsLabels.get(0).setIcon(new ImageIcon(dealerCards.get(0).getImage()));
		dealerCardsLabels.get(1).setIcon(new ImageIcon(Card.getCardBackImage()));
		
		
	}
	
	public void dealerDone(int dValue) throws IOException {
		
		int userValue = 0;
		
		int userAces = 0;
		for (Card c : userCards) {
			userValue += c.getValue();
			if (c.getValue() == 11) userAces++;
		}
		
		
		//if (userAces > 0 && userValue > 21) userValue-=10;
		while (userAces > 0 && userValue > 21) {
			userValue-=10;
			userAces--;
		}
		
		if (userBusts) {
			JOptionPane.showMessageDialog(centerPanel,"Dealer Wins! You had " + userValue + " and dealer had " + dValue);
		}
		else if (dValue > 21){
			JOptionPane.showMessageDialog(centerPanel, "Dealer busted! You win! You had " + userValue + " and dealer had " + dValue);
			currentMoney += betAmount*2;
		}
		else if (dValue <= userValue) {
			JOptionPane.showMessageDialog(centerPanel, "You win! You had " + userValue + " and dealer had " + dValue);
			currentMoney += betAmount*2;
		}
		else JOptionPane.showMessageDialog(centerPanel, "Dealer wins! You had " + userValue + " and dealer had " + dValue);
		
		resetCards();

		
	}
	
	public void runDealer() throws IOException  {
		try {
			dealerCardsLabels.get(1).setIcon(new ImageIcon(dealerCards.get(1).getImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int value = 0;
		int aces = 0;
		for (Card c : dealerCards) {
			value += c.getValue();
			if (c.getValue() == 11) aces++;
		}
		if (userBusts) dealerDone(value);
		else {
			while (value <= 16) {
				value = 0;
				aces = 0;
				for (Card c : dealerCards) {
					value += c.getValue();
					if (c.getValue() == 11) aces++;
				}
				if (aces != 0) dealerHit();
				else if (value <= 16) dealerHit();
				while (value > 21 && aces > 0) {
					value-=10;
					aces--;
				}
			}
			dealerDone(value);
			/*
			if (value <= 16) {
				dealerHit();
				//runDealer();
			}
			else if (aces != 0) {
				dealerHit();
				//runDealer();
			}
			else dealerDone(value);
			*/
		}
		
		
	}
	
	public void dealerHit() {
		dealerCards.add(deck.next());
		JLabel l = new JLabel();
		try {
			l.setIcon(new ImageIcon(dealerCards.get(dealerCards.size()-1).getImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (dealerCards.size() > 6) {
			dgl.setColumns(dealerCards.size());
			dealerCardsLabels.add(l);
			dealerCardsPanel.add(l);
			validate();
		}
		else {
			JLabel l2 = dealerCardsLabels.get(dealerCards.size()-1);
			try {
				l2.setIcon(new ImageIcon(dealerCards.get(dealerCards.size()-1).getImage()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void addEvents() {
		hitMe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userCards.add(deck.next());
				JLabel l = new JLabel();
				try {
					l.setIcon(new ImageIcon(userCards.get(userCards.size()-1).getImage()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//userCardsLabels.add(l);
				if (userCards.size() > defaultCardsGridAmount) {
					gl.setColumns(userCards.size());
					//userCardsPanel.setLayout(gl);
					userCardsLabels.add(l);
					userCardsPanel.add(l);
					validate();
				}
				else {
					JLabel l2 = userCardsLabels.get(userCards.size()-1);
					try {
						l2.setIcon(new ImageIcon(userCards.get(userCards.size()-1).getImage()));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
				//boolean userBusts = false;
				int aces = 0;
				int userValue = 0;
				for (Card c : userCards) {
					userValue+=c.getValue();
					if (c.getValue() == 11) aces++;
				}
				while (userValue > 21 && aces > 0) {
					userValue -= 10;
					aces--;
				}
				if (userValue > 21) {
					JOptionPane.showMessageDialog(centerPanel, "You Busted!");
					userBusts = true;
					try {
						runDealer();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			}
			
		});
		
		stay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					runDealer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		betButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (betTextField.getText().matches("[0-9]+")) {
					betAmount = Integer.parseInt(betTextField.getText());
					currentMoney-=betAmount;
					betAmountLabel.setText("You bet: " + betAmount);
					currentMoneyLabel.setText("You have: " + currentMoney);
					dealerCardsPanel.setVisible(true);
					userCardsPanel.setVisible(true);
					hitAndStayButtonPanel.setVisible(true);
					centerPanel.setVisible(true);
					bettingPanel.setVisible(false);
					betAmountLabel.setVisible(true);
					((BackgroundPanel) container).mainBg();
				}
				else {
					JOptionPane.showMessageDialog(centerPanel, "Must be a number!");
				}
				
				
			}
		});
	}
}

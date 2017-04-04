package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.EventListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;
import br.pro.hashi.ensino.desagil.lucianogic.model.LED;

// Esta classe representa a interface de uma porta logica.
public class GateView extends FixedPanel implements ItemListener,EventListener, ActionListener, MouseListener{

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private JCheckBox[] inBoxes;
	private JCheckBox outBox;

	private Switch[] switches;
	private Gate gate;
	private JButton button;
	private LED led;	
	
	private Image image;

	public GateView(Gate gate) {
		super(290, 150);
		
		image = loadImage(gate.toString());
		
		this.gate = gate;
		
		this.addMouseListener(this);
		
		// A componente JLabel representa simplesmente um texto fixo.
		// https://docs.oracle.com/javase/tutorial/uiswing/components/label.html
		JLabel inLabel = new JLabel("Entrada:");
		JLabel outLabel = new JLabel("Saida:");
		
		int size = gate.getSize();

		inBoxes = new JCheckBox[size];
		switches = new Switch[size];

		led = new LED(255, 0, 0);

		for(int i = 0; i < size; i++) {
			inBoxes[i] = new JCheckBox();

			// Esta linha garante que, sempre que o usuario clicar
			// na checkbox, o metodo itemStateChanged abaixo sera chamado.
			// https://docs.oracle.com/javase/tutorial/uiswing/components/button.html#checkbox
			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
		}

		outBox = new JCheckBox();

		// Esta linha garante que outBox nao seja clicavel.
		outBox.setEnabled(false);
		
		// Esta linha garante que os componentes sejam simplesmente
		// colocados em linha reta, mais especificamente na vertical.
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		/* A PARTIR DESTE PONTO VOCE DEVE ENTENDER SOZINHO */

		add(inLabel);
//		for(int i = 0; i < inBoxes.length; i++) {
//			add(inBoxes[i], 10,5,1,25);
//		}
		
		if(gate.getSize() == 1){
			add(inBoxes[0], 10,15,17,17);
		}
		else if(gate.getSize() == 2){
			add(inBoxes[0], 10,5,17,17);
			add(inBoxes[1], 10,27,17,17);
		}
		else{
			add(inBoxes[0], 10,5,17,17);
			add(inBoxes[1], 10,26,17,17);
			add(inBoxes[2], 10,47,17,17);			
		}

		add(outLabel);
//		add(outBox,160,25,50,25);
		
		led.connect(gate, 0);
		
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}
		switches[i].setOn(inBoxes[i].isSelected());
		this.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Color color = JColorChooser.showDialog(this, null, null);

		if(color != null) {
			led = new LED (color.getRed(),color.getGreen(),color.getBlue());
		}
	}

	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}
	
	public void mouseClicked(MouseEvent e){
	}
	
	public void mouseReleased(MouseEvent e){
		System.out.println("Mouse released");
		}
//	@Override
//	public void paintComponent(Graphics g) {
//		g.drawImage(image, 27,0,image.getWidth(outBox),image.getHeight(outBox), null);
//	
//		// Evita bugs visuais em alguns sistemas operacionais.
////		getToolkit().sync();
//	}
	@Override
	public void paintComponent(Graphics g) {	
		g.drawImage(image, 27,0,image.getWidth(outBox),image.getHeight(outBox), null);
		if(led.isOn()){
			g.setColor(new Color(led.getR(),led.getG(),led.getB()));
			g.fillOval(117, 17, 15, 15);
		}
		else{
			g.drawOval(117, 17, 15, 15);
		}
		getToolkit().sync();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getX() < 130 && e.getX() > 115){
			Color color = JColorChooser.showDialog(this, null, null);

			if(color != null) {
				led = new LED (color.getRed(),color.getGreen(),color.getBlue());
				led.connect(gate, 0);
				this.repaint();
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
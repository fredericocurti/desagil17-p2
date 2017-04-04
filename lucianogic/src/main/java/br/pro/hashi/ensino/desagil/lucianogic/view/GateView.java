package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTextField;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;
import br.pro.hashi.ensino.desagil.lucianogic.model.LED;

// Esta classe representa a interface de uma porta logica.
public class GateView extends FixedPanel implements ItemListener, ActionListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private JCheckBox[] inBoxes;
	private JCheckBox outBox;

	private Switch[] switches;
	private Gate gate;
	private JButton button;
	
	private Image image;
	

	public GateView(Gate gate) {
		super(290, 150);
		
		image = loadImage(gate.toString());
		
		this.gate = gate;
		
		// A componente JLabel representa simplesmente um texto fixo.
		// https://docs.oracle.com/javase/tutorial/uiswing/components/label.html
		JLabel inLabel = new JLabel("Entrada:");
		JLabel outLabel = new JLabel("Saida:");
		
		int size = gate.getSize();

		inBoxes = new JCheckBox[size];

		switches = new Switch[size];

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

		
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(this);
		
		
		add(button, 117, 20, 10, 10);
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
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Color color = JColorChooser.showDialog(this, null, null);

		if(color != null) {
			button.setBackground(color);
		}
	}

	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 27,0,image.getWidth(outBox),image.getHeight(outBox), null);
	
		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
	}
}
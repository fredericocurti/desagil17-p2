package br.pro.hashi.ensino.desagil.lucianogic.view;
import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Emitter;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;

import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.JTextField;
import javax.swing.JCheckBox;

//inputA.addItemListener(this);
//inputB.addItemListener(this);
//resultbox.addItemListener(this);

// Esta classe representa a interface de uma calculadora de densidade, com
// os dois campos de entrada (peso e raio) e o campo de saida (resultado).
public class GateView extends JPanel implements ItemListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;

	// A componente JTextField representa um campo para digitacao de texto.
	// https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
	private JCheckBox resultbox;
	private Gate gate;
	private JCheckBox input1;
	private JCheckBox input2;
	private JCheckBox input3;
	private Switch switch1;
	private Switch switch2;
	private Switch switch3;
	
	
//	List<Switch> switches = new LinkedList<>();
//	List<JCheckBox> inputs = new LinkedList<>();
	
	public GateView(Gate gate) {
		this.gate = gate;

		// A componente JLabel representa simplesmente um texto fixo.
		// https://docs.oracle.com/javase/tutorial/uiswing/components/label.html
		JLabel entradalabel = new JLabel("Entrada:");
		JLabel resultLabel = new JLabel("Saída:");
		
		input1 = new JCheckBox();
		input2 = new JCheckBox();
		input3 = new JCheckBox();
		resultbox = new JCheckBox();
		
		switch1 = new Switch();
		switch2 = new Switch();
		switch3 = new Switch();
		
		gate.connect(switch1, 0);
		
		add(entradalabel);
		add(input1);
		
		input1.addItemListener(this);
		input2.addItemListener(this);
		input3.addItemListener(this);
		
		if (gate.getSize() == 2 || gate.getSize() == 3){
			add(input2);
			gate.connect(switch2, 1);
		}
		else {
			remove(input2);
		}
		if (gate.getSize() == 3){
			add(input3);
			gate.connect(switch3, 2);
		}
		else {
			remove(input3);
		}
	
		
//		for(int i = 0;i<gate.getSize();i++){
//			System.out.println("iteracao"+i);
//			Switch switchgate = new Switch();
//			switches.add(switchgate);
//			
//			JCheckBox inputbox = new JCheckBox();
//			inputbox.addItemListener(this);
//			inputs.add(inputbox);
//			
//		}

//		for(int i = 0;i<gate.getSize();i++){
//			JCheckBox each = inputs.get(i);
//			each.addItemListener(this);
//			inputs.set(i,each);
//		}
//		
		// Esta linha garante que, sempre que o usuario digitar algo
		// em inputA, o metodo keyPressed abaixo sera chamado.
		// Voce usou a interface KeyListener no Projeto 1, lembra?
		
		// Esta linha garante que, sempre que o usuario digitar algo
		// em inputB, o metodo keyPressed abaixo sera chamado.
		// Voce usou a interface KeyListener no Projeto 1, lembra?
		
		// Esta linha garante que resultbox nao seja digitavel.
		resultbox.setEnabled(false);

		// Esta linha garante que os componentes sejam simplesmente
		// colocados em linha reta, mais especificamente na vertical.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		/* A PARTIR DESTE PONTO VOCE DEVE ENTENDER SOZINHO */
//		for (int i = 0; i < inputs.size(); i++){
//			add(inputs.get(i));
//		}
		
		add(resultLabel);
		add(resultbox);
		
//		add(weightLabel);
//		add(resultLabel);
//		add(resultbox);

//		resultbox.setText("type something");
		
		if(gate.read() == true){
			System.out.println("Chegou");
			resultbox.setSelected(true);
		}
		if(gate.read() == false){
			resultbox.setSelected(false);
		}
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getItemSelectable();
		System.out.println("Event changed");
		
		if (source == input1){
			switch1.setOn(input1.isSelected());	
		}
		
		if (source == input2){
			switch2.setOn(input2.isSelected());	
		}
		
		if (source == input3){
			switch3.setOn(input3.isSelected());	
		}
		
		if(gate.read() == false){
			resultbox.setSelected(false);
		}
		
		if(gate.read() == true){
			System.out.println("Chegou");
			resultbox.setSelected(true);
		}
	}
}
//if (inputs.get(i).isSelected()){
//switches.get(0).setOn(on);
//gate.connect(switches.get(0), 0);
//}
//
//else if (inputA.isSelected() == false) {
//gate.connect(switches.get(0), 0);
//}
//
//if (inputB.isSelected()){
//switches.get(1).setOn(on);
//gate.connect(switches.get(1), 1);
//}
//else if (inputB.isSelected() == false) {
//gate.connect(switches.get(1), 1);
//}

//	@Override
//	public void keyReleased(KeyEvent event) {
//	}
//	@Override
//	public void keyTyped(KeyEvent event) {
//	}
//}

package br.pro.hashi.ensino.desagil.lucianogic.view;
import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Emitter;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;

import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
	List<Switch> switches = new LinkedList<>();
	List<JCheckBox> inputs = new LinkedList<>();


	public GateView(Gate gate) {
		this.gate = gate;

		// A componente JLabel representa simplesmente um texto fixo.
		// https://docs.oracle.com/javase/tutorial/uiswing/components/label.html
		
		JLabel entrada = new JLabel("Entrada:");
		JLabel resultLabel = new JLabel("Saída:");
		JCheckBox resultbox = new JCheckBox();
		add(entrada);
		
		for(int i = 0;i<gate.getSize();i++){
			Switch switchgate = new Switch();
			switches.add(switchgate);
			JCheckBox input = new JCheckBox();
			inputs.add(input);
		}
		for(int i = 0;i<gate.getSize();i++){
			inputs.get(i).addItemListener(this);
		}
		
		
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
		for (int i = 0; i < inputs.size(); i++){
			add(inputs.get(i));
		}
		add(resultLabel);
		add(resultbox);
		
//		add(weightLabel);
//		add(resultLabel);
//		add(resultbox);

//		resultbox.setText("type something");
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		System.out.println(inputs);
		boolean on = true;

		for (int i = 0; i < inputs.size(); i++){
			if (inputs.get(i).isSelected()){
				switches.get(i).setOn(on);
				gate.connect(switches.get(i), i);
			}
			else if (inputs.get(i).isSelected() == false) {
				gate.connect(switches.get(i), i);
			}
			if(gate.read() == true){
				System.out.println("Chegou");
				resultbox.setSelected(true);
			}
			else if(gate.read() == false){
				resultbox.setSelected(false);
			}
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

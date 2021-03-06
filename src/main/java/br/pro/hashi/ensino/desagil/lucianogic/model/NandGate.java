package br.pro.hashi.ensino.desagil.lucianogic.model;

public class NandGate extends Gate {
	public Emitter[] emitters;

	public NandGate() {
		super(2);
		name="NAND";
		emitters = new Emitter[2];
	}

	@Override
	public boolean read() {
		return !(emitters[0].read() && emitters[1].read());
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		emitters[index] = emitter;
	}
}

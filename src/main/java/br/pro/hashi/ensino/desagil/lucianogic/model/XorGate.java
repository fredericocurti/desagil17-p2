package br.pro.hashi.ensino.desagil.lucianogic.model;

public class XorGate extends Gate {
	private NandGate nandGate;
	private NandGate nandGate2;
	private NandGate nandGate3;
	private NandGate nandGate4;

	public XorGate() {
		super(2);
		nandGate = new NandGate();
		nandGate2 = new NandGate();
		nandGate3 = new NandGate();
		nandGate4 = new NandGate();
		nandGate2.connect(nandGate, 1);
		nandGate3.connect(nandGate, 0);
		nandGate4.connect(nandGate2, 0);
		nandGate4.connect(nandGate3, 1);
	}

	@Override
	public boolean read() {
		return nandGate4.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		if (index == 0){
			nandGate.connect(emitter, 0);
			nandGate2.connect(emitter, 0);
		}
		else{
			nandGate.connect(emitter, 1);
			nandGate3.connect(emitter, 1);
		}
	}
}


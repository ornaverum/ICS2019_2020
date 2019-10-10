namespace ICS.Samples{

	open Microsoft.Quantum.Intrinsic;
    open Microsoft.Quantum.Canon;
    open Microsoft.Quantum.Math;
    open Microsoft.Quantum.Diagnostics;
	open Microsoft.Quantum.Measurement;
	open Microsoft.Quantum.Samples;
	open Microsoft.Quantum.Samples.Teleportation;
    
    
	operation HelloQ(name: String):Unit{
		Message($"Hello, {name}!");
	}
	
	operation HelloPi() : Unit {
		let pi = Microsoft.Quantum.Convert.DoubleAsString(PI());
		HelloQ(pi);
	}

	operation HelloClass(): Unit{
		HelloQ("Class");
	}
	
	
	/// # Summary
	/// Sets the qubit's state to |+⟩
	operation SetPlus(q: Qubit) : Unit {
		Reset(q);
		H(q);
	}

	/// # Summary
	/// Sets the qubit's state to |-⟩
	operation SetMinus(q: Qubit) : Unit {
		Reset(q);
		X(q);
		H(q);
	}

	/// # Summary
	/// Randomly prepares the qubit into |+⟩ or |-⟩
	operation PrepareRandomMessage(q: Qubit) : Unit {

		let choice = RandomInt(2);

		if (choice == 0) {
			Message("Prepared |->");
			SetMinus(q);
		} else {
			Message("Prepared |+>");
			SetPlus(q);
		}
	}
	
	operation NextRandomBit() : Result {
		using (q = Qubit()) {
			SetPlus(q);
			return MResetZ(q);
		}
	}

	operation TestPrepareQubits() : Result {
		mutable r = Zero;
		
		using (qubits = Qubit[5]) {
			ApplyToEach(PrepareRandomMessage, qubits);
			DumpMachine();
			
			set r = Measure([PauliX, PauliX, PauliX, PauliX, PauliX], qubits);
			
			ResetAll(qubits);
		}
		
		return r;
	}
	
	operation TestQubitOrientation():Unit{
		let count = 100;
		mutable res = Zero;
		for (i in 0..count-1){
			set res = NextRandomBit();
			if(res==Zero){
				Message($"Zero!");
			} else {
				Message($"One!");
			}
		}
	}
	
	
	
}

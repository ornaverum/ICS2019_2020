namespace Microsoft.Quantum.Samples.Teleportation{
	open Microsoft.Quantum.Intrinsic;
    open Microsoft.Quantum.Canon;
    open Microsoft.Quantum.Math;
    open Microsoft.Quantum.Diagnostics;
	open Microsoft.Quantum.Measurement;
	open Microsoft.Quantum.Samples;

	/// # Summary
	/// Sends the state of one qubit to a target qubit by using
	/// teleportation.
	/// 
	/// Notice that after calling Teleport, the state of `msg` is
	/// collapsed.
	///
	/// # Input
	/// ## msg
	/// A qubit whose state we wish to send.
	/// ## target
	/// A qubit initially in the |0> state that we want to send
	/// the state of msg to.
	
	operation Teleport (msg : Qubit, target : Qubit) : Unit {

		using (register = Qubit()) {
			// Create some entanglement that we can use to send the message.
			H(register);
			CNOT(register, target);

			// Encode the message into the entangled pair,
			// and measure the qubits to extract the classical data
			// we need to correctly decode the message into the target qubit:
			CNOT(msg, register);
			H(msg);
			let data1 = M(msg);
			let data2 = M(register);

			// decode the message by applying the corrections on
			// the target qubit accordingly:
			if (data1 == One) { Z(target); }
			if (data2 == One) { X(target); }

			// Reset our "register" qubit before releasing it.
			Reset(register);
		}
	}
	
	/// # Summary
	/// Uses teleportation to send a randomly picked |-> or |+> state
	/// to another.
	
	operation TeleportRandomMessage () : Unit {

		using (qubits = Qubit[2]) {

			// Ask for some qubits that we can use to teleport.
			let msg = qubits[0];
			let target = qubits[1];

			PrepareRandomMessage(msg);

			// Use the operation we defined above.
			Teleport(msg, target);

			// Report message received:
			if (IsPlus(target))  { Message("Received |+>"); }
			if (IsMinus(target)) { Message("Received |->"); }

			// Reset all of the qubits that we used before releasing
			// them.
			ResetAll(qubits);
		}
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



}

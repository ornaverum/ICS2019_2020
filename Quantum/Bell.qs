namespace ICS.Bell{
	open Microsoft.Quantum.Intrinsic;
    open Microsoft.Quantum.Canon;
    open Microsoft.Quantum.Math;
    open Microsoft.Quantum.Canon;
    open Microsoft.Quantum.Samples;
    
    //operation Set (desired: Result, q1: Qubit) : Unit {
    operation Set (desired: Result, q1: Qubit) : Unit {
        if (desired != M(q1)) {
            X(q1);
        }
    }


    //operation BellTest (count : Int, initial: Result) : (Int, Int, Int) {
    
    operation SimpleBellTest (count : Int, initial: Result) : Unit {
        mutable numOnes = 0;
        mutable agree = 0;
        using ((q0, q1) = (Qubit(), Qubit())) {
            for (test in 1..count)
            {
                Set (initial, q0);
                Set (Zero, q1);
	
                H(q0);
                CNOT(q0,q1);
                let res = M (q0);
	
                if (M (q1) == res) {
                    set agree += 1;
                }
	
                // Count the number of ones we saw:
                if (res == One) {
                    set numOnes += 1;
                }
                
            }
            
            Set(Zero, q0);
            Set(Zero, q1);
        }

        // Return number of times we saw a |0> and number of times we saw a |1>
        //return (count-numOnes, numOnes, agree);
        let numZeroes = count - numOnes;
        Message($"There were {numZeroes} zeroes, {numOnes} ones, and {agree} agreements.");
    }
    
    
    
    
    operation BellTest (count : Int) : Unit {
        mutable agree = 0;
        mutable plus0 = 0;
        mutable plus1 = 0;
		for (test in 1..count)
		{
            using ((q0, q1) = (Qubit(), Qubit())) {
                PrepareEntangledPair(q0, q1);
                SetRandomQubitAngle(q0);
                SetRandomQubitAngle(q1);
	
                if (M (q1) == M(q0)) {
                    set agree += 1;
                }
                if(IsPlus(q0)){
					set plus0 +=1;
                }
                if(IsPlus(q1)){
					set plus1 +=1;
                }
                
            Reset(q0);
            Reset(q1);

            }
        }
        
        // Return number of times we saw a |0> and number of times we saw a |1>
        Message($"There were {agree} agreements. q0 was + {plus0} times and q1 was + {plus1} times");
    }
    
    operation PrepareEntangledPair(q0: Qubit, q1: Qubit): Unit {
		//let choice = RandomInt(2);
		
		Reset(q0);
		Reset(q1);
		H(q0);
		CNOT(q0,q1);
	}
    
    //operation SetRandomQubitAngle(q: Qubit): Qubit{
    operation SetRandomQubitAngle(q: Qubit): Unit{
		let choice = RandomInt(3);
		
		SetQubitAngle(q, choice);
		
		//return q;
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
    
    
    operation BellTestDefault() : Unit{
		let count = 100000;
		let res = One;
		BellTest(count);
    }
    
    operation TestRandom():Unit{
		mutable choice = 0;
		let count = 100;
		for (test in 1..count)
        {
				set choice = RandomInt(100);
				Message($"{choice}");
        }
    }
    
    
    operation SetQubitAngle(q: Qubit, choice : Int) : Unit{
		if (choice == 0) {
			Ry(2.0*PI()/3.0, q);
			//Ry(60.0,q);
		} elif (choice == 1){
			Ry(2.0*2.0*PI()/3.0, q);
		} else {
			// pass
		}
		//X(q);  // Just there for testing.
    }
    
    operation TestAngle():Unit{
		let count = 100000;
		mutable numOnes = 0;
		using (q = Qubit()){
			for (test in 1..count){
			
				Reset(q);
				//SetPlus(q);
				SetQubitAngle(q, 1);
				if (M(q) == One){
					set numOnes += 1;
					Message($"Got a 1!");
				} else {
					Message($"Got a 0!");
				}	
			}
			Set(Zero, q);
		}
		Message($"That's {numOnes} ones!");
		
    }
    
}

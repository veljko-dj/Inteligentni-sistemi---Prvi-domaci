/*
 * Ovde ide izmena na v2, pokusaj resavanja CopyCata(III) da ne dobija kada postoji greska
 * Samo je promenjeno da na copycat stavlja 1 a ne dva (mislim na papir) 
 * Jer ne zelim da jurim maksimalno poena a da gubim vec jurim da bude nereseno a 
 * da dobijam jos poena.
 * 
 * Dodatak na v3. Za v3 koristi komentar, to je jednostavnije, ulazak nulom na III/IV
 * _________________________________________________________________________________________
 * I - Goody
 * II - Stinger
 * III - CopyCat
 * IV - Forgiver
 * V - Avenger
 * VI - Ulazem1v4
 * _________________________________________________________________________________________
 * 
 * */
package rs.ac.bg.etf.players; 

public class Ulazem1v4 extends Player {
	// class Ulazem1v4 (stari naziv)

	private Player.Move myMove = Player.Move.PUT1COIN;

	private String opType = "_";

	public void resetPlayerState() {
		super.resetPlayerState();
		this.myMove = Player.Move.PUT1COIN;
	}

	@Override
	public Move getNextMove() {
		// Prvi potez
		if ((this.opponentMoves.size() == 0)) {
			return this.myMove;
		}

		// Poslednji protivnicki potez
		Move opMove = this.opponentMoves.get(this.opponentMoves.size() - 1);
		// Drugi potez
		if (this.opponentMoves.size() == 1) {
			if (opMove == Player.Move.PUT2COINS) {
				// znaci I ili V
				myMove = Player.Move.PUT1COIN;
				opType = "I|V_3";
				return myMove;
			}
			if (opMove == Player.Move.DONTPUTCOINS) {
				// Znaci II, izgubio sam 4p
				myMove = Move.DONTPUTCOINS;
				opType = "II";
				return myMove;
			}
			if (opMove == Player.Move.PUT1COIN) {
				// Znaci III ili IV
				// Igracu 2
				myMove = Move.PUT2COINS;
				opType = "III|IV_3";
				return myMove;
				/*// Igracu 0
				myMove = Move.DONTPUTCOINS;
				opType = "III|IV";
				return myMove;*/
			}
		}
		switch (opType) {
		case "II":
			return Move.DONTPUTCOINS;
		case "I":
			return Move.DONTPUTCOINS;
		case "V":
			return Move.DONTPUTCOINS;
			//Ovde moze .PUT1COINT ali tako raste Avenger i mozda te pobedi nekad, 
			//i postoji sansa da te zezne kad bude greska u igri, tj. nula ako slucajno upadne odrace te
		case "I|V_3": {
			//ovo je treci potez
			if (opMove == Move.PUT2COINS) {
				opType = "I";
				return Move.DONTPUTCOINS;
			}
			if (opMove == Move.PUT1COIN) {
				opType = "V";
				return Move.PUT1COIN;
			}
		}
		/*case "III|IV": {
			// size() ==2 // Odigran drugi potez
			if (opMove == Move.DONTPUTCOINS) {
				opType = "VI"; // Ulazem1v3- Moj igrac
				return Move.PUT2COINS;
			}
			opType = "III|IV_size==3";
			return Move.PUT1COIN;
		}
		case "III|IV_size==3": {
			if (opMove == Move.PUT1COIN) {
				opType = "IV_last2";
				myMove = Move.PUT2COINS;
			}
			if (opMove == Move.DONTPUTCOINS) {
				opType = "III";
				return Move.PUT1COIN;
			}
		}*/
		case "III|IV_3": {
			if (opMove == Move.PUT2COINS) {
				opType = "VI"; // Ulazem1v3- Moj igrac
				return Move.PUT2COINS;
			}
			opType = "III|IV_4";
			return Move.DONTPUTCOINS;
		}
		case "III|IV_4": {
			opType = "III|IV_5";
			return Move.PUT2COINS;
		}
		case "III|IV_5": {

			if (opMove == Move.PUT2COINS) {
				opType = "IV_last0";
				myMove = Move.DONTPUTCOINS;
			}
			if (opMove == Move.DONTPUTCOINS) {
				opType = "III";
				return Move.PUT1COIN;
			}
		}
		case "III": {
			return Move.PUT1COIN;
		}
		case "IV_last2": {
			opType = "IV_last0";
			return Move.DONTPUTCOINS;
		}
		case "IV_last0": {
			opType = "IV_last2";
			return Move.PUT2COINS;
		}
		case "VI": {
			return Move.PUT2COINS;
		}

		}
		return myMove;
	}

}

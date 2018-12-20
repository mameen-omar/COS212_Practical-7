
public class Main {
	public static void main(String[] args) {
		char[] letters = {'Q', 'W', 'E', 'R', 'T', 'Y'};
		Trie trie = new Trie(letters);

		System.out.println("Inserting");
		trie.insert("WWWWW");
		trie.insert("W");
		trie.insert("WRETE");
		trie.insert("WRWW");
		trie.insert("RTE");
		trie.insert("RTRRR");
		trie.insert("RTWE");
		trie.insert("RTWW");
		trie.insert("TTT");
		trie.insert("TYT");
		trie.print();


		System.out.println("Deleting");
		trie.delete("RTRRR");
		trie.delete("WRETE");
		
		trie.print();

		/*
		Inserting
		 (#,0)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,1)  (Y,0)
		 (#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)
		 (#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,0)
		 (#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)
		W
		WWWWW
		 (#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)
		 (#,0)  (Q,0)  (W,1)  (E,1)  (R,1)  (T,0)  (Y,0)
		TTT
		TYT
		WRWW
		WRETE
		 (#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)
		RTE
		RTRRR
		RTWW
		RTWE
		Deleting
		 (#,0)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,1)  (Y,0)
		 (#,1)  (Q,0)  (W,1)  (E,0)  (R,1)  (T,0)  (Y,0)
		 (#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,0)
		 (#,0)  (Q,0)  (W,0)  (E,0)  (R,0)  (T,1)  (Y,1)
		W
		WWWWW
		WRWW
		 (#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)
		TTT
		TYT
		 (#,0)  (Q,0)  (W,1)  (E,1)  (R,0)  (T,0)  (Y,0)
		RTE
		RTWW
		RTWE
		*/
	}
}

//Mohamed Ameen Omar
//u16055323



public class Trie
{
	protected char[] letters;
	protected Node root = null;
	private int numPtrs;
	/**
	* Initializes the empty trie.
	* @param _letters - The list of characters that the trie should be able to handle
	* (including the end-of-word character, which is stored at the 0th index")
	* It is assumed that _letters is already sorted.
	*/
	public Trie(char[] _letters)
	{
		letters = new char[_letters.length+1];
		letters[0] = '#';
		for (int k = 0; k < _letters.length; k++) {
		  letters[k+1] = _letters[k];
		}
		numPtrs = _letters.length + 1;
	}

	/**
	* Converts the given Node to its string representation
	* @param Node - The Node being printed
	*/
	public String nodeToString(Node node) {
		if (node.isLeaf) {
				return node.key;
		}
		else {
			String res = "";
			for (int k = 0; k < node.ptrs.length; k++) {
				if (node.ptrs[k] == null) {
					res += "("+character(k)+",0)  ";
				} else {
					res += "("+character(k) + ",1)  ";
				}
			}
			return res;
		}
	}

	/**
	* Prints all the nodes in the tree in a breadth-first fashion.
	*/
	public void print() {
		Queue queue = new Queue(10);

		Node n = root;
		if (n != null) {
			queue.enq(n);
			while (!queue.isEmpty()){
				n = queue.deq();
				System.out.println(nodeToString(n));
				for (int k = 0; k < n.ptrs.length; k++) {
					if (n.ptrs[k] != null)
						queue.enq(n.ptrs[k]);
				}
			}
		}
	}

	/**
	* A helper function that finds the index of the given character in the 'letters' array.
	* This can be used to find the index at which a node should be inserted into a ptrs array.
	*/
	protected int index(char c) {
		for (int k = 0; k < letters.length; k++) {
			if (letters[k] == c) {
				return k;
			}
		}
		return -1;
	}

	/**
	* A helper function that finds the character for a given index
	* This can be used to find the character being represented by an index in a ptrs array
	*/
	protected char character(int i) {
		return letters[i];
	}

	/**
	* Inserts the given word into the tree
	*/
	public void insert(String word) {
		if (root == null) {
			root = new Node(word, numPtrs);
			return;
		}

		boolean inserted = false;
		int i = 0;
		Node node = root;

		while(!inserted) {
			if (i == word.length()) {
				node.endOfWord = true;
				node.ptrs[0] = new Node(word, numPtrs);
				inserted = true;
			} else if (node.ptrs[index(word.charAt(i))] == null) {
				if (node.isLeaf) {
					node.isLeaf = false;
					String leafKey = node.key;
					insert(leafKey);
					insert(word);
					inserted = true;
				} else {
					node.ptrs[index(word.charAt(i))] = new Node(word, numPtrs);
					inserted = true;
				}
			} else if (node.ptrs[index(word.charAt(i))].isLeaf) {
				String leafKey = node.ptrs[index(word.charAt(i))].key;

				while (i < word.length() && i < leafKey.length() &&word.charAt(i) == leafKey.charAt(i)) {
					int ii = index(word.charAt(i));
					node.ptrs[ii] = new Node(numPtrs);
					node = node.ptrs[ii];

					i++;
				}
				if (i == word.length()) {
					node.endOfWord = true;
					node.ptrs[0] = new Node(word, numPtrs);
					insert(leafKey);
					return;
				} else if (i == leafKey.length()) {
					node.endOfWord = true;
					node.ptrs[0] = new Node(leafKey, numPtrs);
					insert(word);
				} else if (word.charAt(i) != leafKey.charAt(i)){
					Node newLeafKey = new Node(leafKey, numPtrs);
					Node newWord = new Node(word, numPtrs);
					node.ptrs[index(word.charAt(i))] = newWord;
					node.ptrs[index(leafKey.charAt(i))] = newLeafKey;
				}
				inserted = true;

			} else {
				node = node.ptrs[index(word.charAt(i++))];
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// Do NOT modify any of the code above this line
	//Implement the function below this line

	/**
	* Delete the given word from the trie, also deleting unnecessary non-leaf nodes.
	* @param key the word that is being deleted from the trie
	*/
	public void delete(String key)
	{
		//Your code here

		if(key == null)
		{
			return;
		}

		char[] stringArray = key.toCharArray();

		int size = stringArray.length;

		int stringIndex = 0;

		Node focus = root;

		Node parent = null;

		Node grandparent = null;



		int childIndex = 0;

		int parentIndex = 0;


		while(true)
		{
			if(focus == null)
			{
				break;
			}


			else if(focus.endOfWord == true)
			{
				if(focus.ptrs[0] != null)
				{
					String temp = focus.ptrs[0].key;
					//delete
					if(temp.equals(key))
					{
						if(parent == null)
						{
							focus = null;
							break;
						}

						//delete
						focus.ptrs[0] = null;

						//wont be redundancy
						if(grandparent == null)
						{
							int nullptrs = 0;
							for(int x = 0; x<focus.ptrs.length;x++)
							{
								if(focus.ptrs[x] == null)
								{
									nullptrs++;
								}
							}

							if(nullptrs >= focus.ptrs.length)
							{
								parent.ptrs[childIndex] = null;
							}
							break;
						}

						int nullptrs = 0;
						int numLeaf = 0;
						int nonLeaf = 0;
						int moveIndex = 0;
						//check redundant
						for(int x = 0; x < parent.ptrs.length; x++)
						{
							if(parent.ptrs[x] == null)
							{
								nullptrs++;

							}

							else if(parent.ptrs[x].isLeaf == true)
							{
								numLeaf++;
								moveIndex = x;
							}

							else
							{
								nonLeaf++;
							}
						}

						if(nonLeaf == 0)
						{

							if(numLeaf == 1)
							{

								grandparent.ptrs[parentIndex] = parent.ptrs[moveIndex];
								parent = null;
								break;
							}
							if(nullptrs == parent.ptrs.length)
							{
								grandparent.ptrs[parentIndex] = null;
							}

							else
							{
								break;
							}
						}

						if(nullptrs == parent.ptrs.length)
						{
							grandparent.ptrs[parentIndex] = null;
						}
					}
				}

				else
				{
					focus.endOfWord = false;

				}

			}


			if(focus.isLeaf == true)
			{
				String temp = focus.key;

				//we need to delete
				if(temp.equals(key))
				{
						if(parent == null)
						{
							focus = null;
							break;
						}

						parent.ptrs[childIndex] = null;
						//wont be redundancy
						if(grandparent == null)
						{

							return;
						}

						int numLeaf = 0;
						int nonLeaf = 0;
						int moveIndex = 0;
						int nullptrs = 0;
						//check redundant
						for(int x = 0; x < parent.ptrs.length; x++)
						{
							if(parent.ptrs[x] == null)
							{
								nullptrs++;
								continue;
							}

							else if(parent.ptrs[x].isLeaf == true)
							{
								numLeaf++;
								moveIndex = x;
							}

							else
							{
								nonLeaf++;
							}
						}

						if(nonLeaf == 0)
						{

							if(numLeaf == 1)
							{
								grandparent.ptrs[parentIndex] = parent.ptrs[moveIndex];
								parent = null;
								break;
							}

							if(nullptrs == parent.ptrs.length)
							{
								grandparent.ptrs[parentIndex] = null;
							}

							else
							{
								break;
							}
						}

						if(nullptrs == parent.ptrs.length)
						{
							grandparent.ptrs[parentIndex] = null;
						}
				}

				else
				{
					break;
				}
			}

			if(stringIndex >= stringArray.length)
			{
				return;
			}


			//search
			//this is the index for which move down so it is the index of the alphabet of our new parent and focus becomes this child
			parentIndex = childIndex;
			char c = stringArray[stringIndex];
			childIndex = index(c);

			grandparent = parent;
			parent = focus;
			focus = focus.ptrs[childIndex];
			stringIndex++;

	}



	return;
}
}

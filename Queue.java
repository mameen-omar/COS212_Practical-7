public class Queue {
	public Queue(int _max) {
		elems = new Node[_max];
		max = _max;
	}

	Node[] elems;
	int begin = 0;
	int end = 0;
	int max;

	public void enq(Node e)
	{
		if (begin-end == 1) {
			Node[] temp = new Node[max*2];
			for (int k = 0; k < max; k++) {
				temp[k] = elems[(begin + k%max)];
			}
			temp[max] = e;
			max = max*2;
		}
		else {
			elems[end] = e;
			end = (end+1)%max;
		}
	}

	public Node deq() {
		if (begin == end) {
			System.out.println("ERROR - Print Queue empty");
			return null;
		}
		Node result = elems[begin];
		begin = (begin+1)%max;
		return result;
	}

	public boolean isEmpty() {
		return begin == end;
	}

	public void print() {
		if (this.isEmpty()) {
			System.out.println("[  ]");
		}
		String t = "[ "+elems[begin];
		for (int k = 1; k < max; k++) {
			t += ", "+elems[(begin+k)%max];
		}
		t += " ]";
		System.out.println(t);
    }
}

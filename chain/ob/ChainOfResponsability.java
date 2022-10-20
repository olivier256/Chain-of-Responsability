package chain.ob;

import java.util.function.Consumer;

public abstract class ChainOfResponsability<T> implements Consumer<T> {
	private ChainOfResponsability<T> successor;

	public void acceptAndPass(T t) {
		accept(t);
		if (successor != null) {
			successor.acceptAndPass(t);
		}
	}

	public void setSuccessor(ChainOfResponsability<T> successor) {
		this.successor = successor;
	}

	static class Helper<T> {
		private final ChainOfResponsability<T> root;

		public Helper(ChainOfResponsability<T> root) {
			this.root = root;
		}

		public Helper<T> appendNext(ChainOfResponsability<T> arg) {
			ChainOfResponsability<T> current = root;
			while (current.successor != null) {
				current = current.successor;
			}
			current.successor = arg;
			return this;
		}

		public ChainOfResponsability<T> build() {
			return root;
		}

	}

}

package programming.set9.parser;

import java.util.StringTokenizer;

public class PNParser {

	public static void main(String[] args) {
		System.out.println("+ + 32 4 5 7-> " + new PNParser("+ + 32 4 5 7"));
		System.out.println("* + 3 4 5 -> " + new PNParser("* + 3 4 5"));
		System.out.println("+ 3 * 4 5 -> " + new PNParser("+ 3 * 4 5"));
		System.out.println("* 3 + 4 5 -> " + new PNParser("* 3 + 4 5"));
	}

	/**
	 * Holds the expression.
	 */
	private SimpleTree expTree;

	/**
	 * Expecting an expression in polish notation.
	 * 
	 * @param pNExpression
	 */
	public PNParser(String pNExpression) {

		StringTokenizer tokenizer = new StringTokenizer(pNExpression, " ");

		expTree = buildTree(tokenizer);
		if(tokenizer.hasMoreTokens())
			throw new IllegalArgumentException("The given expression is not valid! ");

	}

	/**
	 * Builds up a {@link SimpleTree} that holds the first expression in the
	 * {@link StringTokenizer}. If the first expression was not fully valid
	 * throws an {@link IllegalArgumentException}. This method takes tokens from
	 * the tokenizer and there makes changes to the object!
	 * 
	 * @param tokenizer
	 *            The {@link StringTokenizer} holding the pNExpression
	 * @return the {@link SimpleTree} holding the expression
	 * 
	 */
	private SimpleTree buildTree(StringTokenizer tokenizer) {

		if (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();
			SimpleTree tree = new SimpleTree(token); // neuer Baum/Ast

			if (isOperator(token)) { // wenn operator
									// -> neue Bäume für link und rechts

				if (tokenizer.hasMoreTokens()) { // es muss noch 2 operanden geben 
					tree.setLeft(buildTree(tokenizer));
					if (tokenizer.hasMoreTokens()) {
						tree.setRight(buildTree(tokenizer));
						return tree;
					}
				} else
					throw new IllegalArgumentException("The given expression is not valid! ");

			} else if (isNumber(token)) {
				return tree;
			}
		}

		// es hat keiner der Fälle zu getroffen...
		throw new IllegalArgumentException("The given expression is not valid! ");

	}

	/**
	 * Checks if the given String holds an integer number.
	 * 
	 * @param str
	 *            the String t check.
	 * @return true if str holds an integer number
	 */
	private boolean isNumber(String str) {
		char[] chars = str.toCharArray();
		for (char c : chars) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

	/**
	 * Checks if the given String holds an operator (+, -, *, /).
	 * 
	 * @param str
	 *            The String to check.
	 * @return true if str is an operator
	 */
	private boolean isOperator(String str) {
		return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
	}

	/**
	 * Returns the expression this Object holds in infix notation.
	 * 
	 * @return the expression in infix notation.
	 */
	@Override
	public String toString() {
		return toString(expTree, false);
	}

	/**
	 * Returns the infix string representation of the given tree. And adds
	 * Parentheses if necessary.
	 * 
	 * @param tree
	 *            The tree holding the expression
	 * @param outerMulti
	 *            indicates whether this expression is inside of a
	 *            multiplicative expression.
	 * @return The infix notation of the expression in tree.
	 */
	private String toString(SimpleTree tree, boolean outerMulti) {
		StringBuilder str = new StringBuilder();

		String cont = tree.getContent();

		if (isOperator(cont)) {
			boolean multi = isMultiOperator(cont);

			if (!multi && outerMulti) // nur wenn keine multiplikative Operation
										// vorliegt die von einer umgeben ist
										// werden klammern benötigt
				str.append("(");
			str.append(toString(tree.getLeft(), multi));
			str.append(" ");
			str.append(cont);
			str.append(" ");
			str.append(toString(tree.getRight(), multi));
			if (!multi && outerMulti)
				str.append(")");

		} else if (isNumber(cont)) {
			str.append(cont);
		} else
			throw new IllegalArgumentException("Invalid tree-exprssion!");

		return str.toString();

	}

	/**
	 * Checks if the op SString holds a multiplicative operator.
	 * 
	 * @param op The Operator
	 * @return true if op is * or /.
	 */
	private boolean isMultiOperator(String op) {
		return op.equals("/") || op.equals("*");
	}
}
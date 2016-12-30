package programming.set8.tree;

/*
Part (b)

Changes to the code:

if(len*0.75 > 2)
	drawTree(x1, y1, len * 0.75, angle + 30);
if(len*0.66 > 2)
	drawTree(x1, y1, len * 0.66, angle - 50);

Erklärung:
Durch das Einfgen der Abfrage, ob die länge der folge Äste noch ausreichend ist, direkt vor dem rekursiven Aufruf von drawTree wird verhindert, dass an jedem ende ein überflüssiger Stack frame erzeugt wird welcher, praktisch sofort wieder geschlossen wird.
Zusätzlich sollte natürlich auch die ursprüngliche "große" Abfrage entfernt werden.
*/

/*
 * Part (c)
 * 
 * Stack
 * Auf dem Stack werden von Unten nah Oben die Stackframes für 
 * jeden einzelnen Methodenaufruf ab gespeichert. Diese beinhalten
 * dann natürlich die lokalen Variablen, bzw. die Referenzen auf
 * den Speicherort der Objektvariablen im Heap.
 * Beim Stack gilt: Last in, First out!
 * > Push and Pop -Strategie <
 * Also: Der zuletzt erzeugte Stackframe muss als erstes abgebaut werden.
 * 
 * zB:	Hier wird für jeden Aufruf der drawTree-Methode ein weiterer 
 * 		Stackframe auf dem Satck abgelegt. Welche auch alle von oben 
 * 		nach unten der Reiche nach wieder abgebaut werden.
 * 
 * Heap
 * In dem Heap, welcher von unterhalb des Statischen Sigments im Speicher
 * nach unten dem Stack entgegen wächst, werden Objekte mit deren Instanz-
 * Variablen gespeichert. Also liegen auch Arrays im Heap. 
 * 
 * zB:	Hier wird jedes erzeugte GLine-Objekt im Heap gepeichert. 
 * 
 * Static Segment
 * Im Statischen Segment des Speichers, welches mit einer unveränderlichen
 * Größe oberhalb des Heap liegt, sind alle Klassen-Variablen gespeichert, 
 * also alle Variablen mit dem Modifier "static".
 * 
 * zB:	Hier werden keine Variablen im Statischen Sigment abgelegt, da 
 * 		keine Klassen-Variablen erzeugt werden.
 * 
 * Recursion
 * Rekursion bezeichnet ein Prinzip bei dem der Algorithmus zur Lösung 
 * eines Problems in einer bestimmten Weise zerlegt wird, so dass im 
 * Algorithmus eine Selbst-Ähnlichkeit auftritt, welche es möglich macht 
 * den Algorithmus auf ein kleineres sich selbst referenzierendes Teilstück
 * herunterzubrechen. Im Programm äußert sich dies durch eine Methode, 
 * welche sich sebst aufruft.
 * 
 * Mutable Class
 * "Mutable Classes" sind Klassen deren Objekte veränderlich sind. Also 
 * Objekte, deren Instanz-Variablen über eine Referenz und die Methoden des 
 * Objekts im Wert verändert werden können.
 * Ein Beispiel für eine Mutable Class ist die Point Klasse.
 * Ein Gegenbeispiel, also ein Beispiel für eine Immutable Class wäre die 
 * String Klasse.
 * 
 * Wrapper Class
 * Eine Wrapper Klasse ist gewissermaßen eine Klasse zur Verpackung eines 
 * primitiven Datentypen in ein Objekt, ausgestattet mit nützlichen Methoden.
 * 
 * Boxing/Unboxing
 * Boxing und Unboxing bezeichnet das "Verpacken" und "Entnehmen" eines 
 * primitiven in und aus einem Objekt.
 * 
 * Garbage Collection
 * Bezeichnet den Vorgang bei dem der "Garbage Collector" alle nicht mehr 
 * referenzierten Objekte vom Heap entfernt und den Speicherplatz wieder freigibt.
 * Dies geschieht nach dem "Mark and sweep"-Prinzip, dass heißt zu erst werden 
 * alle Objekte mit einem "In-use-Flag" versehen und daraufhin wird der Speicher 
 * aller Objekte, die also nicht mehr gebraucht werden freigegeben.
 * 
 * 
 */

import java.util.concurrent.SynchronousQueue;

import acm.graphics.GLine;
import acm.graphics.GMath;
import acm.program.GraphicsProgram;

/**
 * This program provides a drawTree method with all the output and statistics to
 * study the heap and stack.
 * 
 * @author Yannik
 *
 */
public class TreeHeapStackTrace extends GraphicsProgram {

	private int glineCounter = 0;

	private int depth = 0;
	private int maxDepth = 0;

	private int stackAdress = 0x1000000;
	private int stackframeCounter = 0;
	private int minStackAdress = 0xffffe4;

	private int nextheapAdress = 0x100000;

	@Override
	public void run() {
		setSize(500, 350);
		drawTree(250, 350, 100, 90); // ADJUST "TREE PARAMETERS" HERE
	}

	/**
	 * Draws a recursive tree starting with a single line that is specified by
	 * the parameters. This method also prints information on the use of stack
	 * and heap space.
	 * 
	 * @param x0
	 *            the starting points x-coord of the tree
	 * @param y0
	 *            the starting points y-coord of the tree
	 * @param len
	 *            the length of the starting line
	 * @param angle
	 *            the angle of the starting line in degree
	 */
	public void drawTree(double x0, double y0, double len, double angle) {

		// NEW STACK FRAME
		println("Create drawTree() stack frame at adress " + toHexString(stackAdress -= 28) + ", depth " + ++depth);
		stackframeCounter++;
		if (stackAdress < minStackAdress)
			minStackAdress = stackAdress;
		if (depth > maxDepth)
			maxDepth = depth;
		/////

		if (len > 2) {
			double x1 = x0 + len * GMath.cosDegrees(angle);
			double y1 = y0 - len * GMath.sinDegrees(angle);

			// NEW GOBJECT
			println("Create GLine object #" + ++glineCounter + " at address " + toHexString(nextheapAdress));
			nextheapAdress += 20;
			////

			add(new GLine(x0, y0, x1, y1));

			// if(len*0.75 > 2)
			drawTree(x1, y1, len * 0.75, angle + 30);
			// if(len*0.66 > 2)
			drawTree(x1, y1, len * 0.66, angle - 50);

		}

		// DEL STACKFRAME
		println("Delete stack frame at adress " + toHexString(stackAdress) + ", depth " + depth--);
		stackAdress += 28;
		////////

		// END STATS
		if (depth == 0) {

			println("\n" + "HEAP:" + "\n" + "Created " + glineCounter + " GLine objects," + "\n" + "requiring "
					+ (nextheapAdress - 0x100000) + " bytes of heap space," + "\n" + "from address 0x100000 to "
					+ toHexString(nextheapAdress - 1) + "." + "\n" + "\n" + "STACK:" + "\n" + "Created and discarded "
					+ stackframeCounter + " drawTree() stack frames," + "\n" + "with maximal depth " + maxDepth + ","
					+ "\n" + "requiring " + (0xffffff - minStackAdress + 1) + " bytes of stack space," + "\n"
					+ "from address " + toHexString(minStackAdress) + " to 0xffffff. ");
		}
		///////

	}

	/**
	 * This method returns a String that contains the hexadecimal representation
	 * of the given int n which may not be a negative number.
	 * 
	 * @param n
	 *            the int value >= 0
	 * @return the hexadecimal representation, with leading "0x"
	 */
	public static String toHexString(int n) {
		if (n < 0)
			throw new IllegalArgumentException("The givn int n may not be a negative number! :" + n);

		StringBuilder strb = new StringBuilder();

		appendReverseHexString(strb, n);
		strb.append("x0").reverse();

		return strb.toString();
	}

	/**
	 * This method recursively appends the hexadecimal representation of the given number n in revers order. 
	 * 
	 * @param strb the StringBuilder to append the number to 
	 * @param n the number n
	 */
	private static void appendReverseHexString(StringBuilder strb, int n) {
		if (n < 0)
			throw new IllegalArgumentException("The givn int n may not be a negative number! :" + n);

		strb.append(toHexDigit(n % 16)); // appends next digit

		if (n > 15) { // recursive call to append the remaining digits
			appendReverseHexString(strb, n / 16);
		}
	}

	/**
	 * returns a hexadecimal character that represents the number n.
	 * 0 <= n <= 15 
	 * 
	 * @param n the number n
	 * @return the hexadecimal char
	 */
	private static char toHexDigit(int n) {
		if (15 < n || n < 0)
			throw new IllegalArgumentException("The givn int n may not be a negative number or > 15! :" + n);

		if (n < 10)
			return (char) ('0' + n);
		else
			return (char) ('a' + n - 10);

	}

}

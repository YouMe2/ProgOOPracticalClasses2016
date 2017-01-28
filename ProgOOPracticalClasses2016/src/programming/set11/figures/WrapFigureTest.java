package programming.set11.figures;

import acm.program.GraphicsProgram;

public class WrapFigureTest extends GraphicsProgram {

    private static final int WIDTH = 420;
    private static final int HEIGHT = 300;
    
    public void run() {
        setSize(WIDTH, HEIGHT);
        
        String text = "The alley was unusually dark and alley-like. A thick mist covered the ground, "
                + "penetrating the air like a heavy mattress, but without the solid parts. He felt "
                + "reminded of an ingeniously staged theatre play. But this time, he might not make "
                + "it out in one piece, the Don would make sure of that if given half a chance.\n"
                + "'You're late!', said a voice in the dark.\n"
                + "It was then that he noticed that he might not have been on time.";       
        
        WrapFigure wrapFigure = new WrapFigure(WIDTH, "tinlh01w.jpg", 0.46, text);
        wrapFigure.setTextFont("Garamond-bold-12");
        
        add(wrapFigure.getCompound());
    }
}
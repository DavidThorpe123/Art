import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArtMain implements ActionListener {
	//creating the frames and panel
JFrame frame;
JPanel panel;
JButton button;
JButton button2;
BufferedImage ii;
FileWriter fww;
PrintWriter pw;
ArtMain() {
	try {
		 pw = new PrintWriter(fww = new FileWriter("ascii.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	frame = new JFrame();
	panel = new JPanel();
	button = new JButton();
	button2 = new JButton();
	frame.add(panel);
	panel.add(button);
	panel.add(button2);
	button.setText("Convert To ASCII");
	button2.setText("Load Image");
	button.addActionListener(this);
	button2.addActionListener(this);
	frame.setVisible(true);
	frame.setSize(1000, 200);
}
public static void main(String[] args) {
	ArtMain am = new ArtMain();
}
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	if (arg0.getSource() == button2) {
	JFileChooser c = new JFileChooser();
	int r = c.showOpenDialog(panel);
	if (r == JFileChooser.APPROVE_OPTION) {
		try {
			 ii = ImageIO.read(c.getSelectedFile());
			 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
	if (arg0.getSource() == button) {
 

    for (int i = 0; i < ii.getHeight(); i++) {
        for (int j = 0; j < ii.getWidth(); j++) {
            Color pixcol = new Color(ii.getRGB(j, i));
            double pixval = (((pixcol.getRed() * 0.30) + (pixcol.getBlue() * 0.59) + (pixcol
                    .getGreen() * 0.11)));
            print(strChar(pixval));
        }
        try {
            
			pw.println("");
            pw.flush();
       
			fww.flush();
        } catch (Exception ex) {
        }
    }
	}
}

public String strChar(double g) {
    String str = " ";
    if (g >= 240) {
        str = " ";
    } else if (g >= 210) {
        str = ".";
    } else if (g >= 190) {
        str = "*";
    } else if (g >= 170) {
        str = "+";
    } else if (g >= 120) {
        str = "^";
    } else if (g >= 110) {
        str = "&";
    } else if (g >= 80) {
        str = "8";
    } else if (g >= 60) {
        str = "#";
    } else {
        str = "@";
    }
    return str;
}

public void print(String str) {
    try {
        pw.print(str);
        pw.flush();
        fww.flush();
    } catch (Exception ex) {
    }
}
}

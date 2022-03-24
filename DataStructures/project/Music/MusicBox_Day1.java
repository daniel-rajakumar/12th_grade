import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.lang.reflect.Array;
import java.net.*;

import java.io.*;
import java.util.Arrays;
import javax.sound.sampled.*;
import javax.swing.*;


public class MusicBox_Day1 extends JFrame implements Runnable, ActionListener, AdjustmentListener
{
    JToggleButton[][] button =new JToggleButton[37][50];
    JScrollPane buttonPane;
    JScrollBar tempoBar;
    JPanel buttonPanel;
    boolean notStopped = true;
    JFrame frame = new JFrame();
    String[] clipNames;
    Clip[] clip;
    int tempo=150;
    boolean playing = false;
    int row =0, col=0;
    Font font = new Font("Times New Roman",Font.PLAIN,10);
    String[] instrumentNames = {"Bell","Piano","Oboe","Marimba","Glockenspiel","Oh_Ah"};
    Thread timing;

    JMenuBar jMenuBar;
    JMenu instrumentMenu;
    JButton stopPlay, clearButton;
    JPanel menuButtonPanel, tempPanel;
    JMenuItem[] instrumentItems;
    JScrollBar jScrollBar;
    JScrollBar jScrollVolume;
    JLabel tempoLabel;
    JToggleButton jButton;


    public MusicBox_Day1()
    {
        this.setSize(1000,800);
        clipNames=new String[]{"C3","B3","ASharp3","A3","GSharp3","G3","FSharp3","F3","E3","DSharp3","D3","CSharp3","C2","B2","ASharp2","A2","GSharp2","G2","FSharp2","F2","E2","DSharp2","D2","CSharp2","C1","B1","ASharp1","A1","GSharp1","G1","FSharp1","F1","E1","DSharp1","D1","CSharp1","C0"};
        clip=new Clip[clipNames.length];
        addMusic(instrumentNames[0]);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(button.length,button[0].length,2,5));

        for(int r = 0; r < button.length; r++) {
            String name = clipNames[r].replaceAll("Sharp","#");
            for(int c = 0; c < button[0].length; c++) {
                button[r][c] = new JToggleButton();
                button[r][c].setFont(font);
                button[r][c].setText(name);
                button[r][c].setPreferredSize(new Dimension(30,30));
                button[r][c].setMargin(new Insets(0,0,0,0));
                buttonPanel.add(button[r][c]);
            }
        }

        tempoBar = new JScrollBar(JScrollBar.HORIZONTAL, 200, 0, 50, 400);
        tempoBar.addAdjustmentListener(v -> {
            tempo = tempoBar.getValue();
            tempoLabel.setText(String.format("Tempo: %6s", tempo));
        });
        jScrollVolume = new JScrollBar(JScrollBar.VERTICAL, 200, 0, 50, 400);


//        jScrollVolume.addAdjustmentListener(v -> {
//        });


        tempoLabel = new JLabel(String.format("Tempo: %6s", tempo));
        tempPanel = new JPanel(new BorderLayout());
        tempPanel.add(tempoLabel, BorderLayout.WEST);
        tempPanel.add(tempoBar, BorderLayout.CENTER);

        add(tempPanel, BorderLayout.SOUTH);
        add(jScrollVolume, BorderLayout.WEST);

        buttonPane = new JScrollPane(buttonPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(buttonPane,BorderLayout.CENTER);


        jMenuBar = new JMenuBar();
        jMenuBar.setLayout(new GridLayout(1, 2));
        instrumentMenu = new JMenu("Instruments");
        instrumentItems = new JMenuItem[instrumentNames.length];

        for (int i = 0; i < instrumentNames.length; i++) {
            String instrumentName = instrumentNames[i];
            instrumentItems[i] = new JMenuItem(instrumentName);
//            instrumentItems[i].putClientProperty("instrument", instrumentName);
            instrumentItems[i].addActionListener(e -> {
//                System.out.println(e.getSource());
//                System.out.println(e.getSource() instanceof JMenuItem && Arrays.asList(instrumentItems).contains(e.getSource()));
//                Arrays.asList(e.getSource())
//                System.out.println(e.getSource()getSource  JMenuItem);
//                String name = (String) ((JMenuItem) e.getSource()).getClientProperty("Instrument");

                setTitle(instrumentName);
                addMusic(instrumentName);
                reset();
            });

            instrumentMenu.add(instrumentItems[i]);
        }

        jMenuBar.add(instrumentMenu);


        menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new GridLayout(1, 2));

        stopPlay = new JButton("Play");
        clearButton = new JButton("Clear");
        menuButtonPanel.add(stopPlay);
        menuButtonPanel.add(clearButton);
        jMenuBar.add(menuButtonPanel, BorderLayout.EAST);
        jButton = new JToggleButton("Fast");

        jButton.addActionListener(v -> {
            tempo += 10;
            tempoLabel.setText(String.format("Tempo: %6s", tempo));
        });
        jMenuBar.add(jButton);
        add(jMenuBar, BorderLayout.NORTH);

        stopPlay.addActionListener(e -> {
            playing = !playing;
            stopPlay.setText((playing) ? "Stop" : "Play");
        });

        clearButton.addActionListener(e -> {
            for (JToggleButton[] jToggleButtons: button) {
                for (JToggleButton jToggleButton: jToggleButtons) {
                    jToggleButton.setSelected(false);
                }
            }

            reset();
        });



        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timing = new Thread(this);
        timing.start();
    }

    void addMusic(String initInstrument){
        try {
            for(int x=0;x<clipNames.length;x++)
            {
                URL url = this.getClass().getClassLoader().getResource(initInstrument+"\\"+initInstrument+" - "+clipNames[x]+".wav");
                assert url != null;
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                clip[x] = AudioSystem.getClip();
                clip[x].open(audioIn);
            }

        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void reset(){
        playing = false;
        stopPlay.setText("Play");
        col = 0;
    }

    public void run()
    {
        do
        {
            try
            {
                if(!playing)
                {
                    timing.sleep(0); // 7
                }
                else
                {
                    for(int r = 0; r < button.length;r++)
                    {
                        if(button[r][col].isSelected())
                        {
                            clip[r].start();
                            button[r][col].setForeground(Color.YELLOW);
                        }
                    }

                    timing.sleep(tempo);

                    for(int r = 0; r < button.length; r++)
                    {
                        if(button[r][col].isSelected())
                        {
                            clip[r].stop();
                            clip[r].setFramePosition(0);
                            button[r][col].setForeground(Color.BLACK);
                        }
                    }
                    col++;
                    if(col == button[0].length)
                        col = 0;
                }
            }
            catch(InterruptedException e) {
            }
        }while(notStopped);
    }

    public void actionPerformed(ActionEvent e)
    {
    }
    public void adjustmentValueChanged(AdjustmentEvent e)
    {
    }
    public static void main(String[] args)
    {
        MusicBox_Day1 app=new MusicBox_Day1();
    }

}
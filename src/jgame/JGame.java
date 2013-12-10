package jgame;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import jgame.input.EventHandler;
import kuusisto.tinysound.TinySound;

@SuppressWarnings("serial")
public abstract class JGame extends JPanel {
	private boolean running = false;
	private boolean paused = false;
	private double startTime = 0;
	private double dTime = 0;
	protected double fps = 0;
	private int frames = 0;
	protected static JFrame frame;
	private static EventHandler input;
	
	public JGame() {
		this("");
	}
	
	public JGame(String title) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		input = new EventHandler();
		addMouseListener(input);
		addMouseMotionListener(input);
		addKeyListener(input);
		TinySound.init();
	}
	
	public void calcFPS() {
		frames++;
		if(startTime == 0) {
			startTime = System.currentTimeMillis();
		} else {
			double time = System.currentTimeMillis();
			dTime = time - startTime;
			if(dTime > 1000) {
				fps = (frames * 1000) / dTime;
				frames = 0;
				startTime = time;
			}
		}
	}
	
	public void gameLoop(double delta) {
		running = true;
		double nextTime = (double) System.nanoTime() / 1000000000.0;
		double maxTimeDiff = 0.5;
		int skippedFrames = 1;
		int maxSkippedFrames = 5;
		while(running) {
			double now = (double) System.nanoTime() / 1000000000.0;
			if(!paused) {
				if((now - nextTime) > maxTimeDiff)
					nextTime = now;
				if(now >= nextTime) {
					nextTime += delta;
					update((float) (nextTime - now));
					if((now < nextTime) || (skippedFrames > maxSkippedFrames)) {
						repaint();
						skippedFrames = 1;
					} else {
						skippedFrames++;
					}
				} else {
					int sleepTime = (int) (1000.0 * (nextTime - now));
					if(sleepTime > 0) {
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			calcFPS();
		}
	}
	
	public void pause() {
		paused = true;
	}
	
	public void unPause() {
		paused = false;
	}
	
	public void stop() {
		running = false;
		TinySound.shutdown();
		System.exit(1);
	}
	
	public void init(final double deltaTime) {
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		new Thread() {
			public void run() {
				gameLoop(deltaTime);
			}
		}.start();
	}
	
	public void setResolution(Dimension dim) {
		setPreferredSize(dim);
		frame.setSize(dim);
	}
	
	public void setResolution(int x, int y) {
		setResolution(new Dimension(x, y));
	}
	
	public abstract void paintComponent(Graphics g);
	
	public abstract void update(float dt);
}
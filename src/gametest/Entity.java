package gametest;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import se.egy.graphics.Drawable;

public class Entity implements Drawable {
    private Image image;
    private int width, height;
    private double x, y;

    public Entity(int x, int y, String pathToImage, int height, int width ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // Load the image from the provided path
        image = new ImageIcon(getClass().getResource(pathToImage)).getImage();
    }
    public Entity(int x, int y, String pathToImage ) {
    	
        image = new ImageIcon(getClass().getResource(pathToImage)).getImage();
        this.x = x;
        this.y = y;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);

        // Load the image from the provided path
    }
    public double getX() {
    	return x;
    }
    public double getY() {
    	return y;
    }
    public int getHeight() {
    	return height;
    }
    public int getWidth() {
    	return width;
    }
    
    public void setX(double newX) {
    	x = newX;
    }
    public void setY(double newY) {
    	y = newY;
    }
    public void setHeight(int newHeight) {
    	height = newHeight;
    }
    public void setWidth(int newWidth) {
    	width = newWidth;
    }

    public void moveX(double distance) {
    	this.x += distance;
    }
    public void moveY(double distance) {
    	this.y += distance;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int intX = (int) Math.round(x);
        int intY = (int) Math.round(y);
        g2d.drawImage(image, intX, intY, width, height, null);
    }
}

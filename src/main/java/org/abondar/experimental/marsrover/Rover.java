package org.abondar.experimental.marsrover;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Rover {


    private Character direction;


    private Point location;


    private Map<Character, Map<Character, Character>> directionChanges;

    private Map<Character, Point> locationChanges;

    public Rover(Integer maxX, Integer maxY, Integer x, Integer y, Character direction) {
        this.direction = direction;

        location = new Point(x, y);
        location.setMaxVals(maxX, maxY);

        fillDirections();
        fillLocations();
    }

    private void fillDirections() {
        directionChanges = new HashMap<>();
        Map<Character, Character> possibleChanges = new HashMap<>();

        possibleChanges.put('L', 'W');
        possibleChanges.put('R', 'E');
        directionChanges.put('N', possibleChanges);

        possibleChanges = new HashMap<>();
        possibleChanges.put('L', 'E');
        possibleChanges.put('R', 'W');
        directionChanges.put('S', possibleChanges);


        possibleChanges = new HashMap<>();
        possibleChanges.put('L', 'S');
        possibleChanges.put('R', 'N');
        directionChanges.put('W', possibleChanges);

        possibleChanges = new HashMap<>();
        possibleChanges.put('L', 'N');
        possibleChanges.put('R', 'S');
        directionChanges.put('E', possibleChanges);

    }

    private void fillLocations() {
        locationChanges = new HashMap<>();
        locationChanges.put('N', new Point(0, 1));
        locationChanges.put('S', new Point(0, -1));
        locationChanges.put('W', new Point(-1, 0));
        locationChanges.put('E', new Point(1, 0));

    }

    public void move(Character command) {

        switch (command) {
            case 'R':
            case 'L':
                changeDirection(command);
                break;
            case 'M':
                changeLocation();
                break;

        }

    }

    private void changeLocation() {
        location.add(locationChanges.get(direction));
    }

    private void changeDirection(Character commad) {
        direction = directionChanges.get(direction).get(commad);
    }


    public Character getDirection() {
        return direction;
    }

    public Point getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return  String.format("%d %d %s",location.getX(),location.getY(),direction);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rover rover = (Rover) o;

        if (!direction.equals(rover.direction)) return false;
        return location.equals(rover.location);
    }

    @Override
    public int hashCode() {
        int result = direction.hashCode();
        result = 31 * result + location.hashCode();
        return result;
    }

    public static void main(String[] args) throws IOException {
        if (args.length==0){
            throw new IOException("No input file");
        }

        Map<Rover,String> roverCommands= readInput(args[0]);

        roverCommands.forEach((rover,cmd)->{
            for (Character c: cmd.toCharArray()){
                rover.move(c);
            }
            System.out.println(rover);
        });
    }


    public static Map<Rover,String> readInput(String fileName) throws IOException {
        Map<Rover,String> roverCommands = new HashMap<>();

        InputStream is;
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException ex){
            is = Rover.class.getClass().getResourceAsStream(fileName);
            if (is==null){
                throw  new IOException("File not found");
            }
        }


        Scanner scanner = new Scanner(is);

        Pattern platoPattern = Pattern.compile("\\d+ \\d+");
        Pattern roverPattern = Pattern.compile("\\d+ \\d+ [N|S|W|E]");
        Pattern cmdPattern = Pattern.compile("[L|R|M]+");


        String line = scanner.nextLine();
        if (!line.matches(platoPattern.pattern())){
          throw  new InvalidParameterException("Wrong first line");
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(line.getBytes());

        Scanner isScan = new Scanner(bis);
        Integer maxX = isScan.nextInt();
        Integer maxY = isScan.nextInt();

            while (scanner.hasNextLine()){
                line = scanner.nextLine();

                if (!line.matches(roverPattern.pattern())){
                    throw  new InvalidParameterException("Invalid rover string");
                }

                bis = new ByteArrayInputStream(line.getBytes());
                isScan = new Scanner(bis);
                Integer x = isScan.nextInt();
                Integer y = isScan.nextInt();
                Character dir = isScan.next().charAt(0);

                line = scanner.nextLine();
                if (!line.matches(cmdPattern.pattern())){
                    throw  new InvalidParameterException("Invalid commands string");
                }

                bis = new ByteArrayInputStream(line.getBytes());
                isScan = new Scanner(bis);
                String cmd = isScan.next();

                Rover rover = new Rover(maxX,maxY,x,y,dir);
                roverCommands.put(rover,cmd);
            }


        scanner.close();

        return roverCommands;
    }
}

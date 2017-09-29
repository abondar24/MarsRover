package org.abondar.experimental.marsrover;

import org.junit.Test;

import java.io.IOException;
import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class RoverTest {


    @Test
    public void testMoveChangeDirection() throws Exception {
        Rover rover = new Rover(2,2,1,1,'N');

        rover.move('R');

        Rover expectedRover = new Rover(2,2,1,1,'E');
        assertEquals(expectedRover,rover);
    }

    @Test(expected=IOException.class)
    public void testReadInputFileNotFound() throws IOException{
        Rover.readInput("/bla_bla.txt");
    }


    @Test(expected=InvalidParameterException.class)
    public void testReadInputWrongLine1() throws IOException{
          Rover.readInput("/wrong_input_line1.txt");
    }


    @Test(expected=InvalidParameterException.class)
    public void testReadInputWrongRoverLineMissingSpace() throws IOException{
        Rover.readInput("/wrong_rover_line_missing_space.txt");
    }

    @Test(expected=InvalidParameterException.class)
    public void testReadInputWrongRoverLineWrongDirection() throws IOException{
        Rover.readInput("/wrong_rover_line_wrong_direction.txt");
    }


    @Test(expected=InvalidParameterException.class)
    public void testReadInputWrongCommandsLineWrongChars() throws IOException{
        Rover.readInput("/wrong_commands_line_has_wrong_chars.txt");
    }

    @Test(expected=InvalidParameterException.class)
    public void testReadInputWrongLineOrder() throws IOException{
        Rover.readInput("/wrong_line_order.txt");
    }

}
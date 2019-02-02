/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


public class RobotMap {

    public static class DriveMap {
		// TALON/VICTOR ID'S (2018 bot, testbed)
		// all front (masters) are talons, all slaves (rear/middle) are victors
		public static final int ID_LEFT_FRONT = 8; // 2, 8
		public static final int ID_LEFT_MIDDLE = 4;
		public static final int ID_LEFT_REAR = 3;

		public static final int ID_RIGHT_FRONT = 7; // 14, 7
		public static final int ID_RIGHT_MIDDLE = 1;
		public static final int ID_RIGHT_REAR = 2;

		// OLD TALON SRX FOLLOWER ID'S
		// public static final int ID_LEFT_MIDDLE = 4; // 4, 11
		// public static final int ID_LEFT_REAR = 10; // 10, 5

		// public static final int ID_RIGHT_MIDDLE = 3; // 3, 12
		// public static final int ID_RIGHT_REAR = 9; // 9, 6

	}
	
	public static class JoystickMap {
		public static final int USB_LEFT = 0;
		public static final int USB_RIGHT = 1;
		public static final int USB_CONTROLLER =2;
		
	}

	public static class PogoMap {
		public static final int ID_EXTEND_MOTOR = 6;
		public static final int ID_ROLL_MOTOR = 12;

		public static final double ROLL_SPEED = .5;

		public static final double DISTANCE_PER_PULSE = 1.0;
	}

	public static class ElevatorMap {
		public static final int DIO_LEFT_LIMIT = 0;
		public static final int DIO_RIGHT_LIMIT = 1;
	}

	public static class CargoMap {
		public static final int ID_CARGO_MOTOR = 5;

		public static final int SOL_FWD = 0;
		public static final int SOL_REV = 1;

		public static final double SPEED_INTAKE = .5;
		public static final double SPEED_EJECT = -.5;
	}
  
}

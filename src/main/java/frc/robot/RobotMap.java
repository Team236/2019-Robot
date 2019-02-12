/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import lib.pid.PIDParameters;

public class RobotMap {
	// TODO: clarify which id's are talon vs. victor

	public static final int PWM_SERVO_CAM_1 = 0;
	public static final int PWM_SERVO_CAM_2 = 1;

	public static final int ANALOG_PRESSURE_SENSOR = 0;

	public static final int RELAY_FLASHLIGHT = 0;

	public static class DriveMap {
		// TALON/VICTOR ID'S (2019 bot, testbed)
		// all front (masters) are talons, all slaves (rear/middle) are victors
		public static final int ID_LEFT_FRONT = 877; // 20, 8
		public static final int ID_LEFT_MIDDLE = 4; // 9, 4
		public static final int ID_LEFT_REAR = 3; // 10, 3

		public static final int ID_RIGHT_FRONT = 7; // 21, 7
		public static final int ID_RIGHT_MIDDLE = 177; // 11, 1
		public static final int ID_RIGHT_REAR = 2; //  12, 2

		public static final double DIAMETER = 3.96; // wheel diameter in inches
		public static final double CIRCUMFERENCE = DIAMETER * Math.PI;
		public static final int PULSE_PER_ROTATION = 512;
		public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / PULSE_PER_ROTATION;
	}

	public static class AutoMap {
		public static final int MARGIN_GYRO_DRIVE = 3;
	}

	public static class JoystickMap {
		public static final int USB_LEFT = 0;
		public static final int USB_RIGHT = 1;
		public static final int USB_CONTROLLER = 2;
	}

	public static class PogoMap {
		public static final int ID_LEFT_EXTEND_MOTOR = 22; // 22
		public static final int ID_RIGHT_EXTEND_MOTOR = 23; // 23
		public static final int ID_LEFT_ROLL_MOTOR = 5; // 5
		public static final int ID_RIGHT_ROLL_MOTOR = 6; // 6

		public static final int DIO_LEFT_TOP_LIMIT = 3;
		public static final int DIO_RIGHT_TOP_LIMIT = 5;
		public static final int DIO_LEFT_BOTTOM_LIMIT = 4;
		public static final int DIO_RIGHT_BOTTOM_LIMIT = 6;

		public static final int DIO_SENSOR = 7;

		public static final double EXTEND_SPEED = .5;
		public static final double RETRACT_SPEED = -.5;
		public static final double ROLL_SPEED = .5;

		public static final double DISTANCE_PER_PULSE = 1.0;
	}

	public static class ElevatorMap {
		// talon/victor ID's, master = talon, slave = victor
		public static final int ID_LEFT_MASTER = 8; // 10, 6, were using 8 for testing on testbed w/ drive wheels
		public static final int ID_RIGHT_SLAVE = 1; // 7, 12, 1 for testing

		public static final int DIO_TOP_LIMIT = 0;
		public static final int DIO_BOTTOM_LIMIT = 1;

		public static final double DIAMETER = 1.6; // inches
		public static final double CIRCUMFERENCE = DIAMETER * Math.PI;
		public static final int PULSE_PER_ROTATION = 512;
		public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / PULSE_PER_ROTATION;

		// public static final PIDParameters UP_PARAMS = new PIDParameters(0.1, 0, 0, 1 / 100.0);
		public static final PIDParameters UP_PARAMS = new PIDParameters(0.5, 0, 0, 1 / 100.0);
		public static final PIDParameters DOWN_PARAMS = new PIDParameters(0.5, 0, 0, 1 / 100.0);
		public static final double HEIGHT_MARGIN = 10;
	}

	public static class CargoMap {
		public static final int ID_CARGO_MOTOR = 577; // 13(talon)

		public static final int DIO_CARGO_LIMIT = 2;

		public static final int SOL_FWD = 0;
		public static final int SOL_REV = 1;

		public static final double SPEED_INTAKE = 1.0;
		public static final double SPEED_EJECT = -.5;
	}

	public static class HatchMap {
		public static final int SOL_FWD = 2;
		public static final int SOL_REV = 3;
		public static final int SCORE_SOL_FWD = 4;
		public static final int SCORE_SOL_REV = 5;
	}

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import lib.pid.PIDParameters;

public class RobotMap {
	public static final int PWM_SERVO_CAM_1 = 0;
	public static final int PWM_SERVO_CAM_2 = 1;

	public static final int ANALOG_PRESSURE_SENSOR = 0;

	public static final int RELAY_FLASHLIGHT = 0;

	public static class DriveMap {
		// TALON/VICTOR ID'S (2019 bot, testbed)
		public static final int ID_T_LEFT_FRONT = 20; // 20, 8
		public static final int ID_V_LEFT_MIDDLE = 9; // 9, 4
		public static final int ID_V_LEFT_REAR = 10; // 10, 3

		public static final int ID_T_RIGHT_FRONT = 21; // 21, 7
		public static final int ID_V_RIGHT_MIDDLE = 11; // 11, 1
		public static final int ID_V_RIGHT_REAR = 12; // 12, 2

		public static final double DIAMETER = 3.96; // wheel diameter in inches
		public static final double CIRCUMFERENCE = DIAMETER * Math.PI;
		public static final int PULSE_PER_ROTATION = 512;
		public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / PULSE_PER_ROTATION;
	}

	public static class AutoMap {
		public static final int MARGIN_GYRO_DRIVE = 3;
		public static final double GYRO_DRIVE_KP = .01;
	}

	public static class JoystickMap {
		public static final int USB_LEFT = 0;
		public static final int USB_RIGHT = 1;
		public static final int USB_CONTROLLER = 2;
	}

	public static class PogoMap {
		public static final int ID_T_LEFT_EXTEND_MOTOR = 22; // 22, 6
		public static final int ID_T_RIGHT_EXTEND_MOTOR = 23; // 23, 12
		public static final int ID_V_LEFT_ROLL_MOTOR = 5; // 5, 5
		public static final int ID_V_RIGHT_ROLL_MOTOR = 6; // 6

		public static final int DIO_LEFT_TOP_LIMIT = 3;
		public static final int DIO_RIGHT_TOP_LIMIT = 5;
		public static final int DIO_LEFT_BOTTOM_LIMIT = 4;
		public static final int DIO_RIGHT_BOTTOM_LIMIT = 6;

		public static final int DIO_SENSOR = 7;

		public static final double LEFT_EXTEND_SPEED = .6;
		public static final double RIGHT_EXTEND_SPEED = .585;
		public static final double LEFT_RETRACT_SPEED = -.6;
		public static final double RIGHT_RETRACT_SPEED = -.6;
		public static final double ROLL_SPEED = .5;

		public static final double DISTANCE_PER_PULSE = 1.0;
		public static final double MAX_COUNT = 2278; // 1500?
		public static final double MIN_COUNT = 100;

		public static final double KP = 0;
		public static final double SPEED = .3;
	}

	public static class ElevatorMap {
		// talon/victor ID's
		public static final int ID_T_LEFT_MASTER = 11; // 10, 6, were using 8 for testing on testbed w/ drive wheels
		public static final int ID_V_RIGHT_SLAVE = 7; // 7, 12, 1 for testing

		public static final int DIO_TOP_LIMIT = 0;
		public static final int DIO_BOTTOM_LIMIT = 1;

		public static final double DIAMETER = 1.6; // 1.6 inches (3.96 for wheel testing)
		public static final double CIRCUMFERENCE = DIAMETER * Math.PI;
		public static final int PULSE_PER_ROTATION = 512;
		public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / PULSE_PER_ROTATION;

		public static final PIDParameters UP_PARAMS = new PIDParameters(0.06, 0, 0, 1 / 100.0);
		public static final PIDParameters DOWN_PARAMS = new PIDParameters(0.01, 0, 0, 1 / 100.0); // for climbing
		public static final double HEIGHT_MARGIN = 1;

		public static final double CARGO_OFFSET = 6.75; // amount from elevator position 0 to Cargo lvl1 (inches)
		public static final double TOP_HEIGHT = 65;

		public static final double SPEED_LIMIT_1 = 24; // (inches) lower
		public static final double SPEED_LIMIT_2 = 40;
	}

	public static class CargoMap {
		public static final int ID_T_CARGO_MOTOR = 13; // 13, 5

		public static final int DIO_CARGO_LIMIT = 2;

		public static final int SOL_FWD = 0;
		public static final int SOL_REV = 1;

		public static final double SPEED_INTAKE = 1.0;
		public static final double SPEED_EJECT = -1;
	}

	public static class HatchMap {
		public static final int SOL_FWD = 2;
		public static final int SOL_REV = 3;
		public static final int SCORE_SOL_FWD = 4;
		public static final int SCORE_SOL_REV = 5;
	}

}

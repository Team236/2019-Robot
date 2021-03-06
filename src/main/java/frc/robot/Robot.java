/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.auto.GyroDrive;
import frc.robot.commands.elevator.ElevatorToHeight;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Pogo;
import lib.pid.PIDParameters;

public class Robot extends TimedRobot {
  // TODO: organize SmartDashboard before competition
  public static OI oi;
  public static Cargo cargo = new Cargo();
  public static Drive drive = new Drive();
  public static Elevator elevator = new Elevator();
  public static Hatch hatch = new Hatch();
  public static Pogo pogo = new Pogo();

  private Compressor compressor;
  public PowerDistributionPanel pdp;

  public UsbCamera camera0;
  public UsbCamera camera1;

  public static Servo camServo1, camServo2;

  public AnalogInput pressureSensor;

  public static double elevatorP;
  public static double elevatorI;
  public static double elevatorD;
  public static double gyroP;

  private static boolean isDebug = true;

  @Override
  public void robotInit() {
    oi = new OI();

    compressor = new Compressor();
    compressor.start();

    pdp = new PowerDistributionPanel();

    elevator.resetAtBottom();
    pogo.resetEncodersAtTop();;

    camServo1 = new Servo(RobotMap.PWM_SERVO_CAM_1);
    camServo2 = new Servo(RobotMap.PWM_SERVO_CAM_2);

    pressureSensor = new AnalogInput(RobotMap.ANALOG_PRESSURE_SENSOR);

    // USED FOR TUNING CONSTANTS
    // SmartDashboard.putNumber("P", 0.0);
    // SmartDashboard.putNumber("I", 0.0);
    // SmartDashboard.putNumber("D", 0.0);
    // SmartDashboard.putNumber("Gyro P", 0.0);

    try {
      camera0 = CameraServer.getInstance().startAutomaticCapture(0);
      camera1 = CameraServer.getInstance().startAutomaticCapture(1);
    } catch (Exception e) {
      System.out.println("camera capture failed");
      System.out.println(e.getStackTrace());

      SmartDashboard.putString("camera capture failed", "failed");
    }

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    // USED FOR TUNING
    // elevatorP = (double) SmartDashboard.getNumber("Elevator P", 0.0);
    // elevatorI = (double) SmartDashboard.getNumber("Elevator I", 0.0);
    // elevatorD = (double) SmartDashboard.getNumber("Elevator D", 0.0);
    // gyroP = (double) SmartDashboard.getNumber("Gyro P", 0.0);
    if (isDebug) {
      SmartDashboard.putBoolean("left at top", pogo.leftAtTop());
      SmartDashboard.putBoolean("right at top", pogo.rightAtTop());
      SmartDashboard.putBoolean("left at bottom", pogo.leftAtBottom());
      SmartDashboard.putBoolean("right at bottom", pogo.rightAtBottom());

      SmartDashboard.putBoolean("cargo extended", cargo.isExtended());
      
      SmartDashboard.putBoolean("at top", elevator.atTop());
      SmartDashboard.putBoolean("at bottom", elevator.atBottom());  
    }

    SmartDashboard.putNumber("left pogo encoder", pogo.getLeftPogoEncoder());
    SmartDashboard.putNumber("right pogo encoder", pogo.getRightPogoEncoder());
    SmartDashboard.putNumber("height", elevator.getHeight());
    SmartDashboard.putBoolean("cargo limit", cargo.haveCargo());

    SmartDashboard.putNumber("angle", drive.navx.getAngle());
    SmartDashboard.putNumber("left dist", drive.getLeftDist());
    SmartDashboard.putNumber("right dist", drive.getRightDist());

    double current = pdp.getCurrent(1);
    SmartDashboard.putNumber("current 1", current);

  }

  @Override
  public void autonomousInit() {
    // USE THIS TO TUNE FROM DASHBOARD
    // new ElevatorToHeight(28, 3, new PIDParameters(elevatorP, 0, 0, 1 / 100.0), RobotMap.ElevatorMap.DOWN_PARAMS).start();
    // new ElevatorToHeight(28, 3, RobotMap.ElevatorMap.UP_PARAMS, new PIDParameters(elevatorP, elevatorI, elevatorD, 1 / 100.0)).start();
    // new GyroDrive(gyroP, 120, .5).start();
    // pogo.resetEncodersAtTop();
    // elevator.resetAtBottom();
    // drive.resetEncoders();
    teleopInit();
    // hatch.Extend();
    
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    teleopPeriodic();
  }

  @Override
  public void teleopInit() {
    pogo.resetEncodersAtTop();
    elevator.resetAtBottom();
    drive.resetEncoders();
    // hatch.Retract();

    // double current = pdp.getCurrent(1);
    // SmartDashboard.putNumber("current 1", current);

  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    if (isDebug) {
      SmartDashboard.putBoolean("left at top", pogo.leftAtTop());
      SmartDashboard.putBoolean("right at top", pogo.rightAtTop());
      SmartDashboard.putBoolean("left at bottom", pogo.leftAtBottom());
      SmartDashboard.putBoolean("right at bottom", pogo.rightAtBottom());

      SmartDashboard.putBoolean("cargo extended", cargo.isExtended());
      
      SmartDashboard.putBoolean("at top", elevator.atTop());
      SmartDashboard.putBoolean("at bottom", elevator.atBottom());  
    }

    SmartDashboard.putNumber("left pogo encoder", pogo.getLeftPogoEncoder());
    SmartDashboard.putNumber("right pogo encoder", pogo.getRightPogoEncoder());
    SmartDashboard.putNumber("height", elevator.getHeight());
    SmartDashboard.putBoolean("cargo limit", cargo.haveCargo());

    SmartDashboard.putNumber("angle", drive.navx.getAngle());
    SmartDashboard.putNumber("left dist", drive.getLeftDist());
    SmartDashboard.putNumber("right dist", drive.getRightDist());

    // SmartDashboard.putNumber("match time", DriverStation.getInstance().getMatchTime());

    // SmartDashboard.putNumber("pressure", pressureSensor.getAverageVoltage() * (110.0 / 2.75));
  }

  @Override
  public void testPeriodic() {
    teleopPeriodic();
  }
}

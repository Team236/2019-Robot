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
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Cargo;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Pogo;

public class Robot extends TimedRobot {
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
  public MjpegServer server0;
  public MjpegServer server1;

  public static Servo camServo1, camServo2;

  public AnalogInput pressureSensor;

  Preferences prefs;

  public static double elevatorP;

  @Override
  public void robotInit() {
    oi = new OI();

    compressor = new Compressor();
    compressor.start();

    elevator.resetEncoder();
    pogo.resetEncodersAtTop();;

    camServo1 = new Servo(RobotMap.PWM_SERVO_CAM_1);
    camServo2 = new Servo(RobotMap.PWM_SERVO_CAM_2);

    pressureSensor = new AnalogInput(RobotMap.ANALOG_PRESSURE_SENSOR);

    prefs = Preferences.getInstance();
    elevatorP = prefs.getDouble("elevator P", 0.0);

    try {
      CameraServer inst0 = CameraServer.getInstance();
      UsbCamera camera0 = new UsbCamera("usb camera 0", 0);
      inst0.addCamera(camera0);
      server0 = inst0.addServer("serve_USB Camera 0");
      server0.setSource(camera0);
      server0.getProperty("compression").set(50);
      server0.getProperty("default_compression").set(50);
      server0.getProperty("width").set(320);
      server0.getProperty("height").set(240);
      camera0.setResolution(320, 240);

      CameraServer inst1 = CameraServer.getInstance();
      UsbCamera camera1 = new UsbCamera("usb camera 1", 1);
      inst1.addCamera(camera1);
      server1 = inst1.addServer("serve_USB Camera 1");
      server1.setSource(camera1);
      server1.getProperty("compression").set(50);
      server1.getProperty("default_compression").set(50);
      server1.getProperty("width").set(320);
      server1.getProperty("height").set(240);
      camera1.setResolution(320, 240);

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
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    pogo.resetEncodersAtTop();
    // elevator.resetAtBottom();
    elevator.resetEncoder();
    // elevatorP = SmartDashboard.getNumber("elevator P", 0.0);

  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    // SmartDashboard.putNumber("left pogo encoder", pogo.getLeftPogoDistance());
    // SmartDashboard.putNumber("right pogo encoder", pogo.getRightPogoDistance());
    // SmartDashboard.putBoolean("top pogo limit", pogo.atTop());
    // SmartDashboard.putBoolean("bottom pogo limit", pogo.atBottom());
    // SmartDashboard.putBoolean("pogo sensor", pogo.rollSensor.get());
    // SmartDashboard.putBoolean("left elevator limit", elevator.isLeftLimit());
    // SmartDashboard.putBoolean("right elevator limit", elevator.isRightLimit());
    SmartDashboard.putBoolean("elevator at top", elevator.atTop());
    SmartDashboard.putBoolean("elevator at bottom", elevator.atBottom());
    SmartDashboard.putNumber("elevator encoder", elevator.getEncoder());
    SmartDashboard.putNumber("elevator height", elevator.getHeight());
    // SmartDashboard.putBoolean("cargo limit", cargo.isLimit());

    SmartDashboard.putNumber("gyro angle", drive.navx.getAngle());
    SmartDashboard.putNumber("left dist", drive.getLeftDist());
    SmartDashboard.putNumber("right dist", drive.getRightDist());

    SmartDashboard.putNumber("air pressure", pressureSensor.getAverageVoltage() * (110.0 / 2.75));

    /* SmartDashboard.putNumber("pdp lf", pdp.getCurrent(3));
    SmartDashboard.putNumber("pdp lm", pdp.getCurrent(1));
    SmartDashboard.putNumber("pdp lr", pdp.getCurrent(0));

    SmartDashboard.putNumber("pdp rf", pdp.getCurrent(13));
    SmartDashboard.putNumber("pdp rm", pdp.getCurrent(12));
    SmartDashboard.putNumber("pdp rr", pdp.getCurrent(2)); */
  }

  @Override
  public void testPeriodic() {
  }
}

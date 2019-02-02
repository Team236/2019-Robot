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
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drive;


public class Robot extends TimedRobot {
  public static OI oi;
  public static Drive drive = new Drive();

  public UsbCamera camera0;
  public UsbCamera camera1;
  public MjpegServer server0;
  public MjpegServer server1;
  
  @Override
  public void robotInit() {
    oi = new OI();

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
    
  }

  
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  
  @Override
  public void testPeriodic() {
  }
}

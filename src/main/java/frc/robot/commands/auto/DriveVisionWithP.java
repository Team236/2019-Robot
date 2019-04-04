/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveVisionWithP extends Command {
  private double kP;
  private double speed;

  public DriveVisionWithP(double _kP, double _speed) {
    this.kP = _kP;
    this.speed = _speed;

    requires(Robot.drive);
   // if more than one method will use LimeLight requires should be defined
   // requires(Robot.theLimeLight);
  }

  @Override
  protected void initialize() {
    Robot.drive.resetEncoders();
    // System.out.println("gyro drive init");
  }

  @Override
  protected void execute() {
    
    double leftSpeed = speed;
    double rightSpeed = speed;
    
    double ang = Robot.theLimeLight.getLimeLight().getdegRotationToTarget();
    
  //TODO need to determine if 0.3 is a valid area by positioning robot 3-4 feet from target
    double area = Robot.theLimeLight.getLimeLight().getTargetArea();
    
    if (area > .3) {  
      leftSpeed += ang * kP * .5;   // 50% reduced speed when target is close
      rightSpeed -= ang * kP * .5;  // 50% reduced speed when target is close
    } else {
      leftSpeed += ang * kP;
      rightSpeed -= ang * kP;
    }

    Robot.drive.setLeftSpeed(leftSpeed);
    Robot.drive.setRightSpeed(rightSpeed);
    // System.out.println("driveVisionWithP");
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.drive.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}

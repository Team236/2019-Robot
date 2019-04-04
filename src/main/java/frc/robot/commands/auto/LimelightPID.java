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

public class LimelightPID extends Command {
  private double kP;
  private double dist;
  private double speed;


  public LimelightPID(double _kP, double _speed) {
    this.kP = _kP;
    //this.speed = _speed;
    requires(Robot.theLimeLight);
    requires(Robot.drive);
  }

  @Override
  protected void initialize() {
    Robot.drive.navx.reset();
    Robot.drive.resetEncoders();
    // System.out.println("gyro drive init");
  }

  @Override
  protected void execute() {
    double leftSpeed = speed;
    double rightSpeed = speed;

    double ang = Robot.theLimeLight.getLimeLight().getdegRotationToTarget();

    leftSpeed += ang * kP;
    rightSpeed -= ang * kP;
    if(Robot.theLimeLight.getTargetArea() >= RobotMap.AutoMap.SLOW_START_SPEED)
    {
      Robot.drive.setLeftSpeed(leftSpeed * .3);
      Robot.drive.setRightSpeed(rightSpeed * .3);
    }
    else 
    {
      Robot.drive.setLeftSpeed(leftSpeed);
      Robot.drive.setRightSpeed(rightSpeed);
    }
    
    // System.out.println("gyro drive exec");
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

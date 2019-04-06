/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LimelightDrive extends Command {
  private double kP;
  private double speed;

  public LimelightDrive(double _kP, double _speed) {
    this.kP = _kP;
    this.speed = _speed;
    requires(Robot.drive);
    // requires(Robot.limelight);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    double leftSpeed = speed;
    double rightSpeed = speed;

    double ang = Robot.limelight.getLimeLight().getdegRotationToTarget();

    leftSpeed += ang * kP;
    rightSpeed -= ang * kP;

    Robot.drive.setLeftSpeed(leftSpeed);
    Robot.drive.setRightSpeed(rightSpeed);
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
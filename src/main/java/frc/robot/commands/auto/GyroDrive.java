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

public class GyroDrive extends Command {
  private double kP;
  private double dist;
  private double speed;

  public GyroDrive(double _kP, double _dist, double _speed) {
    this.kP = _kP;
    this.dist = _dist;
    this.speed = _speed;

    requires(Robot.drive);
  }

  @Override
  protected void initialize() {
    Robot.drive.navx.reset();
    Robot.drive.resetEncoders();
  }

  @Override
  protected void execute() {
    double leftSpeed = speed;
    double rightSpeed = speed;

    double ang = Robot.drive.navx.getAngle();

    leftSpeed -= ang * kP;
    rightSpeed += ang * kP;

    Robot.drive.setLeftSpeed(leftSpeed);
    Robot.drive.setRightSpeed(rightSpeed);
  }

  @Override
  protected boolean isFinished() {
    boolean isLeftFinished = Math.abs(Robot.drive.getLeftDist() - dist) < RobotMap.AutoMap.MARGIN_GYRO_DRIVE;
    boolean isRightFinished = Math.abs(Robot.drive.getRightDist() - dist) < RobotMap.AutoMap.MARGIN_GYRO_DRIVE;

    return isLeftFinished && isRightFinished;
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

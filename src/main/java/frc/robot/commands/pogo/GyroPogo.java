/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pogo;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class GyroPogo extends Command {
  private double kP;
  private double speed;
  private double dist;
  
  public GyroPogo(double _kP, double _dist, double _speed) {
    this.kP = _kP;
    this.dist = _dist;
    this.speed = _speed;
    requires(Robot.pogo);
  }

  public GyroPogo(double _kP, double _speed) {
    this(_kP, RobotMap.PogoMap.MAX_COUNT, _speed);
  }

  @Override
  protected void initialize() {
    Robot.pogo.resetEncodersAtTop();
    Robot.drive.navx.reset();
  }

  @Override
  protected void execute() {
    double ang = Robot.drive.navx.getRoll(); // might have to change this axis
    double leftSpeed = speed;
    double rightSpeed = speed;

    leftSpeed -= ang * kP;
    rightSpeed += ang * kP;

    Robot.pogo.setLeftPogoSpeed(leftSpeed);
    Robot.pogo.setRightSpeed(rightSpeed);
  }

  @Override
  protected boolean isFinished() {
    boolean isLeftFinished = Math.abs(Robot.pogo.getLeftPogoDistance() - dist) < 10;
    boolean isRightFinished = Math.abs(Robot.pogo.getRightPogoDistance() - dist) < 10;
    return isLeftFinished && isRightFinished;
  }

  @Override
  protected void end() {
    Robot.pogo.stopPogo();
  }

  @Override
  protected void interrupted() {
    end();
  }
}

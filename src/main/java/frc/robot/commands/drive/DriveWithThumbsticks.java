/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveWithThumbsticks extends Command {
  public DriveWithThumbsticks() {
    requires(Robot.drive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.drive.setLeftSpeed(Math.pow(Robot.oi.controller.getLeftY(), 3));
    Robot.drive.setRightSpeed(Math.pow(Robot.oi.controller.getRightY(), 3));
    // Robot.drive.setLeftSpeed(Robot.oi.controller.getLeftY());
    // Robot.drive.setRightSpeed(Robot.oi.controller.getRightY());
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

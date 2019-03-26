/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class DriveWithJoysticks extends Command {
  public DriveWithJoysticks() {
    requires(Robot.drive);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.oi.rightStick.right.get()) {
      Robot.drive.setLeftSpeed(-Robot.oi.rightStick.getY());
      Robot.drive.setRightSpeed(-Robot.oi.leftStick.getY());
    } else if (Robot.oi.leftStick.left.get()) {
      Robot.drive.setLeftSpeed(Robot.oi.leftStick.getY() * .5);
      Robot.drive.setRightSpeed(Robot.oi.rightStick.getY() * .5);
      // Robot.drive.setLeftSpeed(Math.pow(Robot.oi.leftStick.getY(), 3));
      // Robot.drive.setRightSpeed(Math.pow(Robot.oi.rightStick.getY(), 3));
    } else {
      Robot.drive.setLeftSpeed(Robot.oi.leftStick.getY());
      Robot.drive.setRightSpeed(Robot.oi.rightStick.getY());
    }
   /* if (Robot.elevator.getHeight() >= RobotMap.ElevatorMap.SPEED_LIMIT_2) {
      if (Robot.oi.leftStick.left.get()) {
        Robot.drive.setLeftSpeed(Robot.oi.rightStick.getY() * .25);
        Robot.drive.setRightSpeed(Robot.oi.leftStick.getY() * .25);
      } else {
        Robot.drive.setLeftSpeed(-Robot.oi.leftStick.getY() * .25);
        Robot.drive.setRightSpeed(-Robot.oi.rightStick.getY() * .25);
      }
    } else if (Robot.elevator.getHeight() < RobotMap.ElevatorMap.SPEED_LIMIT_2
        && Robot.elevator.getHeight() > RobotMap.ElevatorMap.SPEED_LIMIT_1) {
      if (Robot.oi.leftStick.left.get()) {
        Robot.drive.setLeftSpeed(Robot.oi.rightStick.getY() * .4);
        Robot.drive.setRightSpeed(Robot.oi.leftStick.getY() * .4);
      } else {
        Robot.drive.setLeftSpeed(-Robot.oi.leftStick.getY() * .4);
        Robot.drive.setRightSpeed(-Robot.oi.rightStick.getY() * .4);
      }
    } else {
      if (Robot.oi.leftStick.left.get()) {
        Robot.drive.setLeftSpeed(Robot.oi.rightStick.getY());
        Robot.drive.setRightSpeed(Robot.oi.leftStick.getY());
      } else {
        Robot.drive.setLeftSpeed(-Robot.oi.leftStick.getY());
        Robot.drive.setRightSpeed(-Robot.oi.rightStick.getY());
      }
    }*/
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

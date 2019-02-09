/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorWithThumbstick extends Command {
  public ElevatorWithThumbstick() {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double speed = Robot.oi.controller.getLeftY();
    Robot.elevator.setSpeed(-speed);
    /* if (Robot.elevator.atTop() && speed > 0) {
      Robot.elevator.manualSetSpeed(0);
    } else if (Robot.elevator.atBottom() && speed < 0) {
      Robot.elevator.manualSetSpeed(0);
    } else {
      Robot.elevator.manualSetSpeed(speed);
    } */
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.elevator.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}

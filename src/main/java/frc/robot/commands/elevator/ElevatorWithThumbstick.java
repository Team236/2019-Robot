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
    // requires(Robot.elevator);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
        // TODO: if going up w clutch engaged and pogo limit hit stop (return true)

    double speed = -Robot.oi.controller.getLeftY();
    /*if (speed < 0) {
      speed = 0;
    } */

    if (speed > 0 && Robot.elevator.isClutch() && Robot.elevator.pogoRetracted()) {
      Robot.elevator.stop();
    } else {
      Robot.elevator.setSpeed(speed);
    }
  }

  @Override
  protected boolean isFinished() {
    if (-Robot.oi.controller.getLeftY() > 0 && Robot.elevator.isClutch() && Robot.elevator.pogoRetracted()) {
      return true;
    } else {
      return false;
    }
    
  }

  @Override
  protected void end() {
    Robot.elevator.stop();
    Robot.elevator.engageClutch();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
